package com.gt;

/**
 * @Author Mr.G
 * @Date 2019-4-27 18:36
 * @Description 编码实现HashMap实现过程
 */
public interface Map<K, V> {

    public V get(K k);
    public void put(K k, V v);

    public int size();

    public interface Entry<K, V>{
        public K getKey();
        public V getValue();
    }
}
