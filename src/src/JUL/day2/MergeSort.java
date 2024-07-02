package JUL.day2;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {3, 5, 2, 9, 10, 14, 4, 8};
        mergeSort(arr, 0, arr.length - 1);
        printArray(arr);
    }
    //리스트를 반복적으로 반으로 나누고, 각 부분을 정렬한 후 병합하는 방식
    private static void mergeSort(int[] arr, int left, int right) {
        if(left < right){ //
            int mid = (left + right) / 2;
            // 분할 하여 정렬
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // 정렬된 두 부분 배열을 병합
            merge(arr, left, mid, right);

        }


    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int l = mid - left + 1;
        int r = right - mid;

        int[] leftArr = new int[l];
        int[] rightArr = new int[r];

        // 배열 복사
        for (int i = 0; i < l; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int i = 0; i < r; i++) {
            rightArr[i] = arr[mid + 1 + i];
        }

        int i = 0, j = 0;
        int k = left;

        // 두 배열을 비교하여 작은 값을 넣어줌
        while(i < l && j < r){
            if(leftArr[i] <= rightArr[j]){
                arr[k] = leftArr[i];
                i++;
            }else{
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        } // 이렇게 하면 만약, 한쪽만 작은 값이 있다면 한쪽만 다 넣어지고 나머지는 넣어지지 않을 수 도 있음

        // 남은 값들을 넣어줌
        while(i < l){
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while(j < r){
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    private static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

    }
}
