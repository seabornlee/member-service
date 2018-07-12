package com.seabornlee.springboot.memberservice.util;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {
    public static List<List<Object>> split(List<Object> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }

        ArrayList<List<Object>> result = new ArrayList<>();
        ArrayList<Object> subList = new ArrayList<>();
        for (Object o : list) {
            subList.add(o);
            if (isSubListFull(subList, len)) {
                result.add(subList);
                subList = new ArrayList<>();
            }
        }

        if (!subList.isEmpty()) {
            result.add(subList);
        }
        return result;
    }

    private static boolean isSubListFull(ArrayList<Object> subList, int len) {
        return subList.size() == len;
    }
}
