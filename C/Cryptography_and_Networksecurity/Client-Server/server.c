//Server Code

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netinet/in.h>

#define MAX_CLIENTS 10
#define MAX_DATA 1024

void error(char *msg) {
    perror(msg);
    exit(1);
}

int main() {
    struct sockaddr_in server, client;
    int server_sock, client_sock;
    char buffer[MAX_DATA];

    // Create TCP socket
    if ((server_sock = socket(AF_INET, SOCK_STREAM, 0)) == -1)
        error("Can't open socket");

    // Prepare server structure
    server.sin_family = AF_INET;
    server.sin_port = htons(0);  // 0 = OS assigns a random available port
    server.sin_addr.s_addr = htonl(INADDR_ANY);

    // Bind the socket
    if (bind(server_sock, (struct sockaddr *)&server, sizeof(server)) == -1)
        error("Can't bind to socket");

    // Get assigned port
    socklen_t len = sizeof(server);
    if (getsockname(server_sock, (struct sockaddr *)&server, &len) == -1)
        error("getsockname failed");

    printf("TCP Server listening on random port %d\n", ntohs(server.sin_port));

    // Listen for incoming connections
    if (listen(server_sock, MAX_CLIENTS) == -1)
        error("Can't listen");

    while (1) {
        socklen_t client_size = sizeof(client);

        // Accept client connection
        if ((client_sock = accept(server_sock, (struct sockaddr *)&client, &client_size)) == -1)
            error("Can't accept");

        printf("\nNew client connected from %s:%d\n",
               inet_ntoa(client.sin_addr), ntohs(client.sin_port));

        while (1) {
            int recv_len = recv(client_sock, buffer, MAX_DATA, 0);
            if (recv_len <= 0) {
                printf("Client disconnected.\n");
                break;
            }

            buffer[recv_len] = '\0';  // Null-terminate the received message
            printf("Client: %s", buffer);

            // If the client sends "exit", close the connection
            if (!strncasecmp("exit", buffer, 4)) {
                printf("Client requested to disconnect.\n");
                break;
            }

            // Prompt the server to send a reply
            printf("Enter reply: ");
            fgets(buffer, MAX_DATA, stdin);

            // Send response to client
            send(client_sock, buffer, strlen(buffer), 0);
            printf("Sent message: %s\n", buffer);
        }

        close(client_sock);
    }

    close(server_sock);
    return 0;
}
