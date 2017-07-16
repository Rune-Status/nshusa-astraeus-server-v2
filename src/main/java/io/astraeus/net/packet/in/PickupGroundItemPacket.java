package io.astraeus.net.packet.in;

import java.util.Optional;

import io.astraeus.game.task.impl.DistancedTask;
import io.astraeus.game.world.Position;
import io.astraeus.game.world.entity.item.Item;
import io.astraeus.game.world.entity.item.ItemContainer;
import io.astraeus.game.world.entity.item.ItemContainerPolicy;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.PlayerRights;
import io.astraeus.game.world.entity.object.GameObjects;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.ByteBufReader;
import io.astraeus.net.packet.IncomingPacket;
import io.astraeus.net.packet.Receivable;
import io.astraeus.net.packet.out.RemoveGroundItemPacket;
import io.astraeus.net.packet.out.ServerMessagePacket;

/**
 * The {@link IncomingPacket} responsible for picking up an item on the ground.
 * 
 * @author Freyr
 */
@IncomingPacket.IncomingPacketOpcode(IncomingPacket.PICKUP_GROUND_ITEM)
public final class PickupGroundItemPacket implements Receivable {

	@Override
	public void handlePacket(final Player player, IncomingPacket packet) {
		ByteBufReader reader = packet.getReader();

		final int y = reader.readShort(ByteOrder.LITTLE);
		final int id = reader.readShort(false);
		final int x = reader.readShort(ByteOrder.LITTLE);

		// create the position object
		Position position = new Position(x, y, player.getPosition().getHeight());

		// get the item from the map
		Item[] stack = GameObjects.getGroundItems().get(position);

		if (stack == null) {
			return;
		}

		ItemContainer container = new ItemContainer(64, ItemContainerPolicy.NORMAL);
		container.addAll(stack);
		
		if (!container.contains(id)) {
			return;
		}

		player.startAction(new DistancedTask(player, position, 2) {

			@Override
			public void onReached() {
				
				if (container.contains(id)) {
					Optional<Item> result = container.searchItem(id);					
					
					if (!result.isPresent()) {
						return;
					}
					
					Item item = result.get();

					player.getInventory().add(item);
					
					container.remove(item);
					
					player.queuePacket(new RemoveGroundItemPacket(item));
					
					if (container.size() == 0) {
						GameObjects.getGroundItems().remove(position);
					} else {
						GameObjects.getGroundItems().put(position, container.container());
					}

					if (player.getRights().equals(PlayerRights.DEVELOPER) && player.attr().get(Player.DEBUG_KEY)) {
						player.queuePacket(new ServerMessagePacket(String.format("[PickupItem] - Item: %s Position: %s",
								result.toString(), position.toString())));
					}
				}
				
			}

		});

	}
}
