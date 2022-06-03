package hai704i.tp1.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import hai704i.tp1.common.ICabinet;

public class Server {
	/* ATTRIBUTES */
	private static final int RMI_REGISTRY_PORT = 1099;
	private static final String SECURITY_POLICY_PATH = "policies/server.policy";
	private static final String CODEBASE_PATH = "file:/home/depinfo/eclipse-workspace/hai704i.tp1.client/bin/codebase/";
	private Registry registry;
	private ICabinet cabinet;
	
	/* CONSTRUCTOR */
	public Server() {
		
	}
	
	/* METHODS */
	public void setUp() throws RemoteException {
		setUpSecurity();
		setUpCodeBase();
		setUpRegistry();
		setUpCabinet();
		System.err.println("Server ready");
	}
	
	public void setUpSecurity() {
		System.setProperty("java.security.policy", SECURITY_POLICY_PATH);
		System.setSecurityManager(new SecurityManager());
	}
	
	private void setUpCodeBase() {
		System.setProperty("java.rmi.server.codebase", CODEBASE_PATH);
	}
	
	private void setUpRegistry() throws RemoteException {
		registry = LocateRegistry.createRegistry(RMI_REGISTRY_PORT);
		if (registry == null)
			System.err.println("RMI registry not found at port " + RMI_REGISTRY_PORT);
	}
	
	private void setUpCabinet() throws RemoteException {
		cabinet = new CabinetImpl("Animal Care");
		cabinet.addPatient("Rex", "Paul", "Dog", 20, "Boxer", "In good shape");
		try {
			registry.bind(cabinet.getName(), cabinet);
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}