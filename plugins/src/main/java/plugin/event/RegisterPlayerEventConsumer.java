package plugin.event;

import java.util.logging.Logger;

import io.astraeus.game.event.EventContext;
import io.astraeus.game.event.EventSubscriber;
import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.world.World;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.attr.AttributeKey;
import io.astraeus.game.world.entity.mob.player.event.PostLoginEvent;
import io.astraeus.game.world.entity.mob.player.event.RegisterPlayerEvent;
import io.astraeus.game.world.entity.mob.update.UpdateFlag;
import io.astraeus.util.LoggerUtils;

@SubscribesTo(RegisterPlayerEvent.class)
public final class RegisterPlayerEventConsumer implements EventSubscriber<RegisterPlayerEvent> {

  private static final Logger logger = LoggerUtils.getLogger(RegisterPlayerEventConsumer.class);

  @Override
  public void subscribe(EventContext context, Player player, RegisterPlayerEvent event) {
    World.register(event.getPlayer());
    event.getPlayer().setRegionChange(true);
    event.getPlayer().getUpdateFlags().add(UpdateFlag.APPEARANCE);
    event.getPlayer()
        .setPosition(event.getPlayer().attr().contains(AttributeKey.valueOf("new_player", true))
            ? Player.DEFAULT_RESPAWN : event.getPlayer().getPosition());
    logger.info(String.format("[REGISTERED]: [user= %s]", event.getPlayer().getUsername()));

    player.post(new PostLoginEvent(event.getPlayer()));
  }

}
