package net.kenix.alg.collection.map.impl

import net.kenix.alg.TCK
import net.kenix.alg.collection.map.OrderedSymbolTable
import net.kenix.alg.collection.map.SymbolTable
import spock.lang.Specification

import static net.kenix.alg.collection.CollectionSupport.toString

/**
 * @author zzhao
 */
abstract class OrderedSymbolTableTCK extends Specification implements TCK<OrderedSymbolTable<String, Integer>> {

  /**
   *        S
   *      /   \
   *     E     X
   *  /    \
   * A     R
   *  \   /
   *  C  H
   *      \
   *       M
   */
  private static int construct(SymbolTable<String, Integer> st) {
    def value = 0
    st.put('S', value++)
    st.put('E', value++)
    st.put('A', value++)
    st.put('X', value++)
    st.put('R', value++)
    st.put('C', value++)
    st.put('H', value++)
    st.put('M', value++)
    return value
  }

  def 'ordered symbol table works'() {
    when:
    def ost = subjectUnderTest([:])
    then:
    ost.isEmpty()
    ost.size() == 0

    when:
    def value = construct(ost)
    then:
    !ost.isEmpty()
    ost.size() == value

    ost.get('S') == 0
    ost.get('M') == value - 1

    ost.min() == 'A'
    ost.max() == 'X'

    ost.floor('E') == 'E'
    ost.floor('G') == 'E'
    ost.floor('0') == null

    ost.ceiling('T') == 'X'
    ost.ceiling('X') == 'X'
    ost.ceiling('Z') == null

    ost.rank('B') == 1
    ost.rank('A') == 0
    ost.rank('C') == 1
    ost.rank('D') == 2
    ost.rank('T') == 7
    ost.rank('X') == 7
    ost.rank('Z') == 8

    ost.select(0) == 'A'
    ost.select(8) == null
    ost.select(7) == 'X'
    ost.select(2) == 'E'
    ost.select(1) == 'C'
  }

  def 'range queries work'() {
    when:
    def ost = subjectUnderTest([:])
    construct(ost)
    then:
    ost.size(lo, hi) == size
    toString(ost.keys(lo, hi)) == keys

    where:
    lo  | hi  || size | keys
    '0' | '9' || 0    | '[]'
    '0' | 'A' || 1    | '[A]'
    '0' | 'B' || 1    | '[A]'
    '0' | 'C' || 2    | '[A,C]'
    'A' | 'A' || 1    | '[A]'
    'A' | 'B' || 1    | '[A]'
    'A' | 'C' || 2    | '[A,C]'
    'A' | 'D' || 2    | '[A,C]'
    'A' | 'E' || 3    | '[A,C,E]'
    'A' | 'F' || 3    | '[A,C,E]'
    'A' | 'G' || 3    | '[A,C,E]'
    'A' | 'H' || 4    | '[A,C,E,H]'
    'A' | 'I' || 4    | '[A,C,E,H]'
    'A' | 'J' || 4    | '[A,C,E,H]'
    'A' | 'K' || 4    | '[A,C,E,H]'
    'A' | 'L' || 4    | '[A,C,E,H]'
    'A' | 'M' || 5    | '[A,C,E,H,M]'
    'A' | 'N' || 5    | '[A,C,E,H,M]'
    'A' | 'O' || 5    | '[A,C,E,H,M]'
    'A' | 'P' || 5    | '[A,C,E,H,M]'
    'A' | 'Q' || 5    | '[A,C,E,H,M]'
    'A' | 'R' || 6    | '[A,C,E,H,M,R]'
    'A' | 'S' || 7    | '[A,C,E,H,M,R,S]'
    'A' | 'T' || 7    | '[A,C,E,H,M,R,S]'
    'A' | 'U' || 7    | '[A,C,E,H,M,R,S]'
    'A' | 'V' || 7    | '[A,C,E,H,M,R,S]'
    'A' | 'W' || 7    | '[A,C,E,H,M,R,S]'
    'A' | 'X' || 8    | '[A,C,E,H,M,R,S,X]'
    'A' | 'Y' || 8    | '[A,C,E,H,M,R,S,X]'
    'A' | 'Z' || 8    | '[A,C,E,H,M,R,S,X]'
    'A' | 'a' || 8    | '[A,C,E,H,M,R,S,X]'
    '0' | 'a' || 8    | '[A,C,E,H,M,R,S,X]'
    'C' | 'B' || 0    | '[]'
    'C' | 'D' || 1    | '[C]'
    'C' | 'E' || 2    | '[C,E]'
    'C' | 'F' || 2    | '[C,E]'
    'C' | 'X' || 7    | '[C,E,H,M,R,S,X]'
    'C' | 'Z' || 7    | '[C,E,H,M,R,S,X]'
    'R' | 'S' || 2    | '[R,S]'
    'R' | 'T' || 2    | '[R,S]'
    'R' | 'W' || 2    | '[R,S]'
    'R' | 'X' || 3    | '[R,S,X]'
    'R' | 'Y' || 3    | '[R,S,X]'
    'R' | 'a' || 3    | '[R,S,X]'
    'R' | '0' || 0    | '[]'
    'S' | '0' || 0    | '[]'
    'S' | 'A' || 0    | '[]'
    'S' | 'S' || 1    | '[S]'
    'S' | 'T' || 1    | '[S]'
    'S' | 'W' || 1    | '[S]'
    'S' | 'X' || 2    | '[S,X]'
    'S' | 'Y' || 2    | '[S,X]'
    'S' | 'a' || 2    | '[S,X]'
    'X' | '0' || 0    | '[]'
    'X' | 'A' || 0    | '[]'
    'X' | 'W' || 0    | '[]'
    'X' | 'X' || 1    | '[X]'
    'X' | 'Y' || 1    | '[X]'
    'X' | 'a' || 1    | '[X]'
    'Z' | '0' || 0    | '[]'
    'Z' | 'a' || 0    | '[]'
  }

  def 'deletion works'() {
    when:
    def ost = subjectUnderTest([:])
    construct(ost)
    then:
    !ost.isEmpty()
    ost.min() == 'A'
    ost.max() == 'X'

    when:
    ost.deleteMin()
    then:
    ost.min() == 'C'

    when:
    ost.deleteMax()
    then:
    ost.max() == 'S'

    when:
    ost.delete('E')
    then:
    toString(ost.keys()) == '[C,H,M,R,S]'

    when:
    ost.delete('R')
    then:
    toString(ost.keys()) == '[C,H,M,S]'

    when:
    ost.delete('C')
    then:
    toString(ost.keys()) == '[H,M,S]'

    when:
    ost.delete('H')
    then:
    toString(ost.keys()) == '[M,S]'

    when:
    ost.delete('S')
    then:
    toString(ost.keys()) == '[M]'

    when:
    ost.delete('M')
    then:
    toString(ost.keys()) == '[]'
  }
}
