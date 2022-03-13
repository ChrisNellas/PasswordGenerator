import java.util.Random;

public class passCalc {
	int len ;
	String fName;
	String lName;
	String bYear;
	int values[] ; // will keep all characters(uppercase, lowercase, numbers, special characters)
	char spec[]= {'!','@','#','$','%','^','&','*','(',')'}; //keeps the special characters
	
	
	//constructor
	//initialize values-array 
	public passCalc() {
		values = new int[72];
		
		for(int i=0;i<=25;i++) {
			int ob = 65+i;
			values[i]=  ob;
		}
		for (int i=0;i<=25;i++) {
			values[i+26] = (97+i);
		}
		for (int i=0;i<10;i++) {
			values[i+52] = (48+i);
		}
		for(int i=0;i<10;i++) {
			values[i+62] = spec[i];
		}
	}
	//create a random password with the values-array characters
	public char[] calcRandom(int l) {
		len=l;
				
		Random rand = new Random();
		char res[] = new char[len];
		int pos; 

		
		for (int i=0;i<len;i++) {
			pos =rand.nextInt(72);
			res[i] = (char) values[pos];
		}		
		return res;
	}
	
	/* Create a random password with the values-array characters using user's inputs.
	 * From every user's input the program use the first 2 characters (for birth year keeps the last 2).
	 * 
	 * For every input finds a random position that is not the last(in order to insert the 2 characters)
	 * and is empty(value in occupied-array is false) and change the values from occupied-array that are at the selected position and it's next one. 
	 * If the selected position is not the first the program change the selected position-1 value in occupied-array too.
	 * 
	 * When the program finish with the "reservations" for the array positions starts creating the password.
	 */
	public char[] calcWithData(int l, String fN, String lN, String bY) {
		len=l;
		fName=fN;
		lName=lN;
		bYear=bY;
		
		
		int fNPos= 0;
		int lNPos= 0;
		int bYPos= 0;
		
		Random rand = new Random();
		char res[] = new char[len];
		
		boolean occupied[] = new boolean[len]; //shows if an array position is occupied by other character
		
		int pos; 
				
		this.initializeBool(occupied);
		
		// select the position of the first char of firstname 2 chars 
		fNPos = rand.nextInt(len-1);
		occupied[fNPos]=true;
		occupied[fNPos+1]=true;
		if (fNPos!=0) {
			occupied[fNPos-1]=true;
		}
		
		// select the position of the first char of lastname 2 chars 
		while (true) { // check if this place is occupied 
			lNPos = rand.nextInt(len-1);
			if (!occupied[lNPos]) { break;}
		}
		occupied[lNPos]=true;
		occupied[lNPos+1]=true;
		if (lNPos!=0) {// 1 before the selected position does not fit any other data so i make it also true 
			occupied[lNPos-1]=true;//( if this position be selected then at the end will override another data)
		}
		
		// select the position of the first char of birth-year 2 chars 
		while (true) { // check if this place is occupied 
			bYPos = rand.nextInt(len-1);
			if (!occupied[bYPos]) { break;}
		}
		occupied[bYPos]=true;
		occupied[bYPos+1]=true;
		if (bYPos!=0) {// 1 before the selected position does not fit any other data so i make it also true 
			occupied[bYPos-1]=true;//( if this position be selected then at the end will override another data)
		}
		
		for (int i=0;i<len;i++) {
			if (fNPos!=i&&fNPos+1!=i) { //if this position does not belong to firstname's chars
				if(lNPos!=i&&lNPos+1!=i) { //if this position does not belong to lastname's chars
					if(bYPos!=i && bYPos+1!=i) { //if this position does not belong to birth-year's chars
						//insert random character
						pos =rand.nextInt(72);
						res[i] = (char) values[pos];
					} else {// keep the last 2 digits of your birth year
						if(bYPos==i) {
							res[i]=selectChar(bYear.charAt(bYear.length()-2));
						} else {
							res[i]=selectChar(bYear.charAt(bYear.length()-1));
						}
					}
				} else { //inserts the lastname's first two characters
					if(lNPos==i) {
						res[i]=selectChar(lName.charAt(0));
					} else {
						res[i]=selectChar(lName.charAt(1));
					}
				}
			} else { //inserts the firstname's first two characters
				if(fNPos==i) {
					res[i]=selectChar(fName.charAt(0));
				} else {
					res[i]=selectChar(fName.charAt(1));
				}
			}
		}		
		return res;
	}
	
	
	public void initializeBool(boolean[] b) {
		for (int i=0;i<len;i++) {
			b[i]=false;
		}
	}
	
	// Select randomly if the char will be uppercase or not
	public char selectChar(char c) {
		Random rand = new Random();
		
		int res = rand.nextInt(2);
		
		if (res==0 && c> (char) 96 && c< (char) 123) {//if gen give 0 and char is lowercase then make it uppercase 
			c=(char) ((int) c - 32);
		} else if ( res==1 && c>(char) 64 && c< (char) 91) {// if gen give 1 and char is uppercase then make it lowercase
			c=(char) ((int) c + 32);
		}
		return c;
	}
	
}
