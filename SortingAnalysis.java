import java.util.*;

public class SortingAnalysis {

    // A class to store algorithm name and its execution time.
    static class AlgorithmTime {
        String algorithmName;
        long timeTaken;

        AlgorithmTime(String algorithmName, long timeTaken) {
            this.algorithmName = algorithmName;
            this.timeTaken = timeTaken;
        }
    }

    private static int[] swap(int arr[], int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        return arr;
    }

    public int[] bubble(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    arr = swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    public int[] mergesort(int arr[], int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergesort(arr, l, m);
            mergesort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
        return arr;
    }

    private static void merge(int arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] larr = new int[n1];
        int[] rarr = new int[n2];
        System.arraycopy(arr, l, larr, 0, n1);
        System.arraycopy(arr, m + 1, rarr, 0, n2);

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (larr[i] <= rarr[j]) {
                arr[k] = larr[i];
                i++;
            } else {
                arr[k] = rarr[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = larr[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = rarr[j];
            j++;
            k++;
        }
    }

    public int[] insertsort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    public int[] quicksort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quicksort(arr, low, pi - 1);
            quicksort(arr, pi + 1, high);
        }
        return arr;
    }

    private static int partition(int[] arr, int low, int high) {
        int pi = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pi) {
                i++;
                arr = swap(arr, i, j);
            }
        }
        arr = swap(arr, i + 1, high);
        return i + 1;
    }

    public int[] countsort(int arr[]) {
        if (arr.length == 0) {
            return arr;  // Empty array check
        }
        int max = Arrays.stream(arr).max().getAsInt();
        int[] count = new int[max + 1];
        for (int num : arr) {
            count[num]++;
        }
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr[index++] = i;
                count[i]--;
            }
        }
        return arr;
    }

    public int[] radixsort(int arr[]) {
        int max = Arrays.stream(arr).max().getAsInt();
        for (int exp = 1; (max / exp) > 0; exp *= 10) {
            countsortdigit(arr, exp);
        }
        return arr;
    }

    private static void countsortdigit(int[] arr, int exp) {
        int[] output = new int[arr.length];
        int[] count = new int[10];
        for (int i = 0; i < arr.length; i++) {
            count[(arr[i] / exp) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    public float[] bucketSort(float[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int n = arr.length;
        @SuppressWarnings("unchecked")
        ArrayList<Float>[] buckets = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (float num : arr) {
            int index = (int) (num * n);
            buckets[index].add(num);
        }

        int k = 0;
        for (ArrayList<Float> bucket : buckets) {
            Collections.sort(bucket);
            for (float num : bucket) {
                arr[k++] = num;
            }
        }
        return arr;
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

    public static float[] randomfloat(int n) {
        float[] arr = new float[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextFloat();
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

    public static void printarr(float[] arr) {
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
        SortingAnalysis o1 = new SortingAnalysis();

        List<AlgorithmTime> times = new ArrayList<>();

        // Bubble Sort
        int[] arrbub = new int[n];
        arrbub = randomint(n);
        long stbubble = System.nanoTime();
        arrbub = o1.bubble(arrbub);
        long endbubble = System.nanoTime();
        long bubbletime = endbubble - stbubble;
        times.add(new AlgorithmTime("Bubble Sort", bubbletime));
        System.out.println("Bubble Sort: \n");
        printarr(arrbub);

        // Merge Sort
        int[] arrmerge = new int[n];
        arrmerge = randomint(n);
        long stmerge = System.nanoTime();
        arrmerge = o1.mergesort(arrmerge, 0, n - 1);
        long endmerge = System.nanoTime();
        long mergetime = endmerge - stmerge;
        times.add(new AlgorithmTime("Merge Sort", mergetime));
        System.out.println("Merge Sort: \n");
        printarr(arrmerge);

        // Insertion Sort
        int[] arrinsert = new int[n];
        arrinsert = randomint(n);
        long stinsert = System.nanoTime();
        arrinsert = o1.insertsort(arrinsert);
        long endinsert = System.nanoTime();
        long inserttime = endinsert - stinsert;
        times.add(new AlgorithmTime("Insertion Sort", inserttime));
        System.out.println("Insertion Sort: \n");
        printarr(arrinsert);

        // QuickSort
        int[] arrquick = new int[n];
        arrquick = randomint(n);
        long stquick = System.nanoTime();
        arrquick = o1.quicksort(arrquick, 0, n - 1);
        long endquick = System.nanoTime();
        long quicktime = endquick - stquick;
        times.add(new AlgorithmTime("Quick Sort", quicktime));
        System.out.println("Quick Sort: \n");
        printarr(arrquick);

        // Count Sort
        int[] arrcount = new int[n];
        arrcount = randomint(n);
        long stcount = System.nanoTime();
        arrcount = o1.countsort(arrcount);
        long endcount = System.nanoTime();
        long counttime = endcount - stcount;
        times.add(new AlgorithmTime("Count Sort", counttime));
        System.out.println("Count Sort: \n");
        printarr(arrcount);

        // Radix Sort
        int[] arrradix = new int[n];
        arrradix = randomint(n);
        long stradix = System.nanoTime();
        arrradix = o1.radixsort(arrradix);
        long endradix = System.nanoTime();
        long radixtime = endradix - stradix;
        times.add(new AlgorithmTime("Radix Sort", radixtime));
        System.out.println("Radix Sort: \n");
        printarr(arrradix);

        // Bucket Sort
        float[] arrbucket = new float[n];
        arrbucket = randomfloat(n);
        long stbucket = System.nanoTime();
        arrbucket = o1.bucketSort(arrbucket);
        long endbucket = System.nanoTime();
        long buckettime = endbucket - stbucket;
        times.add(new AlgorithmTime("Bucket Sort", buckettime));
        System.out.println("Bucket Sort: \n");
        printarr(arrbucket);

        // Sort the list of AlgorithmTime based on time taken in ascending order
        times.sort(Comparator.comparingLong(a -> a.timeTaken));

        // Display the sorted algorithms by time taken
        System.out.println("\nSorting algorithms sorted by time taken:");
        for (AlgorithmTime algoTime : times) {
            System.out.println(algoTime.algorithmName + " took: " + algoTime.timeTaken + " nanoseconds.");
        }
        sc.close();
    }
}
