package plugin.click.obj;

import io.astraeus.game.event.EventContext;
import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.ObjectThirdClickEvent;
import io.astraeus.game.world.entity.mob.player.Player;

@SubscribesTo(ObjectThirdClickEvent.class)
public final class ObjectThirdClick implements EventSubscriber<ObjectThirdClickEvent> {

	@Override
	public void subscribe(EventContext context, Player player, ObjectThirdClickEvent event) {
		
	}

}
