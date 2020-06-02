/*
 * Created at 12:46 on 31.05.20
 */
package net.kenix.alg.graph.impl;

import net.kenix.alg.collection.impl.LinkedListBag;
import net.kenix.alg.graph.Graph;

/** @author zzhao */
public class AdjListGraph implements Graph {

  private final int vertexCount;

  private final LinkedListBag<Integer>[] adj;

  public AdjListGraph(int vertexCount) {
    this.vertexCount = vertexCount;
    this.adj = (LinkedListBag<Integer>[]) new LinkedListBag[vertexCount];
    for (int i = 0; i < this.adj.length; i++) {
      this.adj[i] = new LinkedListBag<>();
    }
  }

  @Override
  public void addEdge(int v, int w) {
    this.adj[v].add(w);
    this.adj[w].add(v);
  }

  @Override
  public Iterable<Integer> adj(int v) {
    return this.adj[v];
  }

  @Override
  public int V() {
    return this.vertexCount;
  }

  @Override
  public int E() {
    int cnt = 0;
    for (int i = 0; i < this.adj.length; i++) {
      cnt += this.adj[i].size();
    }
    return cnt / 2;
  }
}
