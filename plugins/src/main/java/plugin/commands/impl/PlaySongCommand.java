package plugin.commands.impl;

import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.CommandEvent;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.PlayerRights;
import io.astraeus.net.packet.out.PlaySongPacket;
import lombok.val;
import plugin.commands.Command;
import plugin.commands.CommandParser;

@SubscribesTo(CommandEvent.class)
public final class PlaySongCommand extends Command {
	
	@Override
	protected boolean execute(Player player, CommandParser parser) {
		if (parser.hasNext()) {
			val id = parser.nextInt();
			
			player.queuePacket(new PlaySongPacket(id));
		}
		return true;
	}

	@Override
	protected PlayerRights rights() {
		return PlayerRights.DEVELOPER;
	}
	
	public boolean test(CommandEvent event) {
		return event.getName().equalsIgnoreCase("song");
	}

}
