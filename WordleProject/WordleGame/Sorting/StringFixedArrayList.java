public class StringFixedArrayList implements StringList {

    String [] list;
    int length = 0;

    public StringFixedArrayList(int size) {
        list = new String[size];
    }
    
    public void add(String x) {
        if ( length == list.length ) {
            // List is full
            throw new IndexOutOfBoundsException("no more room.");
        }
        else {
            list[length] = x;
            length = length + 1;
        }
    }

    public boolean contains(String x) {
        for ( int i = 0 ; i < length ; i++ ) {
            if ( x.equals(list[i])) {
                return true;
            }
        }
        return false;
    }

    public String get(int i) {
        if ( i < length ) {
            return list[i];
        }

        throw new IndexOutOfBoundsException("" + i);
    }

    public void remove(String x) {
        // TODO Auto-generated method stub
        for ( int i = 0 ; i < length ; i++ ) {
            if ( x.equals(list[i]) ) {
                for ( int j = i ; j < length-1 ; j++ ) {
                    list[j] = list[j+1];
                }
                length = length - 1;
                return;
            }
        }
        
    }

    public void clear() {
        length = 0;
    }

    public int length() {
        return length;
    }

    public void sort() {
        for ( int i = 0 ; i < length-1 ; i++ ) {
            for ( int j = 0 ; j < length-(i+1) ; j++ ) {
                if ( list[j].compareTo(list[j+1]) > 0 ) {
                    // swap
                    String temp = list[j];
                    list[j] = list[j+1];
                    list[j+1] = temp;
                }
            }
        }
    }

    public void mergeSort() {
        String[] data = new String[length];
        for ( int i = 0 ; i < length ; i++ ) {
            data[i] = list[i];
        }

        data = mergeSort(data);

        for ( int i = 0 ; i < length ; i++ ) {
            list[i] = data[i];
        }
    }

    private String[] mergeSort(String[] arr) {

        // Split arr into ar1 & ar2

        int n = arr.length;
        int n1 = n/2;
        int n2 = n - n1;
        String[] ar1 = new String[n1];
        String[] ar2 = new String[n2];

        for ( int i = 0 ; i < n1 ; i++ ) {
            ar1[i] = arr[i];
        }
        for ( int i = 0; i < n2 ; i++ ) {
            ar2[i] = arr[i+n1];
        }

        ar1 = mergeSort(ar1);
        ar2 = mergeSort(ar2);

        // now merge ar1 & ar2 into arr
        int i1 = 0, i2 = 0;
        for ( int i = 0 ; i < n ; i++ ) {
            if ( i2 < n2 && (i1 >= n1 || ar1[i1].compareTo(ar2[i2]) > 0) ) {
                arr[i] = ar2[i2];
                i2 = i2 + 1;
            }
            else {
                arr[i] = ar1[i1];
                i1 = i1 + 1;
            }
        }

        // Return the result
        return arr;
    }
    
}
