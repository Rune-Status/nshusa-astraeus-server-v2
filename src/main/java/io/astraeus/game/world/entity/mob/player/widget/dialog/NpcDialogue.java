package io.astraeus.game.world.entity.mob.player.widget.dialog;

import lombok.Data;

/**
 * The {@link Chainable} implementation that represents dialogue in which an NPC is talking.
 *
 * @author Vult-R
 */
@Data
public final class NpcDialogue implements Chainable {

  /**
   * The id of this npc.
   */
  private int id = -1;

  /**
   * The expression of this NPC.
   */
  private final Expression expression;

  /**
   * The text for this dialogue.
   */
  private final String[] lines;

  /**
   * Creates a new {@link NpcDialogue}
   *
   * @param lines The text for this dialogue.
   */
  public NpcDialogue(String... lines) {
    this(Expression.DEFAULT, lines);
  }

  /**
   * Creates a new {@link NpcDialogue}
   *
   * @param expression The expression of this npc.
   *
   * @param lines The text for this dialogue.
   */
  public NpcDialogue(Expression expression, String... lines) {
    this.expression = expression;
    this.lines = lines;
  }

  /**
   * Creates a new {@link NpcDialogue}
   *
   * @param id The id of this npc.
   *
   * @param lines The text for this dialogue.
   */
  public NpcDialogue(int id, String... lines) {
    this(id, Expression.DEFAULT, lines);
  }

  /**
   * Creates a new {@link NpcDialogue}
   *
   * @param id The id of this npc.
   *
   * @param expression The expression of this npc.
   *
   * @param lines The text for this dialogue.
   */
  public NpcDialogue(int id, Expression expression, String... lines) {
    this.id = id;
    this.expression = expression;
    this.lines = lines;
  }

  @Override
  public void accept(DialogueFactory factory) {
    factory.sendNpcChat(this);
  }
}

