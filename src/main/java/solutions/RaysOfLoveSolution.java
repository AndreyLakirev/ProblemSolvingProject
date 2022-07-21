package solutions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RaysOfLoveSolution {
    public static int getResult(List<Integer> list) {
        Set<Integer> angleSet = new HashSet<>();
        for (int i = 0; i < list.size(); i+= 2) {
            int angle = getAngle(list.get(i), list.get(i + 1));
            System.out.println(angle);
            angleSet.add(angle);
        }
        System.out.println(angleSet);
        return angleSet.size();
    }

    private static int getAngle(double x1, double y1) {
        return (int) (180.0 / Math.PI * Math.atan2(x1, y1));
    }
}
