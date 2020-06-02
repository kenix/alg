/*
 * Created at 17:44 on 31.05.20
 */
package net.kenix.alg.collection.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.kenix.alg.collection.Stack;

/** @author zzhao */
public class ResizingArrayStack<T> implements Stack<T> {

  private int n;

  private T[] items;

  public ResizingArrayStack(int n) {
    this.n = 0;
    this.items = (T[]) new Object[n];
  }

  public ResizingArrayStack() {
    this(2);
  }

  @Override
  public void push(T t) {
    if (this.n == this.items.length) {
      resizeArray();
    }
    this.items[this.n] = t;
    this.n++;
  }

  private void resizeArray() {
    final T[] ts = (T[]) new Object[this.items.length * 2];
    System.arraycopy(this.items, 0, ts, 0, this.items.length);
    this.items = ts;
  }

  @Override
  public T pop() {
    assertNotEmpty();
    final T item = this.items[--this.n];
    this.items[this.n] = null;
    return item;
  }

  private void assertNotEmpty() {
    if (isEmpty()) {
      throw new NoSuchElementException("Stack underflow");
    }
  }

  @Override
  public T peek() {
    assertNotEmpty();
    return this.items[this.n - 1];
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public int size() {
    return this.n;
  }

  @Override
  public Iterator<T> iterator() {
    return new ArrayStackIterator<>(this.items, this.n);
  }
}
