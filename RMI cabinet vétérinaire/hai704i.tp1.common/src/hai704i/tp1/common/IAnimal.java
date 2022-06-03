package hai704i.tp1.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnimal extends Remote {
	/* METHODS */
	String getName() throws RemoteException;
	String getOwnerName() throws RemoteException;
	Species getSpecies() throws RemoteException;
	String getRace() throws RemoteException;
	IFollowUpFile getFollowUpFile() throws RemoteException;
	String getState() throws RemoteException;
	void setState(String state) throws RemoteException;
	
	default String getFullName() throws RemoteException {
		return getName() + " (owned by " + getOwnerName() + ")";
	}
	
	default String getInfos() throws RemoteException {
		StringBuffer buffer = new StringBuffer();
		buffer.append("(Full name: " + getFullName());
		buffer.append(", Species: " + getSpecies());
		buffer.append(", Race: " + getRace());
		buffer.append(", Follow-up: " + getFollowUpFile() + ")");
		return buffer.toString();
	}
}
