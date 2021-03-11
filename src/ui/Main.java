package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.chrono.MinguoChronology;
import java.util.Arrays;

import com.sun.org.apache.xpath.internal.FoundIndex;

public class Main {

	private final static BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
	private final static BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		String books = bReader.readLine();
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
					} 
					count++;
					if(count==booksNum) {
						bWriter.write("Peter should buy books whose prices are "+ one + " and " + two);
						bWriter.flush();
					}
				}
			}
		}
		
		
		
		bReader.close();
		bWriter.close();
	}
	
	public static int binarySearch(int[] array, int x) {
		int pos = -1;
		int i = 0;
		int j = array.length;
		
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
