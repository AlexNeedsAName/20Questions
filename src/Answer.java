public class Answer extends LinkedBinaryTreeNode<String> {
    public Answer(String line) {
        setData(line);
    }

    public void setAnswer(String value) {
        setData("A:" + value);
    }

    public void replaceWithQuestion(Question q, boolean isYes) {
        BinaryTreeNode parent = getParent();
        boolean isLeft = (parent.getLeft() == this);
        removeFromParent();

        if (isLeft) {
            parent.setLeft(q);
        } else {
            parent.setRight(q);
        }

        if (isYes) {
            q.setLeft(this);
        } else {
            q.setRight(this);
        }

        q.setParent(this);
    }
}
