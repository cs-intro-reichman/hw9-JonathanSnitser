/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	//public static void main(String[] args) {
	//	MemoryBlock mB = new MemoryBlock(100, 10); 
	//	MemoryBlock mB1 = new MemoryBlock(100, 20); 
	//	MemoryBlock mB2 = new MemoryBlock(100, 30); 
	//	Node testNode = new Node(mB);
	//	LinkedList nL = new LinkedList();
	//	nL.addFirst(mB);
	//	nL.addLast(mB2);
	//	nL.add(2, mB1);
	//	System.out.println(nL);
	//}


	 public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		int i = 0; 
		Node current = first;
		while (i != index) {
			current = current.next;
			i++;
		}
		return current;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		if (index < 0 || index > this.size) {
			throw new IllegalArgumentException("index must be between 0 and size");
		} else if (index == 0) {
			Node newNode = new Node(block);
			newNode.next = this.first;
			this.first = newNode;
		} else if (index == this.size) {
			Node newNode = new Node(block);
			this.last.next = newNode;
			this.last = newNode;
		} else {
			int i = 0;
			Node newNode = new Node(block);
			Node current = this.first;
			while (i != index) {
				current = current.next;
				i++;
			}
			newNode.next = current.next;
			current.next = newNode;
		}
		this.size++;
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		Node newNode = new Node(block);
		if (this.last == null){
			this.first = this.last = newNode;
		}
		else {
			this.last.next = newNode;
			this.last = newNode;
		}
		this.size++;
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		Node newNode = new Node(block);
		if (this.first == null){
			this.first = this.last = newNode;
		}
		else {
			newNode.next = this.first;
			this.first = newNode;
		}
		this.size++;
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		if (index < 0 || index > this.size) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		int i = 0;
		Node newNode = this.first;
		while (i != index) {
			newNode = newNode.next;
			i++;
		}
		return newNode.block;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		int i = 0;
		Node newNode = this.first;
		while (i != this.size + 1) {
			if (newNode.block == block) {
				return i;
			}
			newNode = newNode.next;
			i++;
		}
		return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		if (this.first == node) {
			this.first = this.first.next;
		}
		Node nodeToRemove = this.first.next;
		Node previous = this.first;
		int i = 1;
		while (i != this.size + 1){
			if (nodeToRemove == node) {
				if (nodeToRemove == this.last) {
					this.last = previous;
				}
				previous.next = nodeToRemove.next;
				this.size--;
				break;
			}
		}
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		if (index < 0 || index > this.size) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		if (index == 0) {
			this.first = this.first.next;
		}
		Node nodeToRemove = this.first.next;
		Node previous = this.first;
		int i = 1;
		while (i != index) {
			nodeToRemove = nodeToRemove.next;
			previous = previous.next;
		}
		previous.next = nodeToRemove.next;
		this.size--;
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		if (this.first.block == block) {
			this.first = this.first.next;
			this.size--;
		}
		Node nodeToRemove = this.first.next;
		Node previous = this.first;
		int i = 1;
		while (i != this.size + 1){
			if (nodeToRemove.block == block) {
				if (nodeToRemove == this.last) {
					this.last = previous;
				}
				previous.next = nodeToRemove.next;
				this.size--;
				break;
			}
		}
		if (i > this.size) {
			throw new IllegalArgumentException("The given memory block is not in the list.");
		}
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(this.first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		Node current = this.first;
		if (current == null) {
			return "()";
		}
		String str = "";
		while (current != null) {	
			str += current.block + " ";
			current = current.next;
		}
		return str.substring(0, str.length() -1);
	}
}