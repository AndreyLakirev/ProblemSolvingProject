package solutions;

public class KangarooProblemSolution {
    public static boolean doTheyCollapse(int x1, int v1, int x2, int v2) {
        int approachSpeed = v1 - v2;
        int distance = x1 - x2;
        return distance < distance + approachSpeed && distance % approachSpeed == 0;
    }
}
