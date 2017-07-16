package io.astraeus.net.packet.in;

import io.astraeus.game.event.impl.ItemOnObjectEvent;
import io.astraeus.game.world.Position;
import io.astraeus.game.world.entity.item.Item;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.object.GameObject;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;

@IncomingPacket.IncomingPacketOpcode(IncomingPacket.ITEM_ON_OBJECT)
public final class ItemOnObjectPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    ByteBufReader reader = packet.getReader();

    @SuppressWarnings("unused")
    int interfaceType = reader.readShort();
    final int objectId = reader.readShort(ByteOrder.LITTLE);
    final int objectY = reader.readShort(ByteOrder.LITTLE, ByteModification.ADDITION);
    final int slot = reader.readShort(ByteOrder.LITTLE);
    final int objectX = reader.readShort(ByteOrder.LITTLE, ByteModification.ADDITION);
    final int itemId = reader.readShort();

    final Item item = player.getInventory().get(slot);

    // validate the item exists and is the correct item
    if (item.getId() != itemId) {
      return;
    }

    // instead of doing it this way, when clipping gets added grab the game object from a map of
    // objects
    GameObject object = new GameObject(objectId, new Position(objectX, objectY));

    player.post(new ItemOnObjectEvent(item, object));
  }

}

