package plugin.commands.impl;

import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.CommandEvent;
import io.astraeus.game.world.entity.Graphic;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.PlayerRights;
import lombok.val;
import plugin.commands.Command;
import plugin.commands.CommandParser;

@SubscribesTo(CommandEvent.class)
public final class GfxCommand extends Command {

	@Override
	protected boolean execute(Player player, CommandParser parser) {
		if (parser.hasNext(3)) {
			val id = parser.nextInt();
			
			val delay = parser.nextInt();
			
			val height = parser.nextInt();
			
			player.startGraphic(Graphic.create(id, delay, height));
		} else if (parser.hasNext(2)) {
			val id = parser.nextInt();
			
			val delay = parser.nextInt();
			
			player.startGraphic(Graphic.create(id, delay, Graphic.LOW_HEIGHT));
		} else if (parser.hasNext()) {
			val id = parser.nextInt();

			player.startGraphic(Graphic.create(id));
		}
		return false;
	}

	@Override
	protected PlayerRights rights() {
		return PlayerRights.DEVELOPER;
	}
	
	@Override
	public boolean test(CommandEvent event) {
		return event.getName().equalsIgnoreCase("gfx");
	}
	
}
