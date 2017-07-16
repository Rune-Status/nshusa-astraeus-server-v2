package io.astraeus.io;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;

import io.astraeus.game.world.entity.object.GameObject;
import io.astraeus.game.world.entity.object.GameObjectType;
import io.astraeus.game.world.entity.object.GameObjects;
import io.astraeus.game.world.entity.object.impl.GlobalObject;
import io.astraeus.util.GsonObjectParser;

public final class GlobalObjectParser extends GsonObjectParser<GlobalObject> {

  public GlobalObjectParser() {
    super("./data/object/global_objects");
  }

  @Override
  public GlobalObject[] deserialize(Gson gson, FileReader reader) throws IOException {
    return gson.fromJson(reader, GlobalObject[].class);
  }

  @Override
  public void onRead(GlobalObject[] array) throws IOException {
    Arrays.stream(array)
        .forEach($it -> GameObjects.getGlobalObjects().add(new GameObject($it.getId(),
            GameObjectType.lookup($it.getType()).get(), $it.getLocation(), $it.getOrientation())));
  }

}
