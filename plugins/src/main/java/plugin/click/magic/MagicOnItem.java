package plugin.click.magic;

import io.astraeus.game.event.EventContext;
import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.MagicOnItemEvent;
import io.astraeus.game.world.entity.mob.player.Player;

@SubscribesTo(MagicOnItemEvent.class)
public final class MagicOnItem implements EventSubscriber<MagicOnItemEvent> {

	@Override
	public void subscribe(EventContext context, Player player, MagicOnItemEvent event) {
		
	}

}
