package bank;

import java.util.ArrayList;

import main.ModdedDate;

public class Transaction{

	public double startAmount;
	public double endAmount;
	public String type;
	public double diff;
	private ModdedDate transDate;
	
	public Transaction(double start, double end, String typee){
		this.startAmount = start;
		this.endAmount = end;
		this.type = typee;
		if(this.type.equals("withdrawal")){
			this.diff = this.endAmount+this.startAmount;
		}
		else if(this.type.equals("deposit")){
			this.diff = this.endAmount-this.startAmount;
		}
		this.transDate = new ModdedDate();
	}
	
	public Transaction(ModdedDate date, double start, double diff, double end, String typee){
		this.startAmount = start;
		this.endAmount = end;
		this.diff = diff;
		this.type = typee;
		this.transDate = date;
	}
	
	//type,date,start,difference,end
	public String toString(){
		String retVal = this.type + "::<" + transDate + "><" + this.startAmount + "><" + this.diff + "><" + this.endAmount + ">";
		return retVal;
	}

	public int compareTo(Transaction other) {
		return transDate.compareTo(other.transDate);
	}
}
