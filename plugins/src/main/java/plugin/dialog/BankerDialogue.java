package plugin.dialog;

import io.astraeus.game.world.entity.mob.player.widget.dialog.Dialogue;
import io.astraeus.game.world.entity.mob.player.widget.dialog.DialogueFactory;
import io.astraeus.game.world.entity.mob.player.widget.dialog.Expression;

public final class BankerDialogue extends Dialogue {

	@Override
	public void sendDialogues(DialogueFactory factory) {
        factory.sendNpcChat(Expression.HAPPY, "Good day. How may I help you?")
        .sendOption("I'd like to access my bank account, please.", () -> {
            factory.sendPlayerChat("I'd like to access my bank account, please.")
            .onAction(() -> {
                factory.getPlayer().getBank().open();
            });
        }, "I'd like to check my PIN settings.", () -> {
            factory.sendPlayerChat("I'd like to check my PIN settings.")
            .sendNpcChat("This feature is currently not available.");
        }, "I don't need anything.", () -> {
            factory.sendPlayerChat("I don't need anything.");
        }).execute();
	}

}
