package org.PipesAndFilters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.PipesAndFilters.exceptions.FilterCreationAbortedException;

public class FilterManager {
    private Class<Filter> getFilterTypeClass(String className) throws ClassNotFoundException {
        Class<Filter> filterExtendedClass;
        filterExtendedClass = (Class<Filter>) Class.forName(className);
        return filterExtendedClass;
    }

    private Constructor<Filter> getFilterTypeConstructor(Class<Filter> objClass)
            throws NoSuchMethodException, SecurityException {
        Constructor<Filter> constructor;
        constructor = objClass.getConstructor();
        return constructor;
    }

    private Filter getFilterTypeInstance(Constructor<Filter> constructor)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object object;
        object = constructor.newInstance();
        return (Filter) object;
    }

    public Filter getFilterInstance(String className)
            throws FilterCreationAbortedException, ClassNotFoundException, NoSuchMethodException {
        Filter filter;

        try {
            Class<Filter> objClass = getFilterTypeClass(className);
            Constructor<Filter> objConstructor = getFilterTypeConstructor(objClass);
            filter = getFilterTypeInstance(objConstructor);
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (NoSuchMethodException e) {
            throw e;
        } catch (Exception e) {
            throw new FilterCreationAbortedException(
                    "The definitions of your classes may contain errors when calling a constructor or accesing a method:"
                            + e.getMessage());
        }

        return filter;
    }
}
