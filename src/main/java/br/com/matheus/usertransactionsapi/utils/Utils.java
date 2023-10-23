package br.com.matheus.usertransactionsapi.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class Utils {

    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullProperties(source));
    }

    public static String[] getNullProperties(Object object) {
        final BeanWrapper source = new BeanWrapperImpl(object);

        PropertyDescriptor[] propertyDescriptors = source.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        for(PropertyDescriptor property : propertyDescriptors) {
            Object value = source.getPropertyValue(property.getName());

            if(value == null) {
                emptyNames.add(property.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}