package io.astraeus.net.packet.in;

import io.astraeus.game.event.impl.ObjectFirstClickEvent;
import io.astraeus.game.task.impl.DistancedTask;
import io.astraeus.game.world.Position;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.object.GameObject;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;

@IncomingPacket.IncomingPacketOpcode(IncomingPacket.OBJECT_OPTION_1)
public final class ObjectFirstOptionPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    ByteBufReader reader = packet.getReader();

    int x = reader.readShort(ByteOrder.LITTLE, ByteModification.ADDITION);
    int id = reader.readShort(false);
    int y = reader.readShort(false, ByteModification.ADDITION);

    GameObject object = new GameObject(id, new Position(x, y));

    if (player == null || object == null) {
      return;
    }

    player.startAction(new DistancedTask(player, object.getPosition(), 2) {

      @Override
      public void onReached() {
        player.faceLocation(object.getPosition());
        player.post(new ObjectFirstClickEvent(object));
      }

    });

  }

}
