package plugin.click.item;

import io.astraeus.game.event.EventContext;
import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.ItemThirdClickEvent;
import io.astraeus.game.world.entity.mob.player.Player;

@SubscribesTo(ItemThirdClickEvent.class)
public final class ItemThirdClick implements EventSubscriber<ItemThirdClickEvent> {

	@Override
	public void subscribe(EventContext context, Player player, ItemThirdClickEvent event) {
		
	}

}
