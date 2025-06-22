package com.unideb.inf.tiletango.backend;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Board {
    CircularArrayList<Tile> circle;
    Tile middle;

    public Board(int size) throws IllegalArgumentException {
        if (size <= 2) {
            throw new IllegalArgumentException("Board size must be greater than 2");
        }
        middle = new CenterTile();
        circle = IntStream
                .rangeClosed(1, size)
                .mapToObj(i -> new CircleTile(i, size))
                .collect(Collectors.toCollection(CircularArrayList::new));
        Collections.shuffle(circle);
    }

    Board(Collection<Tile> tiles, Tile middle) {
        this.circle = new CircularArrayList<>(tiles);
        this.middle = middle;
    }

    public boolean isSolved() {
        for (int i = 0; i < circle.size(); i++) {
            if (circle.get(i).compareTo(circle.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidMove(int index) {
        if (index < 0 || index >= circle.size()) {
            throw new IndexOutOfBoundsException("There is no such index");
        }

        if (middle instanceof CenterTile || circle.get(index) instanceof CenterTile) {
            return true;
        }
        if (circle.get(index + 1) instanceof CenterTile) {
            return true;
        }
        if (circle.get(index - 1) instanceof CenterTile) {
            return true;
        }
        return false;
    }

    public boolean tryMove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= circle.size()) {
            throw new IndexOutOfBoundsException("There is no such index");
        }

        if (middle instanceof CenterTile || circle.get(index) instanceof CenterTile) {
            moveToCenter(index);
            return true;
        }
        if (circle.get(index + 1) instanceof CenterTile) {
            moveToRight(index);
            return true;
        }
        if (circle.get(index - 1) instanceof CenterTile) {
            moveToLeft(index);
            return true;
        }
        return false;
    }

    private void moveToCenter(int index) {
        Tile t = middle;
        middle = circle.get(index);
        circle.set(index, t);
    }

    private void moveToRight(int index) {
        Collections.swap(circle, index, index + 1);
    }

    private void moveToLeft(int index) {
        Collections.swap(circle, index, index - 1);
    }

    public List<Tile> getCircle() {
        return circle;
    }

    public Tile getMiddle() {
        return middle;
    }
}
