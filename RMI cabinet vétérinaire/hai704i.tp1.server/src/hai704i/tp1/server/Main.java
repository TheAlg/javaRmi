package hai704i.tp1.server;

import java.rmi.RemoteException;

public class Main {

	public static void main(String[] args) {
		Server server = new Server();
		try {
			server.setUp();
		} catch(RemoteException e) {
			e.printStackTrace();
		}
	}
}