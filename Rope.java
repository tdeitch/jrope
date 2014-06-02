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
       return new Rope(concatNds(this.root, r.root));
    }

    public Node concatNds(Node one, Node two) {
        if (one == null) {
            return two;
        } else if (two == null) {
            return one;
        }
        return new Node(one, two);
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
        Pair<Node> nodes = splitNd(root, index);
        Pair<Rope> ropes = new Pair<Rope>();
        ropes.one = new Rope(nodes.one);
        ropes.two = new Rope(nodes.two);
        return ropes;
    }

    public Pair<Node> splitNd(Node nd, int index) {
        if (nd.left == null) {
            assert index >= 0 && index <= nd.leftLen;
            Pair<Node> nodes = new Pair<Node>();
            if (index == 0) {
                nodes.one = null;
                nodes.two = nd;
            } else if (index == nd.leftLen) {
                nodes.one = nd;
                nodes.two = null;
            } else {
                nodes.one = new Node(nd.data.substring(0, index));
                nodes.two = new Node(nd.data.substring(index, nd.leftLen));
            }
            return nodes;
        }
        else if (index == nd.leftLen) {
            return new Pair<Node>(root.left, root.right);
        } else if (index < nd.leftLen) {
            Pair<Node> pair = splitNd(nd.left, index);
            return new Pair<Node>(pair.one, concatNds(pair.two, nd.right));
        } else {
            Pair<Node> pair = splitNd(nd.right, index - nd.leftLen);
            return new Pair<Node>(concatNds(nd.left, pair.one), pair.two);
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
