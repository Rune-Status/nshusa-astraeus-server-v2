package io.astraeus.net.packet.in;

import io.astraeus.game.world.World;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.PlayerRights;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;
import io.astraeus.net.packet.out.ServerMessagePacket;

@IncomingPacket.IncomingPacketOpcode(IncomingPacket.PLAYER_OPTION_4)
public final class PlayerFourthOptionPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    ByteBufReader reader = packet.getReader();

    final int otherPlayerTradeIndex = reader.readShort(ByteOrder.LITTLE);

    if (otherPlayerTradeIndex == player.getSlot()) {
      return;
    }

    if (player.getRights().equal(PlayerRights.ADMINISTRATOR)) {
      player.queuePacket(new ServerMessagePacket("Trading as an admin has been disabled."));
      return;
    }

    if (otherPlayerTradeIndex < 1) {
      return;
    }

    if (World.getPlayers().get(otherPlayerTradeIndex) == null) {
      return;
    }

    Player other = World.getPlayers().get(otherPlayerTradeIndex);

    if (other == null || !other.isRegistered() || other.isTeleporting() || other.isDead()) {
      return;
    }
  }

}
