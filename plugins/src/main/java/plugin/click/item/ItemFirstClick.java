package plugin.click.item;

import io.astraeus.game.event.EventContext;
import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.ItemFirstClickEvent;
import io.astraeus.game.world.entity.mob.player.Player;

@SubscribesTo(ItemFirstClickEvent.class)
public final class ItemFirstClick implements EventSubscriber<ItemFirstClickEvent> {

	@Override
	public void subscribe(EventContext context, Player player, ItemFirstClickEvent event) {
		
	}

}
