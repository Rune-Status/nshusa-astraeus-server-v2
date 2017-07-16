package io.astraeus.net.packet.in;

import io.astraeus.game.world.entity.item.Item;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.PlayerRights;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;
import io.astraeus.net.packet.out.ServerMessagePacket;

/**
 * The {@link IncomingPacket} responsible for equipping items.
 * 
 * @author SeVen
 */
@IncomingPacket.IncomingPacketOpcode(IncomingPacket.EQUIP_ITEM)
public final class EquipItemPacket implements Receivable {

  @Override
  public void handlePacket(Player player, IncomingPacket packet) {
    ByteBufReader reader = packet.getReader();

    final int id = reader.readShort();
    final int slot = reader.readShort(ByteModification.ADDITION);
    final int interfaceId = reader.readShort(ByteModification.ADDITION);

    if (player.getRights().greaterOrEqual(PlayerRights.DEVELOPER)
        && player.attr().get(Player.DEBUG_KEY)) {
      player.queuePacket(new ServerMessagePacket(String
          .format("[EquipItem] - [id= %d], [slot= %d], [interfaceId= %d]", id, slot, interfaceId)));
    }

    Item item = player.getInventory().get(slot);

    if (item == null || item.getId() != id) {
      return;
    }

    if (interfaceId == 3214) {
      player.getEquipment().equip(slot);
    }

  }
}
