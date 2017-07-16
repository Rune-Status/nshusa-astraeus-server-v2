package plugin.buttons;

import io.astraeus.game.event.EventContext;
import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.event.impl.ButtonActionEvent;
import io.astraeus.game.world.entity.mob.player.Player;

public abstract class ButtonClick implements EventSubscriber<ButtonActionEvent> {

	@Override
	public void subscribe(EventContext context, Player player, ButtonActionEvent event) {
		execute(player, event);
	}
	
	protected abstract void execute(Player player, ButtonActionEvent event);
	
	public abstract boolean test(ButtonActionEvent event);

}
