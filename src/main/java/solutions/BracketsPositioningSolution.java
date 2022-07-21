package solutions;

import java.util.ArrayList;
import java.util.List;

public class BracketsPositioningSolution {
    public static int getResult(String s) {
        List<Character> operationList = new ArrayList<>();
        List<Integer> numberList = new ArrayList<>();
        String[] strings = s.split("[-*+]");
        for (String string : strings) {
            numberList.add(Integer.parseInt(string));
        }
        for (char c : s.toCharArray()) {
            if (c == '+' || c == '*' || c == '-') operationList.add(c);
        }
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < operationList.size(); i++) {
            resultList.addAll(getRecursiveResult(new ArrayList<>(operationList), new ArrayList<>(numberList), i));
        }
        return resultList.stream().mapToInt(Integer::intValue).max().getAsInt();
    }

    private static List<Integer> getRecursiveResult(List<Character> operationList, List<Integer> numberList, int operationNumber) {
        List<Integer> result = new ArrayList<>();
        if (operationList.get(operationNumber) == '+') {
            int tempResult = numberList.get(operationNumber) + numberList.get(operationNumber + 1);
            numberList.set(operationNumber, tempResult);
            numberList.remove(operationNumber + 1);
            operationList.remove(operationNumber);
        } else if (operationList.get(operationNumber) == '-') {
            int tempResult = numberList.get(operationNumber) - numberList.get(operationNumber + 1);
            numberList.set(operationNumber, tempResult);
            numberList.remove(operationNumber + 1);
            operationList.remove(operationNumber);
        } else if (operationList.get(operationNumber) == '*') {
            int tempResult = numberList.get(operationNumber) * numberList.get(operationNumber + 1);
            numberList.set(operationNumber, tempResult);
            numberList.remove(operationNumber + 1);
            operationList.remove(operationNumber);
        }
        if (operationList.isEmpty()) {
            result.add(numberList.get(0));
        } else {
            for (int i = 0; i < operationList.size(); i++) {
                result.addAll(getRecursiveResult(new ArrayList<>(operationList), new ArrayList<>(numberList), i));
            }
        }
        return result;
    }
}
