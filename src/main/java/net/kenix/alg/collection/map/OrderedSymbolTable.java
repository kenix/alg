/*
 * Created at 10:51 on 06.06.20
 */
package net.kenix.alg.collection.map;

/** @author zzhao */
public interface OrderedSymbolTable<K extends Comparable<K>, V> extends SymbolTable<K, V> {

  K min();

  K max();

  K floor(K key);

  K ceiling(K key);

  int rank(K key);

  K select(int rank);

  void deleteMin();

  void deleteMax();

  int size(K lo, K hi);

  Iterable<K> keys(K lo, K hi);
}
