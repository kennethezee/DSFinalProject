public class StringLinkedList implements StringList {

    Node list;
    int length;

    public StringLinkedList() {
        list = null;
        length = 0;
    }

    private class Node {
        String data;
        Node next;
    }

    public void add(String x) {
        Node newNode = new Node();
        newNode.data = x;
        newNode.next = list;

        list = newNode;
        length = length + 1;
    }

    public void clear() {
        list = null;
        length = 0;        
    }

    public boolean contains(String x) {
        // TODO Auto-generated method stub
        return false;
    }

    public String get(int i) {
        Node current = list;
        for ( int j = 0 ; j < i ; j++ ) {
            current = current.next;
        }
        return current.data;
    }

    public int length() {
        // TODO Auto-generated method stub
        return 0;
    }

    public void remove(String x) {
        // TODO Auto-generated method stub
        
    }

    public void quickSort() {
        list = quickSort(list);
    }

    private Node quickSort(Node ll) {

        // Lists of 0 or 1 things is already sorted
        if ( ll == null || ll.next == null ) {
            return ll;
        }

        Node small = null, same = null, big = null;

        Node cur = ll.next;

        same = ll;
        same.next = null;

        // Split by using the pivot
        while ( cur != null ) {
            int comp = cur.data.compareTo(same.data);

            if ( comp < 0 ) { // Put current on small
                Node next = cur.next;
                cur.next = small;
                small = cur;
                cur = next;
            }
            else if ( comp == 0 ) { // put current on same
                Node next = cur.next;
                cur.next = same;
                same = cur;
                cur = next;
            }
            else { // Put current on big, since comp > 0
                Node next = cur.next;
                cur.next = big;
                big = cur;
                cur = next;
            }
        }

        // Sort the parts
        small = quickSort(small);
        big = quickSort(big);

        // Paste them back together
        ll = small;
        if ( small != null ) {
            cur = small;
            while ( cur.next != null ) {
                cur = cur.next;
            }
            cur.next = same;
        }
        else {
            ll = same;
        }

        cur = same;
        while ( cur.next != null ) {
            cur = cur.next;
        }
        cur.next = big;

        return ll;
    }
}