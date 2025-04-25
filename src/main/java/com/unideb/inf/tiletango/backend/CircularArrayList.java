package com.unideb.inf.tiletango.backend;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * Own implementation of ArrayList, where indexes wrap around.
 * {@link Math#floorMod} has been used to achieve this behaviour.
 * @param <T> the type of elements in this list
 */
public class CircularArrayList<T> extends ArrayList<T> {
    @Override
    public T set(int index, T element) {
        return super.set(Math.floorMod(index, size()), element);
    }

    @Override
    public void add(int index, T element) {
        super.add(Math.floorMod(index, size()), element);
    }

    @Override
    public T remove(int index) {
        return super.remove(Math.floorMod(index, size()));
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return super.addAll(Math.floorMod(index, size()), c);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        super.removeRange(Math.floorMod(fromIndex, size()), Math.floorMod(toIndex, size()));
    }

    @Override
    public @NotNull ListIterator<T> listIterator(int index) {
        return super.listIterator(Math.floorMod(index, size()));
    }

    @Override
    public @NotNull List<T> subList(int fromIndex, int toIndex) {
        return super.subList(Math.floorMod(fromIndex, size()), Math.floorMod(toIndex, size()));
    }

    @Override
    public T get(int index) {
        return super.get(Math.floorMod(index, size()));
    }
}
