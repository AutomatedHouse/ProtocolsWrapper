/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filetcp;

import java.io.*;
import java.net.*;
import javax.swing.*;

/**
 *
 * @author Ahmed
 */
public class Server {

    ServerSocket serverSocket = null;
    DataInputStream din = null;
    DataOutputStream dout = null;
    Socket socket = null;
    InputStream in = null;
    OutputStream out = null;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        din = new DataInputStream(socket.getInputStream());
        dout = new DataOutputStream(socket.getOutputStream());
        in = socket.getInputStream();
    }

    public String folderChoose() {

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose A Folder To Save Your File");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        String path;

        chooser.showOpenDialog(null);
        path = chooser.getSelectedFile().getPath();
        return path;
    }

    public String retrieveFileName(String path) throws IOException {
        String filename = din.readUTF();
        out = new FileOutputStream(path + "\\Recieved " + filename);
        return filename;
    }

    public void writeFile() throws IOException {
        byte[] bytes = new byte[16 * 1024];
        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }
    }
//
//    public static void main(String[] args) throws IOException {
//        System.out.println("Server---\n");
//        Server server = new Server(11223);
//        server.retrieveFileName(server.folderChoose());
//        server.writeFile();
//        server.close();
//    }

    public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
        serverSocket.close();
    }
}
