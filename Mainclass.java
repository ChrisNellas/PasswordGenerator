import javax.swing.JOptionPane;

public class Mainclass {

	public static void main(String[] args) {

		String fName = new String();
		String lName = new String();;
		String bthYear = new String();;
		int len = 0;
		char[] res = null;
		boolean ok = false;
		
		String type=JOptionPane.showInputDialog("Choose the password type: (1:Total Random)(2:With personal data)");
		if (type.equals("2")) {
			fName= JOptionPane.showInputDialog("Give your firstname");
			lName= JOptionPane.showInputDialog("Give your lastname");
			bthYear= JOptionPane.showInputDialog("Give your birth year");
			
		}		
		while(!ok) {
			String strLen = JOptionPane.showInputDialog("How many characters do you want?");
			len = Integer.parseInt(strLen);
			if(len>=8) {
				ok=true;
			}else{
				JOptionPane.showMessageDialog(null, "password's length must be >= 8");
			}
		}
		
		passCalc calc = new passCalc();
		if (type.equals("1")) {
			res =calc.calcRandom(len);
		} else {
			res = calc.calcWithData(len, fName, lName, bthYear);
		}
		System.out.print( "The produced password is: ");
		for (int i=0;i<len;i++) {
			System.out.print(res[i]);
		}
		
	}
}
