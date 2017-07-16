package io.astraeus.game.world.entity.mob.player.widget.dialog;

import java.util.function.Consumer;

/**
 * The chain-able interface that allows implementing dialogue factories the ability to chain
 * together.
 *
 * @author Vult-R
 */
public interface Chainable extends Consumer<DialogueFactory> {

}

