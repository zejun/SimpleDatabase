package com.database;

import java.util.Stack;

/**
 * Transaction class to maintains all the transaction 
 * @author Zejun
 *
 */
public class Transaction {
	
	Stack<Command> stack;
	DataBase db;
	
	public Transaction(){
		stack = new Stack<Command>();
		db = DataBase.getInstance();
	}
	
	//add new command to current transaction
	public void add(Command c){
		Comm comm = c.comm;
		String key;
		switch(comm){
		case SET:
			key = c.key;
			Command roll;
			//check whether there is such value before do something
			if (DataBase.getInstance().get(key) != null){
				roll = new Command(Comm.SET, key, DataBase.getInstance().get(key));
			} else {
				roll = new Command(Comm.UNSET, key);
			}
			stack.push(roll);
			break;
		case GET:
			break;
		case UNSET:
			key = c.key;
			roll = new Command(Comm.SET, key, DataBase.getInstance().get(key));
			stack.push(roll);
			break;
		case NUMEQUALTO:
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
	
	//Do all the command within one transaction
	public void rollback(){
		while (!stack.isEmpty()){
			Command current = stack.pop();
			db.execute(current);
		}
	}
}
