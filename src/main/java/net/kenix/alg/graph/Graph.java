/*
 * Created at 12:32 on 31.05.20
 */
package net.kenix.alg.graph;

/** @author zzhao */
public interface Graph {

  /**
   * Add an edge from v to w
   *
   * @param v a vertex
   * @param w a vertex
   */
  void addEdge(int v, int w);

  /**
   * @param v a vertex
   * @return vertices adjacent to v
   */
  Iterable<Integer> adj(int v);

  /** @return number of vertices */
  int V();

  /** @return number of edges */
  int E();
}
