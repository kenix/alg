/*
 * Created at 19:35 on 31.05.20
 */
package net.kenix.alg.collection.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.kenix.alg.collection.Queue;

/** @author zzhao */
public class ResizingArrayQueue<T> implements Queue<T> {

  private T[] items;

  private int first; // queue head

  private int last; // queue tail

  public ResizingArrayQueue(int n) {
    this.items = (T[]) new Object[n];
    this.first = 0;
    this.last = 0;
  }

  public ResizingArrayQueue() {
    this(2);
  }

  @Override
  public void enqueue(T t) {
    if (size() == this.items.length) {
      resizeArray();
    }
    if (this.last == this.items.length) {
      this.last = 0;
    }
    this.items[this.last++] = t;
  }

  private void resizeArray() {
    final T[] ts = (T[]) new Object[this.items.length * 2];
    final int n = size();
    for (int i = 0; i < n; i++) {
      ts[i] = this.items[(this.first + i) % this.items.length];
    }
    this.items = ts;
    this.first = 0;
    this.last = n;
  }

  @Override
  public T dequeue() {
    assertNotEmpty();
    final T item = this.items[this.first];
    this.items[this.first] = null;
    this.first++;

    if (this.first == this.last) {
      this.first = 0;
      this.last = 0;
    } else if (this.first == this.items.length) {
      this.first = 0;
    }

    return item;
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public int size() {
    final int n = this.last - this.first;
    return n >= 0 ? n : this.items.length + n;
  }

  @Override
  public Iterator<T> iterator() {
    return new ArrayQueueIterator<>(this.items, size(), this.first);
  }

  private void assertNotEmpty() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue underflow");
    }
  }

  @Override
  public T peek() {
    assertNotEmpty();
    return this.items[this.first];
  }
}
