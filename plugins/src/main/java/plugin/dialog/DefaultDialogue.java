package plugin.dialog;

import io.astraeus.game.world.entity.mob.player.widget.dialog.Dialogue;
import io.astraeus.game.world.entity.mob.player.widget.dialog.DialogueFactory;
import io.astraeus.game.world.entity.mob.player.widget.dialog.Expression;

public final class DefaultDialogue extends Dialogue {
	
	private final int npcId;
	
	public DefaultDialogue(int npcId) {
		this.npcId = npcId;
	}

	@Override
	public void sendDialogues(DialogueFactory factory) {
        factory.sendNpcChat(npcId, "Hello!", "Welcome to Astraeus!")
        .sendPlayerChat(Expression.HAPPY, "Well hello there stranger!")
        .sendNpcChat(Expression.HAPPY, "Enjoy your stay!", "If you see any bugs, please report them!")
        .execute();
	}

}
