package com.acmes.acmes.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fishyu on 2018/3/2.
 */

public class Test1 {



    static class Food {

    }


    static class Meat extends Food {

    }


    public void test() {
        List<Food> temp = new ArrayList<>();

        List<? extends Food> list = new ArrayList<>();
        list = temp;

        List<? super Meat> list1 = new ArrayList<>();
//        Meat ss = list1.get(0);

    }


}
