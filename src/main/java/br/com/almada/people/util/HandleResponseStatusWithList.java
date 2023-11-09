package br.com.almada.people.util;

import java.util.List;

public class HandleResponseStatusWithList {

    private HandleResponseStatusWithList() {

    }

    public static String message (List<?> list, String listName) {

        if(list.isEmpty()) {
            return "No data found on database";
        }
        return listName + " recovered successfully";
    }

}
