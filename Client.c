#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>

#define PORT 4444

int main(){
	
	int clientSocket, ret;
	struct sockaddr_in serverAddr;
	char buffer[1201];

	clientSocket = socket(PF_INET, SOCK_STREAM, 0);
	if (clientSocket < 0){
		printf("[+]Error in connection.\n");
		exit(1);
	}
	printf("[+]Client Socket Created Sucessfully.\n");

	memset(&serverAddr, '\0', sizeof(serverAddr));
	serverAddr.sin_family = AF_INET;
	serverAddr.sin_port = htons(PORT);
	serverAddr.sin_addr.s_addr = inet_addr("127.0.0.1");

	ret = connect(clientSocket, (struct sockaddr*)&serverAddr, sizeof(serverAddr));
	if(ret < 0){
		printf("[+]Error in connection.\n");
		exit(1);
	}

	recv(clientSocket, buffer, 1201, 0);
	printf("[+]Data Recv: %s\n", buffer);
	
	return 0;
	

}
