package Client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner; 

import ServerInterface.AddItemInterface;

public class Client {

	public static void main(String args[])
	{
		
		startSystem();
		
	}
	// Initiating client site program 
	private static void startSystem() {
		System.out.println("Enter your username: ");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine().toUpperCase();
		System.out.println("You are loging as " + username);
		if(username.length()!=8) {
			System.out.println("Wrong ID");
			startSystem();
		}
		String accessParameter = username.substring(3, Math.min(username.length(), 4));
		System.out.println("You are loging as " + accessParameter);
		if(accessParameter.equals("U") || accessParameter.equals("u") ) {
			try {
				user(username);
				startSystem();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(accessParameter.equals("M") || accessParameter.equals("m")) {
			try {
				manager(username);
//				startSystem();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("This user is not authorized");
			startSystem();
		}
	}
	
	private static void user(String username) throws Exception
	{
		int serverPort = decideServerport(username);
		if(serverPort==1) {
			return;
		}
		Registry registry = LocateRegistry.getRegistry(serverPort);
		System.out.println("1. Borrow Item \n 2.Find Item \n 3. Return item \n 4. Borrowed Items \n 5. Logout");
		System.out.println("Select the option you want to do: ");
		Scanner scanner = new Scanner(System.in);
		String menuSelection = scanner.nextLine();

		if(menuSelection.equals("1")) {
			String itemId = setItemId(username);
			int numbersOfDay = setNumbersOfDay(username);
			AddItemInterface obj = (AddItemInterface) registry.lookup("AddItem");
			boolean n = obj.borrowItem(username, itemId,numbersOfDay);
			System.out.println("Item Borrowed : " + n);
			if(!n) {
				System.out.println("Item is not available now. Do you like to stay in waiting list? \n Press Y for yes and enter. N for NO and enter");
				String waitingOption = scanner.nextLine();
				if(waitingOption.equals("Y")||waitingOption.equals("y")) {
					AddItemInterface obj1 = (AddItemInterface) registry.lookup("AddItem");
					boolean m = obj1.waitingList(username, itemId);
					if(m) {
						System.out.println("Your Item will be lend to you, when it will be available.");
					}
				}else {
					user(username);
				}
			}
			user(username);
		}
		else if(menuSelection.equals("2")) {
			String itemName = setItemName(username);
			System.out.println("Item List is given below. ");
			AddItemInterface obj = (AddItemInterface) registry.lookup("AddItem");
			System.out.println(obj.findItem(username, itemName));
			System.out.println("To GO back press E and enter");
			String exit = scanner.nextLine();
			if(exit.equals("E") || exit.equals("e")) {
				user(username);
			}else {
				user(username);
			}
		}
		else if(menuSelection.equals("3")) {
			String itemId = setItemId(username);
			AddItemInterface obj = (AddItemInterface) registry.lookup("AddItem");
			boolean n = obj.returnItem(username, itemId);
			System.out.println("Item Returend : " + n);
			user(username);
		}
		else if (menuSelection.equals("4")) {
			System.out.println("Item List is given below. ");
			AddItemInterface obj = (AddItemInterface) registry.lookup("AddItem");
			System.out.println(obj.findBorrowedItems(username));
			System.out.println("To GO back press E and enter");
			String exit = scanner.nextLine();
			if(exit.equals("E") || exit.equals("e")) {
				user(username);
			}else {
				user(username);
			}
		}
		else if (menuSelection.equals("5")) {
			startSystem();
		}
		else {
			user(username);
		}
	}
	
	private static void manager(String username) throws Exception
	{
		int serverPort = decideServerport(username);
		if(serverPort==1) {
			return;
		}
		Registry registry = LocateRegistry.getRegistry(serverPort);
		System.out.println("1. Add Items \n 2.Remove Item \n 3. List of the items \n 4. Waiting List \n 5. Logout");
		System.out.println("Select the option you want to do: ");
		Scanner scanner = new Scanner(System.in);
		String menuSelection = scanner.nextLine();
		if(menuSelection.equals("1")) {
			String itemId = setItemId(username);
			String itemName = setItemName(username);
			int itemQty = setItemQty(username);
			AddItemInterface obj = (AddItemInterface) registry.lookup("AddItem");
			boolean n = obj.addItem(username, itemId,itemName,itemQty);
			System.out.println("Item Added : " + n);
			manager(username);
		}
		else if(menuSelection.equals("2")) {
			String itemId = setItemId(username);
			int itemQty = setItemQty(username);
			AddItemInterface obj = (AddItemInterface) registry.lookup("AddItem");
			boolean n = obj.removeItem(username, itemId,itemQty);
			System.out.println("Item Removed : " + n);
			if(!n) {
				System.out.println("Check Quantity");
			}
			manager(username);
		}
		else if(menuSelection.equals("3")) {
			System.out.println("Item List is given below. ");
			AddItemInterface obj = (AddItemInterface) registry.lookup("AddItem");
			System.out.println(obj.listItem(username));
			System.out.println("To GO back press E and enter");
			String exit = scanner.nextLine();
			if(exit.equals("E") || exit.equals("e")) {
				manager(username);
			}else {
				manager(username);
			}
		}
		else if(menuSelection.equals("4")) {
			System.out.println("Waiting List is given below. ");
			AddItemInterface obj = (AddItemInterface) registry.lookup("AddItem");
			System.out.println(obj.findWaitingItems());
			System.out.println("To GO back press E and enter");
			String exit = scanner.nextLine();
			if(exit.equals("E") || exit.equals("e")) {
				manager(username);
			}else {
				manager(username);
			}
		}
		else if (menuSelection.equals("5")) {
			startSystem();
		}
		else {
			manager(username);
		}

	}
	
	private static int decideServerport(String username) {
		int serverPort=1;
		String serverDirection = username.substring(0, Math.min(username.length(), 3));
		if(serverDirection.equals("CON") || serverDirection.equals("con")) {
			serverPort = 2964;
		}else if(serverDirection.equals("MCG") || serverDirection.equals("mcg")) {
			serverPort = 2965;
		}else if(serverDirection.equals("MON") || serverDirection.equals("mon")) {
			serverPort = 2966;
		}else {
			System.out.println("This is an invalid request. Please check your username");
			startSystem();
		}
		return serverPort;
	}
	
	private static String setItemId(String username) {
		String libraryCode = username.substring(0, Math.min(username.length(), 3));
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Item Id: ");
		String itemId = scanner.nextLine().toUpperCase();
		String itemPrefix = itemId.substring(0, Math.min(itemId.length(), 3));
		if(itemId.length()!=7 && libraryCode !=itemPrefix) {
			System.out.println("Enter a valid Item Id: ");
			itemId = setItemId(username);
		}
		return  itemId;
	}
	
	private static String setItemName(String username) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Item name: ");
		String itemName = scanner.nextLine().toUpperCase();
	
		
		return  itemName;
	}
	
	private static int setItemQty(String username) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Item Quantity: ");
		int itemQty;
		if(scanner.hasNextInt()){
			itemQty = scanner.nextInt();
			if(itemQty<0) {
				System.out.println("Enter a valid Number: ");
				itemQty = setItemQty(username);
			}
		}else{
			System.out.println("Enter a valid Item Id: ");
			itemQty = setItemQty(username);
		}
		return  itemQty;
	}
	
	private static int setNumbersOfDay(String username) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Number Of Days: ");
		int numberOfDays;
		if(scanner.hasNextInt()){
			numberOfDays = scanner.nextInt();
		}else{
			System.out.println("Enter a valid Number: ");
			numberOfDays = setNumbersOfDay(username);
		}
		return  numberOfDays;
	}
}
