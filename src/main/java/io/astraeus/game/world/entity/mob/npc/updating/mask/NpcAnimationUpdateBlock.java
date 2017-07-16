package io.astraeus.game.world.entity.mob.npc.updating.mask;

import io.astraeus.game.world.entity.mob.Animation;
import io.astraeus.game.world.entity.mob.npc.Npc;
import io.astraeus.game.world.entity.mob.npc.updating.NpcUpdateBlock;
import io.astraeus.game.world.entity.mob.update.UpdateFlag;
import io.astraeus.net.codec.ByteOrder;
import io.astraeus.net.codec.game.GamePacketBuilder;

public class NpcAnimationUpdateBlock extends NpcUpdateBlock {

  public NpcAnimationUpdateBlock() {
    super(0x10, UpdateFlag.ANIMATION);
  }

  @Override
  public void encode(Npc entity, GamePacketBuilder builder) {
    final Animation animation = entity.getAnimation();
    builder.writeShort(animation.getId(), ByteOrder.LITTLE).write(animation.getDelay());
  }

}
