package org.qasimovey.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempStorage {
     private static final Map<String, List<Long>> storage=new HashMap<>();

     private TempStorage() throws IllegalAccessException{
         throw new IllegalAccessException();
     }

     public static synchronized Map<String, List<Long>> getInstance(){
            return storage;
     }
}
