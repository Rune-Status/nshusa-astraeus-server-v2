package io.astraeus.game.world.entity.mob;

import lombok.Value;

/**
 * Class that models a single animation used by an entity.
 * 
 * @author Vult-R
 */
@Value
public final class Animation {
	
	public static final Animation RESET = Animation.create(65535);

  /**
   * The animation id to play
   */
  private final int id;

  /**
   * The delay before playing the animation
   */
  private final int delay;

  /**
   * Creates a new instance of the animation with a specified delay.
   * 
   * @param priority The priority level of the animation.
   * @param id The id of the animation being used.
   * @param delay The delay of the animation in seconds.
   */
  private Animation(int id, int delay) {
    this.id = id;
    this.delay = delay;
  }
  
  public static Animation create(int id, int delay) {
	  return new Animation(id, delay);
  }
  
  public static Animation create(int id) {
	  return new Animation(id, 0);
  }
  
  @Override
  public String toString() {
    return String.format("ANIMATION[id=%s, delay=%s]", id, delay);
  }

}
