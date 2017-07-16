package plugin.commands;

import io.astraeus.game.event.EventContext;
import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.event.impl.CommandEvent;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.PlayerRights;

public abstract class Command implements EventSubscriber<CommandEvent> {
	
	@Override
	public void subscribe(EventContext ctx, Player player, CommandEvent event) {
	  
	  if (player == null) {
	    return;
	  }
	  
	  if (player.getRights().greaterOrEqual(rights())) {
	       execute(player, CommandParser.create(event.getInput()));
	  }	  

	}
	
	protected abstract boolean execute(Player player, CommandParser parser);
	
	@Override
	public abstract boolean test(CommandEvent event);
	
	protected abstract PlayerRights rights();

}
