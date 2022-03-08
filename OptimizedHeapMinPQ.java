import java.util.*;

public class OptimizedHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private List<PriorityNode<T>> items;
    private Map<T, Integer> itemToIndex;

    public OptimizedHeapMinPQ() {
        items = new ArrayList<>();
        itemToIndex = new HashMap<>();
    }

    public void add(T item, double priority) {
        // Since array list auto resizes, we can just add it let list handle the size
        items.add(new PriorityNode(item, priority));
        // Put item:index in map
        int newItemIndex = items.size() - 1;
        itemToIndex.put(item, newItemIndex);
        // Percolate up it's percolate up/ swim/ sort up
        // check https://edstem.org/us/courses/3021/lessons/7201/slides/38007
        swim(newItemIndex);
    }

    public boolean contains(T item) {
        return itemToIndex.containsKey(item);
    }

    public T peekMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return items.get(0).item();
    }

    public T removeMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        T min = items.get(0).item();
        // Swap first with last
        swap(0, items.size() - 1);
        // Remove last
        items.remove(items.size() - 1);
        itemToIndex.remove(min);
        // Sink to retain variant
        sink(0);
        return min;
    }

    public void changePriority(T item, double priority) {
        int itemIndex = itemToIndex.get(item);
        PriorityNode<T> node = items.get(itemIndex);
        double lastPriority = node.priority();
        node.setPriority(priority);
        if(lastPriority < priority) {
            // If new priority is larger, we should sink
            sink(itemIndex);
        } else if (lastPriority > priority) {
            // Otherwise, we should swim
            swim(itemIndex);
        }
    }

    public int size() {
        // TODO: Your code here!
        return items.size();
    }

    public boolean isEmpty() {
        return items.size() == 0;
    }
    
    // Percolate up
    private void swim(int k) {
        // Get the parent's index (https://edstem.org/us/courses/3021/lessons/7201/slides/38008)
        int parentIndex = (k - 1) / 2;
        while (k != 0 && greater(parentIndex, k)) {
            // Keep swapping if parent priority is higher 
            swap(k, parentIndex);
            k = parentIndex;
            parentIndex = (k - 1) / 2;
        }
    }

    private void sink(int k) {
        while (true) {
            // Also from https://edstem.org/us/courses/3021/lessons/7201/slides/38008
            int leftChildIndex  = k * 2 + 1;
            int rightChildIndex = k * 2 + 2;
            if (leftChildIndex < size()) {
                // Get the child with lower priority
                int lower = leftChildIndex;
                if(rightChildIndex < size()) {
                    if(greater(leftChildIndex, rightChildIndex)) {
                        lower = rightChildIndex;
                    }
                } 
                // If priority of lower child is lower than node, sink down
                if(greater(k, lower)) {
                    swap(lower, k);
                    k = lower;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    // returns true if priority is higher
    private boolean greater(int i, int j) {
        return items.get(i).priority() - items.get(j).priority() > 0;
    }

    
    private void swap(int i, int j) {
        T itemA = items.get(i).item();
        T itemB = items.get(j).item();

        // Use built in method to swap
        Collections.swap(items, i, j);

        // Update index in hash map (swapped)
        itemToIndex.put(itemA, j);
        itemToIndex.put(itemB, i);
    }
}
