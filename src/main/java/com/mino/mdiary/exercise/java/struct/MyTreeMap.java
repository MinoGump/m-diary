package com.mino.mdiary.exercise.java.struct;

import com.mchange.v2.lang.ObjectUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 实现一颗TreeSet，它的迭代器是一个二叉查找树，且每个树节点都存储它的父节点
 */
public class MyTreeMap<K extends Comparable<? super K>, V> implements Map<K, V> {

    TreeSet<Map.Entry<K, V>> treeSet;

    private final Comparator<Entry<K, V>> COMPARABLE_FUNCTION = new Comparator<Entry<K, V>>() {
        @Override
        public int compare(Entry<K, V> o1, Entry<K, V> o2) {
            return o1.getKey().compareTo(o2.getKey());
        }
    };

    /**
     * 这里完全可以延迟初始化，即在insert的时候判断treeSet是否为空，若为空，则初始化之
     */
    public MyTreeMap() {
        treeSet = new TreeSet<>(COMPARABLE_FUNCTION);
    }

    @Override
    public boolean isEmpty() {
        return CollectionUtils.isEmpty(treeSet);
    }

    public void makeEmpty() {
        treeSet = null;
    }

    @Override
    public int size() {
        return treeSet == null ? 0 : treeSet.size();
    }

    @Override
    public boolean containsKey(Object key) {
        if (isEmpty()) {
            return false;
        }
        Map.Entry<K, V> entry = treeSet.ceiling(new MapEntry<>((K) key, null));
        return entry != null && entry.getKey() != null && entry.getKey().equals(key);
    }

    @Override
    public boolean containsValue(Object value) {
        if (isEmpty()) {
            return false;
        }
        for (Entry<K, V> entry : treeSet) {
            if (entry.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        Map.Entry<K, V> mapEntry = new MapEntry<>((K) key, null);
        Map.Entry<K, V> kvEntry = treeSet.ceiling(mapEntry);
        if (kvEntry != null) return kvEntry.getValue();
        else return null;
    }

    @Override
    public V put(K key, V value) {
        treeSet.add(new MapEntry<>(key, value));
        return value;
    }

    @Override
    public V remove(Object key) {
        Entry<K, V> entry = getValue(key);
        if (entry != null) {
            treeSet.remove(entry);
            return entry.getValue();
        } else return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        if (m == null) {
            return;
        }
        Set<Entry<K, V>> entries = new HashSet<>();
        for (Map.Entry<? extends K, ? extends V> mapEntry : m.entrySet()) {
            entries.add(new MapEntry<>(mapEntry.getKey(), mapEntry.getValue()));
        }
        treeSet.addAll(entries);
    }

    @Override
    public void clear() {
        if (treeSet != null) treeSet.clear();
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        if (isEmpty()) return keySet;
        for (Entry<K, V> aTreeSet : treeSet) {
            keySet.add(aTreeSet.getKey());
        }
        return keySet;
    }

    @Override
    public Collection<V> values() {
        Collection<V> collection = new ArrayList<>();
        if (isEmpty()) return collection;
        for (Entry<K, V> entry : treeSet) {
            collection.add(entry.getValue());
        }
        return collection;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return treeSet;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Map) {
            for (Object entry : ((Map) o).entrySet()) {
                if (entry instanceof Map.Entry) {
                    Entry<K, V> realEntry = getValue(((Entry) entry).getKey());
                    if (realEntry == null || (realEntry.getValue() == null && ((Entry) entry).getValue() != null)
                            ||
                            (realEntry.getValue() != null &&
                                    !realEntry.getValue().equals(((Entry) entry).getValue()))) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    private Map.Entry<K, V> getValue(Object key) {
        if (!isEmpty()) {
            Map.Entry<K, V> mapEntry = treeSet.ceiling(new MapEntry<>((K) key, null));
            if (mapEntry != null && mapEntry.getKey() != null && mapEntry.getKey().equals(key)) {
                return mapEntry;
            }
        }
        return null;
    }

    private static class MapEntry<K, V> implements Map.Entry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public boolean equals(Object o) {
            if (o instanceof Map.Entry) {
                Map.Entry other = (Map.Entry) o;
                return
                        ObjectUtils.eqOrBothNull(this.key, other.getKey()) &&
                                ObjectUtils.eqOrBothNull(this.value, other.getValue());

            } else
                return false;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return this.value;
        }
    }

}
