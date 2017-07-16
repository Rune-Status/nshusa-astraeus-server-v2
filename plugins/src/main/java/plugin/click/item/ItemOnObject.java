package plugin.click.item;

import io.astraeus.game.event.EventContext;
import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.ItemOnObjectEvent;
import io.astraeus.game.world.entity.mob.player.Player;

@SubscribesTo(ItemOnObjectEvent.class)
public final class ItemOnObject implements EventSubscriber<ItemOnObjectEvent> {

	@Override
	public void subscribe(EventContext context, Player player, ItemOnObjectEvent event) {
		
	}

}
