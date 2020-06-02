/*
 * Created at 15:20 on 31.05.20
 */
package net.kenix.alg.graph;

import java.util.Arrays;
import lombok.Getter;
import net.kenix.alg.collection.Queue;
import net.kenix.alg.collection.impl.ResizingArrayQueue;

/** @author zzhao */
public final class BreadthFirstSearch implements Paths {

  private final boolean[] marked;

  private final int[] crumb;

  private final int[] dist;

  @Getter private final int startVertex;

  public BreadthFirstSearch(Graph g, int startVertex) {
    this.marked = new boolean[g.V()];
    this.crumb = new int[g.V()];
    this.dist = new int[g.V()];
    Arrays.fill(this.dist, -1);
    this.startVertex = startVertex;

    final Queue<Integer> queue = new ResizingArrayQueue<>();
    queue.enqueue(startVertex);
    this.dist[startVertex] = 0;
    this.marked[startVertex] = true;
    visit(queue, g);
  }

  private void visit(Queue<Integer> queue, Graph g) {
    while (!queue.isEmpty()) {
      final Integer v = queue.dequeue();
      for (Integer w : g.adj(v)) {
        if (!this.marked[w]) {
          this.marked[w] = true;
          this.crumb[w] = v;
          this.dist[w] = this.dist[v] + 1;
          queue.enqueue(w);
        }
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

  public int distTo(int v) {
    return this.dist[v];
  }
}
