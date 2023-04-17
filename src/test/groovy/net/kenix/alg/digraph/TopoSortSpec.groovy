package net.kenix.alg.digraph

import spock.lang.Specification

/**
 * @author zzhao
 */
class TopoSortSpec extends Specification {

  def 'topo sort 1 node'() {
    given:
    def g = new Digraph()
    g.addVertice('1')

    when:
    def dfs = new TopoSort(g)
    then:
    dfs.topoOrder == ['1']
  }

  def 'topo sort 2 nodes'() {
    given:
    def g = new Digraph()
    g.addEdge('1', '2')

    when:
    def dfs = new TopoSort(g)
    then:
    dfs.topoOrder == ['2', '1']
  }

  def 'topological sort'() {
    given:
    def g = new Digraph()
    /**
     * 1    7  9
     * |\   |
     * 2 3  8
     * |/|
     * 4-5
     *   |
     *   6
     */
    g.addEdge('1', '2')
    g.addEdge('1', '3')
    g.addEdge('2', '4')
    g.addEdge('3', '4')
    g.addEdge('3', '5')
    g.addEdge('4', '5')
    g.addEdge('5', '6')
    g.addEdge('7', '8')
    g.addVertice('9')

    when:
    def dfs = new TopoSort(g)
    then:
    dfs.topoOrder == ['6', '5', '4', '3', '2', '1', '8', '7', '9']

    when:
    def l = ['1', '5', '6', '8']
    Collections.sort(l, Comparator.comparingInt({ x -> dfs.topoOrder.indexOf(x) }))
    then:
    ['6', '5', '1', '8'] == l
  }

  def 'topological sort cycle'() {
    given:
    def g = new Digraph()
    /**
     * 1
     * |\
     * 2 3
     * |/|
     * 4-5
     *   |
     *   6
     */
    g.addEdge('1', '2')
    g.addEdge('1', '3')
    g.addEdge('2', '4')
    g.addEdge('3', '4')
    g.addEdge('3', '5')
    g.addEdge('4', '5')
    g.addEdge('4', '1')
    g.addEdge('5', '6')

    when:
    def dfs = new TopoSort(g)
    then:
    def e = thrown(IllegalStateException)
    println e
  }
}
