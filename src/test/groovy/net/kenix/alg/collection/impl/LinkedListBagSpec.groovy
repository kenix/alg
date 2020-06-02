package net.kenix.alg.collection.impl

import net.kenix.alg.collection.impl.LinkedListBag
import spock.lang.Specification

/**
 * @author zzhao
 */
class LinkedListBagSpec extends Specification {

  def 'empty bag'() {
    when:
    def bag = new LinkedListBag<Integer>()
    then:
    bag.isEmpty()

    when:
    bag.add(1)
    then:
    !bag.isEmpty()
  }

  def 'bag size'() {
    when:
    def bag = new LinkedListBag<Integer>()
    then:
    bag.size() == 0

    when:
    bag.add(1)
    then:
    bag.size() == 1

    when:
    bag.add(1)
    then:
    bag.size() == 2
  }

  def 'bag items'() {
    when:
    def bag = new LinkedListBag<Integer>()
    then:
    !bag.iterator().hasNext()

    when:
    def items = [1, 2, 3, 4, 5]
    bag.addAll(items)
    then:
    bag.size() == items.size()
    bag.every { items.contains(it) }
  }
}
