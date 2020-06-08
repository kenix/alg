/*
 * Created at 11:04 on 06.06.20
 */
package net.kenix.alg.collection.map.impl;

/** @author zzhao */
class Node<K extends Comparable<K>, V> {

  private final K key;

  private V value;

  private Node<K, V> left;

  private Node<K, V> right;

  private int size;

  private Node(K key, V value) {
    this.key = key;
    this.value = value;
    this.size = 1;
  }

  public static <K extends Comparable<K>, V> Node<K, V> create(K key, V value) {
    return new Node<>(key, value);
  }

  public K key() {
    return this.key;
  }

  public V value() {
    return this.value;
  }

  void setValue(V value) {
    this.value = value;
  }

  public Node<K, V> left() {
    return left;
  }

  void setLeft(Node<K, V> left) {
    this.left = left;
  }

  public Node<K, V> right() {
    return right;
  }

  void setRight(Node<K, V> right) {
    this.right = right;
  }

  public int size() {
    return size;
  }

  void setSize(int size) {
    this.size = size;
  }
}
