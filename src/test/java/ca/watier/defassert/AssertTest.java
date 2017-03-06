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
    public void assertIsNull() {

        boolean isTestPassed = true;

        try {
            Assert.assertNull(null);
        } catch (IllegalArgumentException iae) {
            isTestPassed = false;
        }

        try {
            Assert.assertNull(new Object());
            isTestPassed = false;
        } catch (AssertionError iae) {
            isTestPassed &= true;
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
    }
}