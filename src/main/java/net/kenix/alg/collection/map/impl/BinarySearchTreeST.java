/*
 * Created at 10:59 on 06.06.20
 */
package net.kenix.alg.collection.map.impl;

import net.kenix.alg.collection.Queue;
import net.kenix.alg.collection.impl.ResizingArrayQueue;
import net.kenix.alg.collection.map.OrderedSymbolTable;

/** @author zzhao */
public class BinarySearchTreeST<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {

  private Node<K, V> root;

  public BinarySearchTreeST() {
    this.root = null;
  }

  @Override
  public void put(K key, V value) {
    validateKey(key);
    this.root = put(this.root, key, value);
  }

  private Node<K, V> put(Node<K, V> node, K key, V value) {
    if (node == null) {
      return Node.create(key, value);
    }

    final int cmp = key.compareTo(node.key());
    if (cmp == 0) {
      node.setValue(value);
    } else if (cmp > 0) {
      node.setRight(put(node.right(), key, value));
    } else {
      node.setLeft(put(node.left(), key, value));
    }

    node.setSize(1 + size(node.left()) + size(node.right()));
    return node;
  }

  @Override
  public V get(K key) {
    validateKey(key);
    Node<K, V> x = this.root;
    while (x != null) {
      final int cmp = key.compareTo(x.key());
      if (cmp < 0) {
        x = x.left();
      } else if (cmp > 0) {
        x = x.right();
      } else {
        return x.value();
      }
    }

    return null;
  }

  private void validateKey(K key) {
    if (key == null) {
      throw new IllegalArgumentException("key cannot be null");
    }
  }

  @Override
  public void delete(K key) {
    validateKey(key);
    this.root = delete(this.root, key);
  }

  // Hibbard deletion
  private Node<K, V> delete(Node<K, V> node, K key) {
    if (node == null) {
      return null;
    }

    final int cmp = key.compareTo(node.key());
    if (cmp < 0) {
      node.setLeft(delete(node.left(), key));
    } else if (cmp > 0) {
      node.setRight(delete(node.right(), key));
    } else {
      if (node.left() == null) {
        return node.right();
      }
      if (node.right() == null) {
        return node.left();
      }

      Node<K, V> t = node;
      node = findMinNode(t.right());
      node.setRight(deleteMin(t.right())); // the order of this and following statement is import
      node.setLeft(t.left()); // why? Otherwise node is not the min anymore
    }

    node.setSize(1 + size(node.left()) + size(node.right()));
    return node;
  }

  @Override
  public boolean contains(K key) {
    return get(key) != null;
  }

  @Override
  public Iterable<K> keys() {
    final ResizingArrayQueue<K> queue = new ResizingArrayQueue<>();
    inorder(this.root, queue);
    return queue;
  }

  private void inorder(Node<K, V> node, Queue<K> queue) {
    if (node == null) {
      return;
    }
    inorder(node.left(), queue);
    queue.enqueue(node.key());
    inorder(node.right(), queue);
  }

  @Override
  public boolean isEmpty() {
    return this.root == null;
  }

  @Override
  public int size() {
    return isEmpty() ? 0 : size(this.root);
  }

  private int size(Node<K, V> node) {
    return node == null ? 0 : node.size();
  }

  @Override
  public K min() {
    if (isEmpty()) {
      return null;
    }

    return getLeafKey(this.root, true);
  }

  private Node<K, V> findMinNode(Node<K, V> node) {
    if (node == null) {
      return null;
    }

    if (node.left() == null) {
      return node;
    }

    return findMinNode(node.left());
  }

  private K getLeafKey(Node<K, V> node, boolean left) {
    K theKey = node.key();
    while (node != null) {
      theKey = node.key();
      node = left ? node.left() : node.right();
    }

    return theKey;
  }

  @Override
  public K max() {
    if (isEmpty()) {
      return null;
    }

    return getLeafKey(this.root, false);
  }

  @Override
  public K floor(K key) {
    validateKey(key);
    final Node<K, V> node = floor(this.root, key);
    return node == null ? null : node.key();
  }

  private Node<K, V> floor(Node<K, V> node, K key) {
    if (node == null) {
      return null;
    }

    final int cmp = key.compareTo(node.key());
    if (cmp == 0) {
      return node;
    }

    if (cmp < 0) {
      return floor(node.left(), key);
    }

    final Node<K, V> floor = floor(node.right(), key);
    return floor == null ? node : floor;
  }

  @Override
  public K ceiling(K key) {
    validateKey(key);
    final Node<K, V> node = ceiling(this.root, key);
    return node == null ? null : node.key();
  }

  private Node<K, V> ceiling(Node<K, V> node, K key) {
    if (node == null) {
      return null;
    }

    final int cmp = key.compareTo(node.key());
    if (cmp == 0) {
      return node;
    }

    if (cmp > 0) {
      return ceiling(node.right(), key);
    }

    final Node<K, V> ceiling = ceiling(node.left(), key);
    return ceiling == null ? node : ceiling;
  }

  @Override
  public int rank(K key) {
    validateKey(key);
    return rank(key, this.root);
  }

  private int rank(K key, Node<K, V> node) {
    if (node == null) {
      return 0;
    }

    final int cmp = key.compareTo(node.key());
    return cmp < 0
        ? rank(key, node.left())
        : cmp > 0 ? 1 + size(node.left()) + rank(key, node.right()) : size(node.left());
  }

  @Override
  public K select(int rank) {
    return select(this.root, rank);
  }

  private K select(Node<K, V> node, int rank) {
    if (node == null) {
      return null;
    }
    final int rn = size(node.left());
    if (rank == rn) {
      return node.key();
    } else if (rank < rn) {
      return select(node.left(), rank);
    } else {
      return select(node.right(), rank - rn - 1);
    }
  }

  @Override
  public void deleteMin() {
    this.root = deleteMin(this.root);
  }

  private Node<K, V> deleteMin(Node<K, V> node) {
    if (node == null) {
      return null;
    }
    if (node.left() == null) {
      return node.right();
    }

    node.setLeft(deleteMin(node.left()));
    node.setSize(1 + size(node.left()) + size(node.right()));
    return node;
  }

  @Override
  public void deleteMax() {
    this.root = deleteMax(this.root);
  }

  private Node<K, V> deleteMax(Node<K, V> node) {
    if (node == null) {
      return null;
    }
    if (node.right() == null) {
      return node.left();
    }

    node.setRight(deleteMax(node.right()));
    node.setSize(1 + size(node.left()) + size(node.right()));
    return node;
  }

  @Override
  public int size(K lo, K hi) {
    validateKey(lo);
    validateKey(hi);

    final int lr = rank(lo);
    final int hr = rank(hi);

    return hr < lr
        ? 0 // higher key is less then lower key
        : contains(hi) ? hr - lr + 1 : hr - lr; // inclusion of higher key if it is in tree
  }

  @Override
  public Iterable<K> keys(K lo, K hi) {
    validateKey(lo);
    validateKey(hi);

    final ResizingArrayQueue<K> queue = new ResizingArrayQueue<>();
    keys(this.root, lo, hi, queue);
    return queue;
  }

  private void keys(Node<K, V> node, K lo, K hi, Queue<K> queue) {
    if (node == null) {
      return;
    }
    final int cmpLo = lo.compareTo(node.key());
    final int cmpHi = hi.compareTo(node.key());

    if (cmpLo < 0) { // include keys lower than this node's key
      keys(node.left(), lo, hi, queue);
    }
    if (cmpLo <= 0 && cmpHi >= 0) {
      queue.enqueue(node.key());
    }
    if (cmpHi > 0) {
      keys(node.right(), lo, hi, queue);
    }
  }
}
