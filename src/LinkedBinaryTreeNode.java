import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LinkedBinaryTreeNode<E> implements BinaryTreeNode<E> {

    private E data;
    private BinaryTreeNode<E> leftChild, rightChild, parent;
    private String filename;

    public static LinkedBinaryTreeNode fromFile(String filename) {
        try (Scanner input = new Scanner(new File(filename))) {
            if (!input.hasNextLine()) {
                //Empty file, nothing to read.
                return null;
            }
            String line = input.nextLine();
            LinkedBinaryTreeNode root;
            if (line.charAt(0) == 'A') {
                root = new Answer(line);
            } else {
                root = new Question(line);
                LinkedBinaryTreeNode current = root;
                while (input.hasNextLine()) {
                    while (current.hasLeftChild() && current.hasRightChild()) {
                        current = (LinkedBinaryTreeNode) current.getParent();
                    }
                    line = input.nextLine();
                    if (line.trim().length() == 0) break;
                    LinkedBinaryTreeNode newNode;
                    if (line.charAt(0) == 'A') {
                        newNode = new Answer(line);
                    } else {
                        newNode = new Question(line);
                    }

                    if (!current.hasLeftChild()) {
                        current.setLeft(newNode);

                    } else {
                        current.setRight(newNode);
                    }
                    newNode.setParent(current);
                    if (line.charAt(0) != 'A') {
                        current = newNode;
                    }
                }
            }
            root.setFilename(filename);
            return root;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    /**
     * Returns the data stored in this node.
     */
    @Override
    public E getData() {
        return data;
    }

    /**
     * Modifies the data stored in this node.
     *
     * @param data the data to store
     */
    @Override
    public void setData(E data) {
        this.data = data;
    }

    /**
     * Returns the ancestor of this node that has no parent,
     * or returns this node if it is the root.
     */
    @Override
    public BinaryTreeNode<E> getRoot() {
        if (getParent() == null) {
            return this;
        } else {
            return getParent().getRoot();
        }
    }

    /**
     * Returns the parent of this node, or null if this node is a root.
     */
    @Override
    public BinaryTreeNode<E> getParent() {
        return parent;
    }

    /**
     * Returns the left child of this node, or null if it does
     * not have one.
     */
    @Override
    public BinaryTreeNode<E> getLeft() {
        return leftChild;
    }

    /**
     * Removes child from its current parent and inserts it as the
     * left child of this node.  If this node already has a left
     * child it is removed.
     *
     * @param child the child to add
     */
    @Override
    public void setLeft(BinaryTreeNode<E> child) {
        leftChild = child;
    }

    /**
     * Returns the right child of this node, or null if it does
     * not have one.
     */
    @Override
    public BinaryTreeNode<E> getRight() {
        return rightChild;
    }

    /**
     * Removes child from its current parent and inserts it as the
     * right child of this node.  If this node already has a right
     * child it is removed.
     *
     * @param child the child to add
     */
    @Override
    public void setRight(BinaryTreeNode<E> child) {
        rightChild = child;
    }

    /**
     * Returns true if the node has any children.
     * Otherwise, returns false.
     */
    @Override
    public boolean isParent() {
        return (leftChild != null || rightChild != null);
    }

    public void setParent(BinaryTreeNode<E> parent) {
        this.parent = parent;
    }

    /**
     * Returns true if the node is childless.
     * Otherwise, returns false.
     */
    @Override
    public boolean isLeaf() {
        return !isParent();
    }

    /**
     * Returns true if the node has a left child
     */
    @Override
    public boolean hasLeftChild() {
        return (leftChild != null);
    }

    /**
     * Returns true if the node has a right child
     */
    @Override
    public boolean hasRightChild() {
        return (rightChild != null);
    }

    /**
     * Returns the number of edges in the path from the root to this node.
     */
    @Override
    public int getDepth() {
        return getRoot().pathTo(this).size() - 1;
    }

    /**
     * Returns the number of edges in the path from the root
     * to the node with the maximum depth.
     */
    @Override
    public int getHeight() {
        final int maxDepth[] = {0};
        traversePreorder(node -> maxDepth[0] = Math.max(node.getDepth(), maxDepth[0]));
        return maxDepth[0];
    }

    /**
     * Returns the number of nodes in the subtree rooted at this node.
     */
    @Override
    public int size() {
        final int size[] = {0};
        traversePreorder(node -> size[0]++);
        return size[0];
    }

    /**
     * Removes this node, and all its descendants, from whatever
     * tree it is in.  Does nothing if this node is a root.
     */
    @Override
    public void removeFromParent() {
        if (this.parent != null) {
            if (parent.getLeft() == this) {
                parent.setLeft(null);
            } else if (parent.getRight() == this) {
                parent.setRight(null);
            } // else not actually a child of parent somehow?
        }
    }

    /**
     * Returns the path from this node to the specified descendant.
     * If no path exists, returns an empty list.
     *
     * @param descendant the descendent to get a pathTo
     */
    @Override
    public ArrayList<BinaryTreeNode<E>> pathTo(BinaryTreeNode<E> descendant) {
        ArrayList<BinaryTreeNode<E>> path = new ArrayList<>();
        BinaryTreeNode<E> current = descendant;
        do {
            path.add(current);
            if (current == this) {
                break;
            }
            current = current.getParent();
        } while (current != null);
        if (current == null) {
            path.clear();
        }
        return path;
    }

    /**
     * Returns the path to this node from the specified ancestor.
     * If no path exists, returns an empty list.
     *
     * @param ancestor the ancestor to get the path from
     */
    @Override
    public ArrayList<BinaryTreeNode<E>> pathFrom(BinaryTreeNode<E> ancestor) {
        ArrayList<BinaryTreeNode<E>> path = ancestor.pathTo(this);
        Collections.reverse(path);
        return path;
    }

    /**
     * Visits the nodes in this tree in preorder.
     *
     * @param visitor the code to execute when visiting a node.
     */
    @Override
    public void traversePreorder(Visitor visitor) {
        if (visitor == null) {
            throw new IllegalArgumentException();
        }
        visitor.visit(this);
        if (hasLeftChild()) {
            leftChild.traversePreorder(visitor);
        }
        if (hasRightChild()) {
            rightChild.traversePreorder(visitor);
        }
    }

    /**
     * Visits the nodes in this tree in postorder.
     *
     * @param visitor the code to execute when visiting a node.
     */
    @Override
    public void traversePostorder(Visitor visitor) {
        if (visitor == null) {
            throw new IllegalArgumentException();
        }
        if (hasLeftChild()) {
            leftChild.traversePostorder(visitor);
        }
        if (hasRightChild()) {
            rightChild.traversePostorder(visitor);
        }
        visitor.visit(this);
    }

    /**
     * Visits the nodes in this tree in inorder.
     *
     * @param visitor the code to execute when visiting a node.
     */
    @Override
    public void traverseInorder(Visitor visitor) {
        if (visitor == null) {
            throw new IllegalArgumentException();
        }
        if (hasLeftChild()) {
            leftChild.traverseInorder(visitor);
        }
        visitor.visit(this);
        if (hasRightChild()) {
            rightChild.traversePostorder(visitor);
        }
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename(String filename) {
        return filename;
    }

    public void saveToFile() {
        if (filename != null) {
            try (PrintWriter output = new PrintWriter(filename)) {
                getRoot().traversePreorder(node -> output.println(node.getData().toString()));
                System.out.println("Saved to file.");
            } catch (FileNotFoundException e) {
                System.out.println("Could not save to file.");
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        traversePreorder(node -> {
            stringBuilder.append(node.getData().toString());
            stringBuilder.append("\n");
        });
        return stringBuilder.toString();
    }
}
