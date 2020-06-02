package net.kenix.alg.graph.impl

import net.kenix.alg.TCK
import net.kenix.alg.graph.Graph
import spock.lang.Specification

/**
 * @author zzhao
 */
abstract class GraphTCK extends Specification implements TCK<Graph> {

  def 'graph works'() {
    when:
    def g = subjectUnderTest([numberOfVertices: 7, empty: true])
    then:
    g.V() == 7
    g.E() == 0

    when:
    g = subjectUnderTest([numberOfVertices: 7])
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
}
