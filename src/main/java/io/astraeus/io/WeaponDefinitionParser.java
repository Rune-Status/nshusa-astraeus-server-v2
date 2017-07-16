package io.astraeus.io;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;

import io.astraeus.game.world.entity.mob.combat.def.WeaponDefinition;
import io.astraeus.util.GsonObjectParser;

public final class WeaponDefinitionParser extends GsonObjectParser<WeaponDefinition> {

  public WeaponDefinitionParser() {
    super("./data/equipment/weapon_definitions");
  }

  @Override
  public WeaponDefinition[] deserialize(Gson gson, FileReader reader) throws IOException {
    return gson.fromJson(reader, WeaponDefinition[].class);
  }

  @Override
  public void onRead(WeaponDefinition[] array) throws IOException {
    Arrays.stream(array).forEach(it -> WeaponDefinition.definitions.put(it.getId(), it));
  }

}
