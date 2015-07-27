/* Copyright (C) 2014-2015 Zach Ohara
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
