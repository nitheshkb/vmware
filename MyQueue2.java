package interview;

/*
Implement a queue with the following operations:

I sed BST with two more pointers for queue maintenance, this will works with complexity of BST which is log n in most of the scenario.
with is DS we can always change the underlying DS which is BST.
*/

public class MyQueue2 {
    QueueNode first, last, root = null;

    Integer poll() {
        if (root != null) {
            int data = first.data;
            first = first.next;
            root = deleteRec(root, data);
            return data;
        }
        return null;
    }

    void offer(Integer i) {
        QueueNode offerNode = new QueueNode(i);
        QueueNode node = insert(root, offerNode);
        if (root == null) {
            first = root = last = node;
        } else {
            last.next = offerNode;
            offerNode.prev = last;
            last = offerNode;
        }
    }

    Integer getMin() {
        return minNode(root).data;
    }

    Integer deleteMin() {
        QueueNode node = minNode(root);
        root = deleteRec(root, node.data);

        //de-link min from queue
        node.prev.next = node.next;
        node.next.prev = node.prev;

        return node.data;
    }

    QueueNode insert(QueueNode node, QueueNode offerNode) {

        /* 1. If the tree is empty, return a new,
         single node */
        if (node == null) {
            return offerNode;
        } else {

            /* 2. Otherwise, recur down the tree */
            if (offerNode.data <= node.data) {
                node.left = insert(node.left, offerNode);
            } else {
                node.right = insert(node.right, offerNode);
            }

            /* return the (unchanged) node pointer */
            return node;
        }
    }

    QueueNode deleteRec(QueueNode node, int key) {
        /* Base Case: If the tree is empty */
        if (node == null)
            return node;

        /* Otherwise, recur down the tree */
        if (key < node.data) {
            node.left = deleteRec(node.left, key);
        } else if (key > node.data) {
            node.right = deleteRec(node.right, key);
        }

        // if key is same as root's
        // key, then This is the
        // node to be deleted
        else {
            // node with only one child or no child
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;

            // node with two children: Get the inorder
            // successor (smallest in the right subtree)
            node.data = minNode(node.right).data;

            // Delete the inorder successor
            node.right = deleteRec(node.right, node.data);
        }
        return node;
    }

    QueueNode minNode(QueueNode node) {
        int minv = node.data;
        while (node.left != null) {
            minv = node.left.data;
            node = node.left;
        }
        return node;
    }

    void printQueue() {
        QueueNode node = first;
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        MyQueue2 myQueue2 = new MyQueue2();
        myQueue2.offer(2);
        myQueue2.offer(3);
        myQueue2.offer(4);
        myQueue2.offer(1);
        myQueue2.offer(5);
        myQueue2.offer(6);

        myQueue2.printQueue();
        System.out.println("---");
        System.out.println(myQueue2.deleteMin());
        System.out.println("---");
        myQueue2.printQueue();

    }
}


class QueueNode {

    int data;
    //for tree
    QueueNode left, right;
    //for queue
    QueueNode prev, next;

    QueueNode(int data) {
        this.data = data;
        left = right = next = prev = null;
    }

    public int getData() {
        return data;
    }
}