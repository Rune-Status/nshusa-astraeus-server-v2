package plugin.click.obj;

import io.astraeus.game.event.EventContext;
import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.ObjectSecondClickEvent;
import io.astraeus.game.world.entity.mob.player.Player;

@SubscribesTo(ObjectSecondClickEvent.class)
public final class ObjectSecondClick implements EventSubscriber<ObjectSecondClickEvent> {

	@Override
	public void subscribe(EventContext context, Player player, ObjectSecondClickEvent event) {
		
	}

}
