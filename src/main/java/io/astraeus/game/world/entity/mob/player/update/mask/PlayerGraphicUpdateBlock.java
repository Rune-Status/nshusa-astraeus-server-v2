package io.astraeus.game.world.entity.mob.player.update.mask;

import io.astraeus.game.world.entity.Graphic;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.update.PlayerUpdateBlock;
import io.astraeus.game.world.entity.mob.update.UpdateFlag;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.GamePacketBuilder;

/**
 * The {@link PlayerUpdateBlock} implementation that updates a players graphics.
 * 
 * @author Freyr
 */
public class PlayerGraphicUpdateBlock extends PlayerUpdateBlock {

  /**
   * Creates a new {@link PlayerGraphicUpdateBlock}.
   */
  public PlayerGraphicUpdateBlock() {
    super(0x100, UpdateFlag.GRAPHICS);
  }

  @Override
  public void encode(Player entity, GamePacketBuilder builder) {
    final Graphic graphic = entity.getGraphic();
    builder.writeShort(graphic.getId(), ByteOrder.LITTLE);
    builder.writeInt(graphic.getHeight() << 16 | graphic.getDelay());
  }

}
