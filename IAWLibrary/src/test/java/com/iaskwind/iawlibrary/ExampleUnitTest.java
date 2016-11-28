package com.iaskwind.iawlibrary;

import com.iaskwind.iawlibrary.tools.IAW_StringTool;

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
        assertEquals(4, 2 + 2);
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("a");
        String str = IAW_StringTool.listToString(list,",");
        System.out.println(str);

      List<String>  listStr = IAW_StringTool.stringToList(str,",");
        System.out.println(Arrays.toString(listStr.toArray()));
        String str1 = IAW_StringTool.listToString(listStr,",");
        System.out.println(str1);
    }
}