package com.unideb.inf.tiletango.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Board {
    int size;
    CircularArrayList<Tile> circle;
    Tile middle;

    public Board(int size) {
        this.size = size;
        middle = new CenterTile();
        circle = IntStream
                .rangeClosed(1, size)
                .mapToObj(i -> new CircleTile(i, size))
                .collect(Collectors.toCollection(CircularArrayList::new));
        Collections.shuffle(circle);
    }

    public boolean isSolved() {
        Tile currentTile = circle.getFirst();
        for (int i = 1; i < circle.size(); i++) {
            if (!currentTile.isNext(circle.get(i))) {
                return false;
            }
            currentTile = circle.get(i);
        }
        return true;
    }

    public boolean tryMove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
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

   public ArrayList<Tile> getCircle() {
        return circle;
   }

   public Tile getMiddle() {
        return middle;
   }

}
