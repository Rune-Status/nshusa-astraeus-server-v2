package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.Sendable;

public final class SetItemModelOnWidgetPacket implements Sendable {

  private final int id;

  private final int zoom;

  private final int model;

  public SetItemModelOnWidgetPacket(int id, int zoom, int model) {
    this.id = id;
    this.zoom = zoom;
    this.model = model;
  }

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(246);
    builder.writeShort(id, ByteOrder.LITTLE).writeShort(zoom).writeShort(model);
    return builder.toOutgoingPacket();
  }

}
