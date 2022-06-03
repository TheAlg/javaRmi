package hai704i.tp1.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFollowUpFile extends Remote {
	String getState() throws RemoteException;
	void setState(String state) throws RemoteException;
}
