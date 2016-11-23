package com.iaskwind.iawlibrarydemo;

import android.util.Log;

import com.blankj.utilcode.utils.IntentUtils;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        System.out.println("123");
        System.out.println(Arrays.toString(IntentUtils.class.getMethods()));
        System.out.println(Arrays.toString(IntentUtils.class.getDeclaredMethods()));
        System.out.println(Arrays.toString(IntentUtils.class.getDeclaredMethods()[0].getParameterTypes()));

        assertEquals(4, 2 + 2);
    }
}