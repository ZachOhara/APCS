package oharaClassesPractice;

public class Book {

	private int numPages;
	private int currentPage;

	public Book(int pages) {
		this.numPages = pages;
		this.currentPage = 1;
	}

	public int getNumPages() {
		return this.numPages;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void nextPage() {
		if (this.currentPage < this.numPages) {
			this.currentPage++;
		}
	}

}
