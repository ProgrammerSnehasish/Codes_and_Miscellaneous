// Client Code

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netinet/in.h>

#define MAX_DATA 1024
#define CLIENT_PORT 40000  // Fixed client port

void error(char *msg) {
    perror(msg);
    exit(1);
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        fprintf(stderr, "Usage: %s <Server IP> <Port>\n", argv[0]);
        exit(1);
    }

    int client_sock;
    struct sockaddr_in server, client;
    char buffer[MAX_DATA];

    // Create TCP socket
    if ((client_sock = socket(AF_INET, SOCK_STREAM, 0)) == -1)
        error("Can't create socket");

    // Bind client to a specific port
    memset(&client, 0, sizeof(client));
    client.sin_family = AF_INET;
    client.sin_port = htons(CLIENT_PORT);
    client.sin_addr.s_addr = htonl(INADDR_ANY);

    if (bind(client_sock, (struct sockaddr *)&client, sizeof(client)) == -1)
        error("Can't bind client to port 40000");

    // Setup server details
    server.sin_family = AF_INET;
    server.sin_port = htons(atoi(argv[2]));  // Server port from argument
    if (inet_pton(AF_INET, argv[1], &server.sin_addr) <= 0)
        error("Invalid address/Address not supported");

    // Connect to the server
    if (connect(client_sock, (struct sockaddr *)&server, sizeof(server)) == -1)
        error("Connection failed");

    printf("Connected to server %s on port %s from client port %d\n",
           argv[1], argv[2], CLIENT_PORT);

    while (1) {
        printf("Enter message: ");
        fgets(buffer, MAX_DATA, stdin);

        // Send the message to the server
        send(client_sock, buffer, strlen(buffer), 0);

        // If user types "exit", close the connection
        if (strncmp(buffer, "exit", 4) == 0) {
            printf("Disconnecting from server...\n");
            break;
        }

        // Receive response from the server
        int recv_len = recv(client_sock, buffer, MAX_DATA, 0);
        if (recv_len > 0) {
            buffer[recv_len] = '\0';  // Null-terminate the received message
            printf("Server: %s", buffer);
        }
    }

    close(client_sock);
    return 0;
}
