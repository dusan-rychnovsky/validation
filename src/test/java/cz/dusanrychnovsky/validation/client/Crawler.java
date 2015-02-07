package cz.dusanrychnovsky.validation.client;

import cz.dusanrychnovsky.validation.Path;

import java.lang.reflect.Field;
import java.util.List;

public class Crawler {

    /**
     * Returns the object corresponding to the given path in the given object-graph, converted to string. Throws an
     * {@link IllegalArgumentException} if no such object exists.
     *
     * @param obj
     * @param path
     * @return
     */
    public String getValue(Object obj, Path path) throws IllegalArgumentException {
        return getValue(obj, path, 0);
    }

    /**
     *
     * @param obj
     * @param path
     * @param index
     * @return
     */
    private String getValue(Object obj, Path path, int index) {

        if (index == path.size()) {
            return obj.toString();
        }

        String fieldName = path.get(index);
        Object field = getField(obj, fieldName);

        return getValue(field, path, index + 1);
    }

    /**
     *
     * @param obj
     * @param fieldName
     * @return
     */
    private Object getField(Object obj, String fieldName)
        throws IllegalArgumentException {

        if (obj instanceof List) {

            Integer index = Integer.parseInt(fieldName);
            return ((List) obj).get(index);
        }

        Field field;
        try {
            field = obj.getClass().getDeclaredField(fieldName);
        }
        catch (NoSuchFieldException ex) {
            throw new IllegalArgumentException(ex);
        }

        field.setAccessible(true);

        try {
            return field.get(obj);
        }
        catch (IllegalAccessException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
