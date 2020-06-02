/*
 * Created at 15:21 on 31.05.20
 */
package net.kenix.alg.graph;

import java.util.Collections;
import net.kenix.alg.collection.Stack;
import net.kenix.alg.collection.impl.ResizingArrayStack;

/** @author zzhao */
public interface Paths {

  boolean hasPathTo(int v);

  Iterable<Integer> pathTo(int v);

  static boolean hasPathTo(boolean[] marked, int v) {
    return marked[v];
  }

  static Iterable<Integer> pathTo(boolean[] marked, int startVertex, int[] crumb, int v) {
    if (!hasPathTo(marked, v)) {
      return Collections.emptyList();
    }

    final Stack<Integer> path = new ResizingArrayStack<>();
    for (int i = v; i != startVertex; i = crumb[i]) {
      path.push(i);
    }
    path.push(startVertex);
    return path;
  }
}
