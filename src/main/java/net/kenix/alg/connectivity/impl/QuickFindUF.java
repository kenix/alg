/*
 * Created at 09:09 on 02.06.20
 */
package net.kenix.alg.connectivity.impl;

import net.kenix.alg.connectivity.UnionFind;

/** @author zzhao */
public class QuickFindUF implements UnionFind {

  private final int[] id;

  public QuickFindUF(int numberOfNodes) {
    this.id = new int[numberOfNodes];
    for (int i = 0; i < numberOfNodes; i++) {
      this.id[i] = i;
    }
  }

  @Override
  public void union(int u, int w) {
    if (connected(u, w)) {
      return;
    }
    final int idFirst = this.id[u];
    final int idSecond = this.id[w];
    for (int i = 0; i < this.id.length; i++) {
      if (this.id[i] == idFirst) {
        this.id[i] = idSecond;
      }
    }
  }

  @Override
  public boolean connected(int u, int w) {
    return this.id[u] == this.id[w];
  }

  @Override
  public int find(int u) {
    return this.id[u];
  }
}
