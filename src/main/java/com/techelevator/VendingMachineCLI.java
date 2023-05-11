package com.techelevator;

import com.techelevator.util.ConsoleUtility;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

	// Use this for keyboard input - it is initialized in the constructor
	private Scanner userInput;
	private VendingMachine theVendingMachine;

	public VendingMachineCLI(Scanner userInput) {
		this.userInput = userInput;
	}

	public void run() {

		VendingMachine theVendingMachine = new VendingMachine(new File("vendingmachine.csv"));
		boolean runMainMenu = true;
		while (runMainMenu) {
			System.out.println();
			System.out.print(ConsoleUtility.ANSI_YELLOW);
			System.out.println("(1) Display Vending Machine Items");
			System.out.println("(2) Purchase");
			System.out.println("(3) Exit");
			String mainMenuOption = userInput.nextLine();
			if (mainMenuOption.equals("1")) {
				List<String> displayItems = theVendingMachine.printItemList();
				for (String curString : displayItems) {
					if (curString.contains("SOLD OUT")) {
					System.out.print(ConsoleUtility.ANSI_RED + ConsoleUtility.ANSI_UNDERLINE + ConsoleUtility.ANSI_BOLD);
					System.out.println(curString);
					System.out.print(ConsoleUtility.ANSI_RESET);
				} else {
					System.out.print(ConsoleUtility.ANSI_CYAN);
					System.out.println(curString);
					System.out.print(ConsoleUtility.ANSI_RESET);
				}
				}
			} else if (mainMenuOption.equals("2")) {
				boolean runPurchaseMenu = true;
				while (runPurchaseMenu) {
					System.out.print(ConsoleUtility.ANSI_YELLOW);
					System.out.println();
					System.out.println("Current Money Provided: $" + theVendingMachine.getCurBalance());
					System.out.println();
					System.out.println("(1) Feed Money");
					System.out.println("(2) Select Product");
					System.out.println("(3) Finish Transaction");
					mainMenuOption = userInput.nextLine();
					if (mainMenuOption.equals("1")) {
						System.out.println("Please insert your money (penny or higher, use number keys):");
						String moneyIn = userInput.nextLine();
						System.out.println(theVendingMachine.feedMoney(moneyIn));
					} else if (mainMenuOption.equals("2")) {
						List<String> displayItems = theVendingMachine.printItemList();
						for (String curString : displayItems) {
							if (curString.contains("SOLD OUT")) {
								System.out.print(ConsoleUtility.ANSI_RED + ConsoleUtility.ANSI_UNDERLINE + ConsoleUtility.ANSI_BOLD);
								System.out.println(curString);
								System.out.print(ConsoleUtility.ANSI_RESET);
							}
							else {
								System.out.print(ConsoleUtility.ANSI_CYAN);
								System.out.println(curString);
								System.out.print(ConsoleUtility.ANSI_RESET);
							}
						}
						System.out.println(ConsoleUtility.ANSI_YELLOW);
						System.out.println("Please enter your selection code:");
						String itemSelection = userInput.nextLine();
						String output = theVendingMachine.vend(itemSelection);
						if (output.equalsIgnoreCase("Item is SOLD OUT") || output.equalsIgnoreCase("Insufficient funds")) {
							System.out.println();
							System.out.print(ConsoleUtility.ANSI_RED + ConsoleUtility.ANSI_UNDERLINE + ConsoleUtility.ANSI_BOLD);
						} else {
							System.out.println();
							System.out.println(ConsoleUtility.ANSI_LIGHT_MAGENTA);
						}
						System.out.println(output);
						System.out.print(ConsoleUtility.ANSI_RESET);
					} else if (mainMenuOption.equals("3")) {
						System.out.println(theVendingMachine.finishTransaction());
						runPurchaseMenu = false;
					} else {
						System.out.print(ConsoleUtility.ANSI_RED + ConsoleUtility.ANSI_UNDERLINE + ConsoleUtility.ANSI_BOLD);
						System.out.println("Invalid Selection.  Please try again");
						System.out.println(ConsoleUtility.ANSI_RESET);
					}
				}
			} else if (mainMenuOption.equals("3")) {
				System.out.println("Hahaaa, sucker!");
				runMainMenu = false;
			} else if (mainMenuOption.equals("4")) {
				System.out.println("Invalid Selection.  Please try again");
			} else {
				System.out.print(ConsoleUtility.ANSI_RED + ConsoleUtility.ANSI_UNDERLINE + ConsoleUtility.ANSI_BOLD);
				System.out.println("Invalid Selection.  Please try again");
				System.out.print(ConsoleUtility.ANSI_RESET);
			}
		}
			// Add a loop here for your menu
	}

	public static void main(String[] args) {
		ConsoleUtility test = new ConsoleUtility();
		Scanner input = new Scanner(System.in);
		VendingMachineCLI cli = new VendingMachineCLI(input);
		cli.run();
	}
}
