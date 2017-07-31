package Plane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Plane {
	private final Seatgroup[] seatGroups;
	private static final String CANNOT_ALLOT_MESSAGE = "Cannot allot Group! ";
	
	public Plane(){
		this.seatGroups = new Seatgroup[]
				{
				  new Seatgroup(new ArrayList<>((Arrays.asList("1A","1B")))),
				  new Seatgroup(new ArrayList<>((Arrays.asList("1C","1D","1E","1F")))),
				  new Seatgroup(new ArrayList<>((Arrays.asList("1G","1H")))),
				  new Seatgroup(new ArrayList<>((Arrays.asList("2A","2B")))),
				  new Seatgroup(new ArrayList<>((Arrays.asList("2C","2D","2E","2F")))),
				  new Seatgroup(new ArrayList<>((Arrays.asList("2G","2H")))),
				  new Seatgroup(new ArrayList<>((Arrays.asList("3A","3B")))),
				  new Seatgroup(new ArrayList<>((Arrays.asList("3C","3D","3E","3F")))),
				  new Seatgroup(new ArrayList<>((Arrays.asList("3G","3H"))))
				}; 
	}

	public void init() {
		while(true){
			int groupSize = getGroupSize();
			if (groupSize == 0) break;
			String allotedSeats = allotSeats(groupSize);
			System.out.println(allotedSeats+ "\n");
		}
	}
	
	private static int getGroupSize(){
		Scanner input = new Scanner(System.in);
		System.out.print("Enter Group size: ");
		while(!input.hasNextInt()){
			System.out.println("Thats not a number, Input a number 0,1,2,3,4\n");
			System.out.print("Enter Group size: ");
			input.next();
		}
		int groupSize = input.nextInt();
		return verifyGroupSize(groupSize);
	}
	
	private static int verifyGroupSize(int size){
		if(size < 0 || size > 4){
			System.out.println("Error input!, Input a number 0,1,2,3,4\n");
			return getGroupSize();
		}
		return size;
	}
	
	private String allotSeats(int groupSize){
		switch (groupSize) {
			case 1: return allotOne();
			case 2: return allotTwo();
			case 3: return allotThree();
			case 4: return allotFour();
			default:return "";
		}
	}
	
	private String allotOne(){
		for (int i = 0; i < seatGroups.length; i++) {
			if(seatGroups[i].availableSeats.size() == 1){
				return seatGroups[i].allotGroup(1);
			}
		}
		for (int i = 0; i < seatGroups.length; i++) {
			if(seatGroups[i].canAccommodate(1)){
				return seatGroups[i].allotGroup(1);
			}
		}
		return Plane.CANNOT_ALLOT_MESSAGE;
	}
	
	private String allotTwo(){
		for (int i = 0; i < seatGroups.length; i++) {
			if(seatGroups[i].capacity == 2 && seatGroups[i].canAccommodate(2)){
				return seatGroups[i].allotGroup(2);
			}
		}
		for (int i = 0; i < seatGroups.length; i++) {
			if(seatGroups[i].canAccommodate(2)){
				return seatGroups[i].allotGroup(2);
			}
		}
		return Plane.CANNOT_ALLOT_MESSAGE;
	}
	
	private String allotThree(){
		for (int i = 0; i < seatGroups.length; i++) {
			if(seatGroups[i].capacity > 3 && seatGroups[i].canAccommodate(3)){
				return seatGroups[i].allotGroup(3);
			}
		}
		return Plane.CANNOT_ALLOT_MESSAGE;
	}
	
	private String allotFour(){
		for (int i = 0; i < seatGroups.length; i++) {
			if(seatGroups[i].capacity == 4 && seatGroups[i].canAccommodate(4)){
				return seatGroups[i].allotGroup(4);
			}
		}
		return allotFourInGroupsOfTwo();
	}
	
	private String allotFourInGroupsOfTwo(){
		String groupOneSeats = allotTwo();
		StringBuilder sb = new StringBuilder(groupOneSeats);
		if(groupOneSeats != Plane.CANNOT_ALLOT_MESSAGE){
			sb.append(allotTwo());
		}
		return sb.toString();
	}

}
