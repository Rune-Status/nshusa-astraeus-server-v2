package io.astraeus.game.world.entity.mob.npc.updating.mask;

import io.astraeus.game.world.entity.mob.combat.dmg.Hit;
import io.astraeus.game.world.entity.mob.npc.Npc;
import io.astraeus.game.world.entity.mob.npc.updating.NpcUpdateBlock;
import io.astraeus.game.world.entity.mob.update.UpdateFlag;
import io.astraeus.net.codec.ByteModification;
import io.astraeus.net.codec.game.GamePacketBuilder;

/**
 * {@link NpcUpdateBlock} implementation that displays a single hit-splat on a mob.
 * 
 * @author SeVen
 */
public class NpcSingleHitUpdateBlock extends NpcUpdateBlock {

  private final Hit hit;

  public NpcSingleHitUpdateBlock(Hit hit) {
    super(0x40, UpdateFlag.HIT);
    this.hit = hit;
  }

  @Override
  public void encode(Npc npc, GamePacketBuilder builder) {
    builder.write(hit.getDamage(), ByteModification.NEGATION)
        .write(hit.getType().getId(), ByteModification.SUBTRACTION)
        // .write(hit.getDamageType().getId()) // custom
        .write(npc.getCurrentHealth(), ByteModification.SUBTRACTION)
        .write(npc.getMaximumHealth(), ByteModification.NEGATION);
  }

}
