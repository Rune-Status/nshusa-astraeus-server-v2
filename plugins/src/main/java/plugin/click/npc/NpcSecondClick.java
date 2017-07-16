package plugin.click.npc;

import io.astraeus.game.event.EventContext;
import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.NpcSecondClickEvent;
import io.astraeus.game.world.entity.mob.player.Player;

@SubscribesTo(NpcSecondClickEvent.class)
public final class NpcSecondClick implements EventSubscriber<NpcSecondClickEvent> {

	@Override
	public void subscribe(EventContext context, Player player, NpcSecondClickEvent event) {
		
	}

}
