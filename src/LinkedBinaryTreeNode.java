import java.util.ArrayList;

public class LinkedBinaryTreeNode<E> implements BinaryTreeNode<E> {

    /**
     * Returns the data stored in this node.
     */
    @Override
    public E getData() {
        return null;
    }

    /**
     * Modifies the data stored in this node.
     *
     * @param data
     */
    @Override
    public void setData(E data) {

    }

    /**
     * Returns the ancestor of this node that has no parent,
     * or returns this node if it is the root.
     */
    @Override
    public BinaryTreeNode<E> getRoot() {
        return null;
    }

    /**
     * Returns the parent of this node, or null if this node is a root.
     */
    @Override
    public BinaryTreeNode<E> getParent() {
        return null;
    }

    /**
     * Returns the left child of this node, or null if it does
     * not have one.
     */
    @Override
    public BinaryTreeNode<E> getLeft() {
        return null;
    }

    /**
     * Removes child from its current parent and inserts it as the
     * left child of this node.  If this node already has a left
     * child it is removed.
     *
     * @param child
     */
    @Override
    public void setLeft(BinaryTreeNode<E> child) {

    }

    /**
     * Returns the right child of this node, or null if it does
     * not have one.
     */
    @Override
    public BinaryTreeNode<E> getRight() {
        return null;
    }

    /**
     * Removes child from its current parent and inserts it as the
     * right child of this node.  If this node already has a right
     * child it is removed.
     *
     * @param child
     */
    @Override
    public void setRight(BinaryTreeNode<E> child) {

    }

    /**
     * Returns true if the node has any children.
     * Otherwise, returns false.
     */
    @Override
    public boolean isParent() {
        return false;
    }

    /**
     * Returns true if the node is childless.
     * Otherwise, returns false.
     */
    @Override
    public boolean isLeaf() {
        return false;
    }

    /**
     * Returns true if the node has a left child
     */
    @Override
    public boolean hasLeftChild() {
        return false;
    }

    /**
     * Returns true if the node has a right child
     */
    @Override
    public boolean hasRightChild() {
        return false;
    }

    /**
     * Returns the number of edges in the path from the root to this node.
     */
    @Override
    public int getDepth() {
        return 0;
    }

    /**
     * Returns the number of edges in the path from the root
     * to the node with the maximum depth.
     */
    @Override
    public int getHeight() {
        return 0;
    }

    /**
     * Returns the number of nodes in the subtree rooted at this node.
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Removes this node, and all its descendants, from whatever
     * tree it is in.  Does nothing if this node is a root.
     */
    @Override
    public void removeFromParent() {

    }

    /**
     * Returns the path from this node to the specified descendant.
     * If no path exists, returns an empty list.
     *
     * @param descendant
     */
    @Override
    public ArrayList<BinaryTreeNode<E>> pathTo(BinaryTreeNode<E> descendant) {
        return null;
    }

    /**
     * Returns the path to this node from the specified ancestor.
     * If no path exists, returns an empty list.
     *
     * @param ancestor
     */
    @Override
    public ArrayList<BinaryTreeNode<E>> pathFrom(BinaryTreeNode<E> ancestor) {
        return null;
    }

    /**
     * Visits the nodes in this tree in preorder.
     *
     * @param visitor
     */
    @Override
    public void traversePreorder(Visitor visitor) {

    }

    /**
     * Visits the nodes in this tree in postorder.
     *
     * @param visitor
     */
    @Override
    public void traversePostorder(Visitor visitor) {

    }

    /**
     * Visits the nodes in this tree in inorder.
     *
     * @param visitor
     */
    @Override
    public void traverseInorder(Visitor visitor) {

    }
}
