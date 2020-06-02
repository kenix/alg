/*
 * Created at 13:43 on 31.05.20
 */
package net.kenix.alg.collection.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.kenix.alg.collection.Bag;

/** @author zzhao */
public class LinkedListBag<T> implements Bag<T> {

  private int n;

  private Node<T> first;

  public LinkedListBag() {
    this.n = 0;
    this.first = null;
  }

  private static class Node<T> {
    private T item;

    private Node<T> next;

    public Node(T item, Node<T> next) {
      this.item = item;
      this.next = next;
    }
  }

  @Override
  public boolean isEmpty() {
    return this.first == null;
  }

  @Override
  public void add(T t) {
    this.first = new Node<>(t, this.first);
    this.n++;
  }

  @Override
  public void addAll(T... ts) {
    if (ts != null) {
      for (int i = 0; i < ts.length; i++) {
        add(ts[i]);
      }
    }
  }

  @Override
  public void addAll(Iterable<T> it) {
    for (T t : it) {
      add(t);
    }
  }

  @Override
  public int size() {
    return this.n;
  }

  @Override
  public Iterator<T> iterator() {
    return new BagIterator<>(this.first);
  }

  private static class BagIterator<T> implements Iterator<T> {

    private Node<T> current;

    public BagIterator(Node<T> first) {
      this.current = first;
    }

    @Override
    public boolean hasNext() {
      return this.current != null;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      final T item = this.current.item;
      this.current = this.current.next;
      return item;
    }
  }
}
