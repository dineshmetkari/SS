package com.stackroute.service;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONObject;

public class trail {
public static void main(String[] args) {
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("a", 1);
	jsonObject.put("b", 2);
	
    Set keys = jsonObject.keySet();
    Iterator a = keys.iterator();
    while(a.hasNext()) {
    	String key = (String)a.next();
        // loop to get the dynamic key
        int value = (Integer)jsonObject.get(key);
        System.out.println("key : "+key);
        System.out.println(" value :"+value);
    }
    
    String name="Beginner";
    
    if(name.equals("Beginner"))
    {
    	System.out.println("true");
    }
}
}