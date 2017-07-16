package io.astraeus.io;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;

import io.astraeus.game.world.entity.mob.combat.def.NpcCombatDefinition;
import io.astraeus.util.GsonObjectParser;

public final class NpcCombatDefinitionParser extends GsonObjectParser<NpcCombatDefinition> {

  public NpcCombatDefinitionParser() {
    super("./data/npc/npc_combat_definitions");
  }

  @Override
  public NpcCombatDefinition[] deserialize(Gson gson, FileReader reader) throws IOException {
    return gson.fromJson(reader, NpcCombatDefinition[].class);
  }

  @Override
  public void onRead(NpcCombatDefinition[] array) throws IOException {
    Arrays.stream(array).forEach(it -> NpcCombatDefinition.definitions.put(it.getId(), it));
  }

}
