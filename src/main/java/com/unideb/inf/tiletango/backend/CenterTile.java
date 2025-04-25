package com.unideb.inf.tiletango.backend;

import org.jetbrains.annotations.NotNull;

public class CenterTile implements Tile {
    @Override
    public int getValue() {
        return -1;
    }

    @Override
    public boolean isNext(@NotNull Tile next) {
        return false;
    }

    @Override
    public boolean isPrevious(@NotNull Tile previous) {
        return false;
    }

    @Override
    public String toString() {
        return "CenterTile{}";
    }
}
