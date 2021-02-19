import java.util.Locale;
import java.util.Scanner;

public class StringCalculator {
		public static void main(String[] args) {
			Scanner console = new Scanner(System.in);
			console.useLocale(Locale.US);
			String line1 = bosluksil(console.nextLine()); // I created this string to get the first line of input without spaces from the user. 
			String line2 = bosluksil(console.nextLine()); // I created this string to get the second line of input without spaces from the user. 
			String line3 = bosluksil(console.nextLine()); // I created this string to get the third line of input without spaces from the user. 
			String islem = console.nextLine(); // I created this string to get the forth line of input from the user. 
			islem = bosluksil(islem).substring(0, bosluksil(islem).length()-1); // I do this to delete the spaces in the forth line and delete the last character (";"). 
			String name1, name2, name3 = ""; //I created this strings to assign them the names of variables (x, y, z or something like them) which I took from the user.
			if(line1.charAt(0)=='i'){
			name1 = line1.substring(3,line1.indexOf("="));
				}
			else {
			name1 = line1.substring(6,line1.indexOf("="));
				}
			
			String deger1 = line1.substring(line1.indexOf("=")+1, line1.length()-1); // I create deger1 string to get the values of variables which I took from the user as a string form.
			if(line1.charAt(0)=='d'&&!deger1.contains(".")) {
				deger1 = deger1+".0";
				}
			if(line2.charAt(0)=='i'){
			name2 = line2.substring(3,line2.indexOf("="));
				}
			else {
			name2 = line2.substring(6,line2.indexOf("="));
				}
			String deger2 = line2.substring(line2.indexOf("=")+1, line2.length()-1); // I create deger2 string to get the values of variables which I took from the user as a string form.
			if(line2.charAt(0)=='d'&&!deger2.contains(".")) {
				deger2 = deger2+".0";
				}
			if(line3.charAt(0)=='i'){
				name3 = line3.substring(3,line3.indexOf("="));
				}
				else {
				name3 = line3.substring(6,line3.indexOf("="));
				}
			String deger3 = line3.substring(line3.indexOf("=")+1, line3.length()-1);  // I create deger3 string to get the values of variables which I took from the user as a string form.
			if(line3.charAt(0)=='d'&&!deger3.contains(".")) {
				deger3 = deger3+".0";
			}
			if(islem.contains("(")) {
			islem = parantezsil(islem, name1, deger1, name2, deger2, name3, deger3);
			}
			
			System.out.println(carpbol(islem, name1, deger1, name2, deger2, name3, deger3));
		}
		
		// bosluksil() method takes lines and delete the spaces between characters by using replace method.
		public static String bosluksil(String line) {
			int n = 0; //I create this integer to run through the line from zeroth index to (line.length-1)th index.  
			while(n<line.length()) {
				line=line.replace(" ","");
				n++;
			}
			return line;
		}
		
		// parantezsil() method takes the 'islem' line, name1, name2, and so on parameters from the main method. This method deletes the parentheses one by one by finding the results of the operations between "(" and ")".
		public static String parantezsil(String line, String name1, String deger1, String name2, String deger2, String name3, String deger3) {
			String alinacak, devam, ikinci = ""; // I create these empty strings to assign them the characters before and after the parentheses. At the end of the each cycle, I put together them in the 'line' variable and when the loop ends I returned the line.
			while(line.contains("(")) {
				ikinci = line.substring(line.lastIndexOf("("));
				devam = ikinci.substring(ikinci.indexOf(")")+1);
				alinacak = ikinci.substring(1, ikinci.indexOf(")"));
				line = line.substring(0, line.lastIndexOf("(")) + carpbol(alinacak, name1, deger1, name2, deger2, name3, deger3) + devam;
			}
			return line;
		}	
			
		// carpbol() method with its parameters check which arithmetic operation character line contains and then I recall the islem() method with the specific value of 'deger' integer to find the result. 
		public static String carpbol(String line, String name1, String deger1, String name2, String deger2, String name3, String deger3) {
			int a, b, c, d = 0; // I create these integers to assign them the indexes of mathematical operation characters ("*", "/", "+", "-").
			int deger = 0; // I create this integer to assign it a specific value according to type of operation. Then, I used it in the islem() method as a parameter.
			while(line.contains("*")||line.contains("/")) {
				if(line.contains("*")) {
				a = line.indexOf("*");
				if(line.contains("/")) {
					b = line.indexOf("/");
					if(b<a) {
						deger = 1;
						line = islem(line, b, name1, deger1, name2, deger2, name3, deger3, deger);
					}
					else {
						deger = 0;
						line = islem(line, a, name1, deger1, name2, deger2, name3, deger3, deger);
					}
				}
				else {
					deger = 0;
					line = islem(line, a, name1, deger1, name2, deger2, name3, deger3, deger);
				}
				}
				if(line.contains("/")&&!line.contains("*")) {
					b = line.indexOf("/");
					deger = 1;
					line = islem(line, b, name1, deger1, name2, deger2, name3, deger3, deger);
				}
			}
			
			while(line.contains("+")||line.contains("-")) {
				if(line.contains("+")) {
				c = line.indexOf("+");
					if(line.contains("-")) {
						d = line.indexOf("-");
							if(d<c) {
								deger = 3;
								line = islem(line, d, name1, deger1, name2, deger2, name3, deger3, deger);
							}
							else {
								deger = 2;
								line = islem(line, c, name1, deger1, name2, deger2, name3, deger3, deger);
							}
					}
					else {
						deger = 2;
						line = islem(line, c, name1, deger1, name2, deger2, name3, deger3, deger);
					}
				}
				if(line.contains("-")&&!line.contains("+")) {
					d = line.indexOf("-");
					deger = 3;
					line = islem(line, d, name1, deger1, name2, deger2, name3, deger3, deger);
				}
			}
			return line;
		}
			
		// I create islem() method to find the result of the arithmetic operation. Actually this method works like a calculator. It also put the variables which I took from the user with their values in the arithmetic operation if they exist. 
		public static String islem(String carp, int a, String name1, String deger1, String name2, String deger2, String name3, String deger3, int deger) {
			String first = ""; // I create this string to get the characters which I will use in the arithmetic operation as an integer or double between two arithmetic operation characters. (characters before the arithmetic operation character which I will perform).
			String last = ""; // I create this string also to get the characters which I will use in the arithmetic operation as an integer or double between two arithmetic operation characters. (characters after the arithmetic operation character which I will perform).
			int ilk = 1; // I create this integer, so I could add the characters from arithmetic operation character to another arithmetic operation character one by one. I checked also whether the character in the (a-ilk)th index is an arithmetic operation character or not.
			int iki = 1; // The function of this variable is the same as the one above. I create this integer, so I could add the characters from arithmetic operation character to another arithmetic operation character one by one. I checked also whether the character in the (a+iki)th index is an arithmetic operation character or not.
			String bas = ""; // I create this string to get the all characters before the operator and numbers involved in the arithmetic operation.
			String son = ""; // I create this string to get the all characters after the operator and numbers involved in the arithmetic operation.
			while(a-ilk>=0&&carp.charAt(a-ilk)!='+'&&carp.charAt(a-ilk)!='-'&&carp.charAt(a-ilk)!='*'&&carp.charAt(a-ilk)!='/'&&carp.charAt(a-ilk)!='('&&carp.charAt(a-ilk)!=')') {
				first = carp.charAt(a-ilk) + first;
				ilk++;
			}
			if(a-ilk>=0) {
				bas = carp.substring(0, a-ilk+1);
			}
			while(a+iki<carp.length()&&carp.charAt(a+iki)!='+'&&carp.charAt(a+iki)!='-'&&carp.charAt(a+iki)!='*'&&carp.charAt(a+iki)!='/'&&carp.charAt(a+iki)!='('&&carp.charAt(a+iki)!=')') {
				last = last + carp.charAt(a+iki);
				iki++;				
			}
			if(a+iki<=carp.length()-1) {
				son = carp.substring(a+iki);
			}
			// In this section, I checked whether the 'first' string has one of the input variables or not. If it has, I assign the string form of value of it to a 'first' string. 
			if(first.equals(name1)){
				first = deger1;
			}
			if(first.equals(name2)){
				first = deger2;
			}
			if(first.equals(name3)){
				first = deger3;
			}
			// In this section, I checked whether the 'last' string has one of the input variables or not. If it has, I assign the string form of value of it to a 'last' string. 
			if(last.equals(name1)){
				last = deger1;
			}
			if(last.equals(name2)){
				last = deger2;
			}
			if(last.equals(name3)){
			last = deger3;
			}
			if(!first.contains(".")&&!last.contains(".")) {
				int sonuc; // I create this integer to get the result of the arithmetic operation if both 'first' and 'last' string don't contain ".".
				if(deger==0) {
					sonuc = Integer.parseInt(first) * Integer.parseInt(last);
				}
				else if(deger==1) {
				    sonuc = Integer.parseInt(first) / Integer.parseInt(last);
				}
				else if(deger==2) {
					sonuc = Integer.parseInt(first) + Integer.parseInt(last);
				}
				else {
					sonuc = Integer.parseInt(first) - Integer.parseInt(last);
				}
				return bas + Integer.toString(sonuc) + son;	
			}
			else {
				double sonuc; // I create this double to get the result of the arithmetic operation if one of the 'first' and 'last' strings has ".".
				if(deger==0) {
					sonuc = Double.parseDouble(first) * Double.parseDouble(last);
				}
				else if(deger==1) {
					sonuc = Double.parseDouble(first) / Double.parseDouble(last);
				}
				else if(deger==2) {
					sonuc = Double.parseDouble(first) + Double.parseDouble(last);
				}
				else {
					sonuc = Double.parseDouble(first) - Double.parseDouble(last);
				}
				return bas + Double.toString(sonuc) + son;
			}
		}
}