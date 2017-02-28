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

package com.github.doi9t.defensiveassert;

import com.github.defensiveassert.Assert;
import com.github.doi9t.defensiveassert.utils.EmptyObj;
import org.junit.Test;

import java.util.*;

/**
 * Created by yannick on 2/24/2017.
 */
public class AssertTest {

    @Test
    public void notNull() {

        try {
            Assert.notNull(10);
        } catch (IllegalArgumentException iae) {
            return;
        }

        try {
            Assert.notNull(null);
        } catch (IllegalArgumentException iae) {
            return;
        }

        org.junit.Assert.fail();
    }

    @Test
    public void mustBeofType() {

        boolean isTestPassed = true;

        try {
            Assert.mustBeofType(10, Integer.class);
        } catch (IllegalArgumentException iae) {
            isTestPassed = false;
        }

        try {
            Assert.mustBeofType(20, Double.class);
        } catch (IllegalArgumentException iae) {
            isTestPassed &= true;
        }

        try {
            Assert.mustBeofType(new Object(), Object.class);
            isTestPassed &= true;
        } catch (IllegalArgumentException iae) {
            isTestPassed &= false;
        }

        org.junit.Assert.assertTrue(isTestPassed);
    }


    @Test
    public void notEmpty() {

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
                Assert.notEmpty(obj);
                isTestPassed = false;
            } catch (IllegalArgumentException iae) {
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
                Assert.notEmpty(obj);
                isTestPassed &= true;
            } catch (IllegalArgumentException iae) {
                isTestPassed = false;
            }
        }


        org.junit.Assert.assertTrue(isTestPassed);
    }

}