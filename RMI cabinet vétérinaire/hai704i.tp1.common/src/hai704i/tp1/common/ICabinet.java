package hai704i.tp1.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICabinet extends Remote {
	String getName() throws RemoteException;
	List<IAnimal> getPatients() throws RemoteException;
	boolean addPatient(String name, String ownerName, String speciesName, 
			int speciesAverageLife, String race, String state) 
					throws RemoteException;
	boolean addPatient(String name, String ownerName, Species species, 
			String race, String state) throws RemoteException;
	boolean removePatient(String name) throws RemoteException;
	
	default IAnimal getPatientByName(String name) throws RemoteException {
		for (IAnimal animal: getPatients())
			if(animal.getName().equals(name))
				return animal;
		System.err.println("Patient named " + name + " is unknown");
		return null;
	}
	
	List<IClient> getClients() throws RemoteException;
	
	default boolean subscribe(IClient client) throws RemoteException {
		return getClients().add(client);
	}
	
	default boolean unsubscribe(IClient client) throws RemoteException {
		return getClients().remove(client);
	}
	
	default void alert(String newState) throws RemoteException {
		System.out.println(newState);
		for (IClient client: getClients())
			client.update(newState);
	}
}
