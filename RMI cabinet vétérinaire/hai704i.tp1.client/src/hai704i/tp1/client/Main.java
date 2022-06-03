package hai704i.tp1.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import hai704i.tp1.common.ICabinet;

public class Main {
	
	private static final String EXIT_CABINET = "6";

	public static void main(String[] args) {
		try {
			
			Client client = new Client();
			client.setUp();
			client.lookupCabinet("Animal Care");
			ICabinet cabinet = client.getCabinet();
			System.out.println("Objet proxy de l'objet cabinet: "+cabinet);
			browseCabinet(client, cabinet);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void browseCabinet(Client client, ICabinet cabinet) 
			throws IOException {
		System.out.println("Welcome to the " + cabinet.getName() + " Veterinary");
		String userInput = "";
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		do {
			cabinetMenu();			
			userInput = reader.readLine();
			processUserInput(client, cabinet, userInput, reader);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while(!userInput.equals(EXIT_CABINET));
		
		System.out.println("Exiting the " + cabinet.getName() + " Veterinary...");
		cabinet.unsubscribe(client);
		System.exit(0);
	}

	private static void cabinetMenu() {
		System.out.println();
		System.out.println("Choose an action:");
		System.out.println("1. Display animals currently being cared for.");
		System.out.println("2. Admit an animal.");
		System.out.println("3. Simulate admission of an animal whose species' "
				+ "implementation is unknown to the server.");
		System.out.println("4. Simulate admission of X number of animals.");
		System.out.println("5. Broadcast a message to the cabinet and connected clients.");
		System.out.println(EXIT_CABINET+". Exit.");
		System.out.println();
	}

	private static void processUserInput(Client client, ICabinet cabinet, 
			String userInput, BufferedReader reader) throws IOException {
		switch(userInput) {
			case "1":
				client.displayPatients();
				break;
			
			case "2":
				animalAdmission(client, reader);
				break;
			
			case "3":
				problematicAnimalAdmissionSimulation(client);
				break;
				
			case "4":
				animalAdmissionSimulation(client, reader);
				break;
			
			case "5":
				broadcast(cabinet, reader);
				break;
		}
	}

	private static void animalAdmission(Client client, BufferedReader reader)
			throws IOException {
		System.out.println("Please fill in the animal info for admission:");
		
		System.out.println("Name: ");
		String name = reader.readLine();
		
		System.out.println("Owner: ");
		String ownerName = reader.readLine();
		
		System.out.println("Species: ");
		String speciesName = reader.readLine();
		
		System.out.println("Average Life Span: ");
		String speciesAverageLife = reader.readLine();
		
		System.out.println("Race: ");
		String race = reader.readLine();
		
		System.out.println("State: ");
		String state = reader.readLine();
		
		client.submitPatient(name, ownerName, speciesName, 
				Integer.parseInt(speciesAverageLife), race, state);
	}

	private static void problematicAnimalAdmissionSimulation(Client client) 
			throws RemoteException {
		System.out.println("Simulating admission of Problematic Dog...");
		client.submitPatient("Problematic Dog", "John", new Dog(), "Terrier", 
				"Good shape");
		System.out.println("End of simulation");
		client.checkoutPatient("Problematic Dog");
	}

	private static void animalAdmissionSimulation(Client client, BufferedReader reader)
			throws NumberFormatException, IOException {
		System.out.println("Please choose the number of animals "
				+ "for which you wish to simulate admission: ");
		
		int numberOfPatients = Integer.parseInt(reader.readLine());
		
		System.out.println("Simulating admission of "+numberOfPatients+" patients...");
		for (int i=0; i<numberOfPatients; i++)
			client.submitPatient("Dog"+i, "Owner"+i, "Dog", 20, 
					"Bulldog"+i, "Good health");
		
		System.out.println("End of simulation");
		for (int i=0; i<numberOfPatients; i++)
			client.checkoutPatient("Dog"+i);
	}

	private static void broadcast(ICabinet cabinet, BufferedReader reader)
			throws IOException {
		System.out.println("Please enter the message you wish to broadcast:");
		cabinet.alert("Broadcast message: "+ reader.readLine());
	}
}
