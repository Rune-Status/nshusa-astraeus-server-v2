package plugin.click.item;

import io.astraeus.game.event.EventContext;
import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.ItemOnNpcEvent;
import io.astraeus.game.world.entity.mob.player.Player;

@SubscribesTo(ItemOnNpcEvent.class)
public final class ItemOnNpc implements EventSubscriber<ItemOnNpcEvent> {

	@Override
	public void subscribe(EventContext context, Player player, ItemOnNpcEvent event) {
		
	}

}
