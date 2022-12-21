package com.mygdx.tankgame;


import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;

public class testcases {

    @Test
    public void testSum1() {
        double d = Player.Distance(1,1,4,5);
        assertEquals(d,5, 0.001f);
    }
    @Test
    public void testSum2(){
        double d =Player.Distance(1,2,9,8);
        assertEquals(d,10, 0.001f);
    }

}
