package io.astraeus.game.sound;

import lombok.Data;

/**
 * Represents a single song that can be played in-game.
 * 
 * @author Seven
 */
@Data
public final class Song {

  /**
   * The id of this song.
   */
  private final int id;

  /**
   * The name of this song.
   */
  private final String name;

  /**
   * The regions this song is found in.
   */
  private final int[] region;

  /**
   * Creates a new {@link Song}.
   * 
   * @param id The id of this song.
   * 
   * @param name The name of this song.
   * 
   * @param region The regions this song is played in.
   */
  public Song(int id, String name, int[] region) {
    this.id = id;
    this.name = name;
    this.region = region;
  }

}
