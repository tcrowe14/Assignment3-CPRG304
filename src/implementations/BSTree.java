package implementations;

import utilities.Iterator;
import utilities.BSTreeADT;
import java.util.*;

/**
 * Binary Search Tree implementation based on BSTreeADT. Maintains a reference
 * to the root node and provides various operations on the tree.
 *
 * @param <E> the type of elements stored in the tree, must implement
 * Comparable.
 * @see BSTreeADT
 */
public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E> 
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BSTreeNode<E> root;
	
    /**
     * Constructor for BSTree
     */
    public BSTree() 
    {
        this.root = null;
    }
    
    /**
     * Parameterized constructor for BSTree
     */
    public BSTree(E initialValue) 
    {
        this.root = new BSTreeNode<>(initialValue);
    }
	/**
	 * The node at the root of the Binary Search Tree will be returned.
	 * 
	 * @return node stored at the root of tree is returned
	 * @throws NullPointerException if the tree is empty and there is no root node.
	 */
    @Override
    public BSTreeNode<E> getRoot() throws NullPointerException 
    {
        if (root == null) 
        {
            throw new NullPointerException("The tree is empty; root is null.");
        }
        
        return root;
    }

	/**
	 * Determines the row height of the tree and returns that value as an integer
	 * value.
	 * 
	 * @return the height of the tree.
	 */
    @Override
    public int getHeight() 
    {
        if (root == null) 
        {
            return 0;
        }

        Queue<BSTreeNode<E>> queue = new LinkedList<>();
        queue.add(root);
        int height = 0;

        while (!queue.isEmpty()) 
        {
            int levelSize = queue.size(); // Number of nodes at the current level
            for (int i = 0; i < levelSize; i++) 
            {
                BSTreeNode<E> currentNode = queue.poll();
                if (currentNode.getLeft() != null) 
                {
                    queue.add(currentNode.getLeft());
                }
                if (currentNode.getRight() != null) 
                {
                    queue.add(currentNode.getRight());
                }
            }
            
            height++; // Increment height after processing a level
        }
        
        return height;
    }

	/**
	 * The number of elements currently stored in the tree is counted and the value
	 * is returned.
	 * 
	 * @return number of elements currently stored in tree.
	 */
    @Override
    public int size() 
    {
        if (root == null) 
        {
            return 0;
        }

        Queue<BSTreeNode<E>> queue = new LinkedList<>();
        queue.add(root);
        int size = 0;

        while (!queue.isEmpty()) 
        {
            BSTreeNode<E> currentNode = queue.poll();
            size++; // Count the current node
            if (currentNode.getLeft() != null) 
            {
                queue.add(currentNode.getLeft());
            }
            if (currentNode.getRight() != null) 
            {
                queue.add(currentNode.getRight());
            }
        }

        return size;
    }

	/**
	 * Checks if the tree is currently empty.
	 * 
	 * @return returns boolean true if the tree is empty otherwise false.
	 */
    @Override
    public boolean isEmpty() 
    {
        return root == null;
    }

	/**
	 * Clears all elements currently stored in tree and makes the tree empty.
	 */
    @Override
    public void clear() 
    {
        root = null;
    }

	/**
	 * Checks the current tree to see if the element passed in is stored in the
	 * tree. If the element is found in the tree the method returns true and if the
	 * element is not in the tree the method returns false.
	 * 
	 * @param entry the element to find in the tree
	 * @return returns boolean true if element is currently in the tree and false if
	 *         the element is not found in the tree
	 * @throws NullPointerException if the element being passed in is null
	 */
    @Override
    public boolean contains(E entry) throws NullPointerException 
    {
        if (entry == null) 
        {
            throw new NullPointerException("Cannot search null elements.");
        }

        BSTreeNode<E> current = root;

        while (current != null) 
        {
            int compareResult = entry.compareTo(current.getElement());

            if (compareResult == 0) 
            {
                return true; // Element found
            } else if (compareResult < 0) 
            {
                current = current.getLeft(); // Search left subtree
            }
            else 
            {
                current = current.getRight(); // Search right subtree
            }
        }

        return false; // Element not found
    }

	/**
	 * Retrieves a node from the tree given the object to search for.
	 * 
	 * @param entry element object being searched
	 * @return the node with the element located in tree, null if not found
	 * @throws NullPointerException if the element being passed in is null
	 */
    @Override
    public BSTreeNode<E> search(E entry) throws NullPointerException 
    {
        if (entry == null) 
        {
            throw new NullPointerException("Cannot search null elements.");
        }

        BSTreeNode<E> current = root;

        while (current != null) 
        {
            int compareResult = entry.compareTo(current.getElement());

            if (compareResult == 0) 
            {
                return current; // Element found
            } 
            else if (compareResult < 0) 
            {
                current = current.getLeft(); // Search left subtree
            } else {
                current = current.getRight(); // Search right subtree
            }
        }

        return null; // Element not found
    }


	/**
	 * Adds a new element to the tree according to the natural ordering established
	 * by the Comparable implementation.
	 * 
	 * @param newEntry the element being added to the tree
	 * @return a boolean true if the element is added successfully else false
	 * @throws NullPointerException if the element being passed in is null
	 */
    @Override
    public boolean add(E newEntry) throws NullPointerException 
    {
        if (newEntry == null) 
        {
            throw new NullPointerException("Cannot add null element to the tree.");
        }

        BSTreeNode<E> newNode = new BSTreeNode<>(newEntry);

        if (root == null) 
        {
            root = newNode; // The tree was empty, set the root
            return true;
        }

        BSTreeNode<E> current = root;
        BSTreeNode<E> parent = null;

        while (current != null) 
        {
            int compareResult = newEntry.compareTo(current.getElement());
            parent = current;

            if (compareResult < 0) 
            {
                current = current.getLeft();
                if (current == null) 
                {
                    parent.setLeft(newNode); // Insert as the left child
                    return true;
                }
            } else if (compareResult > 0) 
            {
                current = current.getRight();
                if (current == null) 
                {
                    parent.setRight(newNode); // Insert as the right child
                    return true;
                }
            } 
            else 
            {
                // The element already exists in the tree; do nothing
                return false;
            }
        }

        return false; // Should never reach here
    }

	/**
	 * Removes the smallest element in the tree according to the natural ordering
	 * established by the Comparable implementation.
	 * 
	 * @return the removed element or null if the tree is empty
	 */
    @Override
    public BSTreeNode<E> removeMin() 
    {
        if (root == null) 
        {
            return null; // Tree is empty, nothing to remove.
        }

        // Special case: If the root itself is the minimum
        if (root.getLeft() == null) 
        {
            BSTreeNode<E> minNode = root;
            root = root.getRight(); // Update root to the right subtree
            return minNode;
        }

        BSTreeNode<E> current = root;
        BSTreeNode<E> parent = null;

        // Traverse the left subtree to find the minimum
        while (current.getLeft() != null) 
        {
            parent = current;
            current = current.getLeft();
        }

        // Current now points to the minimum node
        BSTreeNode<E> minNode = current;

        // If the minimum node has a right child, connect it to the parent
        if (parent != null) 
        {
            parent.setLeft(current.getRight());
        }

        return minNode;
    }

	/**
	 * Removes the largest element in the tree according to the natural ordering
	 * established by the Comparable implementation.
	 * 
	 * @return the removed element or null if the tree is empty
	 */
    @Override
    public BSTreeNode<E> removeMax() 
    {
        if (root == null) 
        {
            return null; // Tree is empty, nothing to remove
        }

        // Special case: If the root itself is the maximum
        if (root.getRight() == null) 
        {
            BSTreeNode<E> maxNode = root;
            root = root.getLeft(); // Update root to the left subtree
            return maxNode;
        }

        BSTreeNode<E> current = root;
        BSTreeNode<E> parent = null;

        // Traverse the right subtree to find the maximum
        while (current.getRight() != null) 
        {
            parent = current;
            current = current.getRight();
        }

        // Current now points to the maximum node
        BSTreeNode<E> maxNode = current;

        // If the maximum node has a left child, connect it to the parent
        if (parent != null) 
        {
            parent.setRight(current.getLeft());
        }

        return maxNode;
    }

	/**
	 * Generates an in-order iteration over the contents of the tree. Elements are
	 * in their natural order.
	 * 
	 * @return an iterator with the elements in the natural order
	 */
    @Override
    public Iterator<E> inorderIterator() 
    {
        return new InorderIterator(root);
    }

    private class InorderIterator implements Iterator<E> 
    {

        private Stack<BSTreeNode<E>> stack;
        private BSTreeNode<E> current;

        /**
         * Constructs an in-order iterator for the tree.
         *
         * @param root the root of the tree
         */
        public InorderIterator(BSTreeNode<E> root) 
        {
            stack = new Stack<>();
            current = root;
        }

        /**
         * Checks if there are more elements in the iteration.
         *
         * @return true if there are more elements, false otherwise
         */
        @Override
        public boolean hasNext() 
        {
            return current != null || !stack.isEmpty();
        }

        /**
         * Retrieves the next element in the iteration.
         *
         * @return the next element
         * @throws NoSuchElementException if there are no more elements
         */
        @Override
        public E next() 
        {
            while (current != null) 
            {
                stack.push(current);
                current = current.getLeft(); // Go left as far as possible
            }

            if (stack.isEmpty()) 
            {
                throw new NoSuchElementException("No more elements in the iteration");
            }

            BSTreeNode<E> node = stack.pop();
            E result = node.getElement();
            current = node.getRight(); // Move to the right subtree
            return result;
        }
    }

	/**
	 * Generates a pre-order iteration over the contents of the tree. Elements are
	 * order in such a way as the root element is first.
	 * 
	 * @return an iterator with the elements in a root element first order
	 */
    @Override
    public Iterator<E> preorderIterator() {
        return new PreorderIterator(root);
    }

    private class PreorderIterator implements Iterator<E> 
    {

        private Stack<BSTreeNode<E>> stack;

        /**
         * Constructs a pre-order iterator for the tree.
         *
         * @param root the root of the tree
         */
        public PreorderIterator(BSTreeNode<E> root) 
        {
            stack = new Stack<>();
            if (root != null) {
                stack.push(root);
            }
        }

        /**
         * Checks if there are more elements in the iteration.
         *
         * @return true if there are more elements, false otherwise
         */
        @Override
        public boolean hasNext() 
        {
            return !stack.isEmpty();
        }

        /**
         * Retrieves the next element in the iteration.
         *
         * @return the next element
         * @throws NoSuchElementException if there are no more elements
         */
        @Override
        public E next() 
        {
            if (!hasNext()) 
            {
                throw new NoSuchElementException("No more elements in the iteration");
            }

            BSTreeNode<E> current = stack.pop();

            // Push right child first, so left child is processed first
            if (current.getRight() != null) 
            {
                stack.push(current.getRight());
            }
            if (current.getLeft() != null) 
            {
                stack.push(current.getLeft());
            }

            return current.getElement();
        }
    }

	/**
	 * Generates a post-order iteration over the contents of the tree. Elements are
	 * order in such a way as the root element is last.
	 * 
	 * @return an iterator with the elements in a root element last order
	 */
    @Override
    public Iterator<E> postorderIterator() 
    {
        return new PostorderIterator(root);
    }

    private class PostorderIterator implements Iterator<E> 
    {

        private Stack<BSTreeNode<E>> stack1;
        private Stack<BSTreeNode<E>> stack2;

        /**
         * Constructs a post-order iterator for the tree.
         *
         * @param root the root of the tree
         */
        public PostorderIterator(BSTreeNode<E> root) 
        {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
            if (root != null) 
            {
                stack1.push(root);
            }

            // Populate stack2 with nodes in reverse post-order
            while (!stack1.isEmpty()) 
            {
                BSTreeNode<E> current = stack1.pop();
                stack2.push(current);

                if (current.getLeft() != null) 
                {
                    stack1.push(current.getLeft());
                }
                if (current.getRight() != null) 
                {
                    stack1.push(current.getRight());
                }
            }
        }

        /**
         * Checks if there are more elements in the iteration.
         *
         * @return true if there are more elements, false otherwise
         */
        @Override
        public boolean hasNext() 
        {
            return !stack2.isEmpty();
        }

        /**
         * Retrieves the next element in the iteration.
         *
         * @return the next element
         * @throws NoSuchElementException if there are no more elements
         */
        @Override
        public E next() 
        {
            if (!hasNext()) 
            {
                throw new NoSuchElementException("No more elements in the iteration");
            }
            return stack2.pop().getElement();
        }
    }

}