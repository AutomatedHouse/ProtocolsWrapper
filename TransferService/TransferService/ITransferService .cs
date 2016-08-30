using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.IO;
using System.Text;

namespace TransferService
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface ITransferService
    {

        [OperationContract]
        RemoteFileInfo DownloadFile(DownloadRequest request);

        [OperationContract]
        void UploadFile(RemoteFileInfo request);
    }
}
