/**
 * This class represents a SkipNode.
 */
public class SkipNode {
    int key;
    SkipNode above;
    SkipNode below;
    SkipNode next;
    SkipNode prev;


    /**
     * Constructs a node object for skiplist.
     * @param key key of the skiplistNode.
     */
    public SkipNode(int key) {
        this.key = key;
        this.above = null;
        this.below = null;
        this.next = null;
        this.prev = null;
    }

    /**
     * Second constructor which constructs a node with same key above the given node
     * @param belowLevelNode reference node to construct the new node
     */
    public SkipNode(SkipNode belowLevelNode) {
        this.key = belowLevelNode.key;
        this.prev = null;
        this.next = null;
        this.above = null;
        this.below = belowLevelNode;
    }

}
