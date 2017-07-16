package io.astraeus.game.event.impl;

import io.astraeus.game.event.Event;
import lombok.Data;

@Data
public class MagicOnObjectEvent implements Event {
	
	private int spellId, objectId, objectX, objectY;
	
	public MagicOnObjectEvent(int spellId, int objectId, int objectX, int objectY) {
		this.spellId = spellId;
		this.objectId = objectId;
		this.objectX = objectX;
		this.objectY = objectY;
	}

}
