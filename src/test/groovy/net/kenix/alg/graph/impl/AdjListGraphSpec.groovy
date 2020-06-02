package net.kenix.alg.graph.impl

import net.kenix.alg.graph.Graph
import spock.lang.Specification

/**
 * @author zzhao
 */
class AdjListGraphSpec extends Specification {

  def 'graph works'() {
    when:
    def g = new AdjListGraph(7)
    then:
    g.V() == 7
    g.E() == 0

    when:
    g = create()
    then:
    g.E() == 11
    g.adj(0).every { [1].contains(it) }
    g.adj(1).every { [0, 1, 2, 3].contains(it) }
    g.adj(2).every { [1, 3].contains(it) }
    g.adj(3).every { [1, 2].contains(it) }
    g.adj(4).every { [5, 6].contains(it) }
    g.adj(5).every { [4, 6].contains(it) }
    g.adj(6).every { [4, 5, 6].contains(it) }
  }

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
}
