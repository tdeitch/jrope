public class Node {
    boolean isConcatNode;
    String data;
    Node left;
    Node right;
    int length;
    int leftSubtreeLength;
    int totalSubtreeLength;
    
    public Node(boolean isConcatNode, String data) {
        this.isConcatNode = isConcatNode;
        this.data = data;
        this.length = (data == null) ? 0 : data.length();
    }
}