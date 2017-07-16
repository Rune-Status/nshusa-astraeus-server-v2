package plugin.click.item;

import io.astraeus.game.event.EventContext;
import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.ItemOnItemEvent;
import io.astraeus.game.world.entity.mob.player.Player;

@SubscribesTo(ItemOnItemEvent.class)
public final class ItemOnItem implements EventSubscriber<ItemOnItemEvent> {

	@Override
	public void subscribe(EventContext context, Player player, ItemOnItemEvent event) {
		
	}

}
