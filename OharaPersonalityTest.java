import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class OharaPersonalityTest {

	public static void main(String[] args) {
		String[] surveyResults = getFile("personality.txt");
		Personality[] people = new Personality[surveyResults.length/2];
		for (int i = 0; i < people.length; i++) {
			people[i] = new Personality(surveyResults[i*2]);
		}
	}
	
	public static String[] getFile(String address) {
		File f = new File(address);
		ArrayList<String> output = new ArrayList<String>();
		try {
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				output.add(s.nextLine());
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("File was not found: " + address);
			e.printStackTrace();
		}
		return output.toArray(new String[output.size()]);
	}

}

class Personality {
	
	public String name;
	
	public Personality(String name)	{
		this.name = name;
	}
}