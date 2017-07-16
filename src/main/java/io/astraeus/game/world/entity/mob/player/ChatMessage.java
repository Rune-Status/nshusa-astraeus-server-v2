package io.astraeus.game.world.entity.mob.player;

import lombok.Data;

/**
 * The class that models a players chat message.
 * 
 * @author Seven
 */
@Data
public final class ChatMessage {

  /**
   * The color of the text.
   */
  private final int color;

  /**
   * The client-sided effects.
   */
  private final int effect;

  /**
   * The array of characters.
   */
  private final byte[] text;

  /**
   * A default constructor for new players.
   */
  public ChatMessage() {
    this(0, 0, null);
  }

  /**
   * Creates a new {@link ChatMessage).
   * 
   * @param color The color of this chat message.
   * 
   * @param effect The client-sided effect of this message.
   * 
   * @param text The text in data form.
   */
  public ChatMessage(int color, int effect, byte[] text) {
    this.color = color;
    this.effect = effect;
    this.text = text;
  }

}
