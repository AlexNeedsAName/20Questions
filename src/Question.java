public class Question extends LinkedBinaryTreeNode<String> {
    public Question(String line) {
        setData(line);
    }

    public void setQuestion(String value) {
        super.setData("Q:" + value);
    }
}
