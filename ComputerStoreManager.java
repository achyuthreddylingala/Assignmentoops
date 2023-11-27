package javaactivityoops;

import java.util.Scanner;

public class ComputerStoreManager {
	public static final int MAX_COMPUTERS = 100;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int maxComputers = 0;
		Computer[] inventory = new Computer[MAX_COMPUTERS];

		// Welcome message
		System.out.println("Welcome to the Computer Store!");

		// Prompt the user for the maximum number of computers
		do {
			System.out.print("Please enter the maximum number of computers (1 - 100): ");
			maxComputers = scanner.nextInt();
		} while (maxComputers < 1 || maxComputers > MAX_COMPUTERS);

		int option = 0;
		int passwordAttempts = 0;
		final String PASSWORD = "password";

		do {
			displayMainMenu();
			System.out.print("Please enter your choice (1 - 5): ");
			option = scanner.nextInt();

			switch (option) {
			case 1:
				passwordAttempts = addComputers(scanner, maxComputers, inventory, passwordAttempts, PASSWORD);
				break;
			case 2:
				passwordAttempts = updateComputer(scanner, inventory, passwordAttempts, PASSWORD);
				break;
			case 3:
				findComputersByBrand(scanner, inventory);
				break;
			case 4:
				findCheaperThan(scanner, inventory);
				break;
			case 5:
				System.out.println("Closing the Computer Store...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (option != 5);
	}

	public static void displayMainMenu() {
		System.out.println("Main Menu:");
		System.out.println("1. Add computers");
		System.out.println("2. Update computer");
		System.out.println("3. Find computers by brand");
		System.out.println("4. Find cheaper than");
		System.out.println("5. Exit");
	}

	public static int addComputers(Scanner scanner, int maxComputers, Computer[] inventory, int passwordAttempts,
			String password) {
		if (!authenticate(scanner, password, passwordAttempts)) {
			return passwordAttempts;
		}

		System.out.print("How many computers do you want to enter? ");
		int numComputersToAdd = scanner.nextInt();

		if (numComputersToAdd <= (maxComputers - countComputers(inventory))) {
			for (int i = 0; i < numComputersToAdd; i++) {
				inventory[i] = createComputer(scanner);
			}
		} else {
			System.out.println("Not enough space in inventory. You can only add "
					+ (maxComputers - countComputers(inventory)) + " more computers.");
		}

		return passwordAttempts;
	}

	public static int updateComputer(Scanner scanner, Computer[] inventory, int passwordAttempts, String password) {
		if (!authenticate(scanner, password, passwordAttempts)) {
			return passwordAttempts;
		}

		System.out.print("Enter the computer number to update: ");
		int computerNumber = scanner.nextInt();

		if (inventory[computerNumber - 1] == null) {
			System.out.println("No computer found at index " + computerNumber);
			return passwordAttempts;
		}

		displayComputerInfo(inventory[computerNumber - 1], computerNumber);

		int choice;
		do {
			displayUpdateMenu();
			System.out.print("Enter your choice (1 - 5): ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				System.out.print("Enter the new brand: ");
				String newBrand = scanner.next();
				inventory[computerNumber - 1].updateBrand(newBrand);
				break;
			case 2:
				System.out.print("Enter the new model: ");
				String newModel = scanner.next();
				inventory[computerNumber - 1].updateModel(newModel);
				break;
			case 3:
				System.out.print("Enter the new serial number: ");
				String newSerialNumber = scanner.next();
				inventory[computerNumber - 1].updateSerialNumber(newSerialNumber);
				break;
			case 4:
				System.out.print("Enter the new price: ");
				double newPrice = scanner.nextDouble();
				inventory[computerNumber - 1].updatePrice(newPrice);
				break;
			case 5:
				System.out.println("Changes saved. Computer updated successfully!");
				displayComputerInfo(inventory[computerNumber - 1], computerNumber);
				break;
			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 5.");
			}
		} while (choice != 5);

		return passwordAttempts;
	}

	public static void findComputersByBrand(Scanner scanner, Computer[] inventory) {
		System.out.print("Enter the brand name to search for: ");
		String brandToSearch = scanner.next();

		System.out.println("Computers with brand '" + brandToSearch + "':");
		boolean found = false;

		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] != null && inventory[i].getBrand().equalsIgnoreCase(brandToSearch)) {
				displayComputerInfo(inventory[i], i + 1);
				found = true;
			}
		}

		if (!found) {
			System.out.println("No computers found with the specified brand.");
		}
	}

	public static void findCheaperThan(Scanner scanner, Computer[] inventory) {
		System.out.print("Enter the maximum price: ");
		double maxPrice = scanner.nextDouble();

		System.out.println("Computers cheaper than $" + maxPrice + ":");
		boolean found = false;

		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] != null && inventory[i].getPrice() < maxPrice) {
				displayComputerInfo(inventory[i], i + 1);
				found = true;
			}
		}

		if (!found) {
			System.out.println("No computers found cheaper than $" + maxPrice + ".");
		}
	}

	public static void displayComputerInfo(Computer computer, int computerNumber) {
		System.out.println("Computer # " + computerNumber);
		System.out.println("Brand: " + computer.getBrand());
		System.out.println("Model: " + computer.getModel());
		System.out.println("SN: " + computer.getSerialNumber());
		System.out.println("Price: $" + computer.getPrice());
	}

	public static Computer createComputer(Scanner scanner) {
		System.out.print("Enter the brand: ");
		String brand = scanner.next();

		System.out.print("Enter the model: ");
		String model = scanner.next();

		System.out.print("Enter the serial number: ");
		String serialNumber = scanner.next();

		System.out.print("Enter the price: ");
		double price = scanner.nextDouble();

		return new Computer(brand, model, serialNumber, price);
	}

	public static int countComputers(Computer[] inventory) {
		int count = 0;
		for (Computer computer : inventory) {
			if (computer != null) {
				count++;
			}
		}
		return count;
	}

	public static void displayUpdateMenu() {
		System.out.println("Update Menu:");
		System.out.println("1. Update brand");
		System.out.println("2. Update model");
		System.out.println("3. Update serial number");
		System.out.println("4. Update price");
		System.out.println("5. Save changes and exit");
	}

	public static boolean authenticate(Scanner scanner, String correctPassword, int attempts) {
		final int MAX_ATTEMPTS = 3;

		while (attempts < MAX_ATTEMPTS) {
			System.out.print("Enter your password: ");
			String enteredPassword = scanner.next();

			if (enteredPassword.equals(correctPassword)) {
				return true;
			} else {
				attempts++;
				System.out.println("Incorrect password. Attempts remaining: " + (MAX_ATTEMPTS - attempts));
			}
		}

		System.out.println("Too many incorrect attempts. Returning to the main menu.");
		return false;
	}
}
