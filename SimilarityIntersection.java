import java.io.*;
import java.util.StringTokenizer;
import java.util.Hashtable;

public class SimilarityIntersection {

	public static void main (String[] args) throws IOException {

		/**
			reading .2
# sent_id = train-s1
		 **/
		String fileName = args[0];
		BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream(new File (fileName)), "UTF8"));

		String str = new String();
		int id = 0;
		str = d.readLine();
Hashtable hash = new Hashtable();
		while (str != null) {
			str = str.trim();
if (str.startsWith("# sent_id = ")) hash.put(str, "1");

			str = d.readLine();
		}
		d.close();

		/** 
		  Sentence by sentence in the training data;;;; 
		 **/
		fileName = args[1];
		d = new BufferedReader(new InputStreamReader(new FileInputStream(new File (fileName)), "UTF8"));
boolean flag = false;

		str = new String();
		str = d.readLine();

		String[][] train = new String[2048][2];
		String[] sent = new String[2048];
		int sentAddr = 0; 

		while (str != null) {
			str = str.trim();
			String delim = "\t";

			if (str.startsWith("#")) {
if (str.startsWith("# sent_id = ") && hash.get(str)!=null) {
flag = true;
}
sent[sentAddr++] = str;}
			else if (str.length()==0) {
if(flag) {
					for (int j=0; j<sentAddr; j++) 
						System.out.println(sent[j]); ;
					System.out.println();
}

				train = new String[2048][2]; 
				sent = new String[2048]; sentAddr = 0; 
				flag = false;
			}
			else {
				String[] entry;
				entry = str.split(delim);
				if (entry[0].contains("-")) {
					sent[sentAddr++] = str;
				}
				else {
					sent[sentAddr++] = str; 
				}
			}
			str = d.readLine();
		}
		d.close();
	}

	public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
		double dotProduct = 0.0;
		double normA = 0.0;
		double normB = 0.0;
		for (int i = 0; i < vectorA.length; i++) {
			dotProduct += vectorA[i] * vectorB[i];
			normA += Math.pow(vectorA[i], 2);
			normB += Math.pow(vectorB[i], 2);
		}   
		return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
	}

}


