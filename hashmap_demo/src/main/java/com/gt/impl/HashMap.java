package com.gt.impl;

import com.gt.Map;

/**
 * @Author Mr.G
 * @Date 2019-4-27 18:40
 * @Description 编码实现HashMap实现过程
 */
public class HashMap<K, V> implements Map<K, V>{

    private int size = 0;
    private Entry<K, V>[] table = null;
    private static int defalutLength = 16;

    public HashMap() {
        this.table = new Entry[defalutLength];
    }

    @Override
    public V get(K k) {
        if(k == null){
            for (Entry<K, V> e = table[0]; e != null; e = e.next) {
                if (e.key == null){
                    return e.value;
                }
            }
        }else{
            int hash = hash(k);
            int index = getIndex(hash, defalutLength);
            for (Entry<K, V> e = table[index]; e != null; e = e.next) {
                if (e.hash == hash && (e.key == k || e.key.equals(k))){
                    return e.value;
                }
            }
        }
        return null;
    }

    @Override
    public void put(K k, V v) {
        if(k == null) {
            for (Entry<K, V> e = table[0]; e != null; e = e.next) {
                if (e.key == null){
                    e.value = v;
                    return;
                }
            }
            addEntry(-1, k, v, 0);
            size++;
        }else{
            int hash = hash(k);
            int index = getIndex(hash, defalutLength);
            for (Entry<K, V> e = table[index]; e != null; e = e.next) {
                if (e.hash == hash && (e.key == k || e.key.equals(k))){
                    e.value = v;
                    return;
                }
            }
            addEntry(hash, k, v, index);
            size++;
        }
    }

    public void addEntry(int hash, K k, V v, int index){
        if(table[index] == null){
            table[index] = new Entry<K, V>(k, v , null, hash);
        }else{
            Entry<K, V> enrty = table[index];
            table[index] = new Entry<K, V>(k, v , enrty, hash);
        }

    }

    @Override
    public int size() {
        return this.size;
    }

    public int hash(K k){
        return k.hashCode();
    }

    public int getIndex(int hash, int length){
        int index = hash % (defalutLength - 1);
        return Math.abs(index);
    }

    class Entry<K, v> implements Map.Entry<K, V>{
        private K key;
        private V value;
        private Entry<K, v> next;
        private int hash;

        public Entry(K k, V v, Entry<K, v> next, int hash) {
            this.key = k;
            this.value = v;
            this.next = next;
            this.hash = hash;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }
    }

}
