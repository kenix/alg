/*
 * Created at 09:09 on 02.06.20
 */
package net.kenix.alg.connectivity.impl;

import java.util.Arrays;
import net.kenix.alg.connectivity.UnionFind;

/** @author zzhao */
public class WeightedQuickUnionUF implements UnionFind {

  private final int[] id;

  private final int[] size;

  public WeightedQuickUnionUF(int numberOfNodes) {
    this.id = new int[numberOfNodes];
    for (int i = 0; i < numberOfNodes; i++) {
      this.id[i] = i;
    }
    this.size = new int[numberOfNodes];
    Arrays.fill(this.size, 1);
  }

  private int root(int v) {
    while (this.id[v] != this.id[this.id[v]]) {
      v = this.id[v];
    }
    return this.id[v];
  }

  @Override
  public void union(int u, int w) {
    final int r1 = root(u);
    final int r2 = root(w);

    if (r1 == r2) { // connected
      return;
    }

    if (this.size[r1] > this.size[r2]) { // join the first tree
      this.id[r2] = this.id[r1];
      this.size[r1] += this.size[r2];
    } else { // join the second tree
      this.id[r1] = this.id[r2];
      this.size[r2] += this.size[r1];
    }
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
