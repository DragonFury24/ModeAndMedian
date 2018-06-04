import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static int median(int[] nums) {
        int[] numbers = Arrays.stream(nums)
                              .sorted()
                              .toArray();

        if ((nums.length & 1) != 0)
            return numbers[nums.length / 2];

        return average(numbers[nums.length / 2], numbers[(nums.length / 2) - 1]);
    }

    static int mode(int[] nums) {
        HashMap<Integer, Integer> modeCounts = new HashMap<Integer, Integer>();

        Arrays.stream(nums)
              .forEach(num -> modeCounts.put(num, 0));
        Arrays.stream(nums)
              .forEach(num -> modeCounts.put(num, modeCounts.get(num) + 1));

        return mapIntMax(modeCounts);
    }

    static int average(int num1, int num2) {
        return (num1 + num2) / 2;
    }

    static int[] sort(int[] nums) {
        Integer[] temp = new Integer[nums.length];

        for (int i = 0; i < nums.length; i++)
            temp[i] = nums[i];

        int[] sorted         = new int[temp.length];
        int   largestElement = 0;
        int   largestIndex   = 0;

        for (int i = temp.length - 1; i >= 0; i--) {
            for (int j = 0; j < temp.length; j++)
                if (temp[j] != null && temp[j] > largestElement) {
                    largestElement = temp[j];
                    largestIndex = j;
                }
            temp[largestIndex] = null;
            sorted[i] = largestElement;
            largestElement = 0;
            largestIndex = 0;
        }

        return sorted;
    }

    static int max(int[] nums) {
        return Arrays.stream(nums).max().getAsInt();
    }

    static int mapIntMax(HashMap<Integer, Integer> hashMap) {
        int maxKey   = 0;
        int maxValue = 0;

        for (Integer key : hashMap.keySet()) {
            if (hashMap.get(key) > maxValue) {
                maxKey = key;
                maxValue = hashMap.get(key);
            }
        }

        return maxKey;
    }

    static int receivePositiveInt(Scanner scanner) {
        int inputInt = 0;

        while (true) {
            try {
                inputInt = scanner.nextInt();
                scanner.nextLine();
                if (inputInt < 0) {
                    System.out.println("Please input a positive number.");
                    continue;
                }

                break;
            } catch (InputMismatchException i) {
                System.out.println("Please input a number");
                scanner.reset();
            }
        }

        return inputInt;
    }

    public static void main(String[] args) {
        Scanner keyType = new Scanner(System.in);
        int[]   nums;

        System.out.println("Number of numbers?");
        int numSize = receivePositiveInt(keyType);

        nums = new int[numSize];

        for (int i = 0; i < numSize; i++)
            nums[i] = receivePositiveInt(keyType);

        System.out.println("Median: " + median(nums));
        System.out.println("Mode: " + mode(nums));
    }
}