package plugin.buttons;

import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.ButtonActionEvent;
import io.astraeus.game.world.entity.mob.combat.def.AttackType;
import io.astraeus.game.world.entity.mob.player.Player;

@SubscribesTo(ButtonActionEvent.class)
public final class SwordAttackTypeButtons extends ButtonClick {

	@Override
	protected void execute(Player player, ButtonActionEvent event) {
		switch (event.getButton()) {
		
		case 2282:
			player.setAttackType(AttackType.SWORD_STAB);
			break;
			
		case 2283:
			player.setAttackType(AttackType.SWORD_BLOCK);
			break;
			
		case 2284:
			player.setAttackType(AttackType.SWORD_SLASH);
			break;
			
		case 2285:
			player.setAttackType(AttackType.SWORD_LUNGE);
			break;
		
		}
	}

	@Override
	public boolean test(ButtonActionEvent event) {
		switch (event.getButton()) {		
		case 2282:
		case 2285:
		case 2284:
		case 2283:
			return true;
		
		}
		
		return false;
	}

}
