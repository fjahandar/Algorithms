import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents a SkipList.
 */
class SkipList {
    // Starting nodes, always from top level.
    private SkipNode head;
    private SkipNode tail;

    /**
     * Constructs an object for SkipList.
     */
    public SkipList() {
        // Set the head and tail nodes to minus and positive infinity.
        head = new SkipNode(Integer.MIN_VALUE);
        tail = new SkipNode(Integer.MAX_VALUE);

        // Setting the correct pointers between head and tail.
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Shows the status of the node, if it is already in list or not.
     * @param key key value of the node we are looking for.
     */
    public void lookUp(int key) {
//        //Starting from head look for the given key value.
//        SkipNode curr = head;
//
//        while (curr != null) {
//            while (curr.next != null && curr.next.key <= key) {
//                curr = curr.next;
//            }
//
//            if (curr.key == key) {
//                break;
//            }
//
//            curr = curr.below;
//        }
//        if (curr == null) {
//            System.out.println("Key not found!");
//            return null;
//        }
//        System.out.println("Search Successful!");
//        return curr;
        if (!isInList(key)) {
            System.out.println("Key not found!");
        }
        else {
            System.out.println("Key found in the list!");
        }
    }


    /**
     * Helper method to check if element is in the list.
     * @param key key of the element
     * @return boolean, true if key is already in the list and false otherwise.
     */
    private boolean isInList(int key) {
        //Starting from head look for the given key value.
        SkipNode curr = head;

        // Go as right as it and then as below as it can
        while (curr != null) {
            while (curr.next != null && curr.next.key <= key) {
                curr = curr.next;
            }

            if (curr.key == key) {
                return true;
            }

            curr = curr.below;
        }
        return false;
    }

    /* Function to insert element */
    public void insert(int key) {
        if (isInList(key)) {
            System.out.println("Key already exists in the list!");
        }
        else {
            List<SkipNode> pointersToUpdate = new ArrayList<>();
            SkipNode curr = head;

            while (curr != null) {
                while (curr.next != null && curr.next.key < key) {
                    curr = curr.next;
                }

                pointersToUpdate.add(curr);
                curr = curr.below;
            }

            // insert after this node.
            int level = 0;
            SkipNode newNode = null;

            while (level == 0 || flipCoin()) {
                // now move up.
                if (newNode == null) {
                    newNode = new SkipNode(key);
                } else {
                    newNode = new SkipNode(newNode);
                }

                SkipNode nodeToUpdate;

                if (pointersToUpdate.size() <= level) {
                    createNewLevel();
                    nodeToUpdate = this.head;
                } else {
                    nodeToUpdate = pointersToUpdate.get(pointersToUpdate.size() - level - 1);
                }

                // insert
                newNode.next = nodeToUpdate.next;
                newNode.prev = nodeToUpdate;

                newNode.next.prev = newNode;
                nodeToUpdate.next = newNode;

                level++;
            }
        }
    }

    /**
     * helper method to give the random number for insertion method.
     * It is like a flipping a coin and decide according to the result of the flip coin.
     * @return boolean, true if random number satisfies the given condition and false otherwise.
     */
    private boolean flipCoin() {
        return Math.random() >= 0.5;
    }

    /**
     * Helper method for insertion. This method add new level upon insertion if needed.
     * This also updates the pointers accordingly.
     */
    private void createNewLevel() {
        SkipNode newHead = new SkipNode(Integer.MIN_VALUE);
        SkipNode newTail = new SkipNode(Integer.MAX_VALUE);

        newHead.next = newTail;
        newTail.prev = newHead;

        head.above = newHead;
        newHead.below = head;
        head = newHead;

        tail.above = newTail;
        newTail.below = tail;
        tail = newTail;
    }

    public void delete(int key) {
        if (isInList(key)) {
            List<SkipNode> pointersToUpdate = new ArrayList<>();

            SkipNode curr = this.head;
            while (curr != null) {
                while (curr.next != null && curr.next.key < key) {
                    curr = curr.next;
                }

                if (curr.next.key == key) {
                    pointersToUpdate.add(curr);
                }

                curr = curr.below;
            }

            for (SkipNode nodeToUpdate : pointersToUpdate) {
                SkipNode nodeToDelete = nodeToUpdate.next;

                nodeToUpdate.next = nodeToDelete.next;
                nodeToDelete.next.prev = nodeToUpdate;

                nodeToDelete.above = null;
                nodeToDelete.below = null;
            }
        } else {
            System.out.println("Key not found in the list!");
        }
    }

    /**
     * Prints the list of the keys in the skiplist.
     */
    public void print() {
        SkipNode curr = this.head;

        // go to the base level
        while (curr.below != null) {
            curr = curr.below;
        }

        curr = curr.next;

        // print all the keys in the base level one by one.
        while (curr.next != null) {
            System.out.print(curr.key + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    /**
     * prints all the levels with their nodes one by one from top.
     */
    public void printAllLevels() {
        SkipNode curr = this.head;

        while (curr != null) {
            SkipNode firstInLevel = curr;
            firstInLevel = firstInLevel.next;

            // Starting from head traverse each level to the right as far as possible
            while (firstInLevel.next != null) {
                System.out.print("->");
                System.out.print(firstInLevel.key + "->");
                firstInLevel = firstInLevel.next;
            }

            // goes one level down when traversing to the right is not possible.
            curr = curr.below;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int choice;
        SkipList list = new SkipList();

        do {
            Scanner sc1 = new Scanner(System.in);
            System.out.print("Please select one of the options below: \n");
            System.out.print("1.insert\n");
            System.out.print("2.delete\n");
            System.out.print("3.lookUp\n");
            System.out.print("4.print the list\n");
            System.out.print("5.print levels\n");
            System.out.print("6.quit\n");
            System.out.print("---> ");
            choice = sc1.nextInt();

            if (choice == 1) {
                for (int i = 1; i < 1000; i++) {
                    list.insert(i);
                }
//                Scanner sc2 = new Scanner(System.in);
//                System.out.print("Please Enter the value you want to insert: \n");
//                System.out.print("---> ");
//                String stringValue = sc2.nextLine();
//                list.insert(Integer.parseInt(stringValue));
            }

            if (choice == 2) {
                Scanner sc3 = new Scanner(System.in);
                System.out.print("Please Enter the value you want to delete: \n");
                System.out.print("---> ");
                String stringValue = sc3.nextLine();
                list.delete(Integer.parseInt(stringValue));
            }

            if (choice == 3) {
                Scanner sc4 = new Scanner(System.in);
                System.out.print("Please Enter the value you want to lookUp: \n");
                System.out.print("---> ");
                String stringValue = sc4.nextLine();
                list.lookUp(Integer.parseInt(stringValue));
            }

            if (choice == 4) {
                list.print();
            }

            if (choice == 5) {
                list.printAllLevels();
            }
        } while (choice != 6);
    }
}
