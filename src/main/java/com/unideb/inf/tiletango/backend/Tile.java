package com.unideb.inf.tiletango.backend;

/**
 * Interface which specifies basic behaviour.
 * Ordering {@code Tile} values must be done strictly.
 * Two matching values indicates that the {@code Tile}s are not in order.
 */
public interface Tile extends Comparable<Tile> {
    /**
     * Gets the value of the tile
     * @return integer value of the tile
     */
    int getValue();
}
