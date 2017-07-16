package io.astraeus.net.packet.in;

import io.astraeus.game.event.impl.ButtonActionEvent;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.PlayerRights;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;
import io.astraeus.net.packet.out.ServerMessagePacket;

/**
 * The {@link IncomingPacket} responsible for clicking buttons on the client.
 * 
 * @author SeVen
 */
@IncomingPacket.IncomingPacketOpcode(IncomingPacket.BUTTON_CLICK)
public class ButtonClickPacket implements Receivable {

  @Override
  public void handlePacket(final Player player, IncomingPacket packet) {
    ByteBufReader reader = packet.getReader();

    final int button = reader.readShort();

    if (player.getRights().greaterOrEqual(PlayerRights.DEVELOPER)
        && player.attr().get(Player.DEBUG_KEY)) {
      player.queuePacket(new ServerMessagePacket(String.format("[button= %d]", button)));
    }

    player.post(new ButtonActionEvent(button));
  }

}
