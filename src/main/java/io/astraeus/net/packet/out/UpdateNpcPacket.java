package io.astraeus.net.packet.out;

import java.util.Iterator;
import java.util.Optional;

import io.astraeus.Configuration;
import io.astraeus.game.world.Position;
import io.astraeus.game.world.World;
import io.astraeus.game.world.entity.mob.npc.Npc;
import io.astraeus.game.world.entity.mob.npc.updating.NpcUpdateBlock;
import io.astraeus.game.world.entity.mob.npc.updating.mask.NpcAnimationUpdateBlock;
import io.astraeus.game.world.entity.mob.npc.updating.mask.NpcDoubleHitUpdateBlock;
import io.astraeus.game.world.entity.mob.npc.updating.mask.NpcFaceCoordinateUpdateBlock;
import io.astraeus.game.world.entity.mob.npc.updating.mask.NpcForceChatUpdateBlock;
import io.astraeus.game.world.entity.mob.npc.updating.mask.NpcGraphicsUpdateBlock;
import io.astraeus.game.world.entity.mob.npc.updating.mask.NpcInteractionUpdateBlock;
import io.astraeus.game.world.entity.mob.npc.updating.mask.NpcSingleHitUpdateBlock;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.update.UpdateFlag;
import io.astraeus.net.codec.AccessType;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.PacketHeader;
import io.astraeus.net.packet.Sendable;

/**
 * The {@link OutgoingPacket} that updates a {@link Npc} in the game world.
 * 
 * @author SeVen
 */
public final class UpdateNpcPacket implements Sendable {

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(65, PacketHeader.VARIABLE_SHORT);
    GamePacketBuilder update = new GamePacketBuilder();

    builder.initializeAccess(AccessType.BIT);

    builder.writeBits(8, player.getLocalNpcs().size());

    for (final Iterator<Npc> iterator = player.getLocalNpcs().iterator(); iterator.hasNext();) {

      final Npc mob = iterator.next();

      if (World.getNpcs().get(mob.getSlot()) != null && mob.isRegistered()
          && player.getPosition().isWithinDistance(mob.getPosition(), Position.VIEWING_DISTANCE)) {
        updateMovement(mob, builder);
        if (mob.isUpdateRequired()) {
          appendUpdates(mob, update);
        }
      } else {
        iterator.remove();
        builder.writeBit(true);
        builder.writeBits(2, 3);
      }
    }

    for (final Npc mob : World.getNpcs()) {

      if (player.getLocalNpcs().size() >= 255) {
        break;
      }

      if (mob == null || player.getLocalNpcs().contains(mob) || !mob.isVisible()
          || !mob.isRegistered()) {
        continue;
      }

      if (mob.getPosition().isWithinDistance(player.getPosition(), Position.VIEWING_DISTANCE)) {

        addNPC(player, mob, builder);

        if (mob.isUpdateRequired()) {
          appendUpdates(mob, update);
        }
      }
    }
    if (update.buffer().writerIndex() > 0) {
      builder.writeBits(14, 16383);
      builder.initializeAccess(AccessType.BYTE);
      builder.writeBuffer(update.buffer());
    } else {
      builder.initializeAccess(AccessType.BYTE);
    }
    return builder.toOutgoingPacket();
  }


  /**
   * Adds a npc to a players local npc list to be viewable by a player.
   * 
   * @param mob The mob to add.
   * 
   * @param builder The buffer to store data.
   */
  public static void addNPC(Player player, Npc mob, GamePacketBuilder builder) {
    final int slot = mob.getSlot();
    player.getLocalNpcs().add(mob);
    builder.writeBits(14, slot);
    builder.writeBits(5, mob.getPosition().getY() - player.getPosition().getY());
    builder.writeBits(5, mob.getPosition().getX() - player.getPosition().getX());
    builder.writeBits(1, mob.isUpdateRequired() ? 1 : 0);
    builder.writeBits(Configuration.NPC_BITS, mob.getId());
    builder.writeBit(true);
  }

  /**
   * Appends a single {@link Npc}s update block to the main update block.
   * 
   * @param block The block to append.
   * 
   * @param mob The mob to update.
   * 
   * @param builder The buffer to store data.
   */
  public static void append(NpcUpdateBlock block, Npc mob, GamePacketBuilder builder) {
    block.encode(mob, builder);
  }

  /**
   * Updates the state of a mob.
   * 
   * @param npc The mob to update the state for.
   * 
   * @param builder The buffer that the data will be written to.
   */
  public static void appendUpdates(Npc npc, GamePacketBuilder builder) {

    int updateMask = 0x0;

    if (npc.getUpdateFlags().contains(UpdateFlag.ANIMATION)) {
      updateMask |= 0x10;
    }

    if (npc.getUpdateFlags().contains(UpdateFlag.GRAPHICS)) {
      updateMask |= 0x80;
    }

    if (npc.getUpdateFlags().contains(UpdateFlag.ENTITY_INTERACTION)) {
      updateMask |= 0x20;
    }

    if (npc.getUpdateFlags().contains(UpdateFlag.FORCED_CHAT) && npc.getForcedChat().length() > 0) {
      updateMask |= 0x1;
    }

    if (npc.getUpdateFlags().contains(UpdateFlag.HIT)) {
      if (npc.getHitQueue().size() >= 1) {
        updateMask |= 0x40;
      }

      if (npc.getHitQueue().size() > 1) {
        updateMask |= 0x8;
      }
    }

    if (npc.getUpdateFlags().contains(UpdateFlag.TRANSFORM)) {
      updateMask |= 0x2;
    }

    if (npc.getUpdateFlags().contains(UpdateFlag.FACE_COORDINATE)) {
      updateMask |= 0x4;
    }

    builder.write(updateMask);

    if (npc.getUpdateFlags().contains(UpdateFlag.ANIMATION)) {
      append(new NpcAnimationUpdateBlock(), npc, builder);
    }

    if (npc.getUpdateFlags().contains(UpdateFlag.GRAPHICS)) {
      append(new NpcGraphicsUpdateBlock(), npc, builder);
    }

    if (npc.getUpdateFlags().contains(UpdateFlag.ENTITY_INTERACTION)) {
      append(new NpcInteractionUpdateBlock(), npc, builder);
    }

    if (npc.getUpdateFlags().contains(UpdateFlag.FORCED_CHAT) && npc.getForcedChat().length() > 0) {
      append(new NpcForceChatUpdateBlock(), npc, builder);
    }

    if (npc.getUpdateFlags().contains(UpdateFlag.HIT) && !npc.getHitQueue().isEmpty()) {
      if (npc.getHitQueue().peek() != null) {
        append(new NpcSingleHitUpdateBlock(npc.getHitQueue().poll()), npc, builder);
      }

      if (npc.getHitQueue().peek() != null) {
        append(new NpcDoubleHitUpdateBlock(npc.getHitQueue().poll()), npc, builder);
      }
    }

    npc.getHitQueue().clear();

    if (npc.getUpdateFlags().contains(UpdateFlag.FACE_COORDINATE)) {
      append(new NpcFaceCoordinateUpdateBlock(), npc, builder);
    }
  }

  /**
   * Handles the update of a {@link Npc}'s movement.
   * 
   * @param mob The mob to update.
   * 
   * @param builder The buffer to store data.
   */
  public static void updateMovement(Npc mob, GamePacketBuilder builder) {
    if (mob.getWalkingDirection() == -1) {
      if (mob.isUpdateRequired()) {
        builder.writeBit(true);
        builder.writeBits(2, 0);
      } else {
        builder.writeBits(1, 0);
      }
    } else {
      builder.writeBit(true);
      builder.writeBits(2, 1);
      builder.writeBits(3, mob.getWalkingDirection());
      builder.writeBits(1, mob.isUpdateRequired() ? 1 : 0);
    }
  }

}
