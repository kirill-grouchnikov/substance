/*
 * @(#)Methods.java  1.3.1  2006-09-18
 *
 * Copyright (c) 2005-2006 Werner Randelshofer
 * Staldenmattweg 2, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Werner Randelshofer. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Werner Randelshofer.
 */

package org.pushingpixels.substance.internal.contrib.randelshofer.quaqua.util;

import java.lang.reflect.*;
/**
 * Methods contains convenience methods for method invocations using
 * java.lang.reflect.
 *
 * @author  Werner Randelshofer
 * @version 1.3.1 2006-09-18 Fixed javadoc warnings.
 * <br>1.2 2006-08-20 Additional invokeIfExists method added.
 * <br>1.2 2006-05-07 Added invokeNew method.
 * <br>1.1 2006-02-18 Added more convenience methods.
 * <br>1.0 September 24, 2005 Created.
 */
public class Methods {
    /**
     * Prevent instance creation.
     */
    private Methods() {
    }
    
    /**
     * Invokes the specified accessible parameterless method if it exists.
     *
     * @param obj The object on which to invoke the method.
     * @param methodName The name of the method.
     * @return The return value of the method.
     * @return NoSuchMethodException if the method does not exist or is not
     * accessible.
     */
    public static Object invoke(Object obj, String methodName)
    throws NoSuchMethodException {
        try {
            Method method =  obj.getClass().getMethod(methodName,  new Class[0]);
            Object result = method.invoke(obj, new Object[0]);
            return result;
        } catch (IllegalAccessException e) {
            throw new NoSuchMethodException(methodName+" is not accessible");
        } catch (InvocationTargetException e) {
            // The method is not supposed to throw exceptions
            throw new InternalError(e.getMessage());
        }
    }
    /**
     * Invokes the specified accessible method with a string parameter if it exists.
     *
     * @param obj The object on which to invoke the method.
     * @param methodName The name of the method.
     * @param stringParameter The String parameter
     * @return The return value of the method or METHOD_NOT_FOUND.
     * @return NoSuchMethodException if the method does not exist or is not accessible.
     */
    public static Object invoke(Object obj, String methodName, String stringParameter)
    throws NoSuchMethodException {
        try {
            Method method =  obj.getClass().getMethod(methodName,  new Class[] { String.class });
            Object result = method.invoke(obj, new Object[] { stringParameter });
            return result;
        } catch (IllegalAccessException e) {
            throw new NoSuchMethodException(methodName+" is not accessible");
        } catch (InvocationTargetException e) {
            // The method is not supposed to throw exceptions
            throw new InternalError(e.getMessage());
        }
    }
    
    /**
     * Invokes the specified method if it exists.
     *
     * @param obj The object on which to invoke the method.
     * @param methodName The name of the method.
     * @param types The parameter types.
     * @param values The parameter values.
     * @return The return value of the method.
     * @return NoSuchMethodException if the method does not exist or is not accessible.
     */
    public static Object invoke(Object obj, String methodName, Class[] types, Object[] values)
    throws NoSuchMethodException {
        try {
            Method method =  obj.getClass().getMethod(methodName,  types);
            Object result = method.invoke(obj, values);
            return result;
        } catch (IllegalAccessException e) {
            throw new NoSuchMethodException(methodName+" is not accessible");
        } catch (InvocationTargetException e) {
            // The method is not supposed to throw exceptions
            throw new InternalError(e.getMessage());
        }
    }
    /**
     * Invokes the specified accessible parameterless method if it exists.
     *
     * @param clazz The class on which to invoke the method.
     * @param methodName The name of the method.
     * @return The return value of the method or METHOD_NOT_FOUND.
     * @return NoSuchMethodException if the method does not exist or is not accessible.
     */
    public static Object invokeStatic(Class clazz, String methodName)
    throws NoSuchMethodException {
        try {
            Method method =  clazz.getMethod(methodName,  new Class[0]);
            Object result = method.invoke(null, new Object[0]);
            return result;
        } catch (IllegalAccessException e) {
            throw new NoSuchMethodException(methodName+" is not accessible");
        } catch (InvocationTargetException e) {
            // The method is not supposed to throw exceptions
            throw new InternalError(e.getMessage());
        }
    }
    /**
     * Invokes the specified static parameterless method if it exists.
     *
     * @param clazz The class on which to invoke the method.
     * @param methodName The name of the method.
     * @return The return value of the method.
     * @return NoSuchMethodException if the method does not exist or is not accessible.
     */
    public static Object invokeStatic(String clazz, String methodName)
    throws NoSuchMethodException {
        try {
            return invokeStatic(Class.forName(clazz), methodName);
        } catch (ClassNotFoundException e) {
            throw new NoSuchMethodException("class "+clazz+" not found");
        }
    }
    /**
     * Invokes the specified static method if it exists.
     *
     * @param clazz The class on which to invoke the method.
     * @param methodName The name of the method.
     * @param types The parameter types.
     * @param values The parameter values.
     * @return The return value of the method.
     * @return NoSuchMethodException if the method does not exist or is not accessible.
     */
    public static Object invokeStatic(Class clazz, String methodName,
            Class[] types, Object[] values)
            throws NoSuchMethodException {
        try {
            Method method =  clazz.getMethod(methodName,  types);
            Object result = method.invoke(null, values);
            return result;
        } catch (IllegalAccessException e) {
            throw new NoSuchMethodException(methodName+" is not accessible");
        } catch (InvocationTargetException e) {
            // The method is not supposed to throw exceptions
            throw new InternalError(e.getMessage());
        }
    }
    /**
     * Invokes the specified static method if it exists.
     *
     * @param clazz The class on which to invoke the method.
     * @param methodName The name of the method.
     * @param types The parameter types.
     * @param values The parameter values.
     * @return The return value of the method.
     * @return NoSuchMethodException if the method does not exist or is not accessible.
     */
    public static Object invokeStatic(String clazz, String methodName,
            Class[] types, Object[] values)
            throws NoSuchMethodException {
        try {
            return invokeStatic(Class.forName(clazz), methodName, types, values);
        } catch (ClassNotFoundException e) {
            throw new NoSuchMethodException("class "+clazz+" not found");
        }
    }
    /**
     * Invokes the specified static method if it exists.
     *
     * @param clazz The class on which to invoke the method.
     * @param methodName The name of the method.
     * @param type The parameter types.
     * @param value The parameter values.
     * @return The return value of the method.
     * @return NoSuchMethodException if the method does not exist or is not accessible.
     */
    public static Object invokeStatic(String clazz, String methodName,
            Class type, Object value)
            throws NoSuchMethodException {
        try {
            return invokeStatic(Class.forName(clazz), methodName, new Class[] {type}, new Object[] {value});
        } catch (ClassNotFoundException e) {
            throw new NoSuchMethodException("class "+clazz+" not found");
        }
    }
    /**
     * Invokes the specified static method if it exists.
     *
     * @param clazz The class on which to invoke the method.
     * @param methodName The name of the method.
     * @param types The parameter types.
     * @param values The parameter values.
     * @param defaultValue The default value.
     * @return The return value of the method or the default value if the method
     * does not exist or is not accessible.
     */
    public static Object invokeStatic(String clazz, String methodName,
            Class[] types, Object[] values, Object defaultValue) {
        try {
            return invokeStatic(Class.forName(clazz), methodName, types, values);
        } catch (ClassNotFoundException e) {
            return defaultValue;
        } catch (NoSuchMethodException e) {
            return defaultValue;
        }
    }
    /**
     * Invokes the specified static method if it exists.
     *
     * @param clazz The class on which to invoke the method.
     * @param methodName The name of the method.
     * @param type The parameter type.
     * @param value The parameter value.
     * @return The return value of the method or the default value if the method
     * does not exist or is not accessible.
     */
    public static Object invokeStatic(Class clazz, String methodName,
            Class type, Object value) throws NoSuchMethodException {
        return invokeStatic(clazz, methodName, new Class[] {type}, new Object[] {value});
    }
    
    /**
     * Invokes the specified getter method if it exists.
     *
     * @param obj The object on which to invoke the method.
     * @param methodName The name of the method.
     * @param defaultValue This value is returned, if the method does not exist.
     * @return  The value returned by the getter method or the default value.
     */
    public static int invokeGetter(Object obj, String methodName, int defaultValue) {
        try {
            Method method =  obj.getClass().getMethod(methodName,  new Class[0]);
            Object result = method.invoke(obj, new Object[0]);
            return ((Integer) result).intValue();
        } catch (NoSuchMethodException e) {
            return defaultValue;
        } catch (IllegalAccessException e) {
            return defaultValue;
        } catch (InvocationTargetException e) {
            return defaultValue;
        }
    }
    /**
     * Invokes the specified getter method if it exists.
     *
     * @param obj The object on which to invoke the method.
     * @param methodName The name of the method.
     * @param defaultValue This value is returned, if the method does not exist.
     * @return  The value returned by the getter method or the default value.
     */
    public static long invokeGetter(Object obj, String methodName, long defaultValue) {
        try {
            Method method =  obj.getClass().getMethod(methodName,  new Class[0]);
            Object result = method.invoke(obj, new Object[0]);
            return ((Long) result).longValue();
        } catch (NoSuchMethodException e) {
            return defaultValue;
        } catch (IllegalAccessException e) {
            return defaultValue;
        } catch (InvocationTargetException e) {
            return defaultValue;
        }
    }
    /**
     * Invokes the specified getter method if it exists.
     *
     * @param obj The object on which to invoke the method.
     * @param methodName The name of the method.
     * @param defaultValue This value is returned, if the method does not exist.
     * @return The value returned by the getter method or the default value.
     */
    public static boolean invokeGetter(Object obj, String methodName, boolean defaultValue) {
        try {
            Method method =  obj.getClass().getMethod(methodName,  new Class[0]);
            Object result = method.invoke(obj, new Object[0]);
            return ((Boolean) result).booleanValue();
        } catch (NoSuchMethodException e) {
            return defaultValue;
        } catch (IllegalAccessException e) {
            return defaultValue;
        } catch (InvocationTargetException e) {
            return defaultValue;
        }
    }
    /**
     * Invokes the specified getter method if it exists.
     *
     * @param obj The object on which to invoke the method.
     * @param methodName The name of the method.
     * @param defaultValue This value is returned, if the method does not exist.
     * @return The value returned by the getter method or the default value.
     */
    public static Object invokeGetter(Object obj, String methodName, Object defaultValue) {
        try {
            Method method =  obj.getClass().getMethod(methodName,  new Class[0]);
            Object result = method.invoke(obj, new Object[0]);
            return result;
        } catch (NoSuchMethodException e) {
            return defaultValue;
        } catch (IllegalAccessException e) {
            return defaultValue;
        } catch (InvocationTargetException e) {
            return defaultValue;
        }
    }
    /**
     * Invokes the specified getter method if it exists.
     *
     * @param clazz The class on which to invoke the method.
     * @param methodName The name of the method.
     * @param defaultValue This value is returned, if the method does not exist.
     * @return The value returned by the getter method or the default value.
     */
    public static boolean invokeStaticGetter(Class clazz, String methodName, boolean defaultValue) {
        try {
            Method method =  clazz.getMethod(methodName,  new Class[0]);
            Object result = method.invoke(null, new Object[0]);
            return ((Boolean) result).booleanValue();
        } catch (NoSuchMethodException e) {
            return defaultValue;
        } catch (IllegalAccessException e) {
            return defaultValue;
        } catch (InvocationTargetException e) {
            return defaultValue;
        }
    }
    /**
     * Invokes the specified setter method if it exists.
     *
     * @param obj The object on which to invoke the method.
     * @param methodName The name of the method.
     */
    public static Object invoke(Object obj, String methodName, boolean newValue)
    throws NoSuchMethodException {
        try {
            Method method =  obj.getClass().getMethod(methodName,  new Class[] { Boolean.TYPE} );
            return method.invoke(obj, new Object[] { new Boolean(newValue)});
        } catch (IllegalAccessException e) {
            throw new NoSuchMethodException(methodName+" is not accessible");
        } catch (InvocationTargetException e) {
            // The method is not supposed to throw exceptions
            throw new InternalError(e.getMessage());
        }
    }
    /**
     * Invokes the specified method if it exists.
     *
     * @param obj The object on which to invoke the method.
     * @param methodName The name of the method.
     */
    public static Object invoke(Object obj, String methodName, int newValue)
    throws NoSuchMethodException {
        try {
            Method method =  obj.getClass().getMethod(methodName,  new Class[] { Integer.TYPE} );
            return method.invoke(obj, new Object[] { new Integer(newValue)});
        } catch (IllegalAccessException e) {
            throw new NoSuchMethodException(methodName+" is not accessible");
        } catch (InvocationTargetException e) {
            // The method is not supposed to throw exceptions
            throw new InternalError(e.getMessage());
        }
    }
    /**
     * Invokes the specified setter method if it exists.
     *
     * @param obj The object on which to invoke the method.
     * @param methodName The name of the method.
     */
    public static Object invoke(Object obj, String methodName, float newValue)
    throws NoSuchMethodException {
        try {
            Method method =  obj.getClass().getMethod(methodName,  new Class[] { Float.TYPE} );
            return method.invoke(obj, new Object[] { new Float(newValue)});
        } catch (IllegalAccessException e) {
            throw new NoSuchMethodException(methodName+" is not accessible");
        } catch (InvocationTargetException e) {
            // The method is not supposed to throw exceptions
            throw new InternalError(e.getMessage());
        }
    }
    /**
     * Invokes the specified setter method if it exists.
     *
     * @param obj The object on which to invoke the method.
     * @param methodName The name of the method.
     */
    public static Object invoke(Object obj, String methodName, Class clazz, Object newValue)
    throws NoSuchMethodException {
        try {
            Method method =  obj.getClass().getMethod(methodName,  new Class[] { clazz } );
            return method.invoke(obj, new Object[] { newValue});
        } catch (IllegalAccessException e) {
            throw new NoSuchMethodException(methodName+" is not accessible");
        } catch (InvocationTargetException e) {
            // The method is not supposed to throw exceptions
            throw new InternalError(e.getMessage());
        }
    }
    /**
     * Invokes the specified setter method if it exists.
     *
     * @param obj The object on which to invoke the method.
     * @param methodName The name of the method.
     */
    public static void invokeIfExists(Object obj, String methodName) {
        try {
            invoke(obj, methodName);
        } catch (NoSuchMethodException e) {
            // ignore
        }
    }
    /**
     * Invokes the specified setter method if it exists.
     *
     * @param obj The object on which to invoke the method.
     * @param methodName The name of the method.
     */
    public static void invokeIfExists(Object obj, String methodName, int newValue) {
        try {
            invoke(obj, methodName, newValue);
        } catch (NoSuchMethodException e) {
            // ignore
        }
    }
    /**
     * Invokes the specified setter method if it exists.
     *
     * @param obj The object on which to invoke the method.
     * @param methodName The name of the method.
     */
    public static void invokeIfExists(Object obj, String methodName, float newValue) {
        try {
            invoke(obj, methodName, newValue);
        } catch (NoSuchMethodException e) {
            // ignore
        }
    }
    /**
     * Invokes the specified method if it exists.
     *
     * @param obj The object on which to invoke the method.
     * @param methodName The name of the method.
     */
    public static void invokeIfExists(Object obj, String methodName, boolean newValue) {
        try {
            invoke(obj, methodName, newValue);
        } catch (NoSuchMethodException e) {
            // ignore
        }
    }
    /**
     * Invokes the specified setter method if it exists.
     *
     * @param obj The object on which to invoke the method.
     * @param methodName The name of the method.
     */
    public static void invokeIfExists(Object obj, String methodName, Class parameterClass, Object newValue) {
        try {
            invoke(obj, methodName, parameterClass, newValue);
        } catch (NoSuchMethodException e) {
            // ignore
        }
    }
    /**
     * Invokes the specified setter method if it exists.
     *
     * @param obj The object on which to invoke the method.
     * @param methodName The name of the method.
     */
    public static void invokeIfExistsWithEnum(Object obj, String methodName, String enumClassName, String enumValueName) {
        try {
            Class enumClass = Class.forName(enumClassName);
            Object enumValue = invokeStatic("java.lang.Enum", "valueOf", new Class[] {Class.class, String.class},
                    new Object[] {enumClass, enumValueName}
            );
            invoke(obj, methodName, enumClass, enumValue);
        } catch (ClassNotFoundException e) {
            // ignore
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // ignore
            e.printStackTrace();
        }
    }
    
    /**
     * Invokes the specified constructor if it exists.
     *
     * @param clazz The Class on which to invoke the constructor.
     * @param types The parameter types of the constructor.
     * @param values The parameter values of the constructor.
     */
    public static Object newInstance(Class clazz, Class[] types, Object[] values)
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return clazz.getConstructor(types).newInstance(values);
    };
    
}