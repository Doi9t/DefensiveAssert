/*
 *    Copyright 2014 - 2017 Yannick Watier
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package ca.watier.defassert;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * Created by yannick on 2/24/2017.
 */
public class Assert {

    /**
     * Check if the object is null, throw an exception if it is not the case.
     *
     * @param obj
     * @throws IllegalArgumentException
     */
    public static void assertNull(Object obj) throws AssertionError {
        if (obj != null) {
            throw new AssertionError("The object must be null !");
        }
    }

    /**
     * Check if the object is null, throw an exception if it is the case.
     *
     * @param obj
     * @throws IllegalArgumentException
     */
    public static void assertNotNull(Object obj) throws AssertionError {
        if (obj == null) {
            throw new AssertionError("The object cannot be null !");
        }
    }

    /**
     * Check if the object is of the type (class), throw an exception if not the case.
     *
     * @param obj
     * @param type
     * @throws IllegalArgumentException
     */
    public static void assertType(Object obj, Class<?>... type) throws AssertionError {
        assertNotNull(obj);
        assertNotEmpty(type);

        if (!Arrays.asList(type).contains(obj.getClass())) {
            throw new AssertionError("The object is not of the requested type !");
        }
    }


    public static void assertEmpty(Object obj) throws AssertionError {
        if (!isEmpty(obj)) {
            throw new AssertionError("The object need to be empty !");
        }
    }

    /**
     * This assert try to find a "isEmpty" method. (Uses reflection when the type is not known)
     *
     * @param obj
     * @throws IllegalArgumentException
     */
    public static void assertNotEmpty(Object obj) throws AssertionError {
        if (isEmpty(obj)) {
            throw new AssertionError("The object cannot be empty !");
        }
    }


    /**
     * A utility function to check if the value is empty or not
     *
     * @param obj
     * @throws IllegalArgumentException
     */
    public static boolean isEmpty(Object obj) throws AssertionError {
        assertNotNull(obj);

        boolean isString = obj instanceof String;
        boolean isArray = obj.getClass().isArray();
        boolean isCollection = obj instanceof Collection;
        boolean isMap = obj instanceof Map;

        if (isString && "".equals(obj)) {
            return true;
        } else if (isArray && Array.getLength(obj) == 0) {
            return true;
        } else if (isCollection && ((Collection) obj).isEmpty()) {
            return true;
        } else if (isMap && ((Map) obj).isEmpty()) {
            return true;
        } else if (!isString && !isArray && !isMap && !isCollection) {
            try {
                Class<?> clazz = obj.getClass();
                Method isEmptyMethod = clazz.getMethod("isEmpty");
                Boolean isEmpty = (Boolean) isEmptyMethod.invoke(obj);

                if (isEmpty == null || isEmpty) {
                    return true;
                }
            } catch (Exception e) {
                throw new AssertionError(e);
            }
        }
        return false;
    }
}