#include<stdio.h>
#include"tcp_server.h"

int main(int argc, char *argv[])
{
	int sock_id , 
	bind_status , 
    listen_status , 
	accept_status ,
	conn_num = 5 ,
	client_sock;
	sockaddr_in server_address ,
	client_address;
	if((sock_id = create_socket()) == -1)
	{
		printf("socket creation failed !\n");
		exit(1);
	}
	setup_server_addr(server_address, 9002);
	if((bind_status = bind_socket(sock_id, server_address)) == -1)
	{
		printf("bind failed !\n");
		exit(1);
	}
	if((listen_status = listen_socket(sock_id, conn_num)) == -1)
	{
		printf("listen failed !\n");
		exit(0);
	}
	for( ; ; )
	{
		if((client_sock = accept_socket(sock_id, client_address)) == -1)
		{
			printf("accepting failed ! \n");
			exit(1);
		}
		char server_msg[] = "Test message form server";
		if((int sent_bytes = send_socket(client_sock, server_msg)) == -1)
		{
			printf("sending failed !\n");
			exit(1);
		}
		close_socket(client_sock);
		exit(0);
	}
	close_socket(sock_id);
	
	return 0;
}