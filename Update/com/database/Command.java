package com.database;


/**
 * Command class
 * Use different constructor to build up different command instance
 * @author Zejun
 *
 */
public class Command {
	
	public Comm comm;
	public String key;
	public int value;
	
	public Command(Comm c){
		this.comm = c;
	}

	public Command(Comm c, String key, int value) {
		this.comm = c;
		this.key = key;
		this.value = value;
	}

	public Command(Comm c, String key) {
		this.comm = c;
		this.key = key;
	}

	public Command(Comm c, int val) {
		this.comm = c;
		this.value = val;
	}
	
}
