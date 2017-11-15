package udp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Topography implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static int numberOfServers;
	public static int numberOfNeighbors;
	public static ArrayList<ServerInfo> servers;
	public static ArrayList<NeighborInfo> neighbors;
	
	
	public void printLineEntry() {
		System.out.println("Line Entry\n---------");
		System.out.println("Number of servers: " + numberOfServers);
		System.out.println("Number of neighbors: " + numberOfNeighbors);
		
		for (int i = 0; i < servers.size(); i++) {
			System.out.println(servers.get(i).getServerId() + " " + servers.get(i).getServerIp() + " " + servers.get(i).getServerPort());
		}
		
		for (int i = 0; i < neighbors.size(); i++) {
			System.out.println(neighbors.get(i).getServerId() + " " + neighbors.get(i).getNeighborId() + " " + neighbors.get(i).getCost());
		}	
	}	
	
	public static void writeTopographyToTxt() {
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter("topography.txt");
			bw = new BufferedWriter(fw);

			bw.write(numberOfServers + "");
			bw.newLine();
			bw.write(numberOfNeighbors + "");
			bw.newLine();
			
			for (int i = 0; i < servers.size(); i++) {
				bw.write(servers.get(i).getServerId() + " " + servers.get(i).getServerIp() + " " + servers.get(i).getServerPort());
				bw.newLine();
			}
			
			for (int i = 0; i < neighbors.size(); i++) {
				bw.write(neighbors.get(i).getServerId() + " " + neighbors.get(i).getNeighborId() + " " + neighbors.get(i).getCost());
				if (i != neighbors.size() - 1) {
					bw.newLine();
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} 
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public Topography() {
		this.numberOfServers = 4;
		this.numberOfNeighbors = 3;
		this.servers = new ArrayList<>();
		this.neighbors = new ArrayList<>();
		
		servers.add(new ServerInfo(1, "128.205.36.8", 4322));
		servers.add(new ServerInfo(2, "128.205.35.24", 4322));
		servers.add(new ServerInfo(3, "128.205.36.24", 4322));
		servers.add(new ServerInfo(4, "128.205.36.4", 4322));
		
		neighbors.add(new NeighborInfo(1, 2, 4));
		neighbors.add(new NeighborInfo(1, 3, 4));
		neighbors.add(new NeighborInfo(1, 4, 5));
		
		writeTopographyToTxt();
	}

	public int getNumberOfServers() {
		return numberOfServers;
	}

	public void setNumberOfServers(int numberOfServers) {
		this.numberOfServers = numberOfServers;
	}

	public int getNumberOfNeighbors() {
		return numberOfNeighbors;
	}

	public void setNumberOfNeighbors(int numberOfNeighbors) {
		this.numberOfNeighbors = numberOfNeighbors;
	}

	public ArrayList<ServerInfo> getServers() {
		return servers;
	}

	public void setServers(ArrayList<ServerInfo> servers) {
		this.servers = servers;
	}

	public ArrayList<NeighborInfo> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(ArrayList<NeighborInfo> neighbors) {
		this.neighbors = neighbors;
	}
}
