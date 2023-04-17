package net.kenix.alg.digraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import lombok.Getter;

/**
 * @author zzhao
 */
public class TopoSort {

  private final Map<String, Boolean> marked = new HashMap<>();

  @Getter
  private final List<String> topoOrder = new ArrayList<>();

  private final Stack<String> stack = new Stack<>();

  public TopoSort(Digraph g) {
    g.vertices().forEach(v -> {
      if (!isMarked(v)) {
        this.stack.push(v);
        while (!this.stack.isEmpty()) {
          final String it = this.stack.peek();
          mark(it);
          final int size = this.stack.size();
          g.adj(it).forEach(w -> {
            if (this.stack.contains(w)) {
              throw new IllegalStateException("cycle " + this.stack + ", " + it + "->" + w);
            }
            if (!isMarked(w)) {
              this.stack.push(w);
            }
          });
          if (size == this.stack.size()) {
            this.topoOrder.add(it);
            this.stack.pop();
          }
        }
      }
    });
  }

  private boolean isMarked(String v) {
    return Boolean.TRUE.equals(this.marked.get(v));
  }

  private void mark(String v) {
    this.marked.put(v, Boolean.TRUE);
  }
}
