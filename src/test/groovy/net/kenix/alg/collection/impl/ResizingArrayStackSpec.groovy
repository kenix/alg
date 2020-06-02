package net.kenix.alg.collection.impl

import net.kenix.alg.collection.Stack

/**
 * @author zzhao
 */
class ResizingArrayStackSpec extends StackTCK {

  @Override
  Stack<Integer> stackUnderTest() {
    new ResizingArrayStack<Integer>()
  }
}
