package io.astraeus.game.world.entity.mob.npc.updating.mask;

import io.astraeus.game.world.entity.mob.npc.Npc;
import io.astraeus.game.world.entity.mob.npc.updating.NpcUpdateBlock;
import io.astraeus.game.world.entity.mob.update.UpdateFlag;
import io.astraeus.net.codec.game.GamePacketBuilder;

public class NpcForceChatUpdateBlock extends NpcUpdateBlock {

  public NpcForceChatUpdateBlock() {
    super(0x1, UpdateFlag.FORCED_CHAT);
  }

  @Override
  public void encode(Npc entity, GamePacketBuilder builder) {
    builder.writeString(entity.getForcedChat());
  }

}
