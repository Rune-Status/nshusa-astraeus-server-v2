package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.Sendable;

public final class DisplayWelcomeScreenPacket implements Sendable {

  private final int days;

  private final int unreadMessages;

  private final int member;

  private final int ip;

  private final int daysSince;

  public DisplayWelcomeScreenPacket(int days, int unreadMessages, int member, int ip,
      int daysSince) {
    this.days = days;
    this.unreadMessages = unreadMessages;
    this.member = member;
    this.ip = ip;
    this.daysSince = daysSince;
  }

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(176);
    builder.write(days, ByteModification.NEGATION)
        .writeShort(unreadMessages, ByteModification.ADDITION).write(member)
        .writeInt(ip, ByteOrder.MIDDLE).writeShort(daysSince);

    return builder.toOutgoingPacket();
  }

}
