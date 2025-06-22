package com.unideb.inf.tiletango.backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

public class Game {
    Board board;
    public Game(int numberOfTiles) {
        this.board = new Board(numberOfTiles);
    }

    public Game(Collection<Tile> circle, Tile middle) {
        this.board = new Board(circle, middle);
    }

    public List<Integer> getState() {
        var circle = new ArrayList<>(board.getCircle());
        circle.addFirst(this.board.getMiddle());
        return circle.stream().map(Tile::getValue).toList();
    }

    public int[] getValidMoves() {
        int boardSize = board.getCircle().size();
        return IntStream.range(0, boardSize).filter(board::isValidMove).toArray();
    }

    public void move(int index) {
        board.tryMove(index);
    }

    public void moveValue(int value) {
        if (board.getMiddle().getValue() == value) {
            value = -1;
        }
        for (int i = 0; i < board.getCircle().size(); i++) {
            if (board.getCircle().get(i).getValue() == value && board.isValidMove(i)) {
                board.tryMove(i);
                return;
            }
        }
    }

    public boolean isGameOver() {
        return board.isSolved();
    }
}
