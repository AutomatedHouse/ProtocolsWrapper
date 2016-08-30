using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Runtime.Serialization;
using System.ServiceModel;


namespace TransferService
{

   // [MessageContract]
    //[DataContract]
    [MessageContract]

   public  class DownloadRequest
    {
        [MessageBodyMember]
       // [DataMember]
        public string FileName;

    }
}
