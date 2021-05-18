package com.epam.honchar.collection.mod.unmod;


import java.util.*;

/**
 * Class MyCollection with fields <b>CAPACITY</b>, <b>items</b> and <b>size</b>.
 *
 * @version 1.8
 * @autor Denys Honchar
 */
public class MyCollectionModUnmod<E> implements List<E> {

    private static final String NOTIMPL = "Not yet implemented";
    List<Object> unmodifiableList;
    List<Object> modifiableList;

    public MyCollectionModUnmod(List<Object> unmodifiableList, List<Object> modifiableList) {
        this.unmodifiableList = unmodifiableList;
        this.modifiableList = modifiableList;
    }

    @Override
    public boolean equals(Object object) {
        if (getCombinedLists().contains(object)) {
            for (int i = 0; i < getCombinedLists().size(); i++) {
                if (getCombinedLists().get(i).equals(equals(object))) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Object> getCombinedLists() {
        List<Object> generalList = new ArrayList<>();
        generalList.addAll(unmodifiableList);
        generalList.addAll(modifiableList);
        return generalList;
    }

    public boolean isObjectNotNull(Object object) {
        return object != null;
    }

    public boolean checkIndex(int index) {
        return index >= 0 && index <= size();
    }

    @Override
    public int size() {
        return getCombinedLists().size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object object) {
        return getCombinedLists().contains(object);
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorForEntity();
    }


    @Override
    public E[] toArray() {
        if (!getCombinedLists().isEmpty()) {
            return (E[]) getCombinedLists().toArray();

        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (!getCombinedLists().isEmpty()) {
            return (T[]) getCombinedLists().toArray();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public boolean remove(Object object) {
        if (!unmodifiableList.contains(object)) {
            return modifiableList.remove(object);
        } else {
            throw new IllegalArgumentException();
        }
    }


    @Override
    public boolean addAll(Collection<? extends E> collection) {
        if (!collection.isEmpty()) {
            return modifiableList.addAll(collection);
        }
        return false;
    }

    @Override
    public boolean add(Object object) {
        return modifiableList.add(object);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (checkIndex(index) && isObjectNotNull(collection) && !collection.isEmpty()) {
            return modifiableList.addAll(index, collection);
        }
        return false;
    }

    @Override
    public void clear() {
        if (!getCombinedLists().isEmpty()) {
            modifiableList.clear();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public E get(int index) {
        if (checkIndex(index)) {
            return (E) getCombinedLists().get(index);
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public E set(int index, E element) {
        if (isObjectsIndexOutUnmod(index) && isObjectNotNull(element)) {
            return (E) modifiableList.set(index, element);
        }
        throw new UnsupportedOperationException();
    }

    public boolean isObjectsIndexOutUnmod(int index) {
        return getCombinedLists().size() - index >= unmodifiableList.size();
    }

    @Override
    public void add(int index, Object element) {
        if (isObjectsIndexOutUnmod(index)) {
            modifiableList.add(index, element);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public E remove(int index) {
        if (checkIndex(index)) {
            return (E) modifiableList.remove(index);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object object) {
        if (isObjectNotNull(object) && !getCombinedLists().isEmpty()) {
            getCombinedLists().indexOf(object);
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        if (isObjectNotNull(object) && !getCombinedLists().isEmpty()) {
            getCombinedLists().lastIndexOf(object);
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException(NOTIMPL);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException(NOTIMPL);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException(NOTIMPL);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (isObjectNotNull(collection) && !getCombinedLists().isEmpty()) {
            modifiableList.retainAll(collection);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (isObjectNotNull(collection) && !getCombinedLists().isEmpty()) {
            modifiableList.removeAll(collection);
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (isObjectNotNull(collection) && !getCombinedLists().isEmpty()) {
            getCombinedLists().containsAll(collection);
        }
        return false;
    }

    private class IteratorForEntity implements Iterator<E> {

        Iterator<Object> modIterator = modifiableList.iterator();
        Iterator<Object> unmodIterator = unmodifiableList.iterator();

        @Override
        public boolean hasNext() {
            return unmodIterator.hasNext() || modIterator.hasNext();
        }

        @Override
        public E next() {
            if (unmodIterator.hasNext()) {
                return (E) unmodIterator.next();
            } else if (modIterator.hasNext()) {
                return (E) modIterator.next();
            }
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            if (!unmodIterator.hasNext()) {
                modIterator.remove();
            }
        }
    }
}
