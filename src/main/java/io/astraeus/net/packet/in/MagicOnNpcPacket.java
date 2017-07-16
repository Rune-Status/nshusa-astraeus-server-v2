package io.astraeus.net.packet.in;

import io.astraeus.cache.NpcDefinition;
import io.astraeus.game.world.World;
import io.astraeus.game.world.entity.mob.npc.Npc;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;
import io.astraeus.net.packet.out.ServerMessagePacket;

@IncomingPacket.IncomingPacketOpcode({IncomingPacket.MAGIC_ON_NPC})
public final class MagicOnNpcPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    final int slot = packet.getReader().readShort(ByteOrder.LITTLE, ByteModification.ADDITION);
    final Npc mobMagic = World.getNpcs().get(slot);
    @SuppressWarnings("unused")
    final int spell = packet.getReader().readShort(ByteModification.ADDITION);

    if (mobMagic == null) {
      player.queuePacket(new ServerMessagePacket("You tried to attack a mob that doesn't exist."));
      return;
    }

    NpcDefinition def = NpcDefinition.lookup(mobMagic.getId());

    if (def == null) {
      return;
    }
  }

}
