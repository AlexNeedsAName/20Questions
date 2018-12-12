public class Answer extends LinkedBinaryTreeNode<String> {
    public Answer(String line) {
        setData(line);
    }

    public String getAnswer() {
        return getData().substring(2);
    }

    public void setAnswer(String value) {
        setData("A:" + value);
    }

    public void replaceWithQuestion(Question q, boolean isYes, Answer newAnswer) {
        if (isYes) {
            q.setLeft(this);
            q.setRight(newAnswer);
        } else {
            q.setRight(this);
            q.setLeft(newAnswer);
        }

        BinaryTreeNode parent = getParent();
        if (parent == null) {
            this.setParent(q);
        } else {
            boolean isLeft = (parent.getLeft() == this);
            removeFromParent();
            if (isLeft) {
                parent.setLeft(q);
            } else {
                parent.setRight(q);
            }
            q.setParent(this);
        }
    }
}
