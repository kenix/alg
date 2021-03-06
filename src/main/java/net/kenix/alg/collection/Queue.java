/*
 * Created at 19:27 on 31.05.20
 */
package net.kenix.alg.collection;

/** @author zzhao */
public interface Queue<T> extends Countable, Peekable<T>, Iterable<T> {

  void enqueue(T t);

  T dequeue();
}
