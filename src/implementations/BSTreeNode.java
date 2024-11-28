
package implementations;

/**
 * Represents a node in a Binary Search Tree.
 *
 * @param <E> The type of data stored in the node.
 */
public class BSTreeNode<E> 
{

    private E data;
    private BSTreeNode<E> left;
    private BSTreeNode<E> right;

    /**
     * Constructor for BSTreeNode
     * 
     * @param data value to be stored in this node
     */
    public BSTreeNode(E data) 
    {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    /**
     * Constructor for BSTreeNode
     * 
     * @param data value to be stored in this node
     * @param left node at left
     * @param right node at right
     */
    public BSTreeNode(E data, BSTreeNode<E> left, BSTreeNode<E> right) 
    {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    /**
     * Getter for data
     * 
     * @return data value stored in this node
     */
    public E getElement() 
    {
        return data;
    }

    /**
     * Setter for data
     * 
     * @param data value or data to be set as data for this node
     */
    public void setData(E data) 
    {
        this.data = data;
    }

    /**
     * Getter for left
     * 
     * @return left node at left
     */
    public BSTreeNode<E> getLeft() 
    {
        return left;
    }

    /**
     * Setter for left
     * 
     * @param left node to be set as left
     */
    public void setLeft(BSTreeNode<E> left) 
    {
        this.left = left;
    }

    /**
     * Getter for right
     * 
     * @return node at right
     */
    public BSTreeNode<E> getRight() 
    {
        return right;
    }

    /**
     * Setter for right
     * 
     * @param right node to be set as right
     */
    public void setRight(BSTreeNode<E> right) 
    {
        this.right = right;
    }

}