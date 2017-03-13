package com.example.kokin.encryption;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.kokin.encryption", appContext.getPackageName());
    }

    @Test
    public void newAlgorithTest(){
        NewAlgorithm algorithm = new NewAlgorithm();
        assertEquals("bdd", algorithm.enc("abc", new int[]{1,2}));
        assertEquals("bdf", algorithm.enc("abc", new int[]{1,2,3,4,5}));
        assertEquals("bdfhj", algorithm.enc("abcde", new int[]{1,2,3,4,5}));
        assertEquals("bdf+j", algorithm.enc("abc e", new int[]{1,2,3,11,5}));
    }


}
