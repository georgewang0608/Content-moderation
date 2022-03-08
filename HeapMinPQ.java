import java.util.*;

public class HeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private final PriorityQueue<PriorityNode<T>> pq;

    public HeapMinPQ() {
        pq = new PriorityQueue<>(Comparator.comparingDouble(PriorityNode::priority));
    }

    public void add(T item, double priority) {
         // TODO: Your code here!
         pq.add(new PriorityNode(item, priority));
         //pq.add(item);
    }

    public boolean contains(T item) {
        // TODO: Your code here!
        return pq.contains(new PriorityNode(item, 1));
    }

    public T peekMin() {
        // TODO: Your code here!           
        return pq.peek().item();
    }

    public T removeMin() {
        // TODO: Your code here!
        return pq.remove().item();
    }

    public void changePriority(T item, double priority) {
        // TODO: Your code here!
        pq.remove(new PriorityNode(item, priority));
        pq.add(new PriorityNode(item, priority));
    }

    public int size() {
        // TODO: Your code here!
        return pq.size();
    }
}
