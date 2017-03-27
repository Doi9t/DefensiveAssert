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

import ca.watier.defassert.utils.EmptyObj;
import org.junit.Test;

import java.util.*;

/**
 * Created by yannick on 2/24/2017.
 */
public class AssertTest {

    @Test
    public void assertNumberSuperiorTo() {
        boolean isTestPassed = true;

        /*
         * Supposed to fail
         */
        try {
            Assert.assertNumberSuperiorTo( 1000000d, 10);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumberSuperiorTo( 10, 10d);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumberSuperiorTo( 5, 10);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumberSuperiorTo( 5d, 10d);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumberSuperiorTo(10, 10);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumberSuperiorTo(10d, 10d);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        /*
         * Supposed to pass
         */
        try {
            Assert.assertNumberSuperiorTo( 11, 10);
        } catch (AssertionError ae) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumberSuperiorTo( 11d, 10d);
        } catch (AssertionError ae) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumberSuperiorTo( 1000000000, 10);
        } catch (AssertionError ae) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumberSuperiorTo( 1000000000d, 10d);
        } catch (AssertionError ae) {
            isTestPassed = false;
        }

        org.junit.Assert.assertTrue(isTestPassed);
    }


    @Test
    public void assertNumberSuperiorOrEqualsTo() {
        boolean isTestPassed = true;

        /*
         * Supposed to fail
         */
        try {
            Assert.assertNumberSuperiorOrEqualsTo( 1000000d, 10);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumberSuperiorOrEqualsTo( 10, 10d);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumberSuperiorOrEqualsTo( 5, 10);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumberSuperiorOrEqualsTo( 5d, 10d);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        /*
         * Supposed to pass
         */
        try {
            Assert.assertNumberSuperiorOrEqualsTo(10, 10);
        } catch (AssertionError ignored) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumberSuperiorOrEqualsTo(10d, 10d);
        } catch (AssertionError ignored) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumberSuperiorOrEqualsTo( 11, 10);
        } catch (AssertionError ae) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumberSuperiorOrEqualsTo( 11d, 10d);
        } catch (AssertionError ae) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumberSuperiorOrEqualsTo( 1000000000, 10);
        } catch (AssertionError ae) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumberSuperiorOrEqualsTo( 1000000000d, 10d);
        } catch (AssertionError ae) {
            isTestPassed = false;
        }

        org.junit.Assert.assertTrue(isTestPassed);
    }

    @Test
    public void assertNumbersSameType() {
        boolean isTestPassed = true;

        try {
            Assert.assertNumbersSameType(10, 10d);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumbersSameType(10d, 10);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumbersSameType(10, 10);
        } catch (AssertionError ignored) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumbersSameType(10d, 10d);
        } catch (AssertionError ignored) {
            isTestPassed = false;
        }

        org.junit.Assert.assertTrue(isTestPassed);
    }

    @Test
    public void assertNumbersNotSameType() {
        boolean isTestPassed = true;

        try {
            Assert.assertNumbersNotSameType(10, 10);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumbersNotSameType(10d, 10d);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumbersNotSameType(10, 10d);
        } catch (AssertionError ignored) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumbersNotSameType(10d, 10);
        } catch (AssertionError ignored) {
            isTestPassed = false;
        }

        org.junit.Assert.assertTrue(isTestPassed);
    }

    @Test
    public void assertNumberInferiorTo() {
        boolean isTestPassed = true;

        /*
         * Supposed to fail
         */
        try {
            Assert.assertNumberInferiorTo(10, 1000000d);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumberInferiorTo(10d, 10);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumberInferiorTo(10, 5);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumberInferiorTo(10d, 5d);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumberInferiorTo(10, 10);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumberInferiorTo(10d, 10d);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }


        /*
         * Supposed to pass
         */
        try {
            Assert.assertNumberInferiorTo(10, 11);
        } catch (AssertionError ae) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumberInferiorTo(10d, 11d);
        } catch (AssertionError ae) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumberInferiorTo(10, 1000000000);
        } catch (AssertionError ae) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumberInferiorTo(10d, 1000000000d);
        } catch (AssertionError ae) {
            isTestPassed = false;
        }

        org.junit.Assert.assertTrue(isTestPassed);
    }


    @Test
    public void assertNumberInferiorOrEqualsTo() {
        boolean isTestPassed = true;

        /*
         * Supposed to fail
         */
        try {
            Assert.assertNumberInferiorOrEqualsTo(10, 1000000d);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumberInferiorOrEqualsTo(10d, 10);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumberInferiorOrEqualsTo(10, 5);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumberInferiorOrEqualsTo(10d, 5d);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        /*
         * Supposed to pass
         */
        try {
            Assert.assertNumberInferiorOrEqualsTo(10, 10);
        } catch (AssertionError ignored) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumberInferiorOrEqualsTo(10d, 10d);
        } catch (AssertionError ignored) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumberInferiorOrEqualsTo(10, 11);
        } catch (AssertionError ae) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumberInferiorOrEqualsTo(10d, 11d);
        } catch (AssertionError ae) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumberInferiorOrEqualsTo(10, 1000000000);
        } catch (AssertionError ae) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumberInferiorOrEqualsTo(10d, 1000000000d);
        } catch (AssertionError ae) {
            isTestPassed = false;
        }

        org.junit.Assert.assertTrue(isTestPassed);
    }

    @Test
    public void assertIsNull() {
        boolean isTestPassed = true;

        try {
            Assert.assertNull(null);
        } catch (AssertionError iae) {
            isTestPassed = false;
        }

        try {
            Assert.assertNull(new Object());
            isTestPassed = false;
        } catch (AssertionError iae) {
            isTestPassed &= true;
        }


        try {
            Assert.assertNull(null, new Object(), null);
            isTestPassed = false;
        } catch (AssertionError iae) {
            isTestPassed &= true;
        }


        try {
            Assert.assertNull(null, null, null);
            isTestPassed &= true;

        } catch (AssertionError iae) {
            isTestPassed = false;
        }

        org.junit.Assert.assertTrue(isTestPassed);
    }


    @Test
    public void assertNumberNotEquals() {
        boolean isTestPassed = true;

        try {
            Assert.assertNumberNotEquals(10, 10);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumberNotEquals(10d, 10d);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }


        try {
            Assert.assertNumberNotEquals(11, 10);
        } catch (AssertionError iae) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumberNotEquals(11d, 10d);
        } catch (AssertionError iae) {
            isTestPassed = false;
        }


        org.junit.Assert.assertTrue(isTestPassed);
    }

    @Test
    public void assertNumberEquals() {
        boolean isTestPassed = true;

        try {
            Assert.assertNumberEquals(11, 10);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }

        try {
            Assert.assertNumberEquals(11d, 10d);
            isTestPassed = false;
        } catch (AssertionError ignored) {
        }


        try {
            Assert.assertNumberEquals(10, 10);
        } catch (AssertionError iae) {
            isTestPassed = false;
        }

        try {
            Assert.assertNumberEquals(10d, 10d);
        } catch (AssertionError iae) {
            isTestPassed = false;
        }


        org.junit.Assert.assertTrue(isTestPassed);
    }

    @Test
    public void assertNotNull() {

        boolean isTestPassed = true;

        try {
            Assert.assertNotNull(10);
        } catch (AssertionError iae) {
            isTestPassed = false;
        }

        try {
            Assert.assertNotNull(null);
            isTestPassed = false;
        } catch (AssertionError iae) {
            isTestPassed &= true;
        }


        try {
            Assert.assertNotNull(new Object(), null, new Object());
            isTestPassed = false;
        } catch (AssertionError iae) {
            isTestPassed &= true;
        }

        org.junit.Assert.assertTrue(isTestPassed);
    }

    @Test
    public void assertOfType() {

        boolean isTestPassed = true;

        try {
            Assert.assertType(5);
        } catch (AssertionError iae) {
            isTestPassed = true;
        }

        try {
            Assert.assertType(10, Integer.class);
        } catch (AssertionError iae) {
            isTestPassed = false;
        }

        try {
            Assert.assertType(20, Double.class);
        } catch (AssertionError iae) {
            isTestPassed &= true;
        }

        try {
            Assert.assertType(new Object(), Object.class);
            isTestPassed &= true;
        } catch (AssertionError iae) {
            isTestPassed &= false;
        }

        org.junit.Assert.assertTrue(isTestPassed);
    }


    @Test
    public void assertNotEmpty() {

        boolean isTestPassed = true;

        String string = "";
        String[] array = {};
        int[] primitiveArray = {};
        ArrayList<Object> list = new ArrayList<Object>();
        TreeSet<Object> set = new TreeSet<Object>();
        Vector<Object> vector = new Vector<Object>();
        HashMap<Object, Object> map = new HashMap<Object, Object>();
        EmptyObj emptyObj = new EmptyObj(true);

        //Must be empty
        for (Object obj : Arrays.asList(string, array, primitiveArray, list, set, vector, map, emptyObj)) {
            try {
                Assert.assertNotEmpty(obj);
                isTestPassed = false;
            } catch (AssertionError iae) {
                isTestPassed &= true;
            }
        }

        string = "\0";
        array = new String[]{string};
        primitiveArray = new int[]{10, 20, 30};
        list.add(string);
        set.add(string);
        vector.add(string);
        map.put(string, string);
        emptyObj = new EmptyObj(false);

        //Must be not empty
        for (Object obj : Arrays.asList(string, array, primitiveArray, list, set, vector, map, emptyObj)) {
            try {
                Assert.assertNotEmpty(obj);
                isTestPassed &= true;
            } catch (AssertionError iae) {
                isTestPassed = false;
            }
        }


        org.junit.Assert.assertTrue(isTestPassed);
    }


    @Test
    public void assertEmpty() {

        boolean isTestPassed = true;

        String string = "";
        String[] array = {};
        int[] primitiveArray = {};
        ArrayList<Object> list = new ArrayList<Object>();
        TreeSet<Object> set = new TreeSet<Object>();
        Vector<Object> vector = new Vector<Object>();
        HashMap<Object, Object> map = new HashMap<Object, Object>();
        EmptyObj emptyObj = new EmptyObj(true);

        //Must be assertEmpty
        for (Object obj : Arrays.asList(string, array, primitiveArray, list, set, vector, map, emptyObj)) {
            try {
                Assert.assertEmpty(obj);
                isTestPassed &= true;
            } catch (AssertionError iae) {
                isTestPassed = false;
            }
        }

        string = "\0";
        array = new String[]{string};
        primitiveArray = new int[]{10, 20, 30};
        list.add(string);
        set.add(string);
        vector.add(string);
        map.put(string, string);
        emptyObj = new EmptyObj(false);

        //Must be not assertEmpty
        for (Object obj : Arrays.asList(string, array, primitiveArray, list, set, vector, map, emptyObj)) {
            try {
                Assert.assertEmpty(obj);
                isTestPassed = false;
            } catch (AssertionError iae) {
                isTestPassed &= true;
            }
        }


        org.junit.Assert.assertTrue(isTestPassed);
    }


    @Test
    public void isEmpty() {
        org.junit.Assert.assertTrue(Assert.isEmpty(""));
        org.junit.Assert.assertFalse(Assert.isEmpty("\0x00"));
        org.junit.Assert.assertTrue(Assert.isEmpty(new EmptyObj(true)));
        org.junit.Assert.assertFalse(Assert.isEmpty(new EmptyObj(false)));
        org.junit.Assert.assertNull(Assert.isEmpty(new Object()));
    }
}