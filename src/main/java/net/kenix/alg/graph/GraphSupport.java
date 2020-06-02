/*
 * Created at 12:46 on 31.05.20
 */
package net.kenix.alg.graph;

import net.kenix.alg.graph.impl.AdjListGraph;

/** @author zzhao */
public final class GraphSupport {

  private GraphSupport() {
    throw new AssertionError("not for instantiation or inheritance");
  }

  public static Graph create(int v) {
    return new AdjListGraph(v);
  }

  public static int degree(Graph g, int v) {
    int degree = 0;
    for (Integer w : g.adj(v)) {
      degree++;
    }
    return degree;
  }

  public static int maxDegree(Graph g) {
    int maxDegree = 0;
    for (int i = 0; i < g.V(); i++) {
      maxDegree = Math.max(maxDegree, degree(g, i));
    }
    return maxDegree;
  }

  public static double avgDegree(Graph g) {
    return 2.0 * g.E() / g.V();
  }

  public static int numberOfSelfLoops(Graph g) {
    int loops = 0;
    for (int i = 0; i < g.V(); i++) {
      for (Integer w : g.adj(i)) {
        if (i == w) {
          loops++;
        }
      }
    }
    return loops / 2;
  }
}
