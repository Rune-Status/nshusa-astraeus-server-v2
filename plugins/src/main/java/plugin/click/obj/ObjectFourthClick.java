package plugin.click.obj;

import io.astraeus.game.event.EventContext;
import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.ObjectFourthClickEvent;
import io.astraeus.game.world.entity.mob.player.Player;

@SubscribesTo(ObjectFourthClickEvent.class)
public final class ObjectFourthClick implements EventSubscriber<ObjectFourthClickEvent> {

	@Override
	public void subscribe(EventContext context, Player player, ObjectFourthClickEvent event) {
		
	}

}
