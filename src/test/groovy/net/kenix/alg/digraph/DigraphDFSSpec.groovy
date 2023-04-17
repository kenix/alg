package net.kenix.alg.digraph

import spock.lang.Specification

/**
 * @author zzhao
 */
class DigraphDFSSpec extends Specification {

  def 'no self cycle'() {
    given:
    def g = new Digraph()

    when:
    g.addEdge('1', '1')
    then:
    thrown(IllegalStateException)
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
    def dfs = new DigraphDFS(g)
    then:
    dfs.topoOrder == ['6', '5', '4', '2', '3', '1', '8', '7', '9']

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
    def dfs = new DigraphDFS(g)
    then:
    def e = thrown(IllegalStateException)
    println e
  }
}
