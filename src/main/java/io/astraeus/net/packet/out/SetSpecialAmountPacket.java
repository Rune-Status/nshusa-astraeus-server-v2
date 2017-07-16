package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.Sendable;

public final class SetSpecialAmountPacket implements Sendable {

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(137);

    if (player.getSpecialAmount() > 100) {
      player.setSpecialAmount(100);
    }

    if (player.getSpecialAmount() < 0) {
      player.setSpecialAmount(0);
    }

    builder.write(player.getSpecialAmount());
    return builder.toOutgoingPacket();
  }
}
