import java.util.*;

public class UnsortedArrayMinPQ<T> implements ExtrinsicMinPQ<T> {
    private List<PriorityNode<T>> items;

    public UnsortedArrayMinPQ() {
        items = new ArrayList<>();
    }

    public void add(T item, double priority) {
        items.add(new PriorityNode(item, priority));
    }

    public boolean contains(T item) {
        return items.contains(new PriorityNode(item, 1));
    }

    public T peekMin() { 
        if(items.size() == 0) throw new NoSuchElementException("Priority queue underflow");
        PriorityNode<T> min = items.get(0);
        for(int i = 1; i < items.size(); i++) {            
            PriorityNode<T> toCompare = items.get(i);
            if(toCompare.priority() < min.priority()) {
                min = toCompare;
            }
        }         
        return min.item();
    }

    public T removeMin() {
        // TODO: Your code here!
        T min = peekMin();
        items.remove(new PriorityNode<T>(min, 2));
        return min;
        //return null;
    }

    public void changePriority(T item, double priority) {
        // TODO: Your code here!
        for (PriorityNode pNode : items) {
            if (Objects.equals(pNode.item(), item)) {
                pNode.setPriority(priority);
            }
        }
        //return;
    }

    public int size() {
        // TODO: Your code here!
        return items.size();
        //return 0;
    }
}
