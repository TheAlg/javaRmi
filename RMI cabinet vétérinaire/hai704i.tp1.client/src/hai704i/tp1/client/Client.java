package hai704i.tp1.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import hai704i.tp1.common.IAnimal;
import hai704i.tp1.common.ICabinet;
import hai704i.tp1.common.IClient;
import hai704i.tp1.common.Species;

public class Client extends UnicastRemoteObject implements IClient {
	/* ATTRIBUTES */
	private static final int RMI_REGISTRY_PORT = 1099;
	private Registry registry;
	private ICabinet cabinet;
	
	/* CONSTRUCTOR */
	public Client() throws RemoteException {}
	
	/* METHODS */
	@Override
	public void setUp() throws RemoteException {
		setUpRegistry();
		System.err.println("Client ready");
	}
	
	private void setUpRegistry() throws RemoteException {
		registry = LocateRegistry.getRegistry(RMI_REGISTRY_PORT);
		if (registry == null)
			System.err.println("RMI registry not found at port " + RMI_REGISTRY_PORT);
	}
	
	@Override
	public ICabinet lookupCabinet(String cabinetKey) throws RemoteException, 
		NotBoundException {
		cabinet = (ICabinet) registry.lookup(cabinetKey);
		cabinet.subscribe(this);
		return cabinet;
	}
	
	@Override
	public ICabinet getCabinet() throws RemoteException {
		return cabinet;
	}
	
	@Override
	public IAnimal getPatientByName(String name) throws RemoteException {
		return cabinet.getPatientByName(name);
	}
	
	@Override
	public boolean submitPatient(String name, String ownerName, String speciesName, 
			int speciesAverageLife, String race, String state) 
					throws RemoteException {
		return cabinet.addPatient(name, ownerName, speciesName, speciesAverageLife, 
				race, state);
	}
	
	@Override
	public boolean submitPatient(String name, String ownerName, Species species, 
			String race, String state) throws RemoteException {
		return cabinet.addPatient(name, ownerName, species, race, state);
	}

	@Override
	public void displayPatients() throws RemoteException {
		for (IAnimal patient: cabinet.getPatients())
			System.out.println(patient.getInfos());
	}
	
	@Override
	public void update(String newState) throws RemoteException {
		System.out.println("Update: " + newState);
	}

	@Override
	public boolean checkoutPatient(String name) throws RemoteException {
		return cabinet.removePatient(name);
	}
}
