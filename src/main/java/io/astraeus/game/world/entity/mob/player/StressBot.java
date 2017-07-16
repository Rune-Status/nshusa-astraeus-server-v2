package io.astraeus.game.world.entity.mob.player;

import java.util.ArrayList;
import java.util.List;

import io.astraeus.game.world.Position;
import io.astraeus.game.world.World;
import io.astraeus.game.world.entity.item.Item;
import io.astraeus.game.world.entity.mob.player.collect.Equipment;
import io.astraeus.game.world.entity.mob.player.collect.Equipment.EquipmentDefinition;
import io.astraeus.game.world.entity.mob.player.collect.Equipment.EquipmentType;
import io.astraeus.game.world.entity.mob.update.UpdateFlag;
import io.astraeus.util.RandomUtils;

/**
 * A non-player controlled bot.
 * 
 * @author Seven
 */
public final class StressBot extends Player {

  /**
   * Creates a new {@link StressBot}.
   * 
   * @param username The name of this bot.
   * 
   * @param location The location to spawn this bot.
   * 
   * @param bot The flag that denotes this is a bot.
   */
  public StressBot(String username, Position location) {
    super(username, location);
  }

  /**
   * Creates this bot.
   */
  public void create() {

    register();

    getSkills().setMaxLevel(0, RandomUtils.random(1, 99));
    getSkills().setMaxLevel(1, RandomUtils.random(1, 99));
    getSkills().setMaxLevel(2, RandomUtils.random(1, 99));
    getSkills().setMaxLevel(3, RandomUtils.random(10, 99));
    getSkills().setMaxLevel(4, RandomUtils.random(1, 99));
    getSkills().setMaxLevel(5, RandomUtils.random(1, 99));
    getSkills().setMaxLevel(6, RandomUtils.random(1, 99));

    List<Integer> helms = new ArrayList<>();
    List<Integer> capes = new ArrayList<>();
    List<Integer> weapons = new ArrayList<>();
    List<Integer> torsos = new ArrayList<>();
    List<Integer> legs = new ArrayList<>();
    List<Integer> shields = new ArrayList<>();
    List<Integer> necklaces = new ArrayList<>();
    List<Integer> gloves = new ArrayList<>();
    List<Integer> boots = new ArrayList<>();

    for (EquipmentDefinition def : EquipmentDefinition.getDefinitions().values()) {

      // helm
      if (def.getType() == EquipmentType.HAT) {
        if (Equipment.canEquip(this, def.getId())) {
          helms.add(def.getId());
        }
      } else if (def.getType() == EquipmentType.CAPE) {
        if (Equipment.canEquip(this, def.getId())) {
          capes.add(def.getId());
        }
      } else if (def.getType() == EquipmentType.AMULET) {
        if (Equipment.canEquip(this, def.getId())) {
          necklaces.add(def.getId());
        }
      } else if (def.getType() == EquipmentType.WEAPON) {
        if (Equipment.canEquip(this, def.getId())) {
          weapons.add(def.getId());
        }
      } else if (def.getType() == EquipmentType.BODY) {
        if (Equipment.canEquip(this, def.getId())) {
          torsos.add(def.getId());
        }
      } else if (def.getType() == EquipmentType.SHIELD) {
        if (Equipment.canEquip(this, def.getId())) {
          shields.add(def.getId());
        }
      } else if (def.getType() == EquipmentType.LEGS) {
        if (Equipment.canEquip(this, def.getId())) {
          legs.add(def.getId());
        }
      } else if (def.getType() == EquipmentType.GLOVES) {
        if (Equipment.canEquip(this, def.getId())) {
          gloves.add(def.getId());
        }
      } else if (def.getType() == EquipmentType.BOOTS) {
        if (Equipment.canEquip(this, def.getId())) {
          boots.add(def.getId());
        }
      }

    }

    Item helm = new Item(helms.get(RandomUtils.random(0, helms.size(), false)));
    Item torso = new Item(torsos.get(RandomUtils.random(0, torsos.size(), false)));
    Item cape = new Item(capes.get(RandomUtils.random(0, capes.size(), false)));
    Item necklace = new Item(necklaces.get(RandomUtils.random(0, necklaces.size(), false)));
    Item leg = new Item(legs.get(RandomUtils.random(0, legs.size(), false)));
    Item weapon = new Item(weapons.get(RandomUtils.random(0, weapons.size(), false)));
    Item boot = new Item(boots.get(RandomUtils.random(0, boots.size(), false)));

    getInventory().add(helm);
    getInventory().add(torso);
    getInventory().add(cape);
    getInventory().add(necklace);
    getInventory().add(leg);
    getInventory().add(weapon);
    getInventory().add(boot);

    getEquipment().add(helm, Equipment.HEAD_SLOT);
    getEquipment().add(torso, Equipment.CHEST_SLOT);
    getEquipment().add(cape, Equipment.CAPE_SLOT);
    getEquipment().add(necklace, Equipment.NECKLACE_SLOT);
    getEquipment().add(leg, Equipment.LEGS_SLOT);
    getEquipment().add(weapon, Equipment.WEAPON_SLOT);
    getEquipment().add(boot, Equipment.FEET_SLOT);

  }

  void register() {
    World.register(this);

    getUpdateFlags().add(UpdateFlag.APPEARANCE);

    setRegionChange(true);
    getPlayerRelation().updateLists(true);
    getInventory().refresh();
    getEquipment().refresh();
    getEquipment().refresh();
    getSkills().calculateLevels();

    createAttributes();

  }

}
