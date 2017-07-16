package io.astraeus.game.world.entity.mob.npc.updating.mask;

import io.astraeus.game.world.entity.mob.Mob;
import io.astraeus.game.world.entity.mob.npc.Npc;
import io.astraeus.game.world.entity.mob.npc.updating.NpcUpdateBlock;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.update.UpdateFlag;
import io.astraeus.net.codec.game.GamePacketBuilder;

public class NpcInteractionUpdateBlock extends NpcUpdateBlock {

  public NpcInteractionUpdateBlock() {
    super(0x20, UpdateFlag.ENTITY_INTERACTION);
  }

  @Override
  public void encode(Npc mob, GamePacketBuilder builder) {
    final Mob entity = (Mob) mob.getInteractingEntity();
    builder.writeShort(entity == null ? -1
        : (entity instanceof Player) ? entity.getSlot() + 32768 : entity.getSlot());
  }

}
