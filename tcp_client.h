#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>

#include <netinet/in.h>
 
	// socket creation
int create_socket()
{
	return socket(AF_INET, SOCK_STREAM, 0);
}
	// define the configuration
void setup_addr(sockaddr_in server_addr, int port)
{
	server_addr.sin_family = AF_INET;
	server_addr.sin_port = htons(port);
	server_addr.sin_addr.s_addr = INADDR_ANY;
}
	// client request connecting to the server
int connect_socket(int sock_id, sockaddr_in server_addr)
{
	return connect(sock_id, (struct sockaddr *)&server_addr, sizeof(server_addr));
}
	// client is sending to the server
int send_client(int sock_id, char request[])
{
	return send(sock_id, request, sizeof(request), 0);
}
	// client is recieving from server
int recv_client(int sock_id, char recv_buff[])
{
	return recv(sock_id, &recv_buff, sizeof(recv_buff), 0);
}
	// closing the socket
void close_socket(int sock_id)
{
	close(sock_id);
}




















