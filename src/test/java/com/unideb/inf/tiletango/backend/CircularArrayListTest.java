package com.unideb.inf.tiletango.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CircularArrayListTest {
    CircularArrayList<Integer> list;
    List<Integer> listElements = List.of(1, 2, 3, 4);

    @BeforeEach
    void setUp() {
        list = new CircularArrayList<>(listElements);
    }

    @Test
    void set() {
        list.set(5, 5);
        assertEquals(5, list.get(1));
    }

    @Test
    void add() {
        list.add(5, 10);
        assertEquals(10, list.get(1));
    }

    @Test
    void remove() {
        list.remove(5);
        assertNotEquals(2, list.get(1));
    }

    @Test
    void addAll() {
        list.addAll(5, List.of(10, 11));

    }

    @Test
    void removeRange() {
    }

    @Test
    void listIterator() {
    }

    @Test
    void subList() {
    }

    @Test
    void get() {
    }
}