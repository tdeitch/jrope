public class Node {
    String data;
    Node left;
    Node right;
    int leftLen;

    public Node(String data) {
        this.data = data;
        this.leftLen = data.length();
    }

    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
        int leftLen = 0;
        for (Node nd = left; nd != null; nd = nd.right) {
            leftLen += nd.leftLen;
        }
        this.leftLen = leftLen;
    }

    public String toString() {
        return data;
    }
}
