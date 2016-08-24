/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filetcp;

import java.io.*;
import java.net.*;
import javax.swing.JFileChooser;

/**
 *
 * @author Ahmed
 */
public class Client {

    Socket socket = null;
    String host;
    InputStream in = null;
    OutputStream out = null;
    DataInputStream din = null;
    DataOutputStream dout = null;

    public Client(String host, int port) throws IOException {
        this.host = host;
        socket = new Socket(host, port);
        
            }

    public File fileChoose() {

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose A File To Upload To The Server");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        String path;

        chooser.showOpenDialog(null);
        path = chooser.getSelectedFile().getPath();
        File file = new File(path);
        return file;
    }

    public void initializeInOutStreams(File file) throws FileNotFoundException, IOException {
        in = new FileInputStream(file);
        out = socket.getOutputStream();
        din = new DataInputStream(socket.getInputStream());
        dout = new DataOutputStream(socket.getOutputStream());
    }

    public void sendFileName(File file) throws IOException {
        dout.writeUTF(file.getAbsoluteFile().getName());
        dout.flush();
    }

    public void sendFile(File file) throws IOException {
        
        byte[] bytes = new byte[16 * 1024];
        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }
    }

    public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
    }
//
//    public static void main(String[] args) throws IOException {
//        System.out.println("Client---\n");
//        
//        Client client = new Client("127.0.0.1",11223);
//        File file = client.fileChoose();
//        client.initializeInOutStreams(file);
//        client.sendFileName(file);
//        client.sendFile(file);
//        client.close();
//    }
}
