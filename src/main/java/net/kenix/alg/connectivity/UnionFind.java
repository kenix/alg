/*
 * Created at 09:05 on 02.06.20
 */
package net.kenix.alg.connectivity;

/** @author zzhao */
public interface UnionFind {

  /**
   * Connects the given two nodes
   *
   * @param u
   * @param w
   */
  void union(int u, int w);

  /**
   * @param u
   * @param w
   * @return true if the given two nodes are connected, false otherwise
   */
  boolean connected(int u, int w);

  /**
   * @param u
   * @return the representative of the connected component which the given node is in.
   */
  int find(int u);
}
