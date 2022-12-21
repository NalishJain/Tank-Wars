package com.mygdx.tankgame;


import org.junit.Test;

import static org.junit.Assert.assertSame;

public class testcases {

    @Test
    public void testSum1() {
        double d = Player.Distance(1,1,4,5);
        assertSame(d,5);
    }
    @Test
    public void testSum2(){
        double d =Player.Distance(1,2,9,8);
        assertSame(d,10);
    }

}
