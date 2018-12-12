import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Program6 {
    private static final List<String> YES = Arrays.asList(new String[]{"y", "yes", "yep", "i guess", "sure", "why not"});
    private static final List<String> NO = Arrays.asList(new String[]{"n", "no", "nope", "i don't think so"});

    public static void main(String[] args) {
        LinkedBinaryTreeNode<String> root = LinkedBinaryTreeNode.fromFile("data/pokemon.data");
//        System.out.println(root.toString());
        BinaryTreeNode node = root;
        Scanner input = new Scanner(System.in);
        System.out.print("Shall we play a game? ");
        if (!getYesOrNo(input)) return;
        while (node.isParent()) {
            System.out.print(((Question) node).getQuestion() + " ");
            if (getYesOrNo(input)) {
                node = node.getRight();
            } else {
                node = node.getLeft();
            }
        }
        System.out.print("Were you thinking of " + ((Answer) node).getAnswer() + "? ");
        if (getYesOrNo(input)) {
            System.out.println("Ha, I win!");
        } else {
            System.out.print("What were you thinking of? ");
            Answer answer = new Answer("A:" + input.nextLine().trim());
            System.out.print("What question separates " + ((Answer) node).getAnswer() + " from " + answer.getAnswer() + "? ");
            Question question = new Question("Q:" + input.nextLine().trim());
            System.out.print("What is the correct answer for this animal? ");
            ((Answer) node).replaceWithQuestion(question, getYesOrNo(input), answer);
            root.saveToFile();
        }
    }

    public static boolean getYesOrNo(Scanner input) {
        String line = input.nextLine().toLowerCase().trim();
        boolean valid = false;
        boolean yes = false;
        while (!valid) {
            if (YES.contains(line)) {
                valid = true;
                yes = true;
            } else if (NO.contains(line)) {
                valid = true;
                yes = false;
            } else {
                System.out.println("\"" + line + "\" is not a valid response");
                line = input.nextLine().toLowerCase().trim();
            }
        }
        return yes;
    }
}
