package com.unideb.inf.tiletango.backend;

import org.jetbrains.annotations.NotNull;

final class CenterTile implements Tile {
    @Override
    public int getValue() {
        return -1;
    }

    /**
     * Comparing a {@code CenterTile} to any other Tile is always 0.
     * @param o the object to be compared.
     * @return always 0
     */
    @Override
    public int compareTo(@NotNull Tile o) {
        return 0;
    }
}
