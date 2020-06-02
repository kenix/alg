package net.kenix.alg.graph.impl

import net.kenix.alg.graph.Graph

/**
 * @author zzhao
 */
class AdjListGraphSpec extends GraphTCK {

  /**
   * 0-1>   4=5
   *   |\    \|
   *   2-3    6>>
   */
  static Graph create() {
    def g = new AdjListGraph(7)
    g.addEdge(0, 1)
    g.addEdge(1, 1)
    g.addEdge(1, 2)
    g.addEdge(2, 3)
    g.addEdge(3, 1)
    g.addEdge(4, 5)
    g.addEdge(5, 4)
    g.addEdge(5, 6)
    g.addEdge(6, 4)
    g.addEdge(6, 6)
    g.addEdge(6, 6)
    return g
  }

  @Override
  Graph subjectUnderTest(Map<String, Object> ctx) {
    def nov = ctx['numberOfVertices'] as int
    return ctx['empty'] ? new AdjListGraph(nov) : create()
  }
}
