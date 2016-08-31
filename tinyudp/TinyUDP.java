

package teset1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author root
 */
public class TinyUDP {
    // Class proberties
    private int port ;
    private String host;
    private int PacketSizeLimit;
    private int WindowSize;
    private InetAddress DestinationAddress;
    private DatagramSocket SockeT;
    private DatagramPacket ToSendPacket;
    private DatagramPacket ReceivedPacket;
    
    
    
    
    public TinyUDP(int Uport , String UHost)
    {
        // Set Some default Values
        this.SetPort(Uport);
        this.SetHost(UHost);
        this.PacketSizeLimit = 64000; // max size of UDP packet
        this.WindowSize = 1; // size of send and receive Queues
        
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
    
    
    // Get the host as string
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
            return true;
            
        } catch (SocketException ex) {
            return false;
        }
        
    }



    // prepare UDP packet and send it
    public void SendPacket(byte[] ToSendData) throws IOException
    {
        this.ToSendPacket = new DatagramPacket(ToSendData,ToSendData.length , this.GetHostAddressObj(),this.GetPort());
        this.SockeT.send(ToSendPacket);
        
    }

    // Receive Packets 
    public void ReceivePacket(byte[] ReceivedData) throws IOException
    {
            this.ReceivedPacket = new DatagramPacket(ReceivedData,ReceivedData.length);
            this.SockeT.receive(this.ReceivedPacket);
            
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
