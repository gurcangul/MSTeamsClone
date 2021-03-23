package Main;

import java.io.IOException;
import java.util.Scanner;
import CoursePackage.UnauthorizedUserOperationException;
import FilePackage.*;
import UserPackage.Login;

public class TeamsTECH {
	
	static Scanner scanner = new Scanner(System.in);
	public static void start() throws IOException, UnauthorizedUserOperationException{ 

		FileIO.splitUsers();
		FileIO.splitTeams();
		System.out.println("WELCOME TO OUR TEAMSTECH APP! \n");
		System.out.println("To enter the system, please select E ");
		System.out.println("To exit, please select Q ");
		String option = scanner.next();
		switch(option.toUpperCase()) {
		case "E":
			Login.loginPage();
			break;
		case "Q":
			System.exit(0);
			break;
		default:
			System.out.println("Invalid option!");
			break;
		}
	}
	
}
