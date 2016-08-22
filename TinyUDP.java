/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tinyudp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.File;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class TinyUDP {

    private int port ;
    private String host;
    private int PacketSizeLimit;
    private int WindowSize;
    private InetAddress DestinationAddress;
    private DatagramSocket SockeT;
    private List<DatagramPacket> ToSendQueue;
    private List<DatagramPacket> ReceivedQueue;
    
    
    
    
    public TinyUDP()
    {
        this.port = 15001;
        this.host = "localhost";
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
    
    
    public void SetWindowSize(int Sizee)
    {
        this.WindowSize = Sizee;
    }
            
            
            
            
            
            
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
