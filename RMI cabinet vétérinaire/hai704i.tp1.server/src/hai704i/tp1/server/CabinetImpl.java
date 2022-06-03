package hai704i.tp1.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hai704i.tp1.common.IAnimal;
import hai704i.tp1.common.ICabinet;
import hai704i.tp1.common.IClient;
import hai704i.tp1.common.Species;

public class CabinetImpl extends UnicastRemoteObject implements ICabinet {
	/* ATTRIBUTES */
	private String name;
	private List<IAnimal> patients;
	private List<IClient> clients;
	private List<Integer> notificationThresholds = Arrays.asList(2, 100, 500, 1000);

	/* CONSTRUCTOR */
	protected CabinetImpl(String name) throws RemoteException {
		this.name = name;
		patients = new ArrayList<>();
		clients = new ArrayList<>();
	}

	/* METHODS */
	@Override
	public String getName() throws RemoteException {
		return name;
	}
	
	@Override
	public List<IAnimal> getPatients() throws RemoteException {
		return patients;
	}

	@Override
	public boolean addPatient(String name, String ownerName, String speciesName, 
			int speciesAverageLife, String race, String state) 
					throws RemoteException {
		IAnimal patient = new AnimalImpl(name, ownerName, speciesName, 
				speciesAverageLife, race, state);
		int oldSize = patients.size();
		boolean result = patients.add(patient);
		if(result) {
			System.out.println("Successfully submitted " + patient.getInfos());
			checkForUpdates(oldSize);
		}
		else
			System.err.println("Couldn't add " + patient.getInfos());
		
		return result;
	}
	
	@Override
	public boolean addPatient(String name, String ownerName, Species species,
			String race, String state) throws RemoteException {
		IAnimal patient = new AnimalImpl(name, ownerName, species, race, state);
		int oldSize = patients.size();
		boolean result = patients.add(patient);
		if(result) {
			System.out.println("Successfully submitted " + patient.getInfos());
			checkForUpdates(oldSize);
		}
		else
			System.err.println("Couldn't add " + patient.getInfos());
		
		return result;
	}

	@Override
	public boolean removePatient(String name) throws RemoteException {
		IAnimal patient = getPatientByName(name);
		int oldSize = patients.size();
		boolean result = patients.remove(patient);
		if(result) {
			System.out.println("Successfully checked out " + patient.getInfos());
			checkForUpdates(oldSize);
		}
		else
			System.err.println("Couldn't add " + patient.getInfos());
		
		return result;
	}
	
	@Override
	public List<IClient> getClients() throws RemoteException {
		return clients;
	}
	
	private void checkForUpdates(int oldSize) throws RemoteException {
		int newSize = patients.size();
		
		if (notificationThresholds.contains(newSize)) {
			if (newSize > oldSize)
				alert("Patients number increased to "+newSize);
			else
				alert("Patients number decreased to "+newSize);
		}
	}
}
