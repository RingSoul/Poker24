import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> implements DequeInterface<T>, Iterable<T> {

    // for Node, see the private nested class implementation at the bottom of the outer class
    private Node firstNode; // front of deque; its previousNode is always null
    private Node lastNode; // back of deque; its nextNode is always null
    private int numEntries;

    public Deque() {
        firstNode = null;
        lastNode = null;
        numEntries = 0;
    }

    @Override
    public void addToFront(T data) {
        if (isEmpty())
            firstNode = new Node(null, data, null);
        else {
            Node newNode = new Node(null, data, firstNode);
            firstNode.setPreviousNode(newNode);
            firstNode = newNode;
        }
        numEntries++;
    }

    @Override
    public void addToBack(T data) {
        if (isEmpty())
            lastNode = new Node(null, data, null);
        else {
            Node newNode = new Node(lastNode, data, null);
            lastNode.setNextNode(newNode);
            lastNode = newNode;
        }
        numEntries++;
    }

    @Override
    public T removeFromFront() {
        if (isEmpty())
            return null;
        T originalFirst = firstNode.getData();
        firstNode = firstNode.getNextNode(); // new firstNode
        if (firstNode == null) // if firstNode is null AFTER getNextNode, it means the deque only had one element
            lastNode = null; // so we reset lastNode as well
        else
            firstNode.setPreviousNode(null); // new firstNode
        numEntries--;
        return originalFirst;
    }

    @Override
    public T removeFromBack() {
        if (isEmpty())
            return null;
        T originalLast = lastNode.getData();
        lastNode = lastNode.getPreviousNode();
        if (lastNode == null) // if lastNode is null AFTER getPreviousNode, it means the deque only had one element
            firstNode = null; // so we reset firstNode as well
        else
            lastNode.setNextNode(null);
        numEntries--;
        return originalLast;
    }

    @Override
    public T peekFront() {
        if (isEmpty())
            return null;
        return firstNode.getData();
    }

    @Override
    public T peekBack() {
        if (isEmpty())
            return null;
        return lastNode.getData();
    }

    @Override
    public boolean isEmpty() {
        return numEntries == 0; // when both are null
    }

    @Override
    public int getNumEntries() {
        return numEntries;
    }

    @Override
    // for DequeIterator, see the private nested class implementation at the outer class's bottom
    public Iterator<T> iterator() {  // CANNOT be DequeIterator in the method header because of private access
        return new DequeIterator();
    }

    /* private nested class (non-static) */

    // implementation approach: doubly linked nodes since the size will be dynamic
    // less memory efficient in comparison to implementation with array
    private class Node {
        private Node previousNode;
        private T data;
        private Node nextNode;
        private Node(Node previousNode, T data, Node nextNode) {
            this.previousNode = previousNode;
            this.data = data;
            this.nextNode = nextNode;
        }
        public Node getPreviousNode() {
            return previousNode;
        }
        public void setPreviousNode(Node previousNode) {
            this.previousNode = previousNode;
        }
        public T getData() {
            return data;
        }
        public void setData(T data) {
            this.data = data;
        }
        public Node getNextNode() {
            return nextNode;
        }
        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }

    // iterator for deque
    // does not support any function for modifying the deque (read-only)
    private class DequeIterator implements Iterator<T> {

        private Node next;
        private Node previous;
        /*
        private boolean isNextCalled;
        private boolean isPreviousCalled;
        The above two boolean fields are needed only when we want to implement methods for remove, add and set
         */

        private DequeIterator() {
            previous = null;
            if (!isEmpty()) { // initially, cursor is in front of the deque (if not empty)
                next = firstNode;
            } else {
                next = null;
            } // from this point on,  all methods simply need to consider null, instead of isEmpty
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public T next() throws NoSuchElementException {
            Node oldNext;
            if (hasNext())
                oldNext = next;
            else
                throw new NoSuchElementException();
            next = next.getNextNode();
            previous = oldNext;
            return oldNext.getData();
        }

        public boolean hasPrevious() {
            return previous != null;
        }

        public T previous() {
            Node oldPrevious;
            if (hasPrevious())
                oldPrevious = previous;
            else
                throw new NoSuchElementException();
            previous = previous.getPreviousNode();
            next = oldPrevious;
            return oldPrevious.getData();
        }
    }


}
