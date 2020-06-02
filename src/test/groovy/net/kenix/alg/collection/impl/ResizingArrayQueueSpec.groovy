package net.kenix.alg.collection.impl

import net.kenix.alg.collection.Queue

/**
 * @author zzhao
 */
class ResizingArrayQueueSpec extends QueueTCK {

  @Override
  Queue<Integer> queueUnderTest() {
    new ResizingArrayQueue<>()
  }
}
