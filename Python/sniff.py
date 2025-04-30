#!/usr/bin/python3
# sniff.py

from scapy.all import *

def show_pkt(pkt):
    if IP in pkt and TCP in pkt:  # Ensure packet has IP and TCP layers
        # Print source/destination IP and port
        print(f"\n{pkt[IP].src}({pkt[TCP].sport}) -> {pkt[IP].dst}({pkt[TCP].dport}):", end="")

        # Print flags (SYN/ACK/FIN) and sequence/acknowledgment numbers
        print(f" {pkt[TCP].flags} seq={pkt[TCP].seq}, ack={pkt[TCP].ack}")

        # Print payload if it exists
        if Raw in pkt:
            print(pkt[Raw].load)

# Start sniffing for TCP packets on all ports
sniff(filter="tcp", prn=show_pkt, store=False)
