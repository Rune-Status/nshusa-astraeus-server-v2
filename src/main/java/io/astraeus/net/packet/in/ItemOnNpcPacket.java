package io.astraeus.net.packet.in;

import io.astraeus.game.event.impl.ItemOnNpcEvent;
import io.astraeus.game.world.World;
import io.astraeus.game.world.entity.item.Item;
import io.astraeus.game.world.entity.mob.npc.Npc;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;

@IncomingPacket.IncomingPacketOpcode(IncomingPacket.ITEM_ON_NPC)
public final class ItemOnNpcPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    ByteBufReader reader = packet.getReader();

    final int itemId = reader.readShort(false, ByteModification.ADDITION);
    final int npcSlot = reader.readShort(false, ByteModification.ADDITION);
    final int itemSlot = reader.readShort(ByteOrder.LITTLE);

    final Item item = player.getInventory().get(itemSlot);

    // validate the item is the correct item
    if (item.getId() != itemId) {
      return;
    }

    final Npc npc = World.getNpcs().get(npcSlot);

    // validate the npc actually exists
    if (npc == null) {
      return;
    }

    player.post(new ItemOnNpcEvent(item, npc));
  }

}

