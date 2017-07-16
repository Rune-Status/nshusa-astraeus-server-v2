package io.astraeus.game.world.entity.mob.player.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.astraeus.game.sound.Volume;
import io.astraeus.game.world.Position;
import io.astraeus.game.world.entity.mob.Movement;
import io.astraeus.game.world.entity.mob.player.Appearance;
import io.astraeus.game.world.entity.mob.player.Brightness;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.PlayerRights;
import io.astraeus.game.world.entity.mob.player.skill.Skill;
import lombok.Data;

@Data
public final class PlayerDetails {

  private static final transient Gson gson = new GsonBuilder().setPrettyPrinting().create();  

  private final String username;
  private final String password;
  private final String hostAddress;
  private final PlayerRights rights;
  private final Position location;
  private final Brightness brightness;
  private final Volume musicVolume;
  private final Volume soundEffectVolume;
  private final Volume areaSoundVolume;
  private final boolean newPlayer;
  private final boolean running;
  private final boolean autoRetaliate;
  private final boolean enableSound;
  private final boolean enableMusic;
  private final boolean debugMode;
  private final boolean mouseButtons;
  private final boolean chatEffects;
  private final boolean splitChat;
  private final boolean acceptAid;
  private final Appearance appearance;
  private final Skill[] skills;
  private final List<Long> friendList;
  private final List<Long> ignoreList;

  public PlayerDetails(Player player) {
    username = player.getUsername();
    password = player.getPassword();
    hostAddress = player.getHostAddress();
    rights = player.getRights();
    location = player.getPosition();
    brightness = player.attr().get(Player.BRIGHTNESS_KEY);
    musicVolume = player.attr().get(Player.MUSIC_VOLUME_KEY);
    soundEffectVolume = player.attr().get(Player.SOUND_EFFECT_VOLUME_KEY);
    areaSoundVolume = player.attr().get(Player.AREA_SOUND_VOLUME_KEY);
    newPlayer = player.attr().get(Player.NEW_PLAYER_KEY);
    running = player.attr().get(Movement.RUNNING_KEY);
    autoRetaliate = player.attr().get(Player.AUTO_RETALIATE_KEY);
    enableSound = player.attr().get(Player.SOUND_KEY);
    enableMusic = player.attr().get(Player.MUSIC_KEY);
    debugMode = player.attr().get(Player.DEBUG_KEY);
    mouseButtons = player.attr().get(Player.MOUSE_BUTTON_KEY);
    chatEffects = player.attr().get(Player.CHAT_EFFECTS_KEY);
    splitChat = player.attr().get(Player.SPLIT_CHAT_KEY);
    acceptAid = player.attr().get(Player.ACCEPT_AID_KEY);
    appearance = player.getAppearance();
    skills = player.getSkills().getSkills();
    friendList = player.getPlayerRelation().getFriendList();
    ignoreList = player.getPlayerRelation().getIgnoreList();
  }

  public synchronized boolean save() throws IOException {
    final File dir = new File("./data/characters/details/");

    if (!dir.exists()) {
      dir.mkdirs();
    }
    
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(dir.toString() + File.separator + username + ".json", false))) {
      writer.write(gson.toJson(this));
      return true;
    }

  }
  
  public static synchronized boolean load(Player player) throws IOException {
    final File file = new File("./data/characters/details/" + player.getUsername() + ".json");

    if (!file.exists()) {
      return false;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      final PlayerDetails details = gson.fromJson(reader, PlayerDetails.class);

      player.setUsername(details.getUsername());
      player.setPassword(details.getPassword());
      player.setRights(details.getRights());
      player.setPosition(
          player.attr().get(Player.NEW_PLAYER_KEY) ? Player.DEFAULT_SPAWN : details.getLocation());
      player.attr().put(Player.NEW_PLAYER_KEY, details.isNewPlayer());
      player.attr().put(Player.BRIGHTNESS_KEY, details.getBrightness());
      player.attr().put(Player.MUSIC_VOLUME_KEY, details.getMusicVolume());
      player.attr().put(Player.SOUND_EFFECT_VOLUME_KEY, details.getSoundEffectVolume());
      player.attr().put(Player.AREA_SOUND_VOLUME_KEY, details.getAreaSoundVolume());
      player.attr().put(Movement.RUNNING_KEY, details.isRunning());
      player.attr().put(Player.AUTO_RETALIATE_KEY, details.isAutoRetaliate());
      player.attr().put(Player.SOUND_KEY, details.isEnableSound());
      player.attr().put(Player.MUSIC_KEY, details.isEnableMusic());
      player.attr().put(Player.DEBUG_KEY, details.isDebugMode());
      player.attr().put(Player.MOUSE_BUTTON_KEY, details.isMouseButtons());
      player.attr().put(Player.CHAT_EFFECTS_KEY, details.isChatEffects());
      player.attr().put(Player.SPLIT_CHAT_KEY, details.isSplitChat());
      player.attr().put(Player.ACCEPT_AID_KEY, details.isAcceptAid());

      if (details.getAppearance() == null) {
        player.getAppearance().getDefaultAppearance();
      } else {
        player.setAppearance(details.getAppearance());
      }

      if (details.getSkills() != null) {
        player.getSkills().setSkills(details.getSkills());
      } else {
        player.getSkills().setDefault();
      }

      if (details.getFriendList().size() > 0) {
        player.getPlayerRelation().setFriendList(details.getFriendList());
      }
      if (details.getIgnoreList().size() > 0) {
        player.getPlayerRelation().setIgnoreList(details.getIgnoreList());
      }

      return true;
    }
  }  
  
}
