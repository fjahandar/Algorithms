import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class represents a Binomial Heap.
 */
public class BinomialHeap {
    private BNode head;
    private BNode minimum;


    /**
     * Constructs a BinomialHeap object.
     */
    public BinomialHeap() {
        head = null;
    }

    /**
     * Returns a new heap object.
     *
     * @return a new heap object.
     */
    public BinomialHeap makeHeap() {
        return new BinomialHeap();
    }

    /**
     * Returns the node with the minimum key in the heap.
     *
     * @return the node with the minimum key.
     */
    public BNode bHeapMinimum() {
        BNode y = null;
        BNode x = head;
        int min = Integer.MAX_VALUE;
        while (x != null) {
            if (x.key < min) {
                min = x.key;
                y = x;
            }
            x = x.sibling;
        }
        return y;
    }

    /**
     * makes node y the new head of the linked list of node z's children (makes z the parent of y).
     *
     * @param y new head of the z's children.
     * @param z prev head of the z's children.
     */
    private void link(BNode y, BNode z) {
        if (y.degree == z.degree) {
            y.parent = z;
            y.sibling = z.child;
            z.child = y;
            z.degree++;
        } else {
            System.out.println("Not compatible nodes!");
        }
    }

    /**
     * Unite the heap with given heap.
     *
     * @param heap the heap to be united with this heap.
     * @return returns the union of the heaps.
     */
    public BinomialHeap union(BinomialHeap heap) {
        BinomialHeap newHeap = makeHeap();
        newHeap.head = merge(this, heap);
        BNode newHead = newHeap.head;

        head = null;
        heap.head = null;

        if (newHead == null) {
            return newHeap;
        }
        BNode prev = null;
        BNode curr = newHead;
        BNode next = newHead.sibling;

        while (next != null) {
            if (curr.degree != next.degree || (next.sibling != null && next.sibling.degree == curr.degree)) {
                prev = curr;
                curr = next;
            } else {

                if (curr.key <= next.key) {
                    curr.sibling = next.sibling;
                    link(next, curr);

                } else {
                    if (prev == null) {
                        newHead = next;
                    } else {
                        prev.sibling = next;
                    }
                    link(curr, next);
                    curr = next;
                }
            }
            next = curr.sibling;
        }
        this.head = newHead;
        return newHeap;
    }

    /**
     * Merges the given heaps.
     *
     * @param heap1 first heap to be merged.
     * @param heap2 second heap to be merged.
     * @return merged of the given two heaps.
     */
    private BNode merge(BinomialHeap heap1, BinomialHeap heap2) {
        if (heap1.head == null) {
            return heap2.head;
        } else if (heap2.head == null) {
            return heap1.head;
        } else {
            BNode head;
            BNode heap1Next = heap1.head;
            BNode heap2Next = heap2.head;
            if (heap1.head.degree <= heap2.head.degree) {
                head = heap1.head;
                heap1Next = heap1Next.sibling;
            } else {
                head = heap2.head;
                heap2Next = heap2Next.sibling;
            }
            BNode tail = head;
            while (heap1Next != null && heap2Next != null) {
                if (heap1Next.degree <= heap2Next.degree) {
                    tail.sibling = heap1Next;
                    heap1Next = heap1Next.sibling;
                } else {
                    tail.sibling = heap2Next;
                    heap2Next = heap2Next.sibling;

                }
                tail = tail.sibling;
            }
            if (heap1Next != null) {
                tail.sibling = heap1Next;
            } else {
                tail.sibling = heap2Next;
            }
            updateMin();
            return head;
        }
    }

    /**
     * Helper method to update the pointer to the minimum node in the heap.
     */
    private void updateMin() {
        if (head == null) {
            minimum = null;
        }
        BNode min = head;
        BNode minPrev = null;
        BNode next = min.sibling;
        BNode nextPrev = min;
        while (next != null) {
            if (next.key < min.key) {
                min = next;
                minPrev = nextPrev;
            }
            nextPrev = next;
            next = next.sibling;

        }
        minimum = min;
    }

    /**
     * Inserts the given node to BH.
     *
     * @param node node to be inserted.
     */
    public void insert(BNode node) {
        if (search(node.key) == null) {
            BinomialHeap insertHeap = makeHeap();
            insertHeap.head = node;
            this.union(insertHeap);
            updateMin();
        } else {
            System.out.println("Key " + node.key + " already exists!");
        }
    }

    /**
     * Extracts the minimum node and returns it.
     *
     * @return node with minimum key value.
     */
    public BNode extractMin() {
        // for an empty binomial heap.
        if (head == null)
            return null;

        BNode x = head;
        BNode xPrev = null;  // x prev
        BNode minNode = bHeapMinimum();

        // Determine the node x with the minimum key in the root list.
        while (x.key != minNode.key) {
            xPrev = x;
            x = x.sibling;
        }
        if (xPrev == null) {
            head = x.sibling;
        } else {
            xPrev.sibling = x.sibling;
        }
        x = x.child;
        BNode z = x;

        while (x != null) {
            x.parent = null;
            x = x.sibling;
        }

        if ((head == null) && (z == null)) {

        } else {
            if ((head == null) && (z != null)) {
                head = z.reverse(null);
            } else {
                if ((head != null) && (z == null)) {

                } else {
                    BinomialHeap h = new BinomialHeap();
                    h.head = z.reverse(null);
                    union(h);
                }
            }
        }
        return minNode;
    }

    /**
     * decreases the key value to the given value.
     *
     * @param node   the node that its key is going to be decreased.
     * @param newKey the new key smaller than the given node's key.
     */
    public void decreaseKey(BNode node, int newKey) {
        if (newKey > node.key) {
            System.out.println("new key is greater than current key");
        } else {
            node.key = newKey;
            BNode y = node;
            BNode z = y.parent;
            while (z != null && y.key < z.key) {
                int temp = y.key;
                y.key = z.key;
                z.key = temp;
                y = z;
                z = z.parent;
            }
            updateMin();
        }
    }

    /**
     * Removes the given node from the heap.
     *
     * @param node the node to be removed from the heap.
     */
    public void delete(BNode node) {
        if (head != null && node != null && search(node.key) != null) {
            decreaseKey(node, Integer.MIN_VALUE);
            extractMin();
            updateMin();
        } else {
            System.out.println("No such an element in the heap!");
        }
    }

    /**
     * Looks for the node with given key in the heap.
     *
     * @param key the key value of the node.
     * @return the node with given key value.
     */
    public BNode search(int key) {
        BNode temp = this.head;
        BNode rNode = null;
        BNode cNode;
        BNode sNode;
        BNode sChildNode;

        while (temp != null) {
            if (temp.key == key) {
                rNode = temp;
                return rNode;
            }
            if (temp.child != null) {
                cNode = temp.child;
                while (cNode != null) {
                    if (cNode.key == key) {
                        return cNode;
                    }
                    if (cNode.sibling != null) {
                        sNode = cNode.sibling;
                        while (sNode != null) {
                            if (sNode.key == key) {
                                return sNode;
                            }
                            sNode = sNode.sibling;
                        }
                    }
                    cNode = cNode.child;
                }
            }
            temp = temp.sibling;
        }
        return null;

//        throw new NoSuchElementException("Not found!");

    }

    /**
     * prints the heap.
     */
    public void print() {
        System.out.print("\nHeap : \n");
        displayHeap(head);
        System.out.println("\n");
    }

    /**
     * helper method for print function.
     *
     * @param r the node to start the printing from.
     */
    private void displayHeap(BNode r) {
        if (r != null) {
            displayHeap(r.child);
            if (r.child != null && r.parent != null) {
                System.out.print(r.key + "-> parent: " + r.parent.key + " -> child: " + r.child.key + "\n");
            } else {
                if (r.child == null && r.parent != null) {
                    System.out.print(r.key + "-> parent: " + r.parent.key + " -> child: " + r.child + "\n");
                } else if (r.child != null && r.parent == null) {
                    System.out.print(r.key + "-> parent: " + r.parent + " -> child: " + r.child.key + "\n");
                } else {
                    System.out.print(r.key + "-> parent: " + r.parent + " -> child: " + r.child + "\n");
                }
            }
            displayHeap(r.sibling);
        }
    }

    public static void main(String[] args) {
        BinomialHeap bh = new BinomialHeap();
        BNode a = new BNode(1);
        BNode b = new BNode(10);
        BNode c = new BNode(6);
        BNode d = new BNode(12);
        BNode e = new BNode(25);
        BNode f = new BNode(18);
        BNode h = new BNode(8);
        BNode i = new BNode(14);
        BNode j = new BNode(29);
        BNode k = new BNode(11);
        BNode l = new BNode(17);
        BNode m = new BNode(38);
        BNode g = new BNode(27);

        bh.insert(c);
        bh.insert(j);


        BinomialHeap bh1 = bh.makeHeap();
        bh1.insert(i);
        bh1.insert(m);

        bh.union(bh1);


        BinomialHeap bh2 = bh.makeHeap();
        bh2.insert(h);
        bh2.insert(l);
        BinomialHeap bh3 = bh.makeHeap();
        bh3.insert(k);
        bh3.insert(g);
        bh2.union(bh3);

        bh.union(bh2);

        BinomialHeap bh4 = bh.makeHeap();
        bh4.insert(a);
        bh4.insert(e);

        BinomialHeap bh5 = bh.makeHeap();
        bh5.insert(d);
        bh5.insert(f);

        bh4.union(bh5);
        bh.union(bh4);

        BinomialHeap bh6 = bh.makeHeap();
        bh6.insert(b);
        bh.union(bh6);
        bh.print();

        bh.extractMin();
        bh.print();
        bh.delete(bh.search(10));
        bh.print();
        bh.decreaseKey(bh.search(29), 5);
        bh.print();



//        int c;
//        Scanner sc = new Scanner(System.in);
//        BinomialHeap bh = new BinomialHeap();
//        do {
//            System.out.println("""
//                    1.Insert
//                    2.Minimum
//                    3.Extract Min
//                    4.Decrease Key
//                    5.Delete
//                    6.Print heap
//                    7.Exit
//                    ---->\040""");
//            c = sc.nextInt();
//            switch (c) {
//                case 1:
//                    Scanner sc1 = new Scanner(System.in);
//                    System.out.print("Enter the element key to insert: ");
//                    Integer value = sc1.nextInt();
//                    BNode node = new BNode(value);
//                    bh.insert(node);
//                    break;
//                case 2:
//                    System.out.println("Minimum Element : " + bh.bHeapMinimum().key);
//                    break;
//                case 3:
//                    bh.extractMin();
//                    break;
//                case 4:
//                    Scanner sc2 = new Scanner(System.in);
//                    System.out.print("Enter the key : ");
//                    int oldKey = sc2.nextInt();
//                    Scanner sc3 = new Scanner(System.in);
//                    System.out.print("Enter the new key : ");
//                    int newKey = sc3.nextInt();
//                    bh.decreaseKey(bh.search(oldKey), newKey);
//                    bh.print();
//                    break;
//                case 5:
//                    Scanner sc4 = new Scanner(System.in);
//                    System.out.print("Enter the key to delete: ");
//                    int key = sc4.nextInt();
//                    bh.delete(bh.search(key));
//                    bh.print();
//                    break;
//                case 6:
//                    bh.print();
//                    break;
//            }
//        } while (c != 7);
    }
}