package given;

import java.util.Arrays;

public class ArrayDisputes {
	
	public static void main(String[] args) {
		int[] list = {8, 10, 8, 6, 4, 2};
		System.out.println(mystery1(list));
	}
	
	public static void objectPassingDispute() {
		int[] a1 = {1, 3, 5, 7, 9};
		int[] a2 = {1, 4, 9, 16, 25};
		System.out.println(Arrays.toString(a1));
		System.out.println(Arrays.toString(a2));
		mystery(a1, a2);
		System.out.println(Arrays.toString(a1));
		System.out.println(Arrays.toString(a2));
	}
	
	public static void mystery(int[] a, int[] b) {
		for (int i = 0; i < a.length; i++)
			a[i] += b[b.length - 1 - i];
	}
	
	public static void forLoopDispute() {
		int[] numbers = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
		for (int i = 1; i < 10; i++)
		     numbers[i] = numbers[i - 1];
		System.out.println(Arrays.toString(numbers));
	}
	
	public static int mystery1(int[] list) {
		int x = 0;
		for (int i = 0; i < list.length - 1; i++) {
			if (list[i] > list[i + 1]) {
				x++;
			}
		}
		return x;
	}

}
