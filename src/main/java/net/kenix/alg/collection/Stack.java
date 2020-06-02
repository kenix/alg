/*
 * Created at 17:34 on 31.05.20
 */
package net.kenix.alg.collection;

/** @author zzhao */
public interface Stack<T> extends Common<T>, Peekable<T> {

  void push(T t);

  T pop();
}
