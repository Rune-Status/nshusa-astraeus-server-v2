package plugin.click.item;

import io.astraeus.game.event.EventContext;
import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.ItemSecondClickEvent;
import io.astraeus.game.world.entity.mob.player.Player;

@SubscribesTo(ItemSecondClickEvent.class)
public final class ItemSecondClick implements EventSubscriber<ItemSecondClickEvent> {

	@Override
	public void subscribe(EventContext context, Player player, ItemSecondClickEvent event) {
		
	}

}
