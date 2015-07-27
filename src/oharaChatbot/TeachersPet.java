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

 package oharaChatbot;

public class TeachersPet extends StudentBot {

	public TeachersPet(String nm) {
		super(nm);
	}

	@Override
	public String respondToGreeting(String greeting) {
		String response = super.respondToGreeting(greeting);
		if (greeting.indexOf("name is") != -1) {
			response += greeting.substring(greeting.indexOf("name is") + 7);
		} else {
			response += ".";
		}
		response += "\nWhat are we going to do in class today?";
		return response;
	}

}
