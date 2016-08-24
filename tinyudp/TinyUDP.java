

package tinyudp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author root
 */
public class TinyUDP {
    // Class proberties
    private int port ;
    private String host;
    private int PacketSizeLimit; // Packet Size may be less than 64000
    private int WindowSize; // Queues Size
    private InetAddress DestinationAddress; // Destination Address Object
    private DatagramSocket SockeT; // Socket Object
    private ArrayList<DatagramPacket> ToSendQueue;   // Sending Queue
    private ArrayList<DatagramPacket> ReceivedQueue; // Receiving Queue
    
    
    
    
    public TinyUDP(int Uport , String UHost)
    {
        // Set Some default Values
        this.SetWindowSize(Uport);
        this.SetHost(UHost);
        this.PacketSizeLimit = 64000; // max size of UDP packet
        this.WindowSize = 10; // size of send and receive Queues
        
    }
    
    
    // set port number with limits of 2000(Reserved for system services and 2 power 16)
    public boolean SetPort(int Uport)
    {
        if(Uport < 65500 && Uport > 2000){
            
            this.port = Uport;
            return true;
        }else{
            return false;
        }
    }
    
    public int GetPort()
    {
        return this.port;
    }
    
    // Get the Host By its address if the address can't be found the exception will be caught then return false
    public boolean SetHost(String Uhost)
    {
        try {
            this.DestinationAddress = InetAddress.getByName(Uhost);
            this.host = Uhost;
            return true;
        } catch (UnknownHostException ex) {
            return false;
        }
    }
    
    public String GetHost()
    {
        return this.host;
    }
    
    
    // Get Complet Object of the Address
    public InetAddress GetHostAddressObj()
    {
        return this.DestinationAddress;
    }
    
    // Set Sending and Receiving Queues Size 
    public void SetWindowSize(int Sizee)
    {
        this.WindowSize = Sizee;
    }
            
            
    public int GetWindowSize()
    {
        return this.WindowSize;
        
    }
    
    
    // Set Data Size not larger than 64000 may be less
    public boolean SetPacketSizeLimit(int SizeLimit)
    {
        if(SizeLimit < 64000 && SizeLimit > 0)
        {
            this.PacketSizeLimit = SizeLimit;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    public int GetPacketSizeLimit()
    {
        return this.PacketSizeLimit;
    }
            
    
    // Make Socket Object After Setting required Values and Make Queues
    public boolean PrepareSocket()
    {
        try {
            this.SockeT = new DatagramSocket(this.GetPort());
            this.ReceivedQueue = new ArrayList<DatagramPacket>(this.GetWindowSize());
            this.ToSendQueue = new ArrayList<DatagramPacket>(this.GetWindowSize());
            return true;
            
        } catch (SocketException ex) {
            return false;
        }
        
    }
    
    //Make the Packet and Put it in the queue
    public boolean AppendPacket(byte[] PacketData)
    {
        if(PacketData.length < this.GetPacketSizeLimit())
        {
            DatagramPacket TempPacket = new DatagramPacket(PacketData, PacketData.length,this.GetHostAddressObj(), this.GetPort());
            this.ToSendQueue.add(TempPacket);
            return true;
            
        }else
        {
            return false;
        }
    }
    
    // Override : Make The packet and Send it to different Host As each packet is routed independently by the INFOs in its Header
    // And no Connection is needed to send 
    public boolean AppendPacket(byte[] PacketData , String Host)
    {
        try {
            InetAddress TempHost = InetAddress.getByName(Host);
             if(PacketData.length < this.GetPacketSizeLimit())
                {
                    DatagramPacket TempPacket = new DatagramPacket(PacketData, PacketData.length,TempHost, this.GetPort());
                    this.ToSendQueue.add(TempPacket);
                    return true;

                }else
                {
                    return false;
                }
        } catch (UnknownHostException ex) {
              
            return false;
        }
    }
    
    
    // Start Sending Packet In the Queue
    public void StartSending() throws IOException
    {
        for(DatagramPacket Temp : this.ToSendQueue)
        {
            this.SockeT.send(Temp);
            this.ToSendQueue.remove(Temp);
        }
        
    }
    
    
    // Receive Packets and Put them in the Queue
    public void ReceivePacket() throws IOException
    {
        
        for(int i = 0;i < this.GetWindowSize(); i++)
        {
            byte[] Buffer = new byte[this.GetPacketSizeLimit()];
            DatagramPacket Temp = new DatagramPacket(Buffer, Buffer.length);
            
            this.SockeT.receive(Temp);
            
            this.ReceivedQueue.add(Temp);
        }
    }
    
    
    // Get The Queue of Received Packets
    public ArrayList<DatagramPacket> GetReceivedPackets()
    {
        return this.ReceivedQueue;
    }
    
    // Clear the queue for more space , But you need to make sure extracting Received Packets First
    public void ClearReceivedQueue()
    {
        this.ReceivedQueue.clear();
    }
    
    
    // Get Socket Object for more specific operation
    public DatagramSocket GetSocket()
    {
        return this.SockeT;
    }
    
    // Close the socket and release port
    public void CloseUDPSocket()
    {
        this.SockeT.close();
    }
    
    
    //End Of UDP Wrapper Class;

}
