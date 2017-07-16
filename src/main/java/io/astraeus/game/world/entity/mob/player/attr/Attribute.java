package io.astraeus.game.world.entity.mob.player.attr;

import java.util.Objects;

import lombok.Getter;

/**
 * Represents a single attribute.
 *
 * @author Ryley Kimmel <ryley.kimmel@live.com>
 *
 * @param <T> The attributes values type reference.
 */
final class Attribute<T> {

  /**
   * This attributes key. An attributes key is an identifier that encapsulates this attributes name.
   * This key is used for representing an attribute through some collection.
   */
  @Getter
  private final AttributeKey<T> key;

  /**
   * The value of this attribute, the type of value of this attribute is as described by this
   * classes parameter.
   *
   * @see {@link T}
   */
  @Getter
  private final T value;

  /**
   * Constructs a new {@link Attribute} with the specified key and value.
   *
   * @param key This attributes key, may not be {@code null}.
   * @param value This attributes value.
   */
  protected Attribute(AttributeKey<T> key, T value) {
    this.key = Objects.requireNonNull(key);
    this.value = value;
  }

}
