package io.astraeus.net.packet.in;

import io.astraeus.game.world.Position;
import io.astraeus.game.world.entity.mob.Mob;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;

/**
 * The {@link IncomingPacket}'s responsible for player movement.
 * 
 * @author SeVen
 */
@IncomingPacket.IncomingPacketOpcode({IncomingPacket.WALK_ON_COMMAND, IncomingPacket.REGULAR_WALK,
    IncomingPacket.MAP_WALK})
public class MovementPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {

    int size = packet.getLength();

    if (packet.getOpcode() == IncomingPacket.MAP_WALK) {
      // this is an anti-cheat
      size -= 14;
    }

    if (player.getInteractingEntity() != null) {
      Mob other = player.getInteractingEntity();
      other.setInteractingEntity(null);
      player.setInteractingEntity(null);
    }

    player.getCombat().setInCombat(false);

    player.getCurrentAction().ifPresent(it -> player.stopAction());

    player.getDialogue().ifPresent(it -> player.getDialogueFactory().clear());

    if (!player.getWidgets().isEmpty()) {
      player.getWidgets().close();
    }

    ByteBufReader reader = packet.getReader();

    int steps = (size - 5) / 2;
    int[][] path = new int[steps][2];
    int firstStepX = reader.readShort(ByteOrder.LITTLE, ByteModification.ADDITION);

    for (int i = 0; i < steps; i++) {
      path[i][0] = reader.readByte();
      path[i][1] = reader.readByte();
    }

    int firstStepY = reader.readShort(ByteOrder.LITTLE);
    player.getMovement().reset();
    player.getMovement().setRunningQueueEnabled(reader.readByte(ByteModification.NEGATION) == 1);
    player.getMovement().addToPath(new Position(firstStepX, firstStepY));

    for (int i = 0; i < steps; i++) {
      path[i][0] += firstStepX;
      path[i][1] += firstStepY;
      player.getMovement().addToPath(new Position(path[i][0], path[i][1]));
    }
    player.getMovement().finish();
  }
}
