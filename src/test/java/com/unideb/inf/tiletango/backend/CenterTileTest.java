package com.unideb.inf.tiletango.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CenterTileTest {
    CenterTile tile;

    @BeforeEach
    void setUp() {
        tile = new CenterTile();
    }

    @Test
    void getValue() {
        assertEquals(-1, tile.getValue());
    }

    @Test
    void compareTo() {
        Tile circleTile = new CircleTile(1, 5);
        assertEquals(0, tile.compareTo(circleTile));
    }
}