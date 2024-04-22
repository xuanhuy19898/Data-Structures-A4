/**I, Xuan Huy Pham, 000899551, certify that this material is my original work. No other person's work has been used without suitable acknowledgment and I have not made my work available to anyone else. */
public class SortedLinkedList<T extends Comparable>
{
    /**
     * The Node class stores a list element and a reference to the next node.
     */
    private final class Node<T extends Comparable>
    {
        T value;
        Node next;

        /**
         * Constructor.
         * @param val The element to store in the node.
         * @param n The reference to the successor node.
         */
        Node(T val, Node n)
        {
            value = val;
            next = n;
        }

        /**
         * Constructor.
         *
         * @param val The element to store in the node.
         */
        Node(T val)
        {
            // Call the other (sister) constructor.
            this(val, null);
        }
    }
    private Node first;  // list head

    /**
     * Constructor.
     */
    public SortedLinkedList()
    {
        first = null;
    }

    /**
     * The isEmpty method checks to see if the list is empty.
     *
     * @return true if list is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return first == null;
    }

    /**
     * The size method returns the length of the list.
     * @return The number of elements in the list.
     */
    public int size()
    {
        int count = 0;
        Node p = first;
        while (p != null) {
            // There is an element at p
            count++;
            p = p.next;
        }
        return count;
    }

    /**
     * The add method adds an element at a position.
     * @param element The element to add to the list in sorted order.
     */
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        Node<T> previous = null;
        Node<T> current = first;
        //traverse the list until reaching the end or when it finds the right position for insertion
        while (current != null && element.compareTo(current.value) > 0) {
            previous = current;
            current = current.next;//move to the next node
        }
        //insert the new node at the beginning of the list if it should be the first element
        if (previous == null) {
            newNode.next = first;
            first = newNode;
        } else {//insert a new node between previous and current node
            previous.next = newNode;
            newNode.next = current;
        }
    }

    /**
     * The toString method computes the string representation of the list.
     * @return The string form of the list.
     */
    public String toString()
    {
        StringBuilder listOfItems = new StringBuilder();
        // TODO: Iterate through the list and append items to end of listOfItems
        Node<T> current = first;
        while (current != null) {
            listOfItems.append(current.value);
            if (current.next != null) {
                listOfItems.append(", ");
            }
            current = current.next;
        }
        return listOfItems.toString();
    }

    /**
     * generates a string representation of a specific range of elements in the linked list
     * @param n the starting position of the elements to include in the string
     * @param c the number of elements to include in the string
     * @return a string containing a specified range of elements from the linked list
     */
    public String toString_page(int n, int c){
        StringBuilder listOfItems = new StringBuilder();
        Node<T> current = first;
        int count = 0;

        //traverse to the starting position
        for (int i = 0; i < n && current != null; i++) {
            current = current.next;
        }
        while (current != null && count < c){
            listOfItems.append(current.value);
            if (current.next != null && count < c - 1) {
                listOfItems.append(", ");
            }
            current = current.next;
            count++;
        }
        return listOfItems.toString();
    }


    /**
     * The remove method removes an element.
     * @param element The element to remove.
     * @return true if the remove succeeded, false otherwise.
     */
    public boolean remove(T element) {
        if (isEmpty())
            return false;
        //check if the 1st node contains the element that needs to be removed
        if (first.value.equals(element)) {
            first = first.next;
            return true;
        }

        Node<T> previous = first;
        Node<T> current = first.next;

        //find the element
        while (current != null && !current.value.equals(element)) {
            previous = current;
            current = current.next;
        }

        //if finds the element, remove it
        if (current != null) {
            previous.next = current.next;
            return true;
        }
        return false;
    }
}
