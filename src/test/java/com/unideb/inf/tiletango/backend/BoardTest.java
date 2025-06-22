package com.unideb.inf.tiletango.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board mockedRandomBoard;
    ArrayList<Integer> tileOrder = new ArrayList<>(List.of(4, 6, 5, 1, 2, 3));

    @BeforeEach
    void setUp() {
        this.mockedRandomBoard = new Board(
                tileOrder.stream().map(number -> new CircleTile(number, tileOrder.size()))
                        .collect(Collectors.toList()),
                new CenterTile()
        );
    }

    @Test
    void isSolvedFalse() {
        assertFalse(mockedRandomBoard.isSolved());
    }

    @Test
    void isSolvedTrue() {
        // solve the game
        mockedRandomBoard.tryMove(1);
        mockedRandomBoard.tryMove(2);
        mockedRandomBoard.tryMove(2);

        assertTrue(mockedRandomBoard.isSolved());
    }

    @Test
    void tryMoveInvalidParameter() {
        var exception = assertThrows(IndexOutOfBoundsException.class, () -> mockedRandomBoard.tryMove(-1));
        assertEquals("There is no such index", exception.getMessage());
        var exception2 = assertThrows(IndexOutOfBoundsException.class, () -> mockedRandomBoard.tryMove(10));
        assertEquals("There is no such index", exception2.getMessage());
    }

    @Test
    void tryMoveTrue() {
        assertTrue(mockedRandomBoard.tryMove(1));
    }

    @Test
    void tryMoveFalse() {
        mockedRandomBoard.tryMove(1);
        assertFalse(mockedRandomBoard.tryMove(3));
    }

    @Test
    void tryMoveMiddle() {
        mockedRandomBoard.tryMove(1);
        assertInstanceOf(CircleTile.class, mockedRandomBoard.getMiddle());
    }

    @Test
    void tryMoveLeft() {
        mockedRandomBoard.tryMove(1);
        assertTrue(mockedRandomBoard.tryMove(2));
    }

    @Test
    void tryMoveRight() {
        mockedRandomBoard.tryMove(1);
        assertTrue(mockedRandomBoard.tryMove(0));
    }

    @Test
    void tryMoveBottomBound() {
        mockedRandomBoard.tryMove(5);
        assertTrue(mockedRandomBoard.tryMove(0));
        assertEquals(tileOrder.getFirst(), mockedRandomBoard.getCircle().getLast().getValue());
    }

    @Test
    void tryMoveUpperBound() {
        mockedRandomBoard.tryMove(0);
        assertTrue(mockedRandomBoard.tryMove(5));
        assertEquals(tileOrder.getLast(), mockedRandomBoard.getCircle().getFirst().getValue());
    }

    @Test
    void getCircle() {
        assertEquals(
                tileOrder,
                mockedRandomBoard
                        .getCircle()
                        .stream()
                        .map(Tile::getValue)
                        .collect(Collectors.toList())
        );
    }

    @Test
    void getMiddleCenterTile() {
        assertInstanceOf(CenterTile.class, mockedRandomBoard.getMiddle());
    }

    @Test
    void getMiddleCircleTile() {
        mockedRandomBoard.tryMove(1);
        assertInstanceOf(CircleTile.class, mockedRandomBoard.getMiddle());
    }

    @Test
    void constructorSizeTooSmall() {
        var exception = assertThrows(
                IllegalArgumentException.class, () -> new Board(2)
        );
        assertEquals("Board size must be greater than 2", exception.getMessage());
    }

    @Test
    void constructorHappyCase() {
        assertDoesNotThrow(() -> new Board(10));
    }
}