package com.leetcode.train;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Batman create on 2019-07-19 16:48
 * leetcode 146 LRU缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。
 * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，
 * 它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 */
class LRUCache {

    /**
     * 缓存的大小
     */
    private int cap;

    /**
     * 使用LinkedHashMap保持插入顺序 缓存的map
     *  1.插入顺序：先添加的在前面，后添加的在后面。修改操作不影响顺序
     * 2.访问顺序：所谓访问指的是get/put操作，对一个键执行get/put操作后，其对应的键值对会移动到链表末尾，所以最末尾的是最近访问的，
     * 最开始的是最久没有被访问的，这就是访问顺序。
     */
    private Map<Integer, Integer> map = new LinkedHashMap<>();

    public LRUCache(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            int value = map.get(key);

            // 保证每次查询后，都在末尾。因此可以先删除key然后再执行添加插入 可以保证在LinkedHashMap的链表末尾
            map.remove(key);
            map.put(key, value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        // 如果key值在LinkedHashMap中存储的有 需要进行先删除 然后再put进map中
        if (map.containsKey(key)) {
            map.remove(key);
        } else if (map.size() == cap) {
            Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
            iterator.next();
            iterator.remove();
        }
        map.put(key, value);
    }
}
