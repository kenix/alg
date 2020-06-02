/*
 * Created at 17:54 on 31.05.20
 */
package net.kenix.alg.collection.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** @author zzhao */
class ArrayQueueIterator<T> implements Iterator<T> {

  private final T[] items;

  private final int size;

  private final int first;

  private int index;

  public ArrayQueueIterator(T[] items, int size, int first) {
    this.items = items;
    this.size = size;
    this.first = first;
    this.index = 0;
  }

  @Override
  public boolean hasNext() {
    return this.index < this.size;
  }

  @Override
  public T next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    return this.items[(this.first + this.index++) % this.items.length];
  }
}
