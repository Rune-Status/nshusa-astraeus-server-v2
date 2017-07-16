package plugin.dialog;

import io.astraeus.game.world.entity.mob.player.widget.dialog.Dialogue;
import io.astraeus.game.world.entity.mob.player.widget.dialog.DialogueFactory;
import plugin.shops.ShopEvent;

public final class GeneralStoreDialogue extends Dialogue {

	@Override
	public void sendDialogues(DialogueFactory factory) {
        factory.sendNpcChat("Can I help you at all?")
		.sendOption("Yes please. What are you selling?", () -> {
			factory.onAction(() -> {
				factory.getPlayer().post(new ShopEvent("General Store"));
			});
		}, "No thanks.", () -> {
			factory.sendPlayerChat("No thanks.");
		}).execute();		
	}

}
