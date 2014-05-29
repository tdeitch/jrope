public class Node {
    boolean isConcatNode;
    String data;
    Node left;
    Node right;
    Node parent;
    int leftLen;
    int totalLen;
    
    public Node(String data) {
        this.isConcatNode = false;
        this.data = data;
        this.leftLen = data.length();
        this.totalLen = data.length();
    }

    public Node(Node left, Node right) {
        this.isConcatNode = true;
        this.leftLen = (left == null) ? 0 : left.totalLen;
        this.totalLen = this.leftLen + ((right == null) ? 0 : right.totalLen);
        this.left = left;
        this.right = right;
    }

}