package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Условие задачи:
 * Магазин устроил классную промоакцию. На полке стоят товары, цены на них хранятся в массиве price.
 * Вы можете купить любой товар, но платите при этом только за товар слева + товар справа . Затем эти три товара убираются с полки.
 * Массив price циклический, поэтому всегда есть соседние товары.
 * Ваша задача — купить любые товары с максимально выгодой и так, чтобы на полке не осталось ни одного товара.
 * Определите, какую максимальную выгоду вы получите за счёт этой промоакции.
 * Ввод: price[] — целочисленный массив цен на товары, 0<price[i]≤10000, 3≤length(price)≤15, length(price)%3=0 .
 * Вывод: integer — максимальная выгода по промоакции.
 * Пример:
 * print(getResult([5,20,40,60,80,100 ])) #15: берем товары 60 + 100 = 160, платим 5+20+40+80=145, выгода +15)
 * print(getResult([30,90,110,90,10,20])) #10: берем товары 90+90=180, платим 30+110+10+20=170, выгода +10)
 * print(getResult([200,180,160,140,120,100,80, 60, 40])) #-120
 */
public class GoodsChoosingSolution {
    public static void main(String[] args) {
        System.out.println(getResult(Arrays.asList(5, 20, 40, 60, 80, 100))); //Should print 15
        System.out.println(getResult(Arrays.asList(30, 90, 110, 90, 10, 20))); //Should print 10
        System.out.println(getResult(Arrays.asList(200, 180, 160, 140, 120, 100, 80, 60, 40))); //Should print -120
    }

    public static int getResult(List<Integer> price) {
        if (price == null || price.size() < 3 || price.size() % 3 != 0) throw new IllegalArgumentException(String.valueOf(price));
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < price.size(); i++) {
            result.addAll(getResultRecursive(new ArrayList<>(price), i));
        }
        return result.stream().mapToInt(Integer::intValue).max().getAsInt();
    }

    private static List<Integer> getResultRecursive(List<Integer> prices, int position) {
        List<Integer> result = new ArrayList<>();
        int leftPosition = position - 1;
        int rightPosition = position + 1;
        if (leftPosition < 0) leftPosition = prices.size() - 1;
        if (rightPosition >= prices.size()) rightPosition = 0;
        int intResult = prices.get(position) - prices.get(leftPosition) - prices.get(rightPosition);
        prices.set(leftPosition, null);
        prices.set(position, null);
        prices.set(rightPosition, null);
        prices = prices.stream().filter(Objects::nonNull).collect(Collectors.toList());
        if (!prices.isEmpty()) {
            for (int i = 0; i < prices.size(); i++) {
                for (Integer integer : getResultRecursive(new ArrayList<>(prices), i)) {
                    result.add(intResult + integer);
                }
            }
        } else {
            result.add(intResult);
        }
        return result;
    }
}
