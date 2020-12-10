import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

/**
 * Node class used to build the linked list
 */
class Node {

    // **** ****
    public int data;
    public Node next;

    // **** constructor ****
    public Node(int x) {
        this.data = x;
        this.next = null;
    }

    // **** methdos ****
    @Override
    public String toString() {
        return "" + this.data;
    }
}

/**
 * Reverse Operations
 * https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=623634548182866
 */
public class ReverseOperations {


    /**
     * Create and populate linked list (FIFO).
     * Insertions are made at the tail of the list.
     * Deletions are made at the head of the list.
     * 
     * !!! PART OF TEST SCAFFOLDING !!!
     */
    static Node populate(int[] arr) {

        // **** sanity check(s) ****
        if (arr.length == 0)
            return null;

        // **** initialization ****
        Node head = new Node(arr[0]);
        Node tail = head;

        // **** loop throug the array inserting nodes at the tail of the linked list ****
        for (int i = 1; i < arr.length; i++) {

            // **** create node ****
            Node node = new Node(arr[i]);

            // **** point to node ****
            tail.next = node;

            // **** update tail ****
            tail = node;
        }

        // **** return linked list ****
        return head;
    }


    /**
     * Traverse displaying the linked list.
     * 
     * !!! PART OF TEST SCAFFOLDING !!!
     */
    static void traverse(Node head) {

        // **** sanity checks ****
        if (head == null)
            return;

        // **** traverse the list diplaying nodes (from head to tail) ****
        for (Node p = head; p != null; p = p.next) {
            System.out.print(p.toString());
            if (p.next != null)
                System.out.print("->");
        }
        System.out.println();
    }






    // **** store the new head of the reversed linked list ****
    static Node h                   = null;
    static Stack<Node> evenNodes    = null;


    /**
     * Recursive call.
     * 
     * !!!! WORK IN PROGRESS !!!!
     */
    static Node revEven(Node head, Node prev) {

        // **** base case ****
        if (head == null) {

            // ???? ????
            System.out.println("revEven <<< evenNodes: " + evenNodes.toString());

            // **** ****
            return null;
        }




        // **** recursive case ****
        Node temp = revEven(head.next, head);


        // **** save the new head of the linked list (if needed) ****
        if (temp == null) {

            // **** new head == last node in the linked list ****
            h = head;


            // ???? ????
            System.out.println("revEven <<< h: " + h.toString());
        }






        // **** return new next or the new head ****
        return (prev != null) ? prev : h;
    }


    /**
     * Enrty pint for recursive call.
     * 
     * !!!! WORK IN PROGRESS !!!!
     */
    static Node revEven(Node head) {

        // **** sanity checks ****
        if (head == null || head.next == null)
            return head;

        // **** initialization ****
        h           = null;
        evenNodes   = new Stack<>();

        // **** return reversed linked list ****
        return revEven(head, null);
    } 


    /**
     * Reverse singly linked list.
     * Recursive function.
     * 
     * !!! NOT PART OF SOLUTION !!!!
     */
    static Node reverseRecursive(Node head, Node prev) {
    
        // **** base case (end of linked list) ****
        if (head == null)
            return null;
    
        // **** recursive case ****
        Node temp = reverseRecursive(head.next, head);
    
        // **** save the new head of the linked list (if needed) ****
        if (temp == null) {

            // **** new head == last node in the linked list ****
            h = head;
        }

        // **** update the next element ****
        head.next = prev;

        // **** return new next or the new head ****
        return (prev != null) ? prev : h;
    }


    /**
     * Reverse singly linked list.
     * Entry point for recursive function reverseRecursive().
     * 
     * !!! NOT PART OF SOLUTION !!!!
     */
    static Node reverseList(Node head) {

        // **** sanity checks ****
        if (head == null || head.next == null)
            return head;
    
        // **** initialization ****
        h = null;
    
        // **** reverse entire linked list ****
        return reverseRecursive(head, null);
    }


    /**
     * Reverse even nodes in linked list.
     */
    static Node reverse(Node head) {

        // **** sanity checks ****
        if (head == null || head.next == null)
            return head;

        // **** initialization ****
        Stack<Node> stack   = new Stack<Node>();
        Node q              = null;
        Node r              = null;

        // **** traverse the linked list ****
        for (Node p = head; p != null; ) {

            // **** skip nodes with odd data ****
            if (p.data % 2 == 1) {

                // **** q follows p ****
                q = p;
                p = p.next;

                // **** skip this node ****
                continue;
            }

            // **** push even data nodes into stack ****
            for (r = p; r != null && (r.data % 2) == 0; r = r.next) {
                stack.push(r);
            }

            // **** reverse nodes in stack ****
            while (!stack.isEmpty()) {

                // **** ****
                p = stack.pop();

                // **** ****
                p.next = null;

                // **** updating the head of the linked list ****
                if (q != null)
                    q.next = p;
                else
                    head = p;

                // **** q follows p ****
                q = p;
                p = p.next; 
            }

            // **** ****
            q.next = r;

            // **** update pointer ****
            p = r;
        }

        // **** ****
        return head;
    }


    /**
     * Test scaffolding
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read line and split into array of Strings ****
        String[] strs = br.readLine().trim().split(",");

        // **** close buffered reader ****
        br.close();
        
        // **** create and populate integer array ****
        int[] arr = Arrays.stream(strs)
                        .mapToInt(Integer::parseInt)
                        .toArray();

        // ???? ????
        System.out.println("main <<< arr: " + Arrays.toString(arr));

        // ***** create and populate linked list ****
        Node head = populate(arr);

        // ???? ????
        System.out.print("main <<< populate: ");
        traverse(head);

        // **** reverse linked list ****
        head = reverse(head);

        // ???? ????
        System.out.print("main <<< reverse: ");
        traverse(head);

        // ***** create and populate linked list ****
        head = populate(arr);

        // **** reverse entire linked list recursively ****
        head = reverseList(head);

        // ???? ????
        System.out.print("main <<< reverseList: ");
        traverse(head);

        // // ***** create and populate linked list ****
        // head = populate(arr);

        // // **** reverse even elements in linked list ****
        // head = revEven(head);

        // // **** display reversed linked list ****
        // System.out.print("main <<< revEven: ");
        // traverse(head);
    }
}