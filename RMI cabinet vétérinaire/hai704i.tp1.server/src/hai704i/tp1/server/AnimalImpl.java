package hai704i.tp1.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import hai704i.tp1.common.IAnimal;
import hai704i.tp1.common.IFollowUpFile;
import hai704i.tp1.common.Species;

public class AnimalImpl extends UnicastRemoteObject implements IAnimal {
	/* ATTRIBUTES */
	private String name;
	private String ownerName;
	private Species species;
	private String race;
	private IFollowUpFile followUpFile;
	
	/* CONSTRUCTORS */
	protected AnimalImpl() throws RemoteException {
		
	}
	
	protected AnimalImpl(String name, String ownerName, String speciesName, 
			int speciesAverageLife, String race, String state)
			throws RemoteException {
		this.name = name;
		this.ownerName = ownerName;
		this.species = new Species(speciesName, speciesAverageLife);
		this.race = race;
		this.followUpFile = new FollowUpFileImpl(state);
	}
	
	protected AnimalImpl(String name, String ownerName, Species species, String race,
			String state) throws RemoteException {
		this.name = name;
		this.ownerName = ownerName;
		this.species = species;
		this.race = race;
		this.followUpFile = new FollowUpFileImpl(state);
	}

	/* METHODS */
	@Override
	public String getName() throws RemoteException {
		return name;
	}

	@Override
	public String getOwnerName() throws RemoteException {
		return ownerName;
	}

	@Override
	public Species getSpecies() throws RemoteException {
		return species;
	}

	@Override
	public String getRace() throws RemoteException {
		return race;
	}
	
	@Override
	public IFollowUpFile getFollowUpFile() throws RemoteException {
		return followUpFile;
	}
	
	@Override
	public String getState() throws RemoteException {
		return followUpFile.getState();
	}
	
	@Override
	public void setState(String content) throws RemoteException {
		this.followUpFile.setState(content);
	}
}
