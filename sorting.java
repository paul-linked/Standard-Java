import java.util.*;

public class sorting<T> {
    // one dimensional arrays
    // generic methods for Arrays
    T binaryInsertionSort(T array) {
        if (array instanceof int[]) {
            // If the input is an array of integers
            int[] arr = (int[]) array;
            for (int i = 1; i < arr.length; i++) {
                int x = arr[i];
                int j = Math.abs(Arrays.binarySearch(arr, 0, i, x) + 1);
                System.arraycopy(arr, j, arr, j + 1, i - j);
                arr[j] = x;
            }
            return (T) arr;
        } else if (array instanceof List) {
            // If the input is a List of integers
            List<Integer> list = (List<Integer>) array;
            for (int i = 1; i < list.size(); i++) {
                int x = list.get(i);
                int j = Math.abs(Collections.binarySearch(list.subList(0, i), x) + 1);
                list.add(j, x);
                list.remove(i + 1);
            }
            return (T) list;
        } else {
            throw new IllegalArgumentException("Unsupported array type");
        }
    }

    T bubbleSort(T array) {
        if (array instanceof int[]) {
            // If the input is an array of integers
            int[] arr = (int[]) array;
            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        // Swap arr[j] and arr[j+1]
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
            return (T) arr;
        } else if (array instanceof List) {
            // If the input is a List of integers
            List<Integer> list = (List<Integer>) array;
            int n = list.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (list.get(j) > list.get(j + 1)) {
                        // Swap list.get(j) and list.get(j+1)
                        int temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);
                    }
                }
            }
            return (T) list;
        } else {
            throw new IllegalArgumentException("Unsupported array type");
        }
    }

    public T quickSort(T array) {
        if (array instanceof int[]) {
            // If the input is an array of integers
            int[] arr = (int[]) array;
            quickSortHelper(arr, 0, arr.length - 1);
            return (T) arr;
        } else if (array instanceof List) {
            // If the input is a List of integers
            List<Integer> list = (List<Integer>) array;
            quickSortHelper(list, 0, list.size() - 1);
            return (T) list;
        } else {
            throw new IllegalArgumentException("Unsupported array type");
        }
    }

    // Helper function for quickSort on arrays
    private void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            quickSortHelper(arr, low, pivot - 1);
            quickSortHelper(arr, pivot + 1, high);
        }
    }

    // Helper function for quickSort on Lists
    private void quickSortHelper(List<Integer> list, int low, int high) {
        if (low < high) {
            int pivot = partition(list, low, high);
            quickSortHelper(list, low, pivot - 1);
            quickSortHelper(list, pivot + 1, high);
        }
    }

    // Partition function for quickSort on arrays
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Swap arr[i+1] and arr[high] to put the pivot element in its correct position
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Partition function for quickSort on Lists
    private int partition(List<Integer> list, int low, int high) {
        int pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j) < pivot) {
                i++;
                // Swap list.get(i) and list.get(j)
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        // Swap list.get(i+1) and list.get(high) to put the pivot element in its correct
        // position
        int temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }


    //multidimensional arrays


    private T matrix;

    public void MultiDimensional(T matrix) {
        this.matrix = matrix;
    }

    // Sorts the matrix by the specified row
    public T sortByRow(int row) {
        if (matrix instanceof int[][]) {
            // If the matrix is a 2D array of integers
            int[][] arr = (int[][]) matrix;
            Arrays.sort(arr, Comparator.comparingInt(a -> a[row]));
            return (T) arr;
        } else if (matrix instanceof List) {
            // If the matrix is a List of either Lists or arrays of integers
            List<List<Integer>> list = (List<List<Integer>>) matrix;
            list.sort(Comparator.comparing(rowList -> rowList.get(row)));
            if (matrix instanceof List<?>) {
                // If the matrix is a List of arrays of integers
                List<?> arrList = (List<?>) matrix;
                int[][] arr = arrList.stream().map(r -> ((int[]) r)).toArray(int[][]::new);
                return (T) arr;
            } else {
                // If the matrix is a List of Lists of integers
                return (T) list;
            }
        } else {
            // If the matrix is not one of the supported types
            throw new IllegalArgumentException("Unsupported matrix type");
        }
    }

    // Sorts the matrix by the specified column
    public T sortByColumn(int col) {
        if (matrix instanceof int[][]) {
            // If the matrix is a 2D array of integers
            int[][] arr = (int[][]) matrix;
            Arrays.sort(arr, (a, b) -> Integer.compare(a[col], b[col]));
            return (T) arr;
        } else if (matrix instanceof List) {
            // If the matrix is a List of either Lists or arrays of integers
            List<List<Integer>> list = (List<List<Integer>>) matrix;
            list.sort((row1, row2) -> row1.get(col) - row2.get(col));
            if (matrix instanceof List<?>) {
                // If the matrix is a List of arrays of integers
                List<?> arrList = (List<?>) matrix;
                int[][] arr = arrList.stream().map(r -> ((int[]) r)).toArray(int[][]::new);
                return (T) arr;
            } else {
                // If the matrix is a List of Lists of integers
                return (T) list;
            }
        } else {
            // If the matrix is not one of the supported types
            throw new IllegalArgumentException("Unsupported matrix type");
        }
    }
}

