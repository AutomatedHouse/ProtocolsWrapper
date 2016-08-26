#include <stdio.h>
#include <stdlib.h>

#include <sys/socket.h>
#include <sys/types.h>

#include <netinet/in.h>

int create_socket()
{
	return socket(AF_INET, SOCK_STREAM, 0);
}
	// define server address	
void setup_server_addr(sockaddr_in server_addr, int port)
{
	server_addr.sin_family = AF_INET;
	server_addr.sin_port = htons(port);
	server_addr.sin_addr.s_addr = INADDR_ANY;
}
	// bind the socket to IP and Port
int bind_socket(int sock_id, sockaddr_in server_addr)
{
	return bind(sock_id, (struct sockaddr *)&server_addr, sizeof(server_addr));
}
		// number of connections that will be accepted
int listen_socket(int sock_id, int connection_num)
{
	return listen(sock_id, connection_num);
}
	// accepting the socket
int accept_socket(int sock_id, sockaddr_in client_addr)
{
	return accept(sock_id, (struct sockaddr *)&client_addr, sizeof(client_addr));
}
	// the server sending
int send_server(int client_sock, char server_msg[])
{
	return send(client_sock, server_msg, sizeof(server_msg), 0);
}
	// the server recieving	
int recv_server(int sock_id, char recv_buff[])
{
	return recv(sock_id, recv_buff, sizeof(recv_buff), 0);
}
		// closing the socket
void close_socket(int sock_id)
{
	close(sock_id);
}




















