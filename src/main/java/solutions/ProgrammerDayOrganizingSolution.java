package solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProgrammerDayOrganizingSolution {
    public static int getResult(List<Integer> time, int n) {
        Set<List<Integer>> timeResults = new HashSet<>();
        for (int i = 1; i < time.size(); i++) {
            timeResults.addAll(getAll(new ArrayList<>(time), n, i));
        }
        int best = Integer.MAX_VALUE;
        for (List<Integer> result : timeResults) {
            int max = result.stream().mapToInt(Integer::intValue).max().getAsInt();
            if (max < best) best = max;
        }
        System.out.println(timeResults);
        return best;
    }

    private static List<List<Integer>> getAll(List<Integer> time, int n, int position) {
        List<List<Integer>> result = new ArrayList<>();
        int temp = time.remove(position);
        time.set(position - 1, temp + time.get(position - 1));
        if (time.size() == n) {
            result.add(time);
            System.out.println(time);
            return result;
        }
        for (int i = 1; i < time.size(); i++) {
            System.out.println(time);
            result.addAll(getAll(new ArrayList<>(time), n, i));
        }
        return result;
    }
}
