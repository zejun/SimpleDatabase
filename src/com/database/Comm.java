package com.database;

public enum Comm{
	SET(1), GET(2), UNSET(3), NUMEQUALTO(4), BEGIN(5), COMMIT(6), ROLLBACK(7),END(8);
	
	int index;
	
	Comm(int index){
		this.index = index;
	}
	
	public String toString(){
		return this.name();
	}
};
