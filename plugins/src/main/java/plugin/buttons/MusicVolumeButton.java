package plugin.buttons;

import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.ButtonActionEvent;
import io.astraeus.game.sound.Volume;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.packet.out.SetWidgetConfigPacket;

@SubscribesTo(ButtonActionEvent.class)
public final class MusicVolumeButton extends ButtonClick {

	@Override
	protected void execute(Player player, ButtonActionEvent event) {		
		switch (event.getButton()) {
		case 930:
            player.attr().put(Player.MUSIC_VOLUME_KEY, Volume.SILENT);
            player.queuePacket(new SetWidgetConfigPacket(168, player.attr().get(Player.MUSIC_VOLUME_KEY).getCode()));
			break;
			
		case 931:
            player.attr().put(Player.MUSIC_VOLUME_KEY, Volume.QUIET);
            player.queuePacket(new SetWidgetConfigPacket(168, player.attr().get(Player.MUSIC_VOLUME_KEY).getCode()));
			break;
			
		case 932:
            player.attr().put(Player.MUSIC_VOLUME_KEY, Volume.NORMAL);
            player.queuePacket(new SetWidgetConfigPacket(168, player.attr().get(Player.MUSIC_VOLUME_KEY).getCode()));
			break;
			
		case 933:
            player.attr().put(Player.MUSIC_VOLUME_KEY, Volume.HIGH);
            player.queuePacket(new SetWidgetConfigPacket(168, player.attr().get(Player.MUSIC_VOLUME_KEY).getCode()));
			break;
			
		case 934:
            player.attr().put(Player.MUSIC_VOLUME_KEY, Volume.LOUD);
            player.queuePacket(new SetWidgetConfigPacket(168, player.attr().get(Player.MUSIC_VOLUME_KEY).getCode()));
			break;
		}		
	}

	@Override
	public boolean test(ButtonActionEvent event) {
		switch (event.getButton()) {		
		case 930:
		case 931:
		case 932:
		case 933:
		case 934:
			return true;
		
		}		
		return false;		
	}

}
