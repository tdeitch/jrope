public class Rope {

    String data;
    Rope left;
    Rope right;
    int leftLen;

    public Rope(String data) {
        this.data = data;
        this.leftLen = data.length();
    }

    public Rope(Rope left, Rope right) {
        this.left = left;
        this.right = right;
        int leftLen = length(left);
    }

    public int length() {
        return length(this);
    }

    public int length(Rope r) {
        int len = 0;
        for (; r != null; r = r.right) {
            len += r.leftLen;
        }
        return len;
    }

    public Rope concat(Rope r) {
       return concat(this, r);
    }

    public Rope concat(Rope one, Rope two) {
        if (one == null) {
            return two;
        } else if (two == null) {
            return one;
        }
        return new Rope(one, two);
    }

    private char charAt(Rope node, int i) {
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
        return charAt(this, i);
    }

    public Pair<Rope> split(int index) {
        return split(this, index);
    }

    public Pair<Rope> split(Rope nd, int index) {
        if (nd.left == null) {
            assert index >= 0 && index <= nd.leftLen;
            Pair<Rope> nodes = new Pair<Rope>();
            if (index == 0) {
                nodes.one = null;
                nodes.two = nd;
            } else if (index == nd.leftLen) {
                nodes.one = nd;
                nodes.two = null;
            } else {
                nodes.one = new Rope(nd.data.substring(0, index));
                nodes.two = new Rope(nd.data.substring(index, nd.leftLen));
            }
            return nodes;
        }
        else if (index == nd.leftLen) {
            return new Pair<Rope>(nd.left, nd.right);
        } else if (index < nd.leftLen) {
            Pair<Rope> pair = split(nd.left, index);
            return new Pair<Rope>(pair.one, concat(pair.two, nd.right));
        } else {
            Pair<Rope> pair = split(nd.right, index - nd.leftLen);
            return new Pair<Rope>(concat(nd.left, pair.one), pair.two);
        }
    }

    public Rope substring(int start, int end) {
        Pair<Rope> sp1 = split(start);
        Pair<Rope> sp2 = sp1.two.split(end - start);
        return sp2.one;
    }

    public Rope insert(Rope r, int index) {
        Pair<Rope> pair = this.split(index);
        return concat(concat(pair.one, r), pair.two);
    }

    public String toString() {
        if(left == null) return data;
        return left.toString() + right.toString();
    }
}
