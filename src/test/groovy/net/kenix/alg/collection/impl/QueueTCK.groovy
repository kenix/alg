package net.kenix.alg.collection.impl

import spock.lang.Specification

import static net.kenix.alg.collection.CollectionSupport.toString

/**
 * @author zzhao
 */
abstract class QueueTCK extends Specification {

  abstract net.kenix.alg.collection.Queue<Integer> queueUnderTest();

  def 'resizing array queue works'() {
    when:
    def queue =queueUnderTest()
    then:
    queue.isEmpty()

    when:
    queue.peek()
    then:
    thrown(NoSuchElementException)

    when:
    queue.dequeue()
    then:
    thrown(NoSuchElementException)

    when:
    queue.enqueue(0)
    queue.dequeue()
    queue.enqueue(1)
    queue.dequeue()
    queue.enqueue(2)
    queue.enqueue(3)
    then:
    queue.dequeue() == 2
    queue.dequeue() == 3

    when:
    0.upto(9) { queue.enqueue(it as int) }
    then:
    !queue.isEmpty()
    queue.peek() == 0
    toString(queue) == '[0,1,2,3,4,5,6,7,8,9]'
    queue.dequeue() == 0
    queue.dequeue() == 1
    queue.dequeue() == 2
    queue.dequeue() == 3
    queue.dequeue() == 4
    toString(queue) == '[5,6,7,8,9]'

    when:
    10.upto(14) { queue.enqueue(it as int) }
    then:
    toString(queue) == '[5,6,7,8,9,10,11,12,13,14]'
    queue.dequeue() == 5
    queue.dequeue() == 6
    queue.dequeue() == 7
    queue.dequeue() == 8
    queue.dequeue() == 9
    queue.dequeue() == 10
    queue.dequeue() == 11
    queue.dequeue() == 12
    toString(queue) == '[13,14]'

    when:
    15.upto(16) { queue.enqueue(it as int) }
    toString(queue) == '[13,14,15,16]'
    queue.dequeue() == 13
    queue.dequeue() == 14
    queue.dequeue() == 15
    queue.dequeue() == 16
    then:
    queue.isEmpty()
    toString(queue) == '[]'

    when:
    queue.peek()
    then:
    thrown(NoSuchElementException)

    when:
    queue.dequeue()
    then:
    thrown(NoSuchElementException)
  }
}
