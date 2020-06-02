/*
 * Created at 15:20 on 31.05.20
 */
package net.kenix.alg.graph;

import lombok.Getter;

/** @author zzhao */
public final class DepthFirstSearch implements Paths {

  private final boolean[] marked;

  private final int[] crumb;

  @Getter private final int startVertex;

  public DepthFirstSearch(Graph g, int startVertex) {
    this.marked = new boolean[g.V()];
    this.crumb = new int[g.V()];
    this.startVertex = startVertex;

    visit(g, startVertex);
  }

  private void visit(Graph g, int s) {
    this.marked[s] = true;
    for (Integer v : g.adj(s)) {
      if (!this.marked[v]) {
        visit(g, v);
        this.crumb[v] = s;
      }
    }
  }

  @Override
  public boolean hasPathTo(int v) {
    return Paths.hasPathTo(this.marked, v);
  }

  @Override
  public Iterable<Integer> pathTo(int v) {
    return Paths.pathTo(this.marked, this.startVertex, this.crumb, v);
  }
}
