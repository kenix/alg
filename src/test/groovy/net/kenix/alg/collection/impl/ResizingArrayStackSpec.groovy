package net.kenix.alg.collection.impl


import spock.lang.Specification

import static net.kenix.alg.collection.CollectionSupport.toString

/**
 * @author zzhao
 */
class ResizingArrayStackSpec extends Specification {

  def 'resizing array stack works'() {
    when:
    def stack = new ResizingArrayStack<Integer>()
    then:
    stack.isEmpty()

    when:
    stack.peek()
    then:
    thrown(NoSuchElementException)

    when:
    stack.pop()
    then:
    thrown(NoSuchElementException)

    when:
    0.upto(9) { stack.push(it as int) }
    then:
    !stack.isEmpty()
    stack.peek() == 9
    toString(stack) == '[9,8,7,6,5,4,3,2,1,0]'
    stack.pop() == 9
    stack.pop() == 8
    stack.pop() == 7
    stack.pop() == 6
    stack.pop() == 5
    toString(stack) == '[4,3,2,1,0]'

    when:
    10.upto(14) { stack.push(it as int) }
    then:
    toString(stack) == '[14,13,12,11,10,4,3,2,1,0]'
    stack.pop() == 14
    stack.pop() == 13
    stack.pop() == 12
    stack.pop() == 11
    stack.pop() == 10
    stack.pop() == 4
    stack.pop() == 3
    stack.pop() == 2
    toString(stack) == '[1,0]'

    when:
    15.upto(16) { stack.push(it as int) }
    then:
    toString(stack) == '[16,15,1,0]'
    stack.pop() == 16
    stack.pop() == 15
    stack.pop() == 1
    stack.pop() == 0
    stack.isEmpty()
    toString(stack) == '[]'

    when:
    stack.peek()
    then:
    thrown(NoSuchElementException)

    when:
    stack.pop()
    then:
    thrown(NoSuchElementException)
  }
}
