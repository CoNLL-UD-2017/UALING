import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ReduceLengths {
	public static void main(String[] args) {
		File file = new File(args[0]);
		parser(file);
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public static void parser(File file) {
		ArrayList<String> rtn = new ArrayList<String>();
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();
		int longest = 0;
		int amount = 0;
		int count = 0;
		boolean firstPass = true;
		if (file.canRead()) {
			try {
				String Text = new String(Files.readAllBytes(file.toPath()));
				Scanner reader = new Scanner(Text);
				String LastLen = "";
				while (reader.hasNextLine()) {
					String line = reader.nextLine();
					if (!line.contains(".conllu")) {
						Scanner temp = new Scanner(line);
						temp.useDelimiter("\t");
						String thing = temp.next();
						if (!thing.contains(".")) {
							int num = Integer.parseInt(thing);
							int amt = Integer.parseInt(temp.next());
							if (amount <= amt && num <= 20 && num >= 10) {
								amount = amt;
								longest = num;
							}
						}
					}
					else {
						if (firstPass) {
							firstPass = false;
							list.add(line);
						} else {
							list.add("" + longest);
							list.add(line);
							longest = 0;
							amount = 0;
						}
					}
				}
				list.add("" + longest);
				System.out.println(list.toString());
				firstPass = true;
				reader = new Scanner(Text);
				int i = 1;
				while (reader.hasNextLine()) {
					String line = reader.nextLine();
					if (!line.contains(".conllu")) {
						if (!line.contains(".")) {
							Scanner temp = new Scanner(line);
							temp.useDelimiter("\t");
							int num = Integer.parseInt(temp.next());
							int amt = Integer.parseInt(temp.next());
							
							if (Integer.parseInt(list.get(i)) >= num) {
								count += amt;
							}
						}
					}
					else {
						if (firstPass) {
							firstPass = false;
							list2.add(line);
						} else {
							list2.add("" + count);
							list2.add(line);
							count = 0;
							i+=2;
						}
					}
				}
				list2.add("" + count);
				System.out.println(list2.toString());
				firstPass = true;
				reader = new Scanner(Text);
				i = 1;
				ArrayList<Integer> lengths = new ArrayList<Integer>();
				ArrayList<Integer> Amounts = new ArrayList<Integer>();
				while (reader.hasNextLine()) {
					String line = reader.nextLine();
					if (!line.contains(".conllu")) {
						if (!line.contains(".")) {
							Scanner temp = new Scanner(line);
							temp.useDelimiter("\t");
							int num = Integer.parseInt(temp.next());
							int amt = Integer.parseInt(temp.next());
							if (Integer.parseInt(list.get(i)) >= num) {
								rtn.add("" + num);
								rtn.add("" + amt);
							}
							else {
								lengths.add(num);
								lengths.sort(null);
								int indx = lengths.indexOf(num);
								Amounts.add(indx, amt);
							}
						}
					}
					else {
						if (firstPass) {
							firstPass = false;
							rtn.add(line);
						} else {
							int k = 0;
							for (int j = 0; j < lengths.size(); j++) {
								if (k + Amounts.get(j) < Integer.parseInt(list2.get(i))) {
									k+=Amounts.get(j);
									rtn.add("" + lengths.get(j));
									rtn.add("" + Amounts.get(j));
								} else if (Integer.parseInt(list2.get(i)) - k < Integer.parseInt(list2.get(i)) - (k + Amounts.get(j))){
									break;
								} else {
									k+=Amounts.get(j);
									rtn.add("" + lengths.get(j));
									rtn.add("" + Amounts.get(j));
									break;
								}
							}
							rtn.add(line);
							Amounts = new ArrayList<Integer>();
							lengths = new ArrayList<Integer>();
							i+=2;
						}
					}
				}
				
				/*Collections.sort(rtn, new Comparator<String>() {

					@Override
					public int compare(String arg0, String arg1) {
						// TODO Auto-generated method stub
						if (Integer.parseInt(arg0) == Integer.parseInt(arg1)) {
							return 0;
						}
						if (Integer.parseInt(arg0) > Integer.parseInt(arg1)) {
							return 1;
						}
						return -1;
					}
					
					
				});*/
				
				for (int indx = 0; indx<rtn.size(); indx++) {
					if (rtn.get(indx).contains(".conllu")) {
						System.out.println(rtn.get(indx));
					} else {
						System.out.println(rtn.get(indx) + "\t" + rtn.get(indx+1));
						indx++;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
