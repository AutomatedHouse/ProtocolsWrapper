using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TransferService;
using WebApplication13.Serviceper;
using System.IO;

namespace WebApplication13
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
          if (FileUpload1.HasFile)
            {
               FileInfo fileInfo =
                       new  FileInfo(FileUpload1.PostedFile.FileName);
                // RemoteFileInfo request =new RemoteFileInfo();
                TransferServiceClient clientUpload = new TransferServiceClient();

                TransferServiceClient gty = new TransferServiceClient();
                Serviceper.RemoteFileInfo  uploadRequestInfo = new Serviceper.RemoteFileInfo();

                FileStream stream =new FileStream(@"C:\Users\mohamed\Documents\Visual Studio 2013\Projects\WebApplication13\WebApplication13\"+ FileUpload1.PostedFile.FileName,
                    FileMode.Open, FileAccess.Read);
                       
                
                  
                    string ac;
                    ac = FileUpload1.PostedFile.FileName;
                    int z;
                    z = (int)stream.Length;
                    lblresult.Text = z.ToString();

                    uploadRequestInfo.FileName = FileUpload1.FileName;
                        /* uploadRequestInfo.Length = FileUpload1.FileBytes.Length;
                         uploadRequestInfo.FileByteStream = stream;*/

                    gty.UploadFile(FileUpload1.FileName, stream.Length/*FileUpload1.FileBytes.Length */, stream);

                   
                    //clientUpload.UploadFile(stream);
                }
        }

          /*  if (FileUpload1.HasFile)
            {
               FileInfo fileInfo =
                       new  FileInfo(FileUpload1.PostedFile.FileName);

                 

                Serviceper.RemoteFileInfo uploadRequestInfo = new Serviceper.RemoteFileInfo();

               TransferServiceClient cldr = new  TransferServiceClient ();


                FileStream s = new FileStream(@"C:\Users\mohamed\Documents\Visual Studio 2013\Projects\WebApplication13\WebApplication13\"+ FileUpload1.PostedFile.FileName, FileMode.Open, FileAccess.Read);


               /* uploadRequestInfo.FileName = FileUpload1.FileName;
                uploadRequestInfo.Length = fileInfo.Length;
                uploadRequestInfo.FileByteStream = s;*/

               /* TransferServiceClient a = new TransferServiceClient();
                a.UploadFile(FileUpload1.FileName ,fileInfo.Length,s );*/


           
               
             

               
               
                    
                       
                    

            }

            }
        
    
