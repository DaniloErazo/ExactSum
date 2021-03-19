package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	private final static BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
	private final static BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		String lineString;
		String output = "";
		String books;
		while((lineString=bReader.readLine())!=null) {
			
			if(lineString.equals("")) {
				books = bReader.readLine();
			}else {
				books = lineString;
			}
			int booksNum=Integer.parseInt(books);
			
			if(booksNum<2 || booksNum>10000) {
				bWriter.write("The test case is wrong, check your entry");
				bWriter.flush();
			}else {
				
				String line = bReader.readLine();
				String[] partStrings = line.split(" ");
				int[] array = stringArraytoInt(partStrings);
				
				if(array.length!=booksNum) {
					bWriter.write("The test case is wrong, check your entry");
					bWriter.flush();
				}else {
				
					String money = bReader.readLine();
					int moneyInt = Integer.parseInt(money);
					
					ArrayList<int[]> pairs = new ArrayList<int[]>();
					
					ArrayList<Integer> differences = new ArrayList<Integer>();
					
					Arrays.sort(array);
					int found = 0;
					int one = 0, two = 0;
					int count=0;
					for (int i = 0; i < array.length ; i++) {
					
						int moneySearch = moneyInt-array[i];
						found = binarySearch(array, moneySearch);
						if(found>0) {
							if(array[i]>array[found]) {
								two = array[i];
								one = array[found];
							}else {
								one=array[i];
								two=array[found];
							}
							
							int[] pairsPrice = new int[2];
							pairsPrice[0] = one;
							pairsPrice[1]=two;
							pairs.add(pairsPrice);
							int difference = two-one;
							differences.add(difference);
							
						} 
						count++;
						if(count==booksNum) {
							
						}
					}
					int lessPrices = defineMinorPair(differences);
					int[] finalOne = pairs.get(lessPrices);
					one =finalOne[0];
					two = finalOne[1];
					output+="Peter should buy books whose prices are "+ one + " and " + two +".\n \n";
				}
			}
			
		}
		
		bWriter.write(output);
		bWriter.flush();
		
		bReader.close();
		bWriter.close();
	}
	
	public static int defineMinorPair(ArrayList<Integer> array) {
		
		int minValue = array.get(0);
		int index = 0;
	    for (int i = 1; i < array.size(); i++) {
	    	int test = array.get(i);
	    	if(test>0) {
	    		if (test < minValue) {
		            minValue = array.get(i);
		            index = i;
		        }
	    	}else {
	    		test = test*-1;
	    		if (test < minValue) {
		            minValue = array.get(i);
		            index = i;
	    		}
	    	}
	        
	    }

	    return index;
	}
	
	public static int binarySearch(int[] array, int x) {
		int pos = -1;
		int i = 0;
		int j = array.length-1;
		
		while (i<=j && pos<0) {
			int m = (i+j)/2;
			if (array[m]==x) {
				pos = m;
			} else if (array[m]>x) {
				j= m-1;
			} else {
				i= m+1;
			}
		}
		
		return pos;
	}
	
	
	public static int[] stringArraytoInt(String[] splitArr) {
	    int[] intArr = new int[splitArr.length];
	    for (int i  = 0; i < splitArr.length; i++) {
	      intArr[i] = Integer.parseInt(splitArr[i]);
	    }
	    return intArr;
	}

}
