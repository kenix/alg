package net.kenix.alg.graph


import net.kenix.alg.graph.impl.AdjListGraphSpec
import spock.lang.Specification

/**
 * @author zzhao
 */
class GraphSupportTest extends Specification {

  Graph g

  /**
   * 0-1>   4=5
   *   |\    \|
   *   2-3    6>>
   */
  def setup() {
    g = AdjListGraphSpec.create()
  }

  def "Degree"() {
    expect:
    GraphSupport.degree(g, vertex) == degree

    where:
    vertex << [0, 1, 2, 3, 4, 5, 6]
    degree << [1, 5, 2, 2, 3, 3, 6]
  }

  def "MaxDegree"() {
    expect:
    GraphSupport.maxDegree(g) == 6
  }

  def "AvgDegree"() {
    given:
    def degress = [1, 5, 2, 2, 3, 3, 6]
    expect:
    (GraphSupport.avgDegree(g) - degress.sum() / degress.size()) < 0.000001
  }

  def "NumberOfSelfLoops"() {
    // 1-1, 6-6, 6-6
    expect:
    GraphSupport.numberOfSelfLoops(g) == 3
  }
}
