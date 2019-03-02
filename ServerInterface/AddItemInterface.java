package ServerInterface;


import java.rmi.*;


public interface AddItemInterface extends Remote{

	public boolean addItem(String managerID,String itemID,String itemName,int quantity) throws RemoteException;
	public String listItem(String managerID) throws RemoteException;
	public boolean removeItem(String managerID,String itemID,int quantity) throws RemoteException;
	public boolean borrowItem(String userID,String itemID,int numberOfDay) throws RemoteException;
	public String findItem(String userID, String itemName) throws RemoteException;
	public boolean returnItem(String userID,String itemID) throws RemoteException;
	public boolean waitingList(String userID,String itemID) throws RemoteException;
	public String findBorrowedItems(String userId) throws RemoteException;
	public String findWaitingItems() throws RemoteException;
}
