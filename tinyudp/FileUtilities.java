
package tinyudp;

/**
 *
 * @author root
 */
import java.io.File;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class FileUtilities {
    // Class that encapsulates File Operations 
    private File InputFile ; // input File Object 
    private FileInputStream FileStream; // Stream used to read & write bytes from the file
    
    
    // Set the file and make input stream
    public FileUtilities(File YourFile) throws FileNotFoundException
    {
        if(this.SetFile(YourFile)){
            this.FileStream = new FileInputStream(this.GetFileObj());
        }
    }
    
    // set the file if it is a file and can be read under current permissions
    public boolean SetFile(File YourFile)
    {
        if(YourFile.isFile() && YourFile.canRead())
        {
            this.InputFile = YourFile;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    // return file object for more sepcific operations
    public File GetFileObj()
    {
        return this.InputFile;
    }
    
    // return file size
    //NOTE: Casting from Long To Int
    public int GetFileSize()
    {
        
        return (int)this.GetFileObj().length();
    }
    
    /* read bytes from the file
        Buffer size is limited by Integer.MAX_VALUE =  2147483647
        if the file is larger then it should be partioned
    */
    public byte[] ReadFileBytes()
    {
        try {
            
            byte[] Data = new byte[this.GetFileSize()];
            this.FileStream.read(Data);
            return Data;
        } catch (IOException ex) {
            return null;
        }
    }
    
    
    
    // method to get number of equal fixed size partions needed to hold the file
    //Partion size is datermined by the user
    private int CalculatePartionNumber(long FileSize , int PartSize)
    {
        int PartsNum = (int)(FileSize / PartSize);
        if(FileSize % PartSize > 0)
        {
            PartsNum ++ ;
        }
        
        return PartsNum;
    }
    
    // return file bytes in arraylist of byte buffer after it is partioned
    public ArrayList<byte[]> DoPartitioning(int partSize) throws IOException
    {
        int ArrayNumbers = this.CalculatePartionNumber(this.GetFileObj().length() , partSize);
        ArrayList<byte[]> Partitions = new ArrayList<byte[]>(ArrayNumbers);
        for(int i = 0; i < ArrayNumbers ; i++)
        {
            byte[] TempArray = new byte[partSize];
            this.FileStream.read(TempArray);
            Partitions.add(TempArray);
        }
        return Partitions;
        
    }
    
    
    //More  updates will Be Added
    //End Of The Class
}
