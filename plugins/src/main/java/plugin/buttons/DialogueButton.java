package plugin.buttons;

import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.ButtonActionEvent;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.widget.dialog.Dialogue;

@SubscribesTo(ButtonActionEvent.class)
public final class DialogueButton extends ButtonClick {

	@Override
	protected void execute(Player player, ButtonActionEvent event) {
		switch (event.getButton()) {
		
		case 2461:
		case 2471:
		case 2482:			
		case 2494:
			player.getDialogueFactory().executeOption(0, player.getOptionDialogue());
			break;
			
		case 2495:
		case 2462:
		case 2472:
		case 2483:
			player.getDialogueFactory().executeOption(1, player.getOptionDialogue());
			break;
			
		case 2496:
		case 2473:
		case 2484:
			player.getDialogueFactory().executeOption(2, player.getOptionDialogue());
			break;
			
		case 2497:
		case 2485:
			player.getDialogueFactory().executeOption(3, player.getOptionDialogue());
			break;
			
		case 2498:
			player.getDialogueFactory().executeOption(4, player.getOptionDialogue());
			break;
		
		}
	}

	@Override
	public boolean test(ButtonActionEvent event) {
		return Dialogue.isDialogueButton(event.getButton());
	}

}
