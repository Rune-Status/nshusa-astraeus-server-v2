package plugin.buttons;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import io.astraeus.game.event.SubscribesTo;
import io.astraeus.game.event.impl.ButtonActionEvent;
import io.astraeus.game.world.entity.Graphic;
import io.astraeus.game.world.entity.item.Item;
import io.astraeus.game.world.entity.mob.Animation;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.collect.Equipment;
import io.astraeus.game.world.entity.mob.player.skill.Skill;
import io.astraeus.net.packet.out.ServerMessagePacket;
import io.astraeus.util.StringUtils;
import lombok.Getter;

@SubscribesTo(ButtonActionEvent.class)
public final class EmoteTab extends ButtonClick {

  @Override
  protected void execute(Player player, ButtonActionEvent event) {
    if (event.getButton() == 19052) {

      Item item = player.getEquipment().get(Equipment.CAPE_SLOT);

      if (item == null) {
        player.queuePacket(new ServerMessagePacket("You are not wearing any cape at all!"));
        return;
      }

      Optional<Skillcape> skillcape = Skillcape.lookup(item.getId());      

      if (!skillcape.isPresent()) {
        player.queuePacket(new ServerMessagePacket("You need a skillcape to do this!"));
        return;
      }
      
      skillcape.ifPresent(it -> {
       
        if (player.getSkills().getLevel(skillcape.get().getSkillId()) < 99) {
          player.queuePacket(new ServerMessagePacket("You need " + StringUtils.getAOrAn(player.getSkills().getSkill(skillcape.get().getSkillId()).getName()) + " " + player.getSkills().getSkill(skillcape.get().getSkillId()).getName() + " level of 99 to use this."));
          return;
        }
        
        player.startAnimation(Animation.create(it.getAnimationId()), 3000);
        player.startGraphic(Graphic.create(it.getGfxId()), 3000);
        return;
      });
      return;
    }
    
    Optional<Emote> emote = Emote.get(event.getButton());
    
    emote.ifPresent(it -> {
      player.startAnimation(Animation.create(it.getAnimationId()), 2000);
      player.startGraphic(Graphic.create(it.getGfxId()), 2000);
    });

  }

  @Override
  public boolean test(ButtonActionEvent event) {
    return Emote.isEmote(event.getButton());    
  }

  public static enum Emote {
    YES(168, 855, -1),
    NO(169, 856, -1),
    BOW(164, 858, -1),
    ANGRY(167, 864, -1),
    THINK(162, 857, -1),
    WAVE(163, 863, -1),
    SHRUG(52058, 2113, -1),
    CHEER(171, 862, -1),
    BECKON(165, 859, -1),
    LAUGH(170, 861, -1),
    JUMP_FOR_JOY(13366, 2109, -1),
    YAWN(13368, 2111, -1),
    DANCE(166, 866, -1),
    JIG(13363, 2106, -1),
    TWIRL(13364, 2107, -1),
    HEADBANG(13365, 2108, -1),
    CRY(161, 860, -1),
    BLOW_KISS(11100, 0x558, 574),
    PANIC(13362, 2105, -1),
    RASBERRY(13367, 2110, -1),
    CLAP(172, 865, -1),
    SALUTE(13369, 2112, -1),
    GOBLIN_BOW(13383, 0x84F, -1),
    GOBLIN_SALUTE(13384, 0x850, -1),
    GLASS_BOX(667, 0x46B, -1),
    CLIMB_ROPE(6503, 0x46A, -1),
    LEAN(6506, 0x469, -1),
    GLASS_WALL(666, 0x468, -1),
    IDEA(22588, 4276, 712),
    STOMP(22589, 4278, -1),
    FLAP(22590, 4280, -1),
    SLAP_HEAD(22591, 4275, -1),
    ZOMBIE_WALK(18464, 3544, -1),
    ZOMBIE_DANCE(18465, 3543, -1),
    ZOMBIE_HAND(22593, -1, -1),
    SCARED(15166, 2836, -1),
    BUNNY_HOP(18686, 6111, -1),
    SKILLCAPE(19052, 1, 1);

    public static Map<Integer, Emote> emotes = new HashMap<Integer, Emote>();

    static {
      for (final Emote e : Emote.values()) {
        emotes.put(e.buttonId, e);
      }
    }

    @Getter
    private final int gfxId;

    @Getter
    private final int animationId;

    @Getter
    private final int buttonId;

    private Emote(int buttonId, int animationId, int gfxId) {
      this.buttonId = buttonId;
      this.animationId = animationId;
      this.gfxId = gfxId;
    }

    public static Optional<Emote> get(int buttonId) {
      return Optional.ofNullable(emotes.get(buttonId));
    }

    public static boolean isEmote(int buttonId) {
      return Arrays.stream(Emote.values()).anyMatch(it -> it.getButtonId() == buttonId);
    }

  }

  /**
   * Data of all the skillcape emotes.
   * 
   * @param itemId the id of the skillcape.
   * @param gfxId the gfxId of the skillcape.
   * @param animationId the animationId of the skillcape.
   */
  private enum Skillcape {
    ATTACK_CAPE(new int[] {9747, 9748}, 823, 4959, Skill.ATTACK),
    STRENGTH_CAPE(new int[] {9750, 9751}, 828, 4981, Skill.STRENGTH),
    DEFENCE_CAPE(new int[] {9753, 9754}, 824, 4961, Skill.DEFENCE),
    RANGING_CAPE(new int[] {9756, 9757}, 832, 4973, Skill.RANGE),
    PRAYER_CAPE(new int[] {9759, 9760}, 829, 4979, Skill.PRAYER),
    MAGIC_CAPE(new int[] {9762, 9763}, 813, 4939, Skill.MAGIC),
    RUNECRAFT_CAPE(new int[] {9765, 9766}, 817, 4947, Skill.RUNECRAFTING),
    HITPOINTS_CAPE(new int[] {9768, 9769}, 833, 4971, Skill.HITPOINTS),
    AGILITY_CAPE(new int[] {9771, 9772}, 830, 4977, Skill.AGILITY),
    HERBLORE_CAPE(new int[] {9774, 9775}, 835, 4969, Skill.HERBLORE),
    THEIVING_CAPE(new int[] {9777, 9778}, 826, 4965, Skill.THIEVING),
    CRAFTING_CAPE(new int[] {9780, 9781}, 818, 4949, Skill.CRAFTING),
    FLETCHING_CAPE(new int[] {9783, 9784}, 812, 4937, Skill.FLETCHING),
    SLAYER_CAPE(new int[] {9786, 9787}, 827, 4967, Skill.SLAYER),
    CONSTRUCTION_CAPE(new int[] {9789, 9790}, 820, 4953, Skill.CONSTRUCTION),
    MINING_CAPE(new int[] {9792, 9793}, 814, 4941, Skill.MINING),
    SMITHING_CAPE(new int[] {9795, 9796}, 815, 4943, Skill.SMITHING),
    FISHING_CAPE(new int[] {9798, 9799}, 819, 4951, Skill.FISHING),
    COOKING_CAPE(new int[] {9801, 9802}, 821, 4955, Skill.COOKING),
    FIREMAKING_CAPE(new int[] {9804, 9805}, 831, 4975, Skill.FIREMAKING),
    WOODCUTTING_CAPE(new int[] {9807, 9808}, 822, 4957, Skill.WOODCUTTING),
    FARMING_CAPE(new int[] {9810, 9811}, 825, 4963, Skill.FARMING),
    HUNTER_CAPE(new int[] {9948, 9949}, 907, 5158, Skill.HUNTER),
    QUEST_POINT_CAPE(new int[] {9813, 9814}, 816, 4945, -1),
    ACHIEVEMENT_CAPE(new int[] {13069}, 323, 2709, -1);

    @Getter
    private final int[] itemIds;
    @Getter
    private final int gfxId;
    @Getter
    private final int animationId;
    @Getter
    private final int skillId;

    private Skillcape(int[] itemId, int gfxId, int animationId, int skillId) {
      this.itemIds = itemId;
      this.gfxId = gfxId;
      this.animationId = animationId;
      this.skillId = skillId;
    }

    public static Optional<Skillcape> lookup(int id) {
      for (Skillcape data : Skillcape.values()) {
        for (int index = 0; index < data.getItemIds().length; index++) {
          if (data.getItemIds()[index] == id) {
            return Optional.of(data);
          }
        }
      }
      return Optional.empty();
    }

  }

}
