/*
 * Created at 17:36 on 31.05.20
 */
package net.kenix.alg.collection;

/** @author zzhao */
public interface Bag<T> extends Common<T> {

  void add(T t);

  void addAll(T... ts);

  void addAll(Iterable<T> it);
}
