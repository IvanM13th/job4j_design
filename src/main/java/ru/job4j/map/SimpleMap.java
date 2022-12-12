package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        expand();
        int bucket = getBucket(key);
        if (table[bucket] == null) {
            table[bucket] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> capacity);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        if (count > capacity * LOAD_FACTOR) {
            capacity = capacity * 2;
        }
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int hc = checkHC(key);
        int bucket = getBucket(key);
        if (table[bucket] != null) {
            if (hc == checkHC(table[bucket].key)) {
                if (Objects.equals(key, table[bucket].key)) {
                    rsl = table[bucket].value;
                }
            }
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int bucket = getBucket(key);
        if (count > 0 & table[bucket] != null) {
            table[bucket] = null;
            count--;
            modCount++;
            rsl = true;
        } else if (count > 0 & bucket == 0) {
            table[bucket] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (table[point] == null & point < capacity - 1) {
                    point++;
                }
                return point < capacity - 1;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private int checkHC(K key) {
        return key == null ? 0 : key.hashCode();
    }

    private int getBucket(K key) {
        return key == null ? 0 : indexFor(hash(key.hashCode()));
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}