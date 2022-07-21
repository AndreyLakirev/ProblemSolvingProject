package solutions;

import java.util.List;
import java.util.stream.IntStream;

public class MiniMaxSumFinder {
    public static void miniMaxSum(List<Integer> arr) {
        long minResult = Long.MAX_VALUE;
        long maxResult = Long.MIN_VALUE;
        for (int i = 0; i < arr.size(); i++) {
            int index = i;
            long sum = IntStream.range(0, arr.size()).filter(e -> e!=index).mapToLong(arr::get).sum();
            if (sum > maxResult) maxResult = sum;
            if (sum < minResult) minResult = sum;
        }
        System.out.println(minResult + " " + maxResult);
    }
}
