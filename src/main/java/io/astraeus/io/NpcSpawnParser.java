package io.astraeus.io;

import java.util.Objects;

import com.google.gson.JsonObject;

import io.astraeus.game.world.Direction;
import io.astraeus.game.world.Position;
import io.astraeus.game.world.entity.mob.npc.NpcSpawn;
import io.astraeus.game.world.entity.mob.npc.Npcs;
import io.astraeus.util.GsonParser;
import lombok.val;

public final class NpcSpawnParser extends GsonParser {

	public NpcSpawnParser() {
		super("./data/npc/npc_spawns");
	}

	@Override
	protected void parse(JsonObject data) {

		val id = data.get("id").getAsInt();
		val position = Objects.requireNonNull(builder.fromJson(data.get("position").getAsJsonObject(), Position.class));	
		val facing = Direction.valueOf(data.get("facing").getAsString());
		val radius = data.get("radius").getAsInt();
		
		val spawn = new NpcSpawn(id, position, facing, radius);
		
		Npcs.createSpawn(spawn);
		
//		System.out.println(id);
		
		
	}

}
