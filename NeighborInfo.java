package udp;

public class NeighborInfo {
	public int serverId;
	public int neighborId;
	public int cost;
	
	public NeighborInfo() {
		
	}
	
	public NeighborInfo(int serverId, int neighborId, int cost) {
		this.serverId = serverId;
		this.neighborId = neighborId;
		this.cost = cost;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public int getNeighborId() {
		return neighborId;
	}

	public void setNeighborId(int neighborId) {
		this.neighborId = neighborId;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}	
}
