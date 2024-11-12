import java.util.*;

public class SearchingAnalysis {

    public void linear(int []arr, int key) {
        int n = arr.length;
        int indx = -1;
        for(int i = 0; i < n; i++) {
            if(arr[i] == key) {
                indx = i;
            }
        }
        if(indx == -1) {
            System.out.println("key not present !");
        }
        else {
            System.out.println("Key found at index: "+indx);
        }
    }

    // requires sorted 
    public void binsearch(int[] arr, int key) {
        int l = 0, r = arr.length - 1, indx = -1;
        while(l <= r) {
            int m = (l + r) / 2;
            if(arr[m] == key) {
                indx = m;
                break;
            }
            else if(key > arr[m]) {
                l = m + 1;
            }
            else {
                r = m - 1;
            }
        }
        if(indx == -1) {
            System.out.println("key not present !");
        }
        else {
            System.out.println("Key found at index: "+indx);
        } 
    }

    public static int[] randomint(int n) {
        int min = 0, max = 100;
        int[] arr = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(max - min) + min;
        }
        return arr;
    }

    public static void printarr(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < Math.min(20, n); i++) {
            System.out.print(arr[i] + ", ");
        }
        System.err.println("\n");
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the value of n: ");
        int n = sc.nextInt();
        SearchingAnalysis o1 = new SearchingAnalysis();
        int arr[] = new int[n];
        arr = randomint(n);
        System.out.println("Enter the value of key: ");
        int key = sc.nextInt();

        printarr(arr);
        // linear
        long stlintime = System.nanoTime();
        o1.linear(arr, key);
        long endlintime = System.nanoTime();
        long lindur = endlintime - stlintime;

        // binary search
        Arrays.sort(arr);
        long stbintime = System.nanoTime();
        o1.binsearch(arr, key);
        long endbintime = System.nanoTime();
        long bindur = endbintime - stbintime;

        System.out.println("Time take by Linear search: "+lindur);
        System.out.println("Time take by Binary search: "+bindur);
        sc.close();
    }
    
}
