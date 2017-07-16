package io.astraeus.game.world.entity.mob.player.collect;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import io.astraeus.game.world.entity.item.Item;
import io.astraeus.game.world.entity.item.ItemContainer;
import io.astraeus.game.world.entity.item.ItemContainerPolicy;
import io.astraeus.game.world.entity.mob.MobAnimation;
import io.astraeus.game.world.entity.mob.combat.def.AttackType;
import io.astraeus.game.world.entity.mob.combat.def.WeaponDefinition;
import io.astraeus.game.world.entity.mob.combat.def.WeaponSpecial;
import io.astraeus.game.world.entity.mob.combat.def.WeaponType;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.skill.Skill;
import io.astraeus.game.world.entity.mob.player.skill.SkillRequirement;
import io.astraeus.game.world.entity.mob.update.UpdateFlag;
import io.astraeus.net.packet.out.ServerMessagePacket;
import io.astraeus.net.packet.out.SetItemModelOnWidgetPacket;
import io.astraeus.net.packet.out.SetSideBarWidgetPacket;
import io.astraeus.net.packet.out.SetWidgetConfigPacket;
import io.astraeus.net.packet.out.SetWidgetStringPacket;
import io.astraeus.util.StringUtils;
import lombok.Data;
import lombok.Getter;

/**
 * Represents a {@link Player}s equipment.
 *
 * @author Vult-R
 */
public final class Equipment extends ItemContainer {

  /**
   * Represents the enumerated types of slots that a player can equip items to.
   *
   * @author Vult-R
   */
  public static enum EquipmentType {
    NONE(-1),
    HAT(0),
    CAPE(1),
    SHIELD(5),
    GLOVES(9),
    BOOTS(10),
    AMULET(2),
    RING(12),
    ARROWS(13),
    BODY(4),
    LEGS(7),
    WEAPON(3);

    @Getter
    private final int slot;

    private EquipmentType(final int slot) {
      this.slot = slot;
    }

    public static Optional<EquipmentType> lookup(int value) {     
      return Arrays.stream(EquipmentType.values()).filter(it -> it.getSlot() == value).findFirst();
    }

  }

  /**
   * Represents an in-game equipped item.
   * 
   * @author Vult-R
   */
  @Data
  public static final class EquipmentDefinition {

    public static final Map<Integer, EquipmentDefinition> definitions = new HashMap<>();

    public static EquipmentDefinition get(int id) {
      return definitions.get(id);
    }

    private final int id;

    private final String name;

    private final EquipmentType type;

    private final SkillRequirement[] requirements;

    private final boolean fullBody;

    private final boolean fullHat;

    private final boolean fullMask;

    private final int[] bonuses;

    public EquipmentDefinition(int id, String name, EquipmentType type,
        SkillRequirement[] requirements, boolean fullBody, boolean fullHat, boolean fullMask,
        int[] bonuses) {
      this.id = id;
      this.name = name;
      this.type = type;
      this.requirements = requirements;
      this.fullBody = fullBody;
      this.fullHat = fullHat;
      this.fullMask = fullMask;
      this.bonuses = bonuses;
    }

    public static Map<Integer, EquipmentDefinition> getDefinitions() {
      return definitions;
    }

  }

  public static final int HEAD_SLOT = 0;
  public static final int CAPE_SLOT = 1;
  public static final int NECKLACE_SLOT = 2;
  public static final int WEAPON_SLOT = 3;
  public static final int CHEST_SLOT = 4;
  public static final int SHIELD_SLOT = 5;
  public static final int LEGS_SLOT = 7;
  public static final int HANDS_SLOT = 9;
  public static final int FEET_SLOT = 10;
  public static final int RING_SLOT = 12;
  public static final int AMMO_SLOT = 13;

  public static final int STAB = 0;
  public static final int SLASH = 1;
  public static final int CRUSH = 2;
  public static final int MAGIC = 3;
  public static final int RANGED = 4;
  public static final int STAB_DEFENSE = 5;
  public static final int SLASH_DEFENSE = 6;
  public static final int CRUSH_DEFENSE = 7;
  public static final int MAGIC_DEFENSE = 8;
  public static final int RANGED_DEFENSE = 9;
  public static final int STRENGTH = 10;
  public static final int RANGED_STRENGTH = 11;
  public static final int MAGIC_STRENGTH = 12;
  public static final int PRAYER = 13;
  
  /** Item bonus names. */
  public static final String[] BONUS_NAMES =
      { /* 0 */ "Stab", /* 1 */ "Slash", /* 2 */ "Crush", /* 3 */ "Magic", /* 4 */ "Range",
          /* - */
          /* 5 */ "Stab", /* 6 */ "Slash", /* 7 */ "Crush", /* 8 */ "Magic", /* 9 */ "Range",
          /* - */
          /* 10 */ "Strength", /* 11 */ "Ranged Strength", /* 12 */ "Magic Strength",
          /* 13 */ "Prayer"};

  /**
   * The player that this container belongs to.
   */
  private final Player player;

  /**
   * Creates a new {@link Equipment} container.
   *
   * @param player The player that owns this container.
   */
  public Equipment(Player player) {
    super(14, ItemContainerPolicy.NORMAL);
    this.player = player;
  }

  /**
   * Equips the item in {@code inventorySlot} to the equipment container.
   *
   * @param inventorySlot the slot to equip the item on.
   * @return {@code true} if the item was equipped, {@code false} otherwise.
   */
  public boolean equip(int inventorySlot) {
    Item item = player.getInventory().get(inventorySlot);

    if (!Item.valid(item)) {
      return false;
    }

    EquipmentDefinition equipDef = EquipmentDefinition.get(item.getId());

    if (equipDef == null) {
      return false;
    }

    WeaponDefinition weaponDef = WeaponDefinition.definitions.get(item.getId());

    boolean twoHanded = weaponDef == null ? false : weaponDef.isTwoHanded();

    if (!Equipment.canEquip(player, item.getId())) {
      return false;
    }

    if (item.definition().isStackable()) {
      int designatedSlot = equipDef.getType().getSlot();
      Item equipItem = get(designatedSlot);
      if (used(designatedSlot)) {
        if (item.getId() == equipItem.getId()) {
          set(designatedSlot, new Item(item.getId(), item.getAmount() + equipItem.getAmount()));
        } else {
          player.getInventory().set(inventorySlot, equipItem);
          player.getInventory().refresh();
          set(designatedSlot, item);
        }
      } else {
        set(designatedSlot, item);
      }
      player.getInventory().remove(item, inventorySlot);
    } else {
      int designatedSlot = equipDef.getType().getSlot();
      if (designatedSlot == Equipment.WEAPON_SLOT && twoHanded && used(Equipment.SHIELD_SLOT)) {
        if (!unequip(Equipment.SHIELD_SLOT, true))
          return false;
      }
      if (designatedSlot == Equipment.SHIELD_SLOT && used(Equipment.WEAPON_SLOT)) {

        WeaponDefinition wepDef =
            WeaponDefinition.definitions.get(get(Equipment.WEAPON_SLOT).getId());

        twoHanded = wepDef == null ? false : wepDef.isTwoHanded();

        if (twoHanded) {
          if (!unequip(Equipment.WEAPON_SLOT, true))
            return false;
        }

      }
      if (used(designatedSlot)) {
        Item equipItem = get(designatedSlot);
        if (!equipItem.definition().isStackable()) {
          player.getInventory().set(inventorySlot, equipItem);
        } else {
          player.getInventory().set(inventorySlot, null);
          player.getInventory().add(equipItem, inventorySlot);
        }
        player.getInventory().refresh();
      } else {
        player.getInventory().remove(item, inventorySlot);
      }
      set(designatedSlot, new Item(item.getId(), item.getAmount()));
    }
    refresh();
    return true;
  }

  /**
   * Unequips the item in {@code equipmentSlot} from the equipment container.
   *
   * @param equipmentSlot the slot to unequip the item on.
   * @param addItem if the unequipped item should be added to the inventory.
   * @return {@code true} if the item was unequipped, {@code false} otherwise.
   */
  public boolean unequip(int equipmentSlot, boolean addItem) {
    if (free(equipmentSlot))
      return false;

    Item item = get(equipmentSlot);

    if (!player.getInventory().spaceFor(item)) {
      player.queuePacket(
          new ServerMessagePacket("You do not have enough space in " + "your inventory!"));
      return false;
    }

    remove(item, equipmentSlot);

    if (addItem) {
      player.getInventory().add(new Item(item.getId(), item.getAmount()));
    }

    refresh();
    player.getInventory().refresh();
    return true;
  }

  public static boolean canEquip(Player player, int id) {
    final EquipmentDefinition req = EquipmentDefinition.definitions.get(id);

    if (req != null) {
      for (final SkillRequirement r : req.getRequirements()) {

        if (r == null) {
          continue;
        }

        if (r.getSkill().getId() == Skill.PRAYER || r.getSkill().getId() == Skill.HITPOINTS) {
          if (player.getSkills().getMaxLevel(r.getSkill().getId()) < r.getLevel()) {
            player.queuePacket(new ServerMessagePacket("You need "
                + StringUtils.getAOrAn(r.getSkill().getName()) + " " + r.getSkill().getName()
                + " level of " + r.getLevel() + " to equip this item."));
            return false;
          }
        } else {
          if (player.getSkills().getLevel(r.getSkill().getId()) < r.getLevel()) {
            player.queuePacket(new ServerMessagePacket("You need "
                + StringUtils.getAOrAn(r.getSkill().getName()) + " " + r.getSkill().getName()
                + " level of " + r.getLevel() + " to equip this item."));
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Refreshes the contents of this equipment container to the interface.
   */
  public void refresh() {
    refresh(player, 1688);
    updateWeapon();
    player.getUpdateFlags().add(UpdateFlag.APPEARANCE);
  }

  public void updateWeapon() {

    final Item item = get(Equipment.WEAPON_SLOT);

    if (item == null) {
      player.queuePacket(new SetSideBarWidgetPacket(0, WeaponType.UNARMED.getId()));
      player.queuePacket(new SetWidgetStringPacket("Unarmed", WeaponType.UNARMED.getNameLine()));
      player.setWeaponType(WeaponType.UNARMED);
      WeaponSpecial.update(player);
      for (AttackType type : player.getWeaponType().getAttackTypes()) {
        if (type.getStyle() == player.getAttackType().getStyle()) {
          player.setAttackType(type);
          player.queuePacket(new SetWidgetConfigPacket(player.getAttackType().getParent(),
              player.getAttackType().getChild()));
          return;
        }
      }
      return;
    }

    WeaponDefinition weapon = WeaponDefinition.definitions.get(item.getId());

    if (weapon == null) {
      player.getMobAnimation().setRun(MobAnimation.PLAYER_RUN);
      player.getMobAnimation().setWalk(MobAnimation.PLAYER_WALK);
      player.getMobAnimation().setStand(MobAnimation.PLAYER_STAND);
      return;
    }

    if (weapon.getType() == WeaponType.UNARMED) {
      player.queuePacket(new SetSideBarWidgetPacket(0, weapon.getId()));
      player.queuePacket(new SetWidgetStringPacket("Unarmed", weapon.getType().getNameLine()));
      player.setWeaponType(WeaponType.UNARMED);
      player.getMobAnimation().setRun(MobAnimation.PLAYER_RUN);
      player.getMobAnimation().setWalk(MobAnimation.PLAYER_WALK);
      player.getMobAnimation().setStand(MobAnimation.PLAYER_STAND);
      return;
    } else if (weapon.getType() == WeaponType.CROSSBOW) {
      player.queuePacket(new SetWidgetStringPacket("Weapon: ", weapon.getType().getNameLine() - 1));
    } else if (weapon.getType() == WeaponType.WHIP) {
      player.queuePacket(new SetWidgetStringPacket("Weapon: ", weapon.getType().getNameLine() - 1));
    }
    player.queuePacket(
        new SetItemModelOnWidgetPacket(weapon.getType().getId() + 1, 200, item.getId()));
    player.queuePacket(new SetSideBarWidgetPacket(0, weapon.getType().getId()));
    player.queuePacket(
        new SetWidgetStringPacket(item.definition().getName(), weapon.getType().getNameLine()));
    player.setWeaponType(weapon.getType());
    WeaponSpecial.update(player);

    player.getMobAnimation().setRun(weapon.getCombatAnimation().getRun());
    player.getMobAnimation().setWalk(weapon.getCombatAnimation().getWalk());
    player.getMobAnimation().setStand(weapon.getCombatAnimation().getStand());

    for (AttackType type : weapon.getType().getAttackTypes()) {
      if (type.getStyle() == player.getAttackType().getStyle()) {
        player.setAttackType(type);
        player.queuePacket(new SetWidgetConfigPacket(player.getAttackType().getParent(),
            player.getAttackType().getChild()));
        return;
      }
    }

    player.setAttackType(player.getWeaponType().getAttackTypes()[0]);
    player.queuePacket(new SetWidgetConfigPacket(player.getAttackType().getParent(),
        player.getAttackType().getChild()));
  }

  public static boolean isFullHat(int itemId) {
    return EquipmentDefinition.get(itemId) != null ? EquipmentDefinition.get(itemId).isFullHat()
        : false;
  }

  public static boolean isFullMask(int itemId) {
    return EquipmentDefinition.get(itemId) != null ? EquipmentDefinition.get(itemId).isFullMask()
        : false;
  }

  public static boolean isFullBody(int itemId) {
    return EquipmentDefinition.get(itemId) != null ? EquipmentDefinition.get(itemId).isFullBody()
        : false;
  }

  public boolean hasShield() {
    return this.get(Equipment.SHIELD_SLOT) != null;
  }

  public Item getWeapon() {
    return get(Equipment.WEAPON_SLOT);
  }

  public static boolean isWearingFullVoidMage(Player player) {
    return player.getEquipment().containsAll(new Item(11663), new Item(8839), new Item(8840),
        new Item(8842));
  }

  public static boolean isWearingFullVoidMelee(Player player) {
    return player.getEquipment().containsAll(new Item(11665), new Item(8839), new Item(8840),
        new Item(8842));
  }

  public static boolean isWearingFullVoidRange(Player player) {
    return player.getEquipment().containsAll(new Item(11664), new Item(8839), new Item(8840),
        new Item(8842));
  }

  public static boolean isWearingDharoks(Player player) {
    final boolean weapon = player.getEquipment().containsAny(4718, 4886, 4887, 4888, 4889);
    final boolean helm = player.getEquipment().containsAny(4716, 4880, 4881, 4882, 4883);
    final boolean torso = player.getEquipment().containsAny(4720, 4892, 4893, 4894, 4895);
    final boolean legs = player.getEquipment().containsAny(4722, 4898, 4899, 4900, 4901);
    return weapon && helm && torso && legs;
  }

  public static boolean isWearingGuthans(Player player) {
    final boolean weapon = player.getEquipment().containsAny(4726, 4910, 4911, 4912, 4913);
    final boolean helm = player.getEquipment().containsAny(4724, 4904, 4905, 4906, 4907);
    final boolean chest = player.getEquipment().containsAny(4728, 4916, 4917, 4918, 4919);
    final boolean legs = player.getEquipment().containsAny(4730, 4922, 4923, 4924, 4925);
    return weapon && helm && chest && legs;
  }

  public static boolean isWearingVeracs(Player player) {
    final boolean weapon = player.getEquipment().containsAny(4755, 4982, 4983, 4984, 4985);
    final boolean helm = player.getEquipment().containsAny(4753, 4976, 4977, 4978, 4979);
    final boolean chest = player.getEquipment().containsAny(4757, 4988, 4989, 4990, 4991);
    final boolean legs = player.getEquipment().containsAny(4759, 4994, 4995, 4996, 4997);
    return weapon && helm && chest && legs;
  }

  public static boolean isWearingAntiFire(Player player) {
    return player.getEquipment().containsAny(1540, 11283, 11284);
  }

  public boolean hasWeapon() {
    return get(Equipment.WEAPON_SLOT) != null;
  }

}
