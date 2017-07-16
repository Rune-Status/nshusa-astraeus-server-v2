package io.astraeus.net.packet.in;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;
import io.astraeus.net.packet.out.ServerMessagePacket;

/**
 * The {@link IncomingPacket}'s responsible for player communication.
 * 
 * @author Seven
 */
@IncomingPacket.IncomingPacketOpcode({IncomingPacket.ADD_FRIEND, IncomingPacket.PRIVATE_MESSAGE,
    IncomingPacket.REMOVE_FRIEND, IncomingPacket.REMOVE_IGNORE, IncomingPacket.PRIVACY_OPTIONS,
    IncomingPacket.ADD_IGNORE})
public class PlayerRelationPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {

    ByteBufReader reader = packet.getReader();

    switch (packet.getOpcode()) {

      case IncomingPacket.ADD_FRIEND:
        player.getPlayerRelation().addFriend(reader.readLong());
        break;

      case IncomingPacket.REMOVE_FRIEND:
        player.getPlayerRelation().removeFriend(reader.readLong());
        break;

      case IncomingPacket.ADD_IGNORE:
        player.getPlayerRelation().addIgnore(reader.readLong());
        break;

      case IncomingPacket.REMOVE_IGNORE:
        break;

      case IncomingPacket.PRIVATE_MESSAGE:
        sendMessage(player, packet);
        break;

      case IncomingPacket.PRIVACY_OPTIONS:
        break;

    }

  }

  /**
   * Handles sending a private message.
   *
   * @param player The player sending the message.
   *
   * @param packet The packet responsible for this action.
   */
  private void sendMessage(Player player, IncomingPacket packet) {
    ByteBufReader reader = packet.getReader();
    long to = reader.readLong();
    int newSize = packet.getSize() - 8;
    byte[] data = reader.readBytes(newSize);

    if (to < 0 || newSize < 0 || data == null) {
      return;
    }

    if (!player.getPlayerRelation().getFriendList().contains(to)) {
      player.queuePacket(new ServerMessagePacket(
          "You cannot send a message to a " + "player not on your friends list!"));
      return;
    }

    player.getPlayerRelation().sendPrivateMessage(to, data, newSize);
  }

}
