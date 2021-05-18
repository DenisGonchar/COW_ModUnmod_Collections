package com.epam.honchar.collection.copy.on.write;

import com.epam.honchar.collection.copy.on.write.MyCollection;
import org.testng.annotations.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class MyCollectionTest {
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final String STR1 = "str1";
    private static final String STR_2 = "str2";

    @Test
    public void shouldEquals() {
        MyCollection<Integer> collection = new MyCollection<>();
        collection.add(ONE);
        assertTrue(collection.get(0).equals(ONE));
    }

    @Test
    public void shouldGetSize() {
        MyCollection<Integer> collection = new MyCollection<>();
        collection.add(ONE);
        assertEquals(1, collection.size());
    }

    @Test
    public void shouldCheckIfIsEmpty() {
        MyCollection<Integer> collection = new MyCollection<>();
        assertTrue(collection.isEmpty());
    }

    @Test
    public void shouldContains() {
        MyCollection<Integer> collection = new MyCollection<>();
        collection.add(ONE);
        assertTrue(collection.contains(ONE));
    }

    @Test
    public void shouldAdd() {
        MyCollection<String> collection = new MyCollection();
        collection.add(STR1);
        collection.add(STR_2);
        assertFalse(collection.isEmpty());
        assertTrue(collection.contains(STR1));
    }

    @Test
    public void shouldAddAll() {
        MyCollection<Integer> collection1 = new MyCollection<>();
        collection1.add(ONE);
        MyCollection<Integer> collection2 = new MyCollection<>();
        collection2.add(TWO);
        collection1.addAll(collection2);
        assertEquals(2, collection1.size());
        assertTrue(collection1.contains(TWO));
    }

    @Test
    public void shouldAddByIndex() {
        MyCollection<Integer> collection = new MyCollection<>();
        collection.add(0, ONE);
        collection.add(1, TWO);
        assertEquals(ONE, (int) collection.get(0));
        assertEquals(2, collection.size());
        assertEquals(TWO, (int) collection.get(1));

    }

    @Test
    public void shouldAddAllByIndex() {
        MyCollection<Integer> collection1 = new MyCollection<>();
        collection1.add(10);
        collection1.add(20);
        MyCollection<Integer> collection2 = new MyCollection<>();
        collection2.add(TWO);
        collection1.addAll(1, collection2);
        assertEquals(3, collection1.size());
        assertTrue(collection1.contains(TWO));
        assertEquals(TWO, (int) collection1.get(1));
    }

    @Test
    public void shouldGetIndex() {
        MyCollection<Integer> collection = new MyCollection<>();
        collection.add(ONE);
        collection.add(TWO);
        assertEquals(ONE, collection.get(0));
    }

    @Test
    public void shouldRemove() {
        MyCollection<String> collection = new MyCollection<>();
        collection.add(STR1);
        collection.add(STR_2);
        collection.remove(STR1);
        assertFalse(collection.contains(STR1));
    }

    @Test
    public void shouldRemoveAll() {
        MyCollection<String> collection = new MyCollection<>();
        collection.add(STR1);
        collection.add(STR_2);
        MyCollection<String> collection1 = new MyCollection<>();
        collection1.add(STR1);
        collection.removeAll(collection1);
        assertFalse(collection.contains(STR1));
        assertEquals(1, collection.size());
    }

    @Test
    public void shouldRetainAll() {
        MyCollection<String> collection = new MyCollection<>();
        collection.add(STR1);
        collection.add(STR_2);
        MyCollection<String> collection1 = new MyCollection<>();
        collection1.add(STR1);
        collection.retainAll(collection1);
        assertFalse(collection.contains(STR_2));
        assertEquals(1, collection.size());
    }

    @Test
    public void shouldClear() {
        MyCollection<String> collection = new MyCollection<>();
        collection.add(STR1);
        collection.add(STR_2);
        collection.clear();
        assertTrue(collection.isEmpty());
    }

    @Test
    public void shouldRemoveByIndex() {
        MyCollection<String> collection = new MyCollection<>();
        collection.add(STR1);
        collection.add(STR_2);
        collection.remove(0);
        assertFalse(collection.contains(STR1));
    }

    @Test
    public void shouldHasNext_BaseCase() {
        MyCollection<Integer> collection = new MyCollection<>();
        collection.add(ONE);
        collection.add(TWO);
        Iterator it = collection.iterator();
        assertTrue(it.hasNext());
    }

    @Test
    public void shouldHasNext_C1() {
        MyCollection<Integer> collection = new MyCollection<>();
        collection.add(ONE);
        collection.add(TWO);
        Iterator it = collection.iterator();
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void shouldNext_BaseCase() {
        MyCollection<Integer> collection = new MyCollection<>();
        collection.add(ONE);
        collection.add(TWO);
        Iterator it = collection.iterator();
        it.next();
        assertEquals(TWO, it.next());
    }

    @Test
    public void shouldNext_C2() {
        MyCollection<Integer> collection = new MyCollection<>();
        collection.add(null);
        Iterator it = collection.iterator();
        assertNull(it.next());
    }

    @Test
    public void shouldRemove_BaseCase() {
        MyCollection<String> collection = new MyCollection<>();
        collection.add(STR1);
        Iterator it = collection.iterator();
        it.next();
        it.remove();
        assertFalse(collection.contains(STR1));
    }

    @Test
    public void shouldRemove_C1() {
        MyCollection<Integer> collection = new MyCollection<>();
        collection.add(ONE);
        collection.add(TWO);
        Iterator it = collection.iterator();
        it.next();
        it.remove();
        assertTrue(!collection.contains(ONE) && collection.contains(TWO));
    }
}
