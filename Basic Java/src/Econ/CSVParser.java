package Econ;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.html.HTMLDocument.Iterator;

public class CSVParser {
	public static void main(String[] args) throws IOException {
		System.out.println("Hello World");
		File folder = new File("C:/Users/Alex/Downloads/Student Data");
		File[] listOfFiles = folder.listFiles();
		Map<String,Student> students = new HashMap<>();	
		String[] names = { "Aaron Lachhman", "Adam Shew", "Alex Koskela", "Alex McKeever", "Alex Wood", "Alex Zhu",
				"Andrew Asuvageau", "Anna Wise", "Anshul Bharath", "Asya Anderson", "Ben Donnay", "Brynn Sundgaard",
				"Caruso Caradori", "Colin Lamoreaux", "Collin Goldsworthy", "Dominic Voto", "Doug Rowe",
				"Ethan Engdahl", "Ethan Toth", "Grace Faeh", "Hayden Chester", "sabel Edgar", "Jack Reber",
				"Jack Schroeder", "Jack Troshinsky", "Jack Zickrick", "Jake Smith", "Jenna Vickery", "Jonathan Carman",
				"Joy Cooper", "Kyle West", "Laura Rietveld", "Lizzy Johnson", "Logan Bruce", "Luke Everson",
				"Maris Laurel", "Mark Christianson", "Michael Somsky", "Nathan Ebeling", "Nathan Mracek",
				"Owen Peterson", "Reid Fluegel", "Ryan Avenido", "Ryan Schneider", "Sahil Adige", "Sam Nelson",
				"Sidique Bachelani", "Stanley Ancheta", "T.J. Ligget", "Varsha Tallapaka", "Michael Cassano" };
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				// System.out.println("File " + listOfFiles[i].getName());
				String csvFile = listOfFiles[i].getName();
				String line = "";
				String cvsSplitBy = "\t";
				try (BufferedReader br = new BufferedReader(
						new FileReader("C:/Users/Alex/Downloads/Student Data/" + csvFile))) {

					while ((line = br.readLine()) != null) {
						// use comma as separator
						String[] list = line.split(cvsSplitBy);

						for (int z = 0;z<list.length;z++) {
							String[] words = list[z].split("\\s+");
							if (words.length <= 2){ //&& Character.isUpperCase(words[0].charAt(0)) && Character.isUpperCase(words[1].charAt(0))) {
								if(!students.containsKey(list[z])){
									students.put(list[z],new Student(list[z]));
									if(z!=list.length-1 && list[z+1].split("\\s+").length > 2){
										students.get(list[z]).addQuality(list[z+1]);
										z+=1;
									}
								}
								else{
									if(z!=list.length-1 && list[z+1].split("\\s+").length > 2){
										students.get(list[z]).addQuality(list[z+1]);
										z+=1;
									}
								}
							}
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		java.util.Iterator<Entry<String, Student>> it = students.entrySet().iterator();
	    while (((java.util.Iterator<Entry<String, Student>>) it).hasNext()) {
	    	Map.Entry pair = (Map.Entry)it.next();
	        it.remove(); // avoids a ConcurrentModificationException
	        Student s = (Student)pair.getValue();
			File f = new File(s+".txt");
			FileOutputStream fos = new FileOutputStream(f);
			PrintWriter pw = new PrintWriter(fos);
	
			String str = "";
			for(String x : s.getQualities()){
				str += x+"\r\n";
			}
			// Writing the user input to the file
			pw.write(str);
			pw.flush();
			fos.close();
			pw.close();
		}
	}
}
