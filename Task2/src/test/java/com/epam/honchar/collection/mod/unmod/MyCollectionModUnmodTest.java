package com.epam.honchar.collection.mod.unmod;

import static org.junit.jupiter.api.Assertions.*;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyCollectionModUnmodTest {
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final String STR_2 = "str2";
    private static final String STR_3 = "str3";


    public MyCollectionModUnmod<Object> fillingFirstCollection() {
        List<Object> list1 = new ArrayList<>();
        list1.add(ONE);
        List<Object> list2 = new ArrayList<>();
        return new MyCollectionModUnmod<>(list1, list2);
    }

    public MyCollectionModUnmod<Object> fillingSecondCollection() {
        List<Object> list1 = new ArrayList<>();
        list1.add(ONE);
        List<Object> list2 = new ArrayList<>();
        return new MyCollectionModUnmod<>(list1, list2);
    }


    @Test
    public void shouldEquals() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        collection.add(TWO);
        assertTrue(collection.get(0).equals(ONE));
        assertEquals(2, collection.size());
    }

    @Test
    public void shouldGetSize() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        collection.add(TWO);
        assertEquals(2, collection.size());
    }

    @Test
    public void shouldCheckIfIsEmpty() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        assertFalse(collection.isEmpty());
    }

    @Test
    public void shouldContains() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        collection.add(TWO);
        assertTrue(collection.contains(ONE));
        assertTrue(collection.contains(TWO));
        assertEquals(2, collection.size());
    }

    @Test
    public void shouldAdd() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        collection.add(TWO);
        assertTrue(collection.contains(ONE));
        assertTrue(collection.contains(TWO));
        assertEquals(2, collection.size());
    }

    @Test
    public void shouldAddAll() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        MyCollectionModUnmod<Object> collection2 = fillingSecondCollection();
        collection.add(TWO);
        collection2.add(THREE);
        collection.addAll(collection2);
        assertTrue(collection.contains(ONE));
        assertTrue(collection.contains(THREE));
    }

    @Test
    public void shouldAddByIndex() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        collection.add(0, TWO);
        collection.add(1, THREE);
        assertEquals(3, collection.size());
        assertEquals(THREE, (int) collection.get(2));
    }

    @Test
    public void shouldAddAllByIndex() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        MyCollectionModUnmod<Object> collection2 = fillingSecondCollection();
        collection.add(TWO);
        collection2.add(THREE);
        collection.addAll(1, collection2);
        assertTrue(collection.contains(THREE));
        assertEquals(THREE, collection.get(3));
    }

    @Test
    public void shouldGetIndex() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        collection.add(TWO);
        assertEquals(TWO, collection.get(1));
    }

    @Test
    public void shouldRemove() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        collection.add(STR_2);
        collection.remove(STR_2);
        assertFalse(collection.contains(STR_2));
        assertEquals(1, collection.size());
    }

    @Test
    public void shouldRemoveAll() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        MyCollectionModUnmod<Object> collection2 = fillingSecondCollection();
        collection.add(STR_3);
        collection2.add(STR_3);
        collection.removeAll(collection2);
        assertTrue(collection.contains(ONE));
        assertFalse(collection.contains(STR_2));
        assertFalse(collection.contains(STR_3));
    }

    @Test
    public void shouldRetainAll() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        MyCollectionModUnmod<Object> collection2 = fillingSecondCollection();
        collection.add(STR_3);
        collection2.add(STR_3);
        collection.add(STR_2);
        collection.retainAll(collection2);
        assertTrue(collection.contains(STR_3));
        assertTrue(collection.contains(ONE));
        assertFalse(collection.contains(STR_2));
    }

    @Test
    public void shouldClear() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        collection.add(STR_2);
        collection.clear();
        assertFalse(collection.contains(STR_2));
        assertTrue(collection.contains(ONE));
    }

    @Test
    public void shouldRemoveByIndex() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        collection.add(STR_2);
        collection.remove(0);
        assertTrue(collection.contains(ONE));
        assertFalse(collection.contains(STR_2));
        assertEquals(1, collection.size());
    }

    @Test
    public void shouldHasNext_BaseCase() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        collection.add(TWO);
        Iterator<Object> it = collection.iterator();
        assertTrue(it.hasNext());
    }

    @Test
    public void shouldHasNext_C1() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        collection.add(TWO);
        Iterator<Object> it = collection.iterator();
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void shouldNext_BaseCase() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        collection.add(TWO);
        Iterator<Object> it = collection.iterator();
        it.next();
        assertEquals(TWO, it.next());
    }

    @Test
    public void shouldNext_C2() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        collection.add(TWO);
        Iterator<Object> it = collection.iterator();
        it.next();
        assertEquals(TWO, it.next());
    }

    @Test
    public void shouldRemove_BaseCase() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        collection.add(TWO);
        Iterator<Object> it = collection.iterator();
        it.next();
        it.next();
        it.remove();
        assertFalse(collection.contains(TWO));
    }

    @Test
    public void shouldRemove_C1() {
        MyCollectionModUnmod<Object> collection = fillingFirstCollection();
        collection.add(TWO);
        Iterator<Object> it = collection.iterator();
        it.next();
        it.next();
        it.remove();
        assertFalse(collection.contains(TWO));
    }
}
