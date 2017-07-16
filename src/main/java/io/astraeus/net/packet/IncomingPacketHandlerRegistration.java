package io.astraeus.net.packet;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.net.packet.in.AppearanceChangePacket;
import io.astraeus.net.packet.in.AttackNpcPacket;
import io.astraeus.net.packet.in.ButtonClickPacket;
import io.astraeus.net.packet.in.ChatMessagePacket;
import io.astraeus.net.packet.in.CloseInterfacePacket;
import io.astraeus.net.packet.in.CommandPacket;
import io.astraeus.net.packet.in.DefaultPacket;
import io.astraeus.net.packet.in.DialoguePacket;
import io.astraeus.net.packet.in.DropItemPacket;
import io.astraeus.net.packet.in.EnterRegionPacket;
import io.astraeus.net.packet.in.EquipItemPacket;
import io.astraeus.net.packet.in.IdleLogoutPacket;
import io.astraeus.net.packet.in.ItemFirstOptionPacket;
import io.astraeus.net.packet.in.ItemOnGroundItemPacket;
import io.astraeus.net.packet.in.ItemOnItemPacket;
import io.astraeus.net.packet.in.ItemOnNpcPacket;
import io.astraeus.net.packet.in.ItemOnObjectPacket;
import io.astraeus.net.packet.in.ItemOnPlayerPacket;
import io.astraeus.net.packet.in.ItemSecondOptionPacket;
import io.astraeus.net.packet.in.ItemThirdOptionPacket;
import io.astraeus.net.packet.in.LoadRegionPacket;
import io.astraeus.net.packet.in.MagicOnFloorItemPacket;
import io.astraeus.net.packet.in.MagicOnItemPacket;
import io.astraeus.net.packet.in.MagicOnNpcPacket;
import io.astraeus.net.packet.in.MagicOnObjectPacket;
import io.astraeus.net.packet.in.MagicOnPlayerPacket;
import io.astraeus.net.packet.in.MoveItemPacket;
import io.astraeus.net.packet.in.MovementPacket;
import io.astraeus.net.packet.in.NpcFirstClickPacket;
import io.astraeus.net.packet.in.NpcSecondClickPacket;
import io.astraeus.net.packet.in.NpcThirdClickPacket;
import io.astraeus.net.packet.in.ObjectFirstOptionPacket;
import io.astraeus.net.packet.in.ObjectSecondOptionPacket;
import io.astraeus.net.packet.in.ObjectThirdOptionPacket;
import io.astraeus.net.packet.in.PickupGroundItemPacket;
import io.astraeus.net.packet.in.PlayerFifthOptionPacket;
import io.astraeus.net.packet.in.PlayerFirstOptionPacket;
import io.astraeus.net.packet.in.PlayerFourthOptionPacket;
import io.astraeus.net.packet.in.PlayerRelationPacket;
import io.astraeus.net.packet.in.PlayerSecondOptionPacket;
import io.astraeus.net.packet.in.PlayerThirdOptionPacket;
import io.astraeus.net.packet.in.ReportPlayerPacket;
import io.astraeus.net.packet.in.TradeAnswerPacket;
import io.astraeus.net.packet.in.TypeOnWidgetPacket;
import io.astraeus.net.packet.in.WidgetContainerFifthOptionPacket;
import io.astraeus.net.packet.in.WidgetContainerFirstOptionPacket;
import io.astraeus.net.packet.in.WidgetContainerFourthOptionPacket;
import io.astraeus.net.packet.in.WidgetContainerSecondOptionPacket;
import io.astraeus.net.packet.in.WidgetContainerSixthOptionPacket;
import io.astraeus.net.packet.in.WidgetContainerThirdOptionPacket;
import io.astraeus.util.LoggerUtils;

/**
 * Handles registering the handlers responsible for intervening {@link IncomingPacket}s.
 * 
 * @author Seven
 */
public final class IncomingPacketHandlerRegistration {

  /**
   * The single logger for this class.
   */
  private static final Logger logger =
      LoggerUtils.getLogger(IncomingPacketHandlerRegistration.class);

  /**
   * The map of {@IncomingPacket} opcodes mapped to their listener.
   */
  private final static Map<Integer, Receivable> incomingPackets = new HashMap<>();

  /**
   * Intercepts an {@link IncomingPacket} for a {@code player} and dispatches the packet to the
   * correct listener to be handled.
   * 
   * @param player The player that is receiving the packet.
   * 
   * @param packet The incoming packet to intervene.
   */
  public static final void dispatchToHandler(Player player, IncomingPacket packet) {
    Optional<Receivable> listener = Optional.ofNullable(incomingPackets.get(packet.getOpcode()));

    if (player.attr().get(Player.DEBUG_NETWORK_KEY)) {
      logger.info(String.format(packet.toString()));
    }

    listener.ifPresent(msg -> msg.handlePacket(player, packet));
  }

  /**
   * Registers a handler for the {@link IncomingPacket}s.
   * 
   * @param listener The handler to register.
   */
  private static final void registerHandler(Receivable listener) {
    final IncomingPacket.IncomingPacketOpcode annotation =
        listener.getClass().getAnnotation(IncomingPacket.IncomingPacketOpcode.class);
    if (annotation != null) {
      for (final int opcode : annotation.value()) {
        incomingPackets.put(opcode, listener);
      }
    }
  }

  /**
   * Initializes all the {@link IncomingPacket} handlers when this object is created.
   */
  public IncomingPacketHandlerRegistration() {
    registerHandler(new PlayerSecondOptionPacket());
    registerHandler(new MagicOnPlayerPacket());
    registerHandler(new WidgetContainerFirstOptionPacket());
    registerHandler(new WidgetContainerSecondOptionPacket());
    registerHandler(new WidgetContainerThirdOptionPacket());
    registerHandler(new WidgetContainerFourthOptionPacket());
    registerHandler(new WidgetContainerFifthOptionPacket());
    registerHandler(new WidgetContainerSixthOptionPacket());
    registerHandler(new TypeOnWidgetPacket());
    registerHandler(new MagicOnNpcPacket());
    registerHandler(new AttackNpcPacket());
    registerHandler(new PlayerFirstOptionPacket());
    registerHandler(new PlayerSecondOptionPacket());
    registerHandler(new PlayerThirdOptionPacket());
    registerHandler(new PlayerFourthOptionPacket());
    registerHandler(new PlayerFifthOptionPacket());
    registerHandler(new TradeAnswerPacket());
    registerHandler(new AppearanceChangePacket());
    registerHandler(new ButtonClickPacket());
    registerHandler(new ChatMessagePacket());
    registerHandler(new DefaultPacket());
    registerHandler(new ItemFirstOptionPacket());
    registerHandler(new ItemSecondOptionPacket());
    registerHandler(new ItemThirdOptionPacket());
    registerHandler(new ObjectFirstOptionPacket());
    registerHandler(new ObjectSecondOptionPacket());
    registerHandler(new ObjectThirdOptionPacket());
    registerHandler(new CloseInterfacePacket());
    registerHandler(new CommandPacket());
    registerHandler(new DialoguePacket());
    registerHandler(new DropItemPacket());
    registerHandler(new IdleLogoutPacket());
    registerHandler(new MagicOnFloorItemPacket());
    registerHandler(new MagicOnItemPacket());
    registerHandler(new MoveItemPacket());
    registerHandler(new PickupGroundItemPacket());
    registerHandler(new PlayerRelationPacket());
    registerHandler(new EnterRegionPacket());
    registerHandler(new LoadRegionPacket());
    registerHandler(new ReportPlayerPacket());
    registerHandler(new ItemOnItemPacket());
    registerHandler(new ItemOnObjectPacket());
    registerHandler(new ItemOnNpcPacket());
    registerHandler(new ItemOnGroundItemPacket());
    registerHandler(new ItemOnPlayerPacket());
    registerHandler(new MovementPacket());
    registerHandler(new EquipItemPacket());
    registerHandler(new NpcFirstClickPacket());
    registerHandler(new NpcSecondClickPacket());
    registerHandler(new NpcThirdClickPacket());
    registerHandler(new MagicOnObjectPacket());
  }
}
