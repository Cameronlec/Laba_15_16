package restaurant;

public class CircularDoublyLinkedList<E> {
    // attribute that indicates if the linked list would be iterated in a cycle
    private boolean isCircular = true;

    // A reference to the root node
    private Node<E> head = null;
    private Node<E> tail = null;
    // attribute for storing the size of the linked list
    private int countOfNodes = 0;



    /**
     * The linked list Node class
     *
     * @param <E>
     */

    @SuppressWarnings("hiding")
    public class Node<E> {
        // attribute for storing the value
        private E data;

        // attributes for storing the linked list references
        private Node<E> previousNode = null;
        private Node<E> nextNode = null;

        public Node() {

        }

        public Node(E data) {

            this.data = data;
        }

        public Node<E> getPreviousNode() {
            return previousNode;
        }

        public void setPreviousNode(Node<E> previousNode) {
            this.previousNode = previousNode;
        }

        public Node<E> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }
    }

    /**
     * The iterator implementation
     */
    @SuppressWarnings("hiding")

    class CircularDoublyLinkedListIterator<E> {
        private Node<E> currentNode = null;

        public CircularDoublyLinkedListIterator(Node<E> startNode) {
            super();
            this.currentNode = startNode;
        }

        public boolean hasNext() {
            return (this.currentNode != null);
        }


        public E next() {
            E data = currentNode.getData();
            currentNode = currentNode.getNextNode();

            if (currentNode == null && CircularDoublyLinkedList.this.isCircular()) {
                this.currentNode = (Node<E>) CircularDoublyLinkedList.this.getHead();
            }

            return data;
        }
        public boolean hasPrev() {
            return (this.currentNode.previousNode != null);
        }


        public E prev() {
            E data = currentNode.getData();
            currentNode = currentNode.getPreviousNode();

            if (currentNode == null && CircularDoublyLinkedList.this.isCircular()) {
                this.currentNode = (Node<E>) CircularDoublyLinkedList.this.getTail();
            }

            return data;
        }
    }

    public CircularDoublyLinkedList() {

    }

    public boolean isCircular() {
        return this.isCircular;
    }

    public void setCircular(boolean isCircular) {
        this.isCircular = isCircular;
    }

    public Node<E> getHead() {
        return head;
    }
    public Node<E> getTail() {
        return tail;
    }

    public void setHead(Node<E> head) {
        this.head = head;
    }

    public int size() {
        return this.countOfNodes;
    }

    public CircularDoublyLinkedListIterator<E> iterator() {
        return new CircularDoublyLinkedListIterator<E>(this.getHead());
    }

    public void add(E value) {
        Node<E> node = new Node<E>(value);
        addAfter(getLastNode(), node);
        this.countOfNodes++;
    }
    public boolean remove(Node<E> node){
        node.previousNode.setNextNode(node.getNextNode());
        node.nextNode.setPreviousNode(node.getPreviousNode());
        this.countOfNodes--;
        return  true;
    }

    public Node<E> getLastNode() {
        return tail;
//        Node<E> lastNode = null;
//
//        Node<E> node = getHead();
//        while (node != null) {
//            lastNode = node;
//            node = node.getNextNode();
//        }
//
//        return lastNode;
    }

    public void addAfter(Node<E> parentNode, E value) {
        Node<E> newNode = new Node<E>(value);
        addAfter(parentNode, newNode);
    }

    public void addAfter(Node<E> parentNode, Node<E> newNode) {
        if (parentNode == null) {
            this.setHead(newNode);
        } else {
            parentNode.setNextNode(newNode);
        }
    }

    public void addBefore(Node<E> parentNode, E value) {
        Node<E> newNode = new Node<E>(value);
        addBefore(parentNode, newNode);
    }

    public void addBefore(Node<E> parentNode, Node<E> newNode) {
        if (parentNode == null) {
            this.setHead(newNode);
        } else {
            Node<E> prevNode = parentNode.getPreviousNode();
            parentNode.setPreviousNode(newNode);
            newNode.setNextNode(parentNode);
            newNode.setPreviousNode(prevNode);

            if (newNode.getPreviousNode() == null) {
                this.setHead(newNode);
            }
        }
    }
}