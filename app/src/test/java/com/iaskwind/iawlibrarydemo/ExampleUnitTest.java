package com.iaskwind.iawlibrarydemo;

import android.util.Log;

import com.blankj.utilcode.utils.IntentUtils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        System.out.println(String.valueOf((int)(Math.random() * 9+1)));
        System.out.println(Math.random());
        System.out.println(Arrays.toString(IntentUtils.class.getDeclaredMethods()));
        System.out.println(Arrays.toString(IntentUtils.class.getDeclaredMethods()[0].getParameterTypes()));
        List<String> list =new ArrayList<String>();
        list.contains("1");
        assertEquals(4, 2 + 2);
    }
}