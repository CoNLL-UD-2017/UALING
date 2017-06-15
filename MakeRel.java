import java.io.*;
import java.util.StringTokenizer;
import java.util.Hashtable;

public class MakeRel {

	public static void main (String[] args) throws IOException {

		String fileName = args[0];
		BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream(new File (fileName)), "UTF8"));

		//                OutputStreamWriter out = new OutputStreamWriter (new FileOutputStream(fileName+".1"), "UTF-8");

		String str = new String();

		str = d.readLine();

		String[][] ar = new String[2048][2];
		int id = 0; 

		ar[id++][0] = "BOS"; 

		while (str != null) {
			str = str.trim();
			String delim = "\t";
			/*
# sent_id = dev-s1
# text = Manasse ist ein einzigartiger Parfümeur.
1	Manasse	Manasse	PROPN	NN	Case=Nom|Number=Sing	5	nsubj	_	_
2	ist	sein	VERB	VAFIN	Mood=Ind|Number=Sing|Person=3|Tense=Pres|VerbForm=Fin	5	cop	_	_
3	ein	ein	DET	ART	Definite=Ind|PronType=Art	5	det	_	_
4	einzigartiger	einzigartig	ADJ	ADJA	Degree=Cmp,Pos	5	amod	_	_
*/
			if (str.startsWith("#")) {}
			else if (str.length()==0) {
				ar[id++][0] = "EOS"; 
				for (int i=1; i<id-1; i++) {
					String prev = ar[i][0]; 
					String temp = ar[i][1]; 
					String rel = temp.substring(temp.indexOf(";")+1, temp.length()).trim();
					String addr = temp.substring(0, temp.indexOf(";")).trim();
					if (!addr.equals("0")) {
						String next = ar[Integer.parseInt(addr)][0];
						System.out.println(prev + ";" + rel + ";" + next);
					}
				}
				ar = new String[2048][2]; id = 0; ar[id++][0] = "BOS";  
			}
			else {
				String[] entry;
				entry = str.split(delim);
				if (entry[0].contains("-")) {}
				else {
					ar[id][0] = entry[3];
					ar[id][1] = entry[6] + ";" + entry[7];
					id++;
				}
			}



			str = d.readLine();
		}

		//Hashtable<String,Integer> hash = new Hashtable<String,Integer>();
		d.close();
	}
}


