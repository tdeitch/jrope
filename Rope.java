public class Rope {

    Node root;
    int len;

    public Rope(String str) {
        root = new Node(str);
        len = root.leftLen;
    }

    public Rope(Node root) {
        this.root = root;
        int len = 0;
        for (Node nd = root; nd != null; nd = nd.right) {
            len += nd.leftLen;
        }
        this.len = len;
    }

    public int length() {
        return len;
    }

    public Rope concat(Rope r) {
        Node root = new Node(this.root, r.root);
        return new Rope(root);
    }

    private char charAt(Node node, int i) {
        if(node.left == null) {
            assert i >= 0 && i < node.leftLen;
            return node.data.charAt(i);
        }

        if(node.leftLen > i) {
            return charAt(node.left, i);
        }
        else {
            return charAt(node.right, i - node.leftLen);
        }
    }

    public char charAt(int i) {
        return charAt(root, i);
    }

    public Pair<Rope> split(int index) {
        return split(root, index);
    }

    public Pair<Rope> split(Node nd, int index) {
        if (nd.left == null) {
            assert index >= 0 && index <= nd.leftLen;
            Rope left;
            Rope right;
            if (index == 0) {
                left = new Rope("");
                right = new Rope(nd);
            } else if (index == nd.leftLen) {
                left = new Rope(nd);
                right = new Rope("");
            } else {
                left = new Rope(nd.data.substring(0, index));
                right = new Rope(nd.data.substring(index, nd.leftLen));
            }
            return new Pair<Rope>(left, right);
        }
        else if (index == nd.leftLen) {
            return new Pair<Rope>(new Rope(root.left), new Rope(root.right));
        } else if (index < nd.leftLen) {
            Pair<Rope> pair = split(nd.left, index);
            return new Pair<Rope>(pair.one, pair.two.concat(new Rope(nd.right)));
        } else {
            Pair<Rope> pair = split(nd.right, index - nd.leftLen);
            return new Pair<Rope>((new Rope(nd.left)).concat(pair.one), pair.two);
        }
    }

    public Rope substring(int start, int end) {
        Pair<Rope> sp1 = split(start);
        Pair<Rope> sp2 = sp1.two.split(end - start);
        return sp2.one;
    }

    public Rope insert(Rope r, int index) {
        Pair<Rope> pair = this.split(index);
        return pair.one.concat(r).concat(pair.two);
    }

    public String toString(Node node) {
        if(node.left == null) return node.data;
        return toString(node.left) + toString(node.right);
    }

    public String toString() {
        return toString(root);
    }
}
