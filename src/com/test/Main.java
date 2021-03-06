package com.test;

import java.util.Scanner;
import java.util.Stack;

import com.database.Comm;
import com.database.Command;
import com.database.DataBase;
import com.database.Transaction;

/**
 * Main class for the database
 * Handle all the input from keyboard
 * Also handle the transactions generated by user
 * @author Zejun
 *
 */
public class Main {
	public static void main(String[] args){
		System.out.println("WELCOM TO SIMPLE DATABASE CHALLENGE");
		Scanner stdin = new Scanner(System.in);
		DataBase db = DataBase.getInstance();
		
		//use stack to maintains the transaction block
		Stack<Transaction> trans = new Stack<Transaction>();
		
		//while loop to allow user input
		while(true){
			String command = stdin.nextLine();
			if (command.equals(Comm.END.toString())){
				System.out.println("GOOD BYE");
				break;
			} else {
				String[] split = command.split(" ");
				if (split.length > 3){
					System.out.println("INPUT ERROR");
				} else {
					String comm = split[0];
					//switch the command from first position of input, if unvalued, do nothing
					switch (comm){
					case "SET":
						String key = split[1];
						int value = Integer.parseInt(split[2]);
						Command set = new Command(Comm.SET, key, value);
						//every time when SET or UNSET the database, check if there is exist transactions 
						if (!trans.isEmpty()){
							trans.peek().add(set);
						}
						db.execute(set);
						break;
					case "GET":
						Command get = new Command(Comm.GET, split[1]);
						db.execute(get);
						break;
					case "UNSET":
						key = split[1];
						Command unset = new Command(Comm.UNSET, key);
						//every time when SET or UNSET the database, check if there is exist transactions 
						if (!trans.isEmpty()){
							trans.peek().add(unset);
						}
						db.execute(unset);
						break;
					case "NUMEQUALTO":
						Command numequalto = new Command(Comm.NUMEQUALTO, Integer.parseInt(split[1]));
						db.execute(numequalto);
						break;
					case "BEGIN":
						Transaction t = new Transaction();
						trans.push(t);
						break;
					case "COMMIT":
						trans.clear();
						break;
					case "ROLLBACK":
						if (!trans.isEmpty()){
							Transaction roll = trans.pop();
							//call rollback to execute all command in transaction 
							roll.rollback();
						} else {
							System.out.println("NO TRANSACTION");
						}
						break;
					default:
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Test case
	 * Test
	 * SET a 10
	 * BEGIN
	 * NUMEQUALTO 10
	 * 1
	 * BEGIN
	 * UNSET a
	 * NUMEQUALTO 10
	 * 0
	 * ROLLBACK
	 * NUMEQUALTO 10
	 * 1
	 * COMMIT
	 * END
	 * GOOD BYE
	 */
}
