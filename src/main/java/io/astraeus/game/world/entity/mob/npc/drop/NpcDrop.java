package io.astraeus.game.world.entity.mob.npc.drop;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.Data;

/**
 * A class which represents a single npc drop.
 * 
 * @author Vult-R https://github.com/Vult-R
 */
@Data
public final class NpcDrop {
  
  public static final int ALWAYS = 1;
  
  public static final int ALMOST_ALWAYS = 2;
  
  public static final int VERY_COMMON = 5;
  
  public static final int COMMON = 20;
  
  public static final int UNCOMMON = 50;
  
  public static final int VERY_UNCOMMON = 100;
  
  public static final int RARE = 200;
  
  public static final int VERY_RARE = 286;
  
  public static final int EXTREMELY_RARE = 500;
  
  public static final int LEGENDARY = 900;
  
  /**
   * The map of npc ids mapped to their potential drops.
   */
  public final static Map<Integer, Drop[]> npcDrops = new HashMap<>();  
  
  @Data
  public static final class Drop {
    /**
     * The id of the item being dropped.
     */
    private final int itemId;

    /**
     * The minimum amount of this item that can be dropped.
     */
    private final int minAmount;

    /**
     * The maximum amount of this item that can be dropped.
     */
    private int maxAmount;

    /**
     * The rate at which this item drops.
     */
    private final int rate;
    
    /**
     * The flag that denotes only 1 item will drop.
     */
    private final boolean single;
    
    /**
     * The flag that denotes this drop drops a fixed amount of items.
     */
    private final boolean fixed;
    
    /**
     * Creates a new {@link NpcDrop}.
     * 
     * @param itemId
     *        The id of the item being dropped.
     *        
     * @param rate
     *        The rate at which this item drops.
     */
    public Drop(int itemId, int rate) {
      this(itemId, 1, 1, rate);
    }

    /**
     * Creates a new {@link NpcDrop}.
     * 
     * @param itemId
     *        The id of the item being dropped.
     *        
     * @param minAmount
     *        The minimum amount of this item that can be dropped.
     *        
     * @param maxAmount
     *        The maximum amount of this item that can be dropped.
     *        
     * @param rate
     *        The rate at which this item drops.
     */
    public Drop(int itemId, int minAmount, int maxAmount, int rate) {
      this.itemId = itemId;
      this.minAmount = minAmount;
      this.maxAmount = maxAmount;
      this.rate = rate;
      this.single = minAmount == 1 && maxAmount == 1;
      this.fixed = minAmount == maxAmount;
    }
    
    /**
     * Determines if this drop is rare.
     */
    public boolean isFromRareTable() {
      if (rate <= RARE) {
        return true;
      }
      return false;
    }
    
    /**
     * Gets the difference between the max and the min amount.
     */
    public int getExtraAmount() {
      return maxAmount - minAmount;
    }
    
  }

  /**
   * The id of the npc that contains these drops.
   */
  private final int npcId;
  
  /**
   * The array of drops that are associated with this npc.
   */
  private final Drop[] drops;
  
  /**
   * Creates a new {@link NpcDrop}.
   * 
   * @param npcId
   *        The id that these drops belong to.
   *        
   * @param drops
   *        The drops associated with this npc.
   */
  public NpcDrop(int npcId, Drop[] drops) {
    this.npcId = npcId;
    this.drops= drops;
  }

  /**
   * Gets the optional describing the result of looking for a drop.
   * 
   * @param id
   *        The npc id to check.
   *        
   * @return The optional that may contain the array of drops.
   */
  public static Optional<Drop[]> lookup(int id) {
    return Optional.ofNullable(npcDrops.get(id));
  }

}
