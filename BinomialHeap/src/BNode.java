/**
 * This class represents a node for binomial heap
 */
public class BNode {
    public int key;
    public int degree;
    public BNode parent;
    public BNode child;
    public BNode sibling;

    /**
     * Constructs an object for BNode.
     *
     * @param key key value of the new node.
     */
    public BNode(Integer key) {
        this.key = key;
        this.degree = 0;
        this.parent = null;
        this.child = null;
        this.sibling = null;
    }

    public BNode reverse(BNode sibl) {
        BNode ret;
        if (sibling != null)
            ret = sibling.reverse(this);
        else
            ret = this;
        sibling = sibl;
        return ret;
    }
}
