package plugin.click.obj;

import io.astraeus.game.event.EventContext;
import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.ObjectFifthClickEvent;
import io.astraeus.game.world.entity.mob.player.Player;

@SubscribesTo(ObjectFifthClickEvent.class)
public final class ObjectFifthClick implements EventSubscriber<ObjectFifthClickEvent> {

	@Override
	public void subscribe(EventContext context, Player player, ObjectFifthClickEvent event) {
		
	}

}
