package io.astraeus.net.packet.in;

import io.astraeus.cache.impl.def.NpcDefinition;
import io.astraeus.game.world.World;
import io.astraeus.game.world.entity.mob.npc.Npc;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.PlayerRights;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;
import io.astraeus.net.packet.out.ServerMessagePacket;

@IncomingPacket.IncomingPacketOpcode(IncomingPacket.ATTACK_NPC)
public final class AttackNpcPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    final int npcIndex = packet.getReader().readShort(false, ByteModification.ADDITION);

    if (npcIndex < 0 || npcIndex > NpcDefinition.getCount()) {
      return;
    }

    final Npc npc = World.getNpcs().get(npcIndex);

    if (npc == null) {
      return;
    }

    if (player.getRights().greaterOrEqual(PlayerRights.DEVELOPER)
        && player.attr().get(Player.DEBUG_KEY)) {
      player.queuePacket(new ServerMessagePacket(
          String.format("[attack= npc], [id= %d], [slot= %d]", npc.getId(), npc.getSlot())));
    }

    if (npc.getCurrentHealth() <= 0) {
      player.queuePacket(new ServerMessagePacket("This npc is already dead..."));
      return;
    }

    player.getCombat().attack(npc);

  }

}
