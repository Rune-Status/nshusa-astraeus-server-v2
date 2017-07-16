package io.astraeus.game.world.entity.mob.player.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.astraeus.game.world.entity.item.Item;
import io.astraeus.game.world.entity.mob.player.Player;
import lombok.Data;

/**
 * The class used to create an object and convert to json object.
 *
 * @author Seven
 */
@Data
public final class PlayerContainer {
  
  private static final transient Gson gson = new GsonBuilder().setPrettyPrinting().create();
  
  private final transient String username;

  private Item[] inventory;

  private Item[] equipment;

  private Item[] bank;

  public PlayerContainer(Player player) {
    inventory = player.getInventory().container();
    equipment = player.getEquipment().container();
    bank = player.getBank().container();
    this.username = player.getUsername();
  }

  public synchronized boolean save() throws IOException {
    final File dir = new File("./data/characters/containers/");

    if (!dir.exists()) {
      dir.mkdirs();
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(dir.toString() + File.separator + username + ".json", false))) {
      writer.write(gson.toJson(this));
      return true;
    }
    
  }

  public static synchronized boolean load(Player player) throws Exception {
    final File file = new File("./data/characters/containers/" + player.getUsername() + ".json");
    
    if (!file.exists()) {
      return false;
    }
    
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      final PlayerContainer details = gson.fromJson(reader, PlayerContainer.class);

      if (details.getInventory() != null) {
        player.getInventory().setItems(details.getInventory());
      }

      if (details.getEquipment() != null) {
        player.getEquipment().setItems(details.getEquipment());
      }

      if (details.getBank() != null) {
        player.getBank().setItems(details.getBank());
      }
    }
    return true;
  }

}
