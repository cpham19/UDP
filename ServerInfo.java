package udp;

public class ServerInfo {
	public int serverId;
	public String serverIp;
	public int serverPort;
	
	
	public ServerInfo(int serverId, String serverIp, int serverPort) {
		this.serverId = serverId;
		this.serverIp = serverIp;
		this.serverPort = serverPort;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
}
