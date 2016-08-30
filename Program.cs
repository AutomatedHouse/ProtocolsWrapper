using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ServiceModel;
using TransferService;
using System.ServiceModel.Activation;
using System.ServiceModel.Activities;
using System.ServiceModel.Administration;
using System.ServiceModel.Channels;
using System.ServiceModel.ComIntegration;
using System.ServiceModel.Configuration;
using System.ServiceModel.Description;
using System.ServiceModel.Diagnostics;
using System.ServiceModel.Discovery;
using System.ServiceModel.Web;
using System.ServiceModel.XamlIntegration;
using System.ServiceModel.Transactions;
using System.ServiceModel.Syndication;
using System.ServiceModel.Security;
using System.ServiceModel.Routing;
using System.ServiceModel.PeerResolvers;
using System.ServiceModel.DomainServices;


namespace TransferServiceHosting
{
    class Program
    {
        static void Main(string[] args)
        {

            try
            {
                // IntPtr hwnd;
                // hwnd = GetConsoleWindow();
                // ShowWindow(hwnd, SW_HIDE);

                using ( ServiceHost host = new ServiceHost(typeof(TransferService.TransferService)))
                {
                   host.Open();
                    Console.WriteLine("Client Started..");
                    Console.ReadKey();

                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
                Console.ReadKey();
            }




        }
    }
}
