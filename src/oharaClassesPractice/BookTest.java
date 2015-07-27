package oharaClassesPractice;

public class BookTest {

	public static void main(String[] args) {
		Book myBook = new Book(3);
		System.out.println(myBook.getCurrentPage());
		for (int i = 0; i < 3; i++) {
			myBook.nextPage();
			System.out.println(myBook.getCurrentPage());
		}
	}

}
