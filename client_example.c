#include<stdio.h>
#include"tcp_client.h"

int main(int argc, char *argv[])
{
	int client_sock_id ,
	connect_status ,
	recved_bytes;
	sockaddr_in server_addr;
	char recv_buff[1024];
	if((client_sock_id = create_socket()) == -1)
	{
		printf("socket creation failed !\n");
		exit(1);
	}
	setup_addr(server_addr, 9002);
	if((connect_status = connect_socket(client_sock_id, server_addr)) == -1)
	{
		printf("connection failed !\n");
		exit(1);
	}
	if((recved_bytes = recv_socket(client_sock_id, recv_buff)) == -1)
	{
		printf("recieving failed !\n");
		exit(1);
	}
	printf("message sent from server : %s",recv_buff);
	close_socket(client_sock_id);
	return 0;
}