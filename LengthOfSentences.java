import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class LengthOfSentences {
	public static void main(String[] args) {
		//Only one conllu file
		if (args.length == 1) {
			File file = new File(args[0]);
			parser(file);
		}
		//Multiple conllu files
		else if (args.length > 1){
			File folder = new File(args[0]);
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				File temp = listOfFiles[i];
				if (temp.isFile() && temp.getName().endsWith(".conllu")) {
					System.out.println(temp.getName());
					parser(temp);
				}
			}
		}
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public static void parser(File file) {
		ArrayList<String> rtn = new ArrayList<String>();
		if (file.canRead()) {
			try {
				String Text = new String(Files.readAllBytes(file.toPath()));
				Scanner reader = new Scanner(Text);
				String LastLen = "";
				while (reader.hasNextLine()) {
					String line = reader.nextLine();
					if (line.length() != 0 && line.charAt(0) != '#') {
						Scanner parser = new Scanner(line);
						LastLen = parser.next();
					}
					else {
						if (LastLen.length() !=0)
							rtn.add(LastLen);
							LastLen = "";
					}
				}
				Collections.sort(rtn);
				int total = rtn.size();
				String Prev = (String) rtn.get(0);
				int count = 1;
				for (int i = 1; i<rtn.size(); i++) {
					if (((String) rtn.get(i)).equals(Prev)) {
						count++;
						if (i+1 == rtn.size()) {
							rtn.remove(i);
							rtn.add(i, round((double)(count*100)/total,2) + "");
							rtn.add(i, count + "");
							break;
						}
						rtn.remove(i);
						i--;
					}  else {
						Prev = (String) rtn.get(i);
						if (i+1 == rtn.size()) {
							rtn.add(i+1, round(100.0/total, 2) + "");
							rtn.add(i+1, "1");
							rtn.add(i, round((double)(count*100)/total, 2) + "");
							rtn.add(i, count + "");
							break;
						}
						rtn.add(i, round((double)(count*100)/total, 2) + "");
						rtn.add(i, count + "");
						count = 1;
						i+=2;
					}
				}
				for (int i = 0; i<rtn.size(); i+=3) {
					System.out.println(rtn.get(i) + "\t" + rtn.get(i+1) + "\t" + rtn.get(i+2));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
