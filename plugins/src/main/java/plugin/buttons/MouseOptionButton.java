package plugin.buttons;

import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.ButtonActionEvent;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.packet.out.SetWidgetConfigPacket;

@SubscribesTo(ButtonActionEvent.class)
public final class MouseOptionButton extends ButtonClick {

	@Override
	protected void execute(Player player, ButtonActionEvent event) {
		switch (event.getButton()) {
		
		case 914:
	        player.attr().toggle(Player.MOUSE_BUTTON_KEY);
	        player.queuePacket(new SetWidgetConfigPacket(171, player.attr().get(Player.MOUSE_BUTTON_KEY)));
			break;
		
		}
	}

	@Override
	public boolean test(ButtonActionEvent event) {
		return event.getButton() == 914;
	}

}
