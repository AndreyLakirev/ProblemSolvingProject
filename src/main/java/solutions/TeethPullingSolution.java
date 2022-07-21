package solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Условие задачи:
 * К стоматологу на приём пришёл очень вредный пациент. Ему необходимо вырвать все зубы, отмеченные ‘X’ в массиве teeth.
 * Если у зуба нет соседнего зуба слева или справа, то пациент кусает врача 2 раза.
 * Если вырывать зуб, у которого есть оба соседних зуба, то пациент кусает 4 раза, так как кусать удобнее.
 * Определите минимальное количество укусов у врача, если учесть, что для удобства удалять можно и здоровые зубы (‘I’).
 * Ввод: teeth — строка, состоящая только из ‘I’ и ‘X’, 0<length(teeth)<15
 * Вывод: integer — минимальное количество укусов у врача.
 * Пример:
 * print(getResult('XIIIX')) #4: удаляем один зуб слева (+2), потом один справа (+2)
 * print(getResult("XXIIXIIXX")) #12: удаляем два зуба слева (+2), снова слева (+2), справа (+2), еще справа (+2) и из середины (+4)
 * print(getResult("XXIXXIIXX")) #14: удаляем слева (+2), слева (+2), здоровый зуб слева (+2), слева (+2), слева (+2), справа (+2) и еще справа (+2)
 */
public class TeethPullingSolution {
    public static void main(String[] args) {
        System.out.println(getResult("XIIIX")); //Should print 4
        System.out.println(getResult("XXIIXIIXX")); // Should print 12
        System.out.println(getResult("XXIXXIIXX")); //Should print 14
    }

    public static int getResult(String teeth) {
        if (teeth == null || teeth.isEmpty()) throw new IllegalArgumentException(String.valueOf(teeth));
        char[] chars = teeth.toCharArray();
        Set<Integer> result = new HashSet<>();
        List<Character> characters = new ArrayList<>();
        for (char c : chars) {
            characters.add(c);
        }
        for (int i = 0; i < characters.size(); i++) {
            result.addAll(getResultRecursive(new ArrayList<>(characters), i));
        }
        return result.stream().mapToInt(Integer::intValue).min().getAsInt();
    }

    private static List<Integer> getResultRecursive(List<Character> teeth, int position) {
        List<Integer> result = new ArrayList<>();
        int intResult = 0;
        if (position == 0 || position == teeth.size() - 1) intResult += 2;
        else intResult += 4;
        teeth.remove(position);
        if (teeth.isEmpty() || !teeth.contains('X')) {
            result.add(intResult);
            return result;
        } else {
            for (int i = 0; i < teeth.size(); i++) {
                for (Integer integer : getResultRecursive(new ArrayList<>(teeth), i)) {
                    result.add(intResult + integer);
                }
            }
        }
        return result;
    }
}
