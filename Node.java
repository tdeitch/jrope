public class Node {
    boolean isConcatNode;
    String data;
    int index;
    int length;
    Node left;
    Node right;
    Node parent;
    int leftLen;
    int totalLen;
    
    public Node(String data) {
        this.isConcatNode = false;
        this.data = data;
        this.index = index;
        this.length = data.length();
        this.leftLen = length;
        this.totalLen = length;
    }

    public Node(Node left, Node right) {
        this.isConcatNode = true;
        this.leftLen = (left == null) ? 0 : left.totalLen;
        this.totalLen = this.leftLen + ((right == null) ? 0 : right.totalLen);
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return data;
    }
}
