import java.util.*;

public class searching {
    // Binary search algorithm for an array of integers
    public int binarySearch(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // Binary search algorithm for an array of doubles
    public int binarySearch(double[] arr, double x) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // Binary search algorithm for a List of integers
    public int binarySearch(List<Integer> list, int x) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (list.get(mid) == x) {
                return mid;
            } else if (list.get(mid) < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // Binary search algorithm for a List of doubles
    public int binarySearch(List<Double> list, double x) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (list.get(mid) == x) {
                return mid;
            } else if (list.get(mid) < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // Linear search algorithm for an array of integers
    public int linearSearch(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                return i;
            }
        }
        return -1;
    }

    // Linear search algorithm for an array of doubles
    public int linearSearch(double[] arr, double x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                return i;
            }
        }
        return -1;
    }

    // Linear search algorithm for a List of integers
    public int linearSearch(List<Integer> list, int x) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == x) {
                return i;
            }
        }
        return -1;
    }

    // Linear search algorithm for a List of doubles
    public int linearSearch(List<Double> list, double x) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == x) {
                return i;
            }
        }
        return -1;
    }

}
