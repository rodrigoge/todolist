package br.com.rocketseat.todolist.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;

public class Utils {

    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        var propertiesDescriptor = src.getPropertyDescriptors();
        var emptyNames = new HashSet<>();
        for (var propertyDescriptor : propertiesDescriptor) {
            var srcValue = src.getPropertyValue(propertyDescriptor.getName());
            if (srcValue == null) {
                emptyNames.add(propertyDescriptor.getName());
            }
        }
        var result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
