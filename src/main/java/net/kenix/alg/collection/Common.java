/*
 * Created at 17:40 on 31.05.20
 */
package net.kenix.alg.collection;

/** @author zzhao */
public interface Common<T> extends Iterable<T> {

  boolean isEmpty();

  int size();
}
