package given;

import java.util.Arrays;

public class ArrayDisputes {

	public static void main(String[] args) {
		mystery();
	}

	public static void mystery() {
		int	x =	1;
		int[] a	= new int[2];
		mystery1(x, a);
		System.out.println(x +	" "	+ Arrays.toString(a));
		x--;
		a[1]	=	a.length;
		mystery1(x,	a);
		System.out.println(x +	" "	+ Arrays.toString(a));
	}

	public static void forLoopDispute() {
		int[] numbers = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
		for (int i = 1; i < 10; i++)
			numbers[i] = numbers[i - 1];
		System.out.println(Arrays.toString(numbers));
	}


	public static void mystery1(int	x, int[] list) {
		list[x]++;
		x++;
		System.out.println(x +	" "	+ Arrays.toString(list));
	}

}
