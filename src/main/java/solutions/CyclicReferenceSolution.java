package solutions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CyclicReferenceSolution {
    public static <T> boolean hasCyclicReference(T object) {
        return hasCyclicReference(object, new ArrayList<>());
    }

    private static <T> boolean hasCyclicReference(T object, List<Object> list) {
        if (object == null) {
            return false;
        }
        if (object instanceof Iterable) {
            for (Object iterable : (Iterable<?>) object) {
                if (hasCyclicReference(iterable, list)) {
                    return true;
                }
            }
            return false;
        }
        if (list.contains(object)) {
            return true;
        }
        list.add(object);
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                boolean isAccessible = field.isAccessible();
                field.setAccessible(true);
                Object complexObject = field.get(object);
                field.setAccessible(isAccessible);
                if (hasCyclicReference(complexObject, list)) {
                    return true;
                }
            } catch (Exception e) {
                throw new RuntimeException("Illegal access exception", e);
            }
        }
        list.remove(object);
        return false;
    }
}
