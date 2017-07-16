package io.astraeus.net.packet.in;

import io.astraeus.game.world.World;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;

@IncomingPacket.IncomingPacketOpcode(IncomingPacket.PLAYER_OPTION_3)
public final class PlayerThirdOptionPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    ByteBufReader reader = packet.getReader();

    final int otherPlayerIndex = reader.readShort(ByteOrder.LITTLE);

    if (World.getPlayers().get(otherPlayerIndex) == null) {
      return;
    }

    @SuppressWarnings("unused")
    final Player leader = World.getPlayers().get(otherPlayerIndex);
  }

}
