public class Rope {

    Node root;

    public Rope(String str) {
        root = new Node(false, str);
    }
    
    public Rope(Node root) {
        this.root = root;
    }
    
    public int length() {
        return root.totalSubtreeLength;
    }
    
    public Rope concat(Rope r) {
        Node root = new Node(true, null);
        root.left = this.root;
        root.right = r.root;
        return new Rope(root);
    }
    
    private char charAt(Node node, int i) {
        if(!node.isConcatNode) {
            assert i >= 0 && i < node.data.length();
            return node.data.charAt(i);
        }
        int leftLength = node.left.length;
        if(leftLength < i) return charAt(node.left, i);
        else return charAt(node.right, i - leftLength);
    }
    
    public char charAt(int i) {
        return charAt(root, i);
    }
    
    public Pair<Rope> split() {
        return new Pair<Rope>(new Rope(root.left), new Rope(root.right));
    }
    
    public String toString(Node node) {
        if(!node.isConcatNode) return node.data;
        return toString(node.left) + toString(node.right);
    }
    
    public String toString() {
        return toString(root);
    }
}