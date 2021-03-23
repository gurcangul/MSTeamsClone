package UserPackage;

import java.io.IOException;
import java.util.Scanner;

import CoursePackage.Mediator;
import CoursePackage.UnauthorizedUserOperationException;
import Main.TeamsTECH;

public class UserMenu {
	
	static Scanner scanner = new Scanner(System.in);
	public static void userMainMenu(User user) throws IOException, UnauthorizedUserOperationException {
		while(true) {
			System.out.println("What would you like to do?");
			System.out.println("1- Add a team");
			System.out.println("2- Remove a team");
			System.out.println("3- Update a team");
			System.out.println("4- Back ");
			System.out.println("5- Exit");
			System.out.println("Please enter a number between 1-4: ");
			String option = scanner.next();
			switch(option) {
			case "1":
				Mediator.addTeam(user);
				break;
			case "2":
				Mediator.removeTeam(user);
				break;
			case "3":
				Mediator.updateTeam(user);	
				break;
			case "4":
				TeamsTECH.start();
				break;
			case "5":
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option...");
				break;
			}
		} 
	}
	

}
