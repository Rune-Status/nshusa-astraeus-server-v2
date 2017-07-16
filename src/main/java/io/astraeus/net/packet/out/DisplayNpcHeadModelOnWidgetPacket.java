package io.astraeus.net.packet.out;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.GamePacketBuilder;
import io.astraeus.net.packet.OutgoingPacket;
import io.astraeus.net.packet.Sendable;

/**
 * The {@link OutgoingPacket} that displays an npc model on an interface.
 *
 * @author Seven
 */
public final class DisplayNpcHeadModelOnWidgetPacket implements Sendable {

  /**
   * The id of the npc to display.
   */
  private final int npcId;

  /**
   * The id of the interface to display on.
   */
  private final int interfaceId;

  /**
   * Creates a new {@link DisplayNpcHeadModelOnWidgetPacket}.
   *
   * @param npcId The id of the npc to show.
   *
   * @param interfaceId The id of the interface to display on.
   */
  public DisplayNpcHeadModelOnWidgetPacket(int npcId, int interfaceId) {
    this.npcId = npcId;
    this.interfaceId = interfaceId;
  }

  @Override
  public Optional<OutgoingPacket> writePacket(Player player) {
    GamePacketBuilder builder = new GamePacketBuilder(75);
    builder.writeShort(npcId, ByteModification.ADDITION, ByteOrder.LITTLE).writeShort(interfaceId,
        ByteModification.ADDITION, ByteOrder.LITTLE);
    return builder.toOutgoingPacket();
  }

}
