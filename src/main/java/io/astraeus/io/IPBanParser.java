package io.astraeus.io;

import java.io.IOException;
import java.util.Scanner;

import io.astraeus.game.world.World;
import io.astraeus.util.TextFileParser;

public final class IPBanParser extends TextFileParser {

  public IPBanParser() {
    super("./data/punishment/ip_bans");
  }

  @Override
  public void parse(Scanner reader) throws IOException {
    String ip = reader.nextLine();      
    
    World.getIpBans().add(ip);
  }

}
