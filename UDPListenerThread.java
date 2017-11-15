package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class UDPListenerThread extends Thread 
{

    private DatagramSocket socket = null;
    private int port;
    private boolean listening = true;

    public UDPListenerThread(int port) throws SocketException
    {
        super("UDPListenerThread");
        this.port = port;
        this.socket = new DatagramSocket(port);
    }

    @Override
    public void run() 
    {
        //2. Wait for an incoming data
        System.out.println("Server socket " + this.port + " has been created. Waiting for data.");
    	
        //buffer to receive incoming data
        byte[] buffer = new byte[65536];
        DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
        
        //communication loop
        while(listening)
        {
            try 
            {    
				socket.receive(incoming);
				
	            byte[] data = incoming.getData();
	            ByteArrayInputStream in = new ByteArrayInputStream(data);
	            ObjectInputStream is = new ObjectInputStream(in);
	            
	            Topography tg = (Topography) is.readObject();
	           
	            //Echo the details of incoming data
	            System.out.println("Response from " + incoming.getAddress().getHostAddress() + ": " + tg);
			} 
            catch (IOException | ClassNotFoundException e) 
            {
				e.printStackTrace();
				listening = false;
				socket.close();
			}
        }
      
    }

    private class ServerInfo {
        Integer connectionId = -1;
        String ipAddress;
        Integer port = -1;
    }
}
