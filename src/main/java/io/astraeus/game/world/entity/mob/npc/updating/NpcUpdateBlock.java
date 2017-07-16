package io.astraeus.game.world.entity.mob.npc.updating;

import io.astraeus.game.world.entity.mob.npc.Npc;
import io.astraeus.game.world.entity.mob.update.UpdateBlock;
import io.astraeus.game.world.entity.mob.update.UpdateFlag;

/**
 * The {@link UpdateBlock} implementation for mobs.
 * 
 * @author SeVen
 */
public abstract class NpcUpdateBlock extends UpdateBlock<Npc> {

  /**
   * Creates a new {@link NpcUpdateBlock}.
   * 
   * @param flag The enumerated type for this update.
   */
  public NpcUpdateBlock(int mask, UpdateFlag flag) {
    super(mask, flag);
  }

}
