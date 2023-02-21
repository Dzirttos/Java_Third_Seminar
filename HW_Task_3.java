import java.util.Random;
import java.util.Scanner;

// 3* Реализовать алгоритм сортировки слиянием

public class HW_Task_3 {
    public static void main(String[] args) {
        System.out.print("\033[H\033[J");

        Scanner userInput = new Scanner(System.in);
        System.out.printf("Давайте создадим произвольный массив. Какой длины массив хотите?: ");
        int n = Integer.parseInt(userInput.nextLine());
        userInput.close();

        int[] array = createRandomArray(n);
        System.out.println("\u001B[36mПолучился такой массив: " + printArray(array));
        array = mergeSort(array);
        System.out.println("\u001B[32mОтсортированный массивы выглядит так: " + printArray(array));
    }

    public static int[] mergeSort(int[] array) {
        int[] tmp;
        int[] inputArray = array;
        int[] outputArray = new int[array.length];

        int size = 1;
        while (size < array.length) {
            for (int i = 0; i < array.length; i += 2 * size) {
                merge(inputArray, i, inputArray, i + size, outputArray, i, size);
            }

            tmp = inputArray;
            inputArray = outputArray;
            outputArray = tmp;

            size = size * 2;

        }
        return inputArray;
    }

    private static void merge(int[] array1, int array1Start, int[] array2, int array2Start, int[] finalArray,
            int finalArrayStart, int size) {
        int index1 = array1Start;
        int index2 = array2Start;

        int array1End = Math.min(array1Start + size, array1.length);
        int array2End = Math.min(array2Start + size, array2.length);

        if (array1Start + size > array1.length) {
            for (int i = array1Start; i < array1End; i++) {
                finalArray[i] = array1[i];
            }
            return;
        }

        int iterationCount = array1End - array1Start + array2End - array2Start;

        for (int i = finalArrayStart; i < finalArrayStart + iterationCount; i++) {
            if (index1 < array1End && (index2 >= array2End || array1[index1] < array2[index2])) {
                finalArray[i] = array1[index1];
                index1++;
            } else {
                finalArray[i] = array2[index2];
                index2++;
            }
        }
    }

    private static String printArray(int[] array) {
        StringBuilder newArray = new StringBuilder();
        newArray.append("[");
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                newArray.append(", ");
            }
            newArray.append(array[i]);
        }
        newArray.append("]");
        return newArray.toString();
    }

    private static int[] createRandomArray(int number) {
        Random random = new Random();
        int[] array = new int[number];
        for (int i = 0; i < number; i++) {
            array[i] = random.nextInt(0, 100);
        }
        return array;
    }

}