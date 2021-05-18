package com.epam.honchar.collection.copy.on.write;


import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 * Class MyCollection with fields <b>CAPACITY</b>, <b>NOTIMPL</b>, <b>items</b> and <b>size</b>.
 *
 * @version 1.8
 * @autor Denys Honchar
 */
public class MyCollection<E> implements List<E> {
    private static final String NOTIMPL = "Not yet implemented";
    private Object[] items;

    public MyCollection() {
        this.items = new Object[0];
    }

    final Object[] getArray() {
        return items;
    }

    final void setArray(Object[] objects) {
        items = objects;
    }

    public boolean checkIndex(int index) {
        return index >= 0 && index <= size();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        MyCollection that = (MyCollection) object;

        if (size() != that.size()) {
            return false;
        }
        return Arrays.equals(items, that.items);
    }


    @Override
    public int size() {
        return items.length;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object, items, items.length) >= 0;
    }

    private int indexOf(Object object, Object[] elements, int length) {
        for (int i = 0; i < length; i++) {
            if (elements[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator iterator() {
        return new IteratorForEntity(getArray(), 0);
    }


    @Override
    public E[] toArray() {
        Object[] elements = items;
        return (E[]) Arrays.copyOf(elements, elements.length);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Object[] elements = items;
        int len = elements.length;
        if (a.length < len) {
            return (T[]) Arrays.copyOf(elements, len, a.getClass());
        } else {
            System.arraycopy(elements, 0, a, 0, len);
            if (a.length > len) {
                a[len] = null;
            }
            return a;
        }
    }

    @Override
    public boolean remove(Object object) {
        if (contains(object)) {
            remove(indexOf(object));
            return true;
        }
        return false;
    }


    @Override
    public boolean addAll(Collection collection) {
        if (!collection.isEmpty()) {
            Object[] objects = (collection.getClass() == MyCollection.class) ?
                    ((MyCollection<?>) collection).getArray() : collection.toArray();
            int length = items.length;
            Object[] newElements = Arrays.copyOf(items, length + objects.length);
            System.arraycopy(objects, 0, newElements, length, objects.length);
            setArray(newElements);
            return true;
        }
        return false;
    }

    @Override
    public boolean add(Object object) {
        Object[] elements = getArray();
        int len = elements.length;
        Object[] newElements = Arrays.copyOf(elements, len + 1);
        newElements[len] = object;
        setArray(newElements);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection collection) {
        if (checkIndex(index) && !collection.isEmpty()) {
            Object[] newData = new Object[items.length + collection.size()];
            if (index != 0) {
                System.arraycopy(items, 0, newData, 0, index);
            }
            int itemsLeft = index;
            for (Object value : collection) {
                newData[index++] = value;
            }
            System.arraycopy(items, itemsLeft, newData, 0, items.length - itemsLeft);
            this.items = newData;
            return true;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void clear() {
        setArray(new Object[0]);
    }

    @SuppressWarnings("unchecked")
    private E get(Object[] objects, int index) {
        if (checkIndex(index)) {
            return (E) objects[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E get(int index) {
        if (checkIndex(index)) {
            return get(getArray(), index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E set(int index, E element) {
        if (checkIndex(index)) {
            E oldValue = get(items, index);
            int length = items.length;
            Object[] newElements = Arrays.copyOf(items, length);
            newElements[index] = element;
            setArray(newElements);
            return oldValue;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void add(int index, Object element) {
        if (checkIndex(index)) {
            int length = items.length;
            Object[] newElements;
            int numMoved = length - index;
            if (numMoved == 0) {
                newElements = Arrays.copyOf(items, length + 1);
            } else {
                newElements = new Object[length + 1];
                System.arraycopy(items, 0, newElements, 0, index);
                System.arraycopy(items, index, newElements, index + 1,
                        numMoved);
            }
            newElements[index] = element;
            setArray(newElements);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E remove(int index) {
        if (checkIndex(index)) {
            int length = items.length;
            E oldValue = get(items, index);
            Object[] newElements = new Object[length - 1];
            System.arraycopy(items, 0, newElements, 0, index);
            System.arraycopy(items, index + 1, newElements, index,
                    length - index - 1);
            setArray(newElements);
            return oldValue;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int indexOf(Object object) {
        Object[] elements = getArray();
        return indexOf(object, elements, elements.length);
    }

    @Override
    public int lastIndexOf(Object object) {
        Object[] elements = getArray();
        return lastIndexOf(object, elements, elements.length - 1);
    }

    private int lastIndexOf(Object object, Object[] elements, int index) {
        for (int i = index; i >= 0; i--) {
            if (object == null && elements[i] == null) {
                return i;
            }
            assert object != null;
            if (object.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException(NOTIMPL);
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException(NOTIMPL);
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException(NOTIMPL);
    }

    @Override
    public boolean retainAll(Collection collection) {
        if (collection == null) {
            throw new NullPointerException();
        }
        Object[] elements = getArray();
        int length = elements.length;
        if (length != 0) {
            int newLength = 0;
            Object[] temp = new Object[length];
            for (Object element : elements) {
                if (collection.contains(element)) {
                    temp[newLength++] = element;
                }
            }
            if (newLength != length) {
                setArray(Arrays.copyOf(temp, newLength));
                return true;
            }
        }
        return false;
    }

    public boolean isCollNotNull(Collection<?> collection) {
        return collection != null;
    }

    @Override
    public boolean removeAll(Collection collection) {
        if (isCollNotNull(collection)) {
            for (int i = size() - 1; i >= 0; i--) {
                if (collection.contains(items[i])) {
                    remove(items[i]);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection collection) {
        Object[] elements = getArray();
        int length = elements.length;
        for (Object e : collection) {
            if (indexOf(e, elements, length) < 0)
                return false;
        }
        return true;
    }

    private class IteratorForEntity implements Iterator<E> {
        private int cursor;
        private int lastRet;
        private final Object[] snapshot;

        private IteratorForEntity(Object[] elements, int initialCursor) {
            cursor = initialCursor;
            snapshot = elements;
        }

        public boolean hasNext() {
            return cursor < snapshot.length;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) snapshot[cursor++];
        }

        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            try {
                MyCollection.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }


    }
}
