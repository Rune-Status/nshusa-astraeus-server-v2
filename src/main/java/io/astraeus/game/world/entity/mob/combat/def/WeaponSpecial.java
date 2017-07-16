package io.astraeus.game.world.entity.mob.combat.def;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import io.astraeus.game.world.entity.mob.combat.CombatType;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.packet.out.SetWidgetVisibilityPacket;
import io.astraeus.net.packet.out.UpdateSpecialAmountPacket;

/**
 * The enumerated type whose elements represent the combat special attacks.
 *
 * @author lare96 <http://github.com/lare96>
 */
public final class WeaponSpecial {

  public static final Set<WeaponSpecial> definitions = new HashSet<>();

  private SpecialType type = SpecialType.DRAGON_DAGGER;

  /**
   * The identifiers for the weapons that perform this special.
   */
  private final int[] ids;

  /**
   * The amount of special energy drained by this attack.
   */
  private final int amount;

  /**
   * The strength bonus added when performing this special attack.
   */
  private final double strength;

  /**
   * The accuracy bonus added when performing this special attack.
   */
  private final double accuracy;

  /**
   * The combat type used when performing this special attack.
   */
  private final CombatType combat;

  /**
   * The weapon type used when performing this special attack.
   */
  private final WeaponType weapon;

  /**
   * Creates a new {@link WeaponSpecial}.
   *
   * @param ids the identifiers for the weapons that perform this special.
   * @param amount the amount of special energy drained by this attack.
   * @param strength the strength bonus added when performing this special attack.
   * @param accuracy the accuracy bonus added when performing this special attack.
   * @param combat the combat type used when performing this special attack.
   * @param weapon the weapon type used when performing this special attack.
   */
  public WeaponSpecial(SpecialType type, int[] ids, int amount, double strength, double accuracy,
      CombatType combat, WeaponType weapon) {
    this.type = type;
    this.ids = ids;
    this.amount = amount;
    this.strength = strength;
    this.accuracy = accuracy;
    this.combat = combat;
    this.weapon = weapon;
  }

  public static Optional<WeaponSpecial> lookup(int id) {
    for (WeaponSpecial special : definitions) {
      for (int value : special.getIds()) {
        if (id == special.getIds()[value]) {
          return Optional.of(special);
        }
      }
    }
    return Optional.empty();
  }

  public static void update(Player player) {
    WeaponType type = player.getWeaponType();

    if (type == null) {
      player.queuePacket(new SetWidgetVisibilityPacket(WeaponType.WHIP.getSpecialBar(), true));
      return;
    }

    if (type.getSpecialBar() == -1 || type.getSpecialMeter() == -1) {
      return;
    }

    // display special bar
    player.queuePacket(new SetWidgetVisibilityPacket(type.getSpecialBar(), false));

    int specialCheck = 10;
    int specialBar = type.getSpecialMeter();
    int specialAmount = player.getSpecialAmount();

    // display special bar amount
    for (int i = 0; i < 10; i++) {
      player.queuePacket(
          new UpdateSpecialAmountPacket(--specialBar, specialAmount >= specialCheck ? 500 : 0));
      specialCheck--;
    }

  }

  public SpecialType getSpecialType() {
    return type;
  }

  /**
   * Gets the identifiers for the weapons that perform this special.
   *
   * @return the identifiers for the weapons.
   */
  public final int[] getIds() {
    return ids;
  }

  /**
   * Gets the amount of special energy drained by this attack.
   *
   * @return the amount of special energy drained.
   */
  public final int getAmount() {
    return amount;
  }

  /**
   * Gets the strength bonus added when performing this special attack.
   *
   * @return the strength bonus.
   */
  public final double getStrength() {
    return strength;
  }

  /**
   * Gets the accuracy bonus added when performing this special attack.
   *
   * @return the accuracy bonus.
   */
  public final double getAccuracy() {
    return accuracy;
  }

  /**
   * Gets the combat type used when performing this special attack.
   *
   * @return the combat type.
   */
  public final CombatType getCombat() {
    return combat;
  }

  /**
   * Gets the weapon type used when performing this special attack.
   *
   * @return the weapon type.
   */
  public final WeaponType getWeapon() {
    return weapon;
  }

}
