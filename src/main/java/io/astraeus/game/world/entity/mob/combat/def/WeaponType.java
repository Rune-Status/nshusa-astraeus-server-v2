package io.astraeus.game.world.entity.mob.combat.def;

import java.util.HashMap;
import java.util.Map;

public enum WeaponType {
  STAFF(328, 331), WARHAMMER(425, 428, 7474, 7486), SCYTHE(776, 779), BATTLEAXE(1698, 1701, 7499,
      7511), CROSSBOW(1749, 1752, 7524, 7536), SHORTBOW(1764, 1767, 7549, 7561), LONGBOW(1764, 1767,
          7549, 7561), DAGGER(2276, 2279, 7574, 7586), SWORD(2276, 2279, 7574, 7586), SCIMITAR(2423,
              2426, 7599,
              7611), LONGSWORD(2423, 2426, 7599, 7611), MACE(3796, 3799, 7624, 7636), KNIFE(4446,
                  4449, 7649, 7661), SPEAR(4679, 4682, 7674, 7686), TWO_HANDED_SWORD(4705, 4708,
                      7699, 7711), PICKAXE(5570, 5573), CLAWS(7762, 7765, 7800, 7812), HALBERD(8460,
                          8463, 8493, 8505), UNARMED(5855, 5857), WHIP(12290, 12293, 12323,
                              12335), THROWNAXE(4446, 4449, 7649, 7661), DART(4446, 4449, 7649,
                                  7661), JAVELIN(4446, 4449, 7649, 7661);

  public static final Map<Integer, WeaponType> definitions = new HashMap<>();

  private final int id;

  private final int nameLine;

  private final int specialBar;

  private final int specialMeter;

  private WeaponType(int id, int nameLine, int specialBar, int specialMeter) {
    this.id = id;
    this.nameLine = nameLine;
    this.specialBar = specialBar;
    this.specialMeter = specialMeter;
  }

  private WeaponType(int id, int nameLine) {
    this(id, nameLine, -1, -1);
  }

  public final int getId() {
    return id;
  }

  public final int getNameLine() {
    return nameLine;
  }

  public final AttackType[] getAttackTypes() {
    return WeaponTypeSet.definition.get(this);
  }

  public final int getSpecialBar() {
    return specialBar;
  }

  public final int getSpecialMeter() {
    return specialMeter;
  }

}
