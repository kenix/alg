/*
 * Created at 10:51 on 06.06.20
 */
package net.kenix.alg.collection.map;

import net.kenix.alg.collection.Countable;

/** @author zzhao */
public interface SymbolTable<K, V> extends Countable {

  void put(K key, V value);

  V get(K key);

  void delete(K key);

  boolean contains(K key);

  Iterable<K> keys();
}
