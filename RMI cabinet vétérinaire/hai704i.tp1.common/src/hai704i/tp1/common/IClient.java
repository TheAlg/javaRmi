package hai704i.tp1.common;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {
	void setUp() throws RemoteException;
	ICabinet getCabinet() throws RemoteException;
	ICabinet lookupCabinet(String cabinetKey) throws RemoteException, NotBoundException;
	IAnimal getPatientByName(String name) throws RemoteException;
	boolean submitPatient(String name, String ownerName, String speciesName, 
			int speciesAverageLife, String race, String state) 
					throws RemoteException;
	boolean submitPatient(String name, String ownerName, Species species, 
			String race, String state) throws RemoteException;
	boolean checkoutPatient(String name) throws RemoteException;
	void displayPatients() throws RemoteException;
	void update(String newState) throws RemoteException;
}
