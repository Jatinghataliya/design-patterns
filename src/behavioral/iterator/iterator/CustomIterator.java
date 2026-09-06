package behavioral.iterator.iterator;

/**
 * Custom Iterator Interface.
 * Declares traversal operations over a collection of elements.
 */
public interface CustomIterator<T> {
    boolean hasNext();
    T next();
    void reset();
}
