package io.astraeus.net.codec.login;

import io.astraeus.net.codec.IsaacCipher;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;

/**
 * The upstream packet that contains information about a player.
 * 
 * @author Vult-R
 */
public final class LoginDetailsPacket {

  /**
   * The context to which this player is going through.
   */
  @Getter
  private final ChannelHandlerContext context;

  /**
   * The username for this user.
   */
  @Getter
  private final String username;

  /**
   * The password for this user.
   */
  @Getter
  private final String password;

  /**
   * The unique identifier for this player.
   */
  @Getter
  private final int uid;  

  /**
   * The encrypting isaac
   */
  @Getter
  private final IsaacCipher encryptor;

  /**
   * The decrypting isaac
   */
  @Getter
  private final IsaacCipher decryptor;

  /**
   * Creates a new {@link LoginDetailsPacket}.
   * 
   * @param context The context to which this player is going through
   * 
   * @param username The username for this player
   * 
   * @param password The password for this player
   * 
   * @param uuid The universal unique identifier for this player
   * 
   * @param encrptor The encrypting isaac
   * 
   * @param decrpytor The decrypting isaac
   */
  public LoginDetailsPacket(ChannelHandlerContext context, String username, String password,
      int uid, IsaacCipher encryptor, IsaacCipher decryptor) {
    this.context = context;
    this.username = username;
    this.uid = uid;
    this.password = password;
    this.encryptor = encryptor;
    this.decryptor = decryptor;
  }

}

