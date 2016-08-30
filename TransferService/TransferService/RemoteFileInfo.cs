using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ServiceModel;
using System.Runtime.Serialization;

namespace TransferService
{
    //[DataContract]
    [MessageContract]
   public  class RemoteFileInfo : IDisposable

    {

        [MessageHeader(MustUnderstand = true)]
       // [DataMember]
        public string FileName;

        [MessageHeader(MustUnderstand = true)]
        //[DataMember]
        public long Length;

        [MessageBodyMember(Order = 1)]
        //[DataMember]
        public System.IO.Stream FileByteStream;

        public void Dispose()
        {
            if (FileByteStream != null)
            {
                FileByteStream.Close();
                FileByteStream = null;
            }
        }   



    }
}
