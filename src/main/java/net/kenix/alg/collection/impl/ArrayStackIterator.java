/*
 * Created at 17:54 on 31.05.20
 */
package net.kenix.alg.collection.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** @author zzhao */
class ArrayStackIterator<T> implements Iterator<T> {

  private final T[] items;

  private int index;

  public ArrayStackIterator(T[] items, int size) {
    this.items = items;
    this.index = size - 1;
  }

  @Override
  public boolean hasNext() {
    return this.index >= 0;
  }

  @Override
  public T next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    final T item = this.items[this.index];
    this.index--;
    return item;
  }
}
