package com.hj.interstingalbum.utils;

import java.util.Collection;

public class ListUtil {

    public static boolean isEmpty(Collection collection){
        return collection== null || collection.size()<1;
    }

    public static boolean unEmpty(Collection collection){
        return collection!= null && collection.size()>0;
    }
}
