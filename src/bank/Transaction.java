package bank;

import java.util.ArrayList;
import java.util.Date;

public class Transaction{

	public double startAmount;
	public double endAmount;
	public String type;
	public double diff;
	private Date transDate;
	
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
		this.transDate = new Date();
	}
	
	public String toString(){
		String date = this.month() + "/" + this.date() + "/" + this.year();
		String retVal = this.type + "::<" + date + "><" + this.startAmount + "><" + this.diff + "><" + this.endAmount + ">";
		return retVal;
	}
	
	public int month(){
		return this.transDate.getMonth();
	}
	
	public int date(){
		return this.transDate.getDate();
	}
	
	public int year(){
		return this.transDate.getYear();
	}

	public int compareTo(Transaction other) {
		if(other.year() == this.year()){
			if(other.month() == this.year()){
				if(other.date() == this.date()){
					return 0;
				}
				else if(other.date() < this.date()){
					return 1;
				}
				else{
					return -1;
				}
			}
			else if(other.month() < this.month()){
				return 1;
			}
			else{
				return -1;
			}
		}
		else if(other.year() < this.year()){
			return 1;
		}
		else{
			return -1;
		}
	}
}
