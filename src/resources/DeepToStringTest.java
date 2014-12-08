package resources;

import java.util.Arrays;

public class DeepToStringTest {
	
	public static void main(String[] args) {
		int[][][][] arr = {{{{1,1,1}, 
				{2,2,2}, 
				{3,3,3}},
				{{1,1,1}, 
					{2,2,2}, 
					{3,3,3}}}, {{{1,1,1}, 
						{2,2,2}, 
						{3,3,3}},
						{{1,1,1}, 
							{2,2,2}, 
							{3,3,3}}}};
		System.out.println(Arrays.deepToString(arr));

		StringClass[] sc = new StringClass[5];
		for (int i = 0; i < 5; i++)
			sc[i] = new StringClass(i);
		
		System.out.println(Arrays.deepToString(sc));
	}
}

class StringClass {
	
	int data;
	
	public StringClass(int i) {
		this.data = i;
	}
	
	public String toString() {
		return "" + data;
	}
	
}