package io.astraeus.game.sound;

import lombok.Getter;

/**
 * An enumeration of the volume levels in-game.
 *
 * @author Seven
 */
public enum Volume {

  SILENT(4),

  QUIET(3),

  NORMAL(2),

  HIGH(1),

  LOUD(0);

  @Getter private final int code;

  private Volume(int code) {
    this.code = code;
  }

}
