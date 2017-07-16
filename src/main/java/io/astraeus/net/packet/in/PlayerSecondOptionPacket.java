package io.astraeus.net.packet.in;

import io.astraeus.game.world.World;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;
import io.astraeus.net.packet.out.ServerMessagePacket;

@IncomingPacket.IncomingPacketOpcode(IncomingPacket.PLAYER_OPTION_2)
public final class PlayerSecondOptionPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    ByteBufReader reader = packet.getReader();

    int otherPlayerIndex = reader.readShort(ByteOrder.LITTLE);
    Player other = World.getPlayers().get(otherPlayerIndex);

    if (other == null) {
      player
          .queuePacket(new ServerMessagePacket("You tried to attack a player that doesn't exist."));
      return;
    }
  }

}
