package io.astraeus.game.world.entity.mob.player.widget.dialog;

import lombok.Data;

/**
 * A {@link Chainable} implementation that represents a player talking.
 *
 * @author Vult-R
 */
@Data
public class PlayerDialogue implements Chainable {

  /**
   * The expression of this player.
   */
  private final Expression expression;

  /**
   * The text for this dialogue.
   */
  private final String[] lines;

  /**
   * Creates a new {@link PlayerDialogue} with a default expression of {@code DEFAULT}.
   *
   * @param lines The text for this dialogue.
   */
  public PlayerDialogue(String... lines) {
    this(Expression.DEFAULT, lines);
  }

  /**
   * Creates a new {@link PlayerDialogue}.
   *
   * @param expression The expression for this dialogue.
   *
   * @param lines The text for this dialogue.
   */
  public PlayerDialogue(Expression expression, String... lines) {
    this.expression = expression;
    this.lines = lines;
  }

  @Override
  public void accept(DialogueFactory factory) {
    factory.sendPlayerChat(this);
  }
}

