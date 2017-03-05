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
    private static final String CANNOT_BE_EMPTY = "The %s cannot be empty !";

    /**
     * Check if the object is null, throw an exception if it is the case.
     * @param obj
     * @throws IllegalArgumentException
     */
    public static void notNull(Object obj) throws IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException("The object cannot be null !");
        }
    }

    /**
     * Check if the object is of the type (class), throw an exception if not the case.
     * @param obj
     * @param type
     * @throws IllegalArgumentException
     */
    public static void mustBeofType(Object obj, Class<?>... type) throws IllegalArgumentException {
        notNull(obj);
        notEmpty(type);

        if (!Arrays.asList(type).contains(obj.getClass())) {
            throw new IllegalArgumentException("The object is not of the requested type !");
        }
    }

    /**
     * This assert try to find a "isEmpty" method. (Uses reflection when the type is not known)
     *
     * @param obj
     * @throws IllegalArgumentException
     */
    public static void notEmpty(Object obj) throws IllegalArgumentException {
        notNull(obj);

        boolean isString = obj instanceof String;
        boolean isArray = obj.getClass().isArray();
        boolean isCollection = obj instanceof Collection;
        boolean isMap = obj instanceof Map;

        if (isString && "".equals(obj)) {
            throw new IllegalArgumentException(String.format(CANNOT_BE_EMPTY, "string"));
        } else if (isArray && Array.getLength(obj) == 0) {
            throw new IllegalArgumentException(String.format(CANNOT_BE_EMPTY, "collection"));
        } else if (isCollection && ((Collection) obj).isEmpty()) {
            throw new IllegalArgumentException(String.format(CANNOT_BE_EMPTY, "collection"));
        } else if (isMap && ((Map) obj).isEmpty()) {
            throw new IllegalArgumentException(String.format(CANNOT_BE_EMPTY, "map"));
        } else if (!isString && !isArray && !isMap && !isCollection) {
            try {
                Class<?> clazz = obj.getClass();
                Method isEmptyMethod = clazz.getMethod("isEmpty");
                Boolean isEmpty = (Boolean) isEmptyMethod.invoke(obj);

                if (isEmpty == null || isEmpty) {
                    throw new IllegalArgumentException(String.format(CANNOT_BE_EMPTY, clazz.getSimpleName()));
                }
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}