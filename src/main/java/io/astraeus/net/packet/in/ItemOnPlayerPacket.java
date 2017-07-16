package io.astraeus.net.packet.in;

import io.astraeus.game.event.impl.ItemOnPlayerEvent;
import io.astraeus.game.world.World;
import io.astraeus.game.world.entity.item.Item;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;

@IncomingPacket.IncomingPacketOpcode(IncomingPacket.ITEM_ON_PLAYER)
public final class ItemOnPlayerPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    final int playerIndex = packet.getReader().readShort(false);
    final int itemSlot = packet.getReader().readShort(ByteOrder.LITTLE);

    final Item used = player.getInventory().get(itemSlot);

    final Player usedWith = World.getPlayers().get(playerIndex);

    player.post(new ItemOnPlayerEvent(used, usedWith));

  }

}
