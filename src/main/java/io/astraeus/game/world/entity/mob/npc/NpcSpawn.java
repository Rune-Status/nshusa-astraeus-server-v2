package io.astraeus.game.world.entity.mob.npc;

import io.astraeus.game.world.Direction;
import io.astraeus.game.world.Position;
import lombok.Value;

@Value
public final class NpcSpawn {

	private final int id;

	private final Position position;
	
	private final Direction facing;

	private final int radius;
	
	public NpcSpawn(int id, Position position, int radius) {		
		this(id, position, Direction.SOUTH, radius);
	}

	public NpcSpawn(int id, Position position, Direction facing, int radius) {		
		this.id = id;
		this.position = position;
		this.facing = facing;
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "Id: " + id + " pos: " + getPosition().toString() + " facing: "
				+ facing.name();
	}

}
