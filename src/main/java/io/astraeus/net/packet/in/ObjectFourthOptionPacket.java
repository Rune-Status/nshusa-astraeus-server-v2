package io.astraeus.net.packet.in;

import io.astraeus.game.event.impl.ObjectFourthClickEvent;
import io.astraeus.game.task.impl.DistancedTask;
import io.astraeus.game.world.Position;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.object.GameObject;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;

@IncomingPacket.IncomingPacketOpcode(IncomingPacket.OBJECT_OPTION_4)
public final class ObjectFourthOptionPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    ByteBufReader reader = packet.getReader();

    int x = reader.readShort(ByteModification.ADDITION);
    int y = reader.readShort(ByteModification.ADDITION);
    int id = reader.readShort();

    GameObject object = new GameObject(id, new Position(x, y, player.getPosition().getHeight()));

    if (player == null || object == null || object.getId() != id) {
      return;
    }

    player.startAction(new DistancedTask(player, object.getPosition(), 2) {

      @Override
      public void onReached() {
        player.faceLocation(object.getPosition());
        player.post(new ObjectFourthClickEvent(object));
      }

    });

  }

}
