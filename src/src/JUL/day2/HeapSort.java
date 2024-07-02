package JUL.day2;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {3, 5, 2, 9, 10, 14, 4, 8};
        System.out.println("Unsorted array:");
        printArray(arr);

        heapSort(arr);

        System.out.println("Sorted array:");
        printArray(arr);
    }

    private static void heapSort(int[] arr) {
        int n = arr.length;

        // 힙을 만듬
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 힙에서 하나씩 빼서 정렬
        // 힙을 통해 가장 큰값을 맨앞으로 뺌
        for (int i = n - 1; i > 0; i--) { //인덱스를 줄이면서, 맨뒤에 큰값을 옮김
            // 루트와 마지막 노드를 교환
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // 힙을 다시 만듬
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // 왼쪽 자식이 루트보다 크면
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        // 오른쪽 자식이 루트보다 크면
        if(right < n && arr[right] > arr[largest]){
            largest = right;
        }
        // 루트가 자식보다 작으면
        if(largest != i){
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            // 자식 노드도 힙으로 만듬
            heapify(arr, n, largest);
        }

    }


    // 배열을 출력하는 유틸리티 메소드
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
