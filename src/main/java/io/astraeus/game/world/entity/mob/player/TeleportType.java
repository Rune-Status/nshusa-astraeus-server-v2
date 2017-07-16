package io.astraeus.game.world.entity.mob.player;

import io.astraeus.game.world.entity.Graphic;
import io.astraeus.game.world.entity.mob.Animation;

public enum TeleportType {
	
	NORMAL(3, Animation.create(714), Animation.create(715), Graphic.create(308, 50, Graphic.HIGH_HEIGHT)),	
	ANCIENT(5, Animation.create(6303), Animation.RESET, Graphic.create(392));

	TeleportType(int endTick, Animation startAnim, Animation endAnim, Graphic startGraphic) {		
		this.endTick = endTick;
		this.startAnim = startAnim;
		this.endAnim = endAnim;
		this.startGraphic = startGraphic;
	}
	
	private Animation startAnim, endAnim;
	private Graphic startGraphic, endGraphic;
	private int endTick;	

	public Animation getStartAnimation() {
		return startAnim;
	}

	public Animation getEndAnimation() {
		return endAnim;
	}

	public Graphic getStartGraphic() {
		return startGraphic;
	}

	public Graphic getEndGraphic() {
		return endGraphic;
	}

	public int getEndTick() {
		return endTick;
	}
}

