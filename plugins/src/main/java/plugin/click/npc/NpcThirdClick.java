package plugin.click.npc;

import io.astraeus.game.event.EventContext;
import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.NpcThirdClickEvent;
import io.astraeus.game.world.entity.mob.player.Player;

@SubscribesTo(NpcThirdClickEvent.class)
public final class NpcThirdClick implements EventSubscriber<NpcThirdClickEvent> {

	@Override
	public void subscribe(EventContext context, Player player, NpcThirdClickEvent event) {
		
	}

}
