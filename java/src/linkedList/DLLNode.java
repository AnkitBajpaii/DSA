package linkedList;

/*Doubly linked list node*/
public class DLLNode extends ListNode {
    
    public ListNode prev;

    DLLNode(int x) {
        super(x);
        prev = null;
    }
}