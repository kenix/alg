/*
 * Created at 09:09 on 02.06.20
 */
package net.kenix.alg.connectivity.impl;

import net.kenix.alg.connectivity.UnionFind;

/** @author zzhao */
public class QuickUnionUF implements UnionFind {

  private final int[] id;

  public QuickUnionUF(int numberOfNodes) {
    this.id = new int[numberOfNodes];
    for (int i = 0; i < numberOfNodes; i++) {
      this.id[i] = i;
    }
  }

  private int root(int v) {
    while (this.id[v] != this.id[this.id[v]]) {
      v = this.id[v];
    }
    return this.id[v];
  }

  @Override
  public void union(int u, int w) {
    final int rootFirst = root(u);
    final int rootSecond = root(w);
    if (rootFirst == rootSecond) { // connected
      return;
    }
    this.id[rootFirst] = rootSecond;
  }

  @Override
  public boolean connected(int u, int w) {
    return root(u) == root(w);
  }

  @Override
  public int find(int u) {
    return root(u);
  }
}
