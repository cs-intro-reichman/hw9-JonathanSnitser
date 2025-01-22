/** Represents an iterator of a linked list. */
public class ListIterator {

    // current position in the list (cursor)
    public Node current;

    /** Constructs a list iterator, starting at the given node */
    public ListIterator(Node node) {
        this.current = node;
    }

    /** Checks if this iterator has more nodes to process */
    public boolean hasNext() {
        return (this.current != null);
    }

    /** Returns the current element in the list, and advances the cursor */
    public MemoryBlock next() {
        Node currentNode = current;
        this.current = current.next;
        return currentNode.block;
    }
}