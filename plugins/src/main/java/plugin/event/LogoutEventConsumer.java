package plugin.event;

import io.astraeus.game.event.EventContext;
import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.event.LogoutEvent;
import io.astraeus.net.packet.out.LogoutPlayerPacket;

@SubscribesTo(LogoutEvent.class)
public final class LogoutEventConsumer implements EventSubscriber<LogoutEvent> {

	@Override
	public void subscribe(EventContext context, Player player, LogoutEvent event) {		
		
		event.getPlayer().queuePacket(new LogoutPlayerPacket());
		
		event.getPlayer().resetEntityInteraction();
		
		event.getPlayer().attr().put(Player.ACTIVE_KEY, false);
		event.getPlayer().attr().put(Player.LOGOUT_KEY, true);
		event.getPlayer().attr().put(Player.DISCONNECTED_KEY, true);
	}

}
