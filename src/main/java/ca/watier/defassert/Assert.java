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

    private static final String ERROR_SUPERIOR = "%s must be superior THAN %s !";
    private static final String ERROR_CANNOT_BE_NULL = "The object cannot be null !";
    private static final String ERROR_MUST_BE_NULL = "The object must be null !";
    private static final String THE_NUMBER_MUST_BE_EQUALS = "The number must be equals !";
    private static final String THE_NUMBER_MUST_NOT_BE_EQUALS = "The number must not be equals !";
    private static final String THE_OBJECT_IS_NOT_OF_THE_REQUESTED_TYPE = "The object is not of the requested type !";
    private static final String THE_OBJECT_NEED_TO_BE_EMPTY = "The object need to be empty !";
    private static final String THE_OBJECT_CANNOT_BE_EMPTY = "The object cannot be empty !";
    private static final String THE_VALUES_MUST_BE_THE_SAME_TYPE = "The values must be the same type !";

    /**
     * Check if the number is superior than the other number, throw an exception if it is not the case.
     *
     * @param value
     * @param lowestValue
     * @throws IllegalArgumentException
     */
    public static void assertNumberSuperiorTo(Number value, Number lowestValue) throws AssertionError {
        assertNotNull(value, lowestValue);
        assertNumberNotEquals(value, lowestValue);
        assertSuperiorInferiorNumber(value, lowestValue, true, false);
    }

    /**
     * Check if the number is superior than the other number, throw an exception if it is not the case.
     *
     * @param value
     * @param lowestValue
     * @throws IllegalArgumentException
     */
    public static void assertNumberInferiorTo(Number value, Number lowestValue) throws AssertionError {
        assertNotNull(value, lowestValue);
        assertNumberNotEquals(value, lowestValue);
        assertSuperiorInferiorNumber(value, lowestValue, false, false);
    }


    /**
     * Check if the number is superior or equals than the other number, throw an exception if it is not the case.
     *
     * @param value
     * @param lowestValue
     * @throws IllegalArgumentException
     */
    public static void assertNumberSuperiorOrEqualsTo(Number value, Number lowestValue) throws AssertionError {
        assertNotNull(value, lowestValue);
        assertSuperiorInferiorNumber(value, lowestValue, true, true);
    }

    /**
     * Check if the number is superior or equals than the other number, throw an exception if it is not the case.
     *
     * @param value
     * @param lowestValue
     * @throws IllegalArgumentException
     */
    public static void assertNumberInferiorOrEqualsTo(Number value, Number lowestValue) throws AssertionError {
        assertNotNull(value, lowestValue);
        assertSuperiorInferiorNumber(value, lowestValue, false, true);
    }

    /**
     * Check is the numbers are equals
     *
     * @param firstNumber
     * @param secondNumber
     * @throws AssertionError
     */
    public static void assertNumberEquals(Number firstNumber, Number secondNumber) throws AssertionError {
        assertNotNull(firstNumber, secondNumber);
        assertNumbersSameType(firstNumber, secondNumber);

        if (!firstNumber.equals(secondNumber)) {
            throw new AssertionError(THE_NUMBER_MUST_BE_EQUALS);
        }
    }


    /**
     * Check is the numbers are not equals
     *
     * @param firstNumber
     * @param secondNumber
     * @throws AssertionError
     */
    public static void assertNumberNotEquals(Number firstNumber, Number secondNumber) throws AssertionError {
        assertNotNull(firstNumber, secondNumber);
        assertNumbersSameType(firstNumber, secondNumber);

        if (firstNumber.equals(secondNumber)) {
            throw new AssertionError(THE_NUMBER_MUST_NOT_BE_EQUALS);
        }
    }


    /**
     * Check if the object is null, throw an exception if it is not the case.
     *
     * @param obj
     * @throws IllegalArgumentException
     */
    public static void assertNull(Object... obj) throws AssertionError {
        if (obj == null) {
            return;
        }

        for (Object o : obj) {
            if (o != null) {
                throw new AssertionError(ERROR_MUST_BE_NULL);
            }
        }
    }

    /**
     * Check if the object is null, throw an exception if it is the case.
     *
     * @param obj
     * @throws IllegalArgumentException
     */
    public static void assertNotNull(Object... obj) throws AssertionError {

        if (obj == null) {
            throw new AssertionError(ERROR_CANNOT_BE_NULL);
        }

        for (Object o : obj) {
            if (o == null) {
                throw new AssertionError(ERROR_CANNOT_BE_NULL);
            }
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
            throw new AssertionError(THE_OBJECT_IS_NOT_OF_THE_REQUESTED_TYPE);
        }
    }

    /**
     * This assert try to find a "isEmpty" method. (Uses reflection when the type is not known)
     *
     * @param obj
     * @throws AssertionError
     */
    public static void assertEmpty(Object obj) throws AssertionError {
        if (!isEmpty(obj)) {
            throw new AssertionError(THE_OBJECT_NEED_TO_BE_EMPTY);
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
            throw new AssertionError(THE_OBJECT_CANNOT_BE_EMPTY);
        }
    }


    /**
     * A utility function to check if the value is empty or not, null if undefined
     *
     * @param obj
     * @throws IllegalArgumentException
     */
    public static Boolean isEmpty(Object obj) throws AssertionError {
        assertNotNull(obj);

        boolean isString = obj instanceof String;
        boolean isArray = obj.getClass().isArray();
        boolean isCollection = obj instanceof Collection;
        boolean isMap = obj instanceof Map;
        Boolean value = false;

        if (isString && "".equals(obj)) {
            value = true;
        } else if (isArray && Array.getLength(obj) == 0) {
            value = true;
        } else if (isCollection && ((Collection) obj).isEmpty()) {
            value = true;
        } else if (isMap && ((Map) obj).isEmpty()) {
            value = true;
        } else if (!isString && !isArray && !isMap && !isCollection) {
            try {
                Class<?> clazz = obj.getClass();
                Method isEmptyMethod = clazz.getMethod("isEmpty");
                Boolean isEmpty = (Boolean) isEmptyMethod.invoke(obj);

                value = (isEmpty == null || isEmpty);
            } catch (Exception e) {
                value = null;
            }
        }
        return value;
    }

    /**
     * Check if the number are the same type (floating vs integer)
     *
     * @param firstNumber
     * @param secondNumber
     */
    public static void assertNumbersSameType(Number firstNumber, Number secondNumber) {
        assertNotNull(firstNumber, secondNumber);

        boolean isValueFloatingPoint = firstNumber instanceof Double || firstNumber instanceof Float;
        boolean isLowestValueFloatingPoint = secondNumber instanceof Double || secondNumber instanceof Float;

        if (isValueFloatingPoint != isLowestValueFloatingPoint) {
            throw new AssertionError(THE_VALUES_MUST_BE_THE_SAME_TYPE);
        }
    }


    /**
     * Check if the number are not the same type (floating vs integer)
     *
     * @param firstNumber
     * @param secondNumber
     */
    public static void assertNumbersNotSameType(Number firstNumber, Number secondNumber) {
        assertNotNull(firstNumber, secondNumber);

        boolean isValueFloatingPoint = firstNumber instanceof Double || firstNumber instanceof Float;
        boolean isLowestValueFloatingPoint = secondNumber instanceof Double || secondNumber instanceof Float;

        if (isValueFloatingPoint == isLowestValueFloatingPoint) {
            throw new AssertionError(THE_VALUES_MUST_BE_THE_SAME_TYPE);
        }
    }

    /**
     * A utility function that check if the number is inferior / superior than the other number
     *
     * @param number1
     * @param number2
     * @param isSuperior
     * @param canBeEquals
     * @throws AssertionError
     */
    private static void assertSuperiorInferiorNumber(Number number1, Number number2, boolean isSuperior, boolean canBeEquals) throws AssertionError {
        assertNotNull(number1, number2);
        assertNumbersSameType(number1, number2);

        boolean isValueFloatingPoint = number1 instanceof Double || number1 instanceof Float;

        if (canBeEquals) {
            if (isSuperior) {
                if (isValueFloatingPoint) {
                    double doubleValue = number1.doubleValue();
                    double doubleLowestValue = number2.doubleValue();

                    if (doubleValue < doubleLowestValue) {
                        throw new AssertionError(String.format(ERROR_SUPERIOR, number1, number2));
                    }
                } else {
                    long longValue = number1.longValue();
                    long longLowestValue = number2.longValue();

                    if (longValue < longLowestValue) {
                        throw new AssertionError(String.format(ERROR_SUPERIOR, number1, number2));
                    }
                }
            } else {
                if (isValueFloatingPoint) {
                    double doubleValue = number1.doubleValue();
                    double doubleLowestValue = number2.doubleValue();

                    if (doubleValue > doubleLowestValue) {
                        throw new AssertionError(String.format(ERROR_SUPERIOR, number1, number2));
                    }
                } else {
                    long longValue = number1.longValue();
                    long longLowestValue = number2.longValue();

                    if (longValue > longLowestValue) {
                        throw new AssertionError(String.format(ERROR_SUPERIOR, number1, number2));
                    }
                }
            }
        } else {
            if (isSuperior) {
                if (isValueFloatingPoint) {
                    double doubleValue = number1.doubleValue();
                    double doubleLowestValue = number2.doubleValue();

                    if (doubleValue <= doubleLowestValue) {
                        throw new AssertionError(String.format(ERROR_SUPERIOR, number1, number2));
                    }
                } else {
                    long longValue = number1.longValue();
                    long longLowestValue = number2.longValue();

                    if (longValue <= longLowestValue) {
                        throw new AssertionError(String.format(ERROR_SUPERIOR, number1, number2));
                    }
                }
            } else {
                if (isValueFloatingPoint) {
                    double doubleValue = number1.doubleValue();
                    double doubleLowestValue = number2.doubleValue();

                    if (doubleValue >= doubleLowestValue) {
                        throw new AssertionError(String.format(ERROR_SUPERIOR, number1, number2));
                    }
                } else {
                    long longValue = number1.longValue();
                    long longLowestValue = number2.longValue();

                    if (longValue >= longLowestValue) {
                        throw new AssertionError(String.format(ERROR_SUPERIOR, number1, number2));
                    }
                }
            }
        }
    }
}