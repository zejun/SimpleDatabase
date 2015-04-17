package com.database;

import java.util.HashMap;
import java.util.Stack;

/**
 * DataBase class 
 * Use singleton pattern to make sure there is only one instance in program
 * Use two hash map to maintains the key value pair and value numbers
 * The GET and SET time complexity is O(1)
 * Use Command class to normalize the input and output, and provide api from outer world
 * @author Zejun
 *
 */
public class DataBase {
	
	private static DataBase instance;
	
	private HashMap<String, Integer> map1;
	private HashMap<Integer, Integer> map2;
	
	//Constructor
	private DataBase(){
		this.map1 = new HashMap<String, Integer>();
		this.map2 = new HashMap<Integer, Integer>();
	}
	
	//Singleton pattern, make sure there is only one instance in the program
	public static synchronized DataBase getInstance(){
		if (instance == null){
			instance = new DataBase();
		}
		return instance;
	}
	
	//Check whether key is exist 
	public Integer get(String key){
		if (map1.containsKey(key)){
			return map1.get(key);
		} else {
			return null;
		}
	}
	
	//execute command to the database. Only valid command could be input
	public void execute(Command c){
		Comm comm = c.comm;
		String key;
		int value;
		switch(comm){
		case SET:
			key = c.key;
			value = c.value;
			if (map1.containsKey(key)){
				int preval = map1.get(key);
				map2.put(preval, map2.get(preval) - 1);
			}
			map1.put(key, value);
			if (map2.containsKey(value)){
				map2.put(value, map2.get(value) + 1);
			} else {
				map2.put(value, 1);
			}
			break;
		case GET:
			key = c.key;
			if (map1.containsKey(key)){
				System.out.println(map1.get(key));
			} else {
				System.out.println("NULL");
			}
			break;
		case UNSET:
			key = c.key;
			if (map1.containsKey(key)){
				int temVal = map1.get(key);
				map2.put(temVal, map2.get(temVal) - 1);
				map1.remove(key);
			}
			break;
		case NUMEQUALTO:
			value = c.value;
			if (map2.containsKey(value)){
				System.out.println(map2.get(value));
			} else {
				System.out.println("0");
			}
			break;
		case BEGIN:
			break;
		case END:
			break;
		case COMMIT:
			break;
		default:
			break;
		}
	}
	
}
