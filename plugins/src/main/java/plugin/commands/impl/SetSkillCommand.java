package plugin.commands.impl;

import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.CommandEvent;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.PlayerRights;
import plugin.commands.Command;
import plugin.commands.CommandParser;

@SubscribesTo(CommandEvent.class)
public final class SetSkillCommand extends Command {

	@Override
	protected boolean execute(Player player, CommandParser parser) {
		if (parser.hasNext(2)) {
            int skill = parser.nextInt();

            int lvl = parser.nextInt();

            if (skill < 0 || skill > 22) {
                return false;
            }

            if (skill == 3) {
                if (lvl <= 10) {
                    lvl = 10;
                }
            }

            if (lvl < 1) {
                lvl = 1;
            } else if (lvl > 99) {
                lvl = 99;
            }

            player.getSkills().setMaxLevel(skill, lvl);
            return true;
		}
		
		return false;
	}

	@Override
	public boolean test(CommandEvent event) {
		return event.getName().equalsIgnoreCase("lvl");
	}

	@Override
	protected PlayerRights rights() {
		return PlayerRights.DEVELOPER;
	}

}
