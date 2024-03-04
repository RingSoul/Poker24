public interface DequeInterface<T> {
    // the java deque interface has too many methods to be overridden, so I created a personal one for simplicity
    void addToFront(T element);
    void addToBack(T element);
    T removeFromFront();
    T removeFromBack();
    T peekFront();
    T peekBack();
    boolean isEmpty();
    int getNumEntries();
}
