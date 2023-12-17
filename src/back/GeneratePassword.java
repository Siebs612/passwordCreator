package back;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public abstract class GeneratePassword {
	
	public static String createPassword(int size) {
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		ArrayList<Character> preMixList = new ArrayList<>();
		try {
			preMixList = loadList();
		} catch (Exception e) {
			System.out.print("Error, File Not found:\n");
			e.printStackTrace();
		}
		ArrayList<Character> finalList = mixOrder(preMixList);
		for (int i = 0; i < size; i++) {
			char letter = finalList.get(r.nextInt(finalList.size()));
				if (Character.isAlphabetic(letter)) {
					if(r.nextInt(999)%2==0) {
						letter = Character.toTitleCase(letter);
					} 
				}
				sb.append(String.valueOf(letter));
				if ((i+1)%20==0) {
					sb.append("\n");
				}
		}
		
		return sb.toString();
		
	}

	public static ArrayList<Character> loadList() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(GeneratePassword.class.getResourceAsStream("/res/AllString.txt")));
		String line = br.readLine();
		ArrayList<Character> masterList = new ArrayList<>();
		while(line != null) {
			masterList.add(line.charAt(0));
			line = br.readLine();
		}
		br.close();
		return masterList;
		
	}
	
	public static ArrayList<Character> mixOrder(ArrayList<Character> old) {
		Random r = new Random();
		ArrayList<Integer> tmp = new ArrayList<>();
		for (int i = 0; i < old.size(); i++) {
			tmp.add(i);
		}
		ArrayList<Integer> newOrder = new ArrayList<>();
		while(!tmp.isEmpty()) {
			newOrder.add(tmp.remove(r.nextInt(tmp.size())));
		}
		ArrayList<Character> newList = new ArrayList<>();
		for (int i=0; i < newOrder.size(); i++) {
			newList.add(old.get(newOrder.get(i)));
		}
		return newList;
		
		
	}
}
