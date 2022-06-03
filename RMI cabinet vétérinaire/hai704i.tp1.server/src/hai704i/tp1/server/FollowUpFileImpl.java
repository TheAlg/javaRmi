package hai704i.tp1.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import hai704i.tp1.common.IFollowUpFile;

public class FollowUpFileImpl extends UnicastRemoteObject implements IFollowUpFile {
	
	/* ATTRIBUTES */
	private String state;

	protected FollowUpFileImpl() throws RemoteException {
		
	}
	
	protected FollowUpFileImpl(String state) throws RemoteException {
		this.state = state;
	}

	@Override
	public String getState() throws RemoteException {
		return state;
	}

	@Override
	public void setState(String state) throws RemoteException {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return state;
	}
}
