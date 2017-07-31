package Plane;

import java.util.ArrayList;


public class Seatgroup {
	public ArrayList<String> availableSeats;
	public int capacity;

	public Seatgroup(ArrayList<String> avs){
		this.availableSeats = avs;
		this.capacity = this.availableSeats.size();
	}
	
	public boolean canAccommodate(int groupSize){
		if(this.availableSeats.size() < groupSize){
			return false;
		}
		return true;
	}

	public String allotGroup(int groupSize) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < groupSize; i++) {
			sb.append(this.availableSeats.remove(0)+ " ");
		}
		return sb.toString();
	}
}
