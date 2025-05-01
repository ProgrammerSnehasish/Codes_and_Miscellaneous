//C programme to implement the Diffy-Hellman Key Exchange,.
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <ctype.h>
#include <time.h>

int modExp(int,int,int);
int isPrime(int num); // Common Functions for RSA and ElGamal
void getPairPrime(int *prime1, int *prime2); // Common Functions for RSA and ElGamal

//Global Variables for RSA & ElGamal.
int prime_1,prime_2;

//Diffy-Hellman Functions
void process_diffyHellmanKeyExchange(int,int,int,int,int,int);
void diffyHellmanKeyExchange();

//RSA Functions
int generate_publicKey();
int generate_factor(int, int);
int generate_privateKey(int, int, int);
void convertToNumberedString(const char *, char *);
char convertToChar(int);
int encryptAndDecryptMessage(const char *, int, int, int, int);
void RSA();

//ElGamal Functions
void elgamal_keygen(int *, int *, int *, int *);
void elgamal_encrypt(int,int,int,const char *,int *,int *,int *);
void elgamal_decrypt(int,int,int *,int *,int);
void ElGamal();

int modExp(int base,int exp,int mod){
	int result = 1;
	base = base % mod;
	while(exp>0){
		if(exp%2 == 1){
			result = (result*base)%mod;
		}
		exp=exp>>1;
		base = (base*base)%mod;
	}
	return result;
}

int isPrime(int num) {
    int i;
    if (num <= 1) return 0;
    if (num <= 3) return 1;
    if (num % 2 == 0 || num % 3 == 0) return 0;
    for (i = 5; i * i <= num; i += 6) {
        if (num % i == 0 || num % (i + 2) == 0) return 0;
    }
    return 1;
}

void getPairPrime(int *prime1, int *prime2) {
    do {
        printf("Enter the first prime number: ");
        scanf("%d", prime1);
        if (!isPrime(*prime1)) {
            printf("The number entered is not a prime number. Please enter a valid prime number.\n");
        }
    } while (!isPrime(*prime1));

    do {
        printf("Enter the second prime number (must be a pair prime number): ");
        scanf("%d", prime2);
        if (!isPrime(*prime2)) {
            printf("The number entered is not a prime number. Please enter a valid prime number.\n");
        }
    } while (!isPrime(*prime2));
}

//Diffy-Hellman Key Exchange Function.
void process_diffyHellmanKeyExchange(int P1_Pub1,int P1_Pub2,int P1_Pvt,int P2_Pub1,int P2_Pub2,int P2_Pvt){
	int newCombinationKey_P1 = modExp(P1_Pub2,P1_Pvt,P1_Pub1);
	printf("The new combination key of Person 1: %d\n",newCombinationKey_P1);
	int newCombinationKey_P2 = modExp(P2_Pub2,P2_Pvt,P2_Pub1);
	printf("The new combination key of Person 2: %d\n",newCombinationKey_P2);
	int exchangedKey_P1 = modExp(newCombinationKey_P2,P1_Pvt,P1_Pub1);
	printf("The new combination key of Person 1: %d\n",exchangedKey_P1);
	int exchangedKey_P2 = modExp(newCombinationKey_P1,P2_Pvt,P2_Pub1);
	printf("The new combination key of Person 1: %d\n",exchangedKey_P2);
	if(exchangedKey_P1 == exchangedKey_P2)
		printf("The key exchange between person 1 and person 2 is successful.\n");
	else
		printf("The key exchange between person 1 and person 2 is not successful.\n");
}

void diffyHellmanKeyExchange(){
	int P1_Pub1,P1_Pub2,P1_Pvt,P2_Pub1,P2_Pub2,P2_Pvt;
	printf("You have chosen Diffy-Hellman Key Exchange...\n");
	printf("Fill the data for Person 1:\n");
	printf("Enter first public key for Person 1(should be same as person 2):");
	scanf("%d",&P1_Pub1);
	printf("Enter second public key for Person 1(should be same as person 2):");
	scanf("%d",&P1_Pub2);
	printf("Enter private key for Person 1:");
	scanf("%d",&P1_Pvt);
	printf("Fill the data for Person 2:\n");
	printf("Enter first public key for Person 2(should be same as person 1):");
	scanf("%d",&P2_Pub1);
	printf("Enter second public key for Person 2(should be same as person 1):");
	scanf("%d",&P2_Pub2);
	printf("Enter private key for Person 2:");
	scanf("%d",&P2_Pvt);
	printf("Checking if the key exchange is successful...\n");
	process_diffyHellmanKeyExchange(P1_Pub1,P1_Pub2,P1_Pvt,P2_Pub1,P2_Pub2,P2_Pvt);
}

//RSA Functions
int generate_publicKey() {
    printf("You have to give prime pair numbers...\n");
    getPairPrime(&prime_1, &prime_2);
    return prime_1 * prime_2;
}

int generate_factor(int prime_1, int prime_2) {
    return (prime_1 - 1) * (prime_2 - 1);
}

int generate_privateKey(int private_key_generatingFactor, int k, int e) {
    return (k * private_key_generatingFactor + 1) / e;
}

void convertToNumberedString(const char *input, char *output) {
    int i = 0;
    int j = 0;
    while (input[i] != '\0') {
        if (isalpha(input[i])) {
            char letter = tolower(input[i]);
            int position = letter - 'a' + 1;
            j += sprintf(output + j, "%d", position);
        }
        i++;
    }
    output[j] = '\0';
}

int encryptAndDecryptMessage(const char *input, int e, int public_key, int private_key, int len) {
    int encrypted[265], i;
    printf("The encrypted message is (in the number format): ");
    for (i = 0; i < len; i++) {
        encrypted[i] = modExp((int)input[i], e, public_key);
        printf("%d ", encrypted[i]);
    }
    printf("\n");
    char result[len + 1];
    for (i = 0; i < len; i++) {
        result[i] = convertToChar(encrypted[i]);
    }
    result[len] = '\0';
    printf("The encrypted message is: %s\n", result);
    printf("Decrypted Message: ");
    for (i = 0; i < len; i++) {
        char decryptedChar = (char)modExp(encrypted[i], private_key, public_key);
        printf("%c", decryptedChar);
    }
    printf("\n");
}

char convertToChar(int num) {
    int mod = num % 100;
    if (mod >= 1 && mod <= 26) {
        return 'A' + (mod - 1);
    } else if (mod >= 27 && mod <= 52) {
        return 'a' + (mod - 27);
    } else if (mod >= 53 && mod <= 62) {
        return '0' + (mod - 53);
    } else if (mod >= 63 && mod <= 78) {
        return 'i' + (mod - 63);
    }
    return '?';
}

void RSA(){
	printf("You have chosen RSA encryption...\n");
            int public_key = generate_publicKey();
            int private_key_generatingFactor = generate_factor(prime_1, prime_2);
            printf("The private key generating factor is: %d\n", private_key_generatingFactor);
            int e, k;
            do {
                printf("Enter a public exponent 'e' (must be an integer greater than 1 and less than %d): ", private_key_generatingFactor);
                scanf("%d", &e);
                if (e <= 1 || e >= private_key_generatingFactor) {
                    printf("Invalid value for 'e'. Please try again.\n");
                }
            } while (e <= 1 || e >= private_key_generatingFactor);

            printf("Enter any key for generating private key (must be any integer): ");
            scanf("%d", &k);
            int private_key = generate_privateKey(private_key_generatingFactor, k, e);
            printf("The private key is: %d\n", private_key);

            char input[100], output[200];
            printf("Enter a message to encrypt: ");
            getchar();  // Clear newline character from buffer
            fgets(input, sizeof(input), stdin);
            size_t len = strlen(input);
            if (len > 0 && input[len - 1] == '\n') {
                input[len - 1] = '\0';
            }
            convertToNumberedString(input, output);
            printf("Numbered string (given message): %s\n", output);
            encryptAndDecryptMessage(input, e, public_key, private_key, len);
}

//ElGamal Functions
//ElGamal key generation with user input
void elgamal_keygen(int *p, int *g, int *x, int *h) {
    printf("Enter a prime number 'p': ");
    scanf("%d", p);
    if (!isPrime(*p)) {
        printf("The number entered is not a prime number. Exiting...\n");
        exit(0);
    }

    printf("Enter the generator 'g': ");
    scanf("%d", g);
    
    printf("Enter your private key 'x': ");
    scanf("%d", x);
    
    *h = modExp(*g, *x, *p);  // Public key h = g^x mod p
    printf("ElGamal public key (h) is: %d\n", *h);
}

// ElGamal encryption for a string
void elgamal_encrypt(int p, int g, int h, const char *msg, int *c1, int *c2, int *len) {
    srand(time(0));
    int message_len = strlen(msg);
    *len = message_len; // Store length of the message
    printf("ElGamal encrypted message (c1, c2):\n");
    for (int i = 0; i < message_len; i++) {
        int y = rand() % (p - 1) + 1;  // Random value for each character
        c1[i] = modExp(g, y, p);  // c1 = g^y mod p
        c2[i] = (msg[i] * modExp(h, y, p)) % p;  // c2 = m * h^y mod p
        printf("(%d, %d) ", c1[i], c2[i]);
    }
    printf("\n");
}

// ElGamal decryption for a string
void elgamal_decrypt(int p, int x, int *c1, int *c2, int len) {
    printf("ElGamal decrypted message: ");
    for (int i = 0; i < len; i++) {
        int s = modExp(c1[i], x, p);  // s = c1^x mod p
        int m = (c2[i] * modExp(s, p - 2, p)) % p;  // m = c2 * s^-1 mod p
        printf("%c", (char)m); // Convert the decrypted integer to char
    }
    printf("\n");
}

void ElGamal(){
	printf("___Encrypt and Decrypt Your Message using ElGamal Algorithm___\n");
            int p, g, x, h;
            elgamal_keygen(&p, &g, &x, &h);  // User input for p, g, x, and h
            char input[100];
            printf("Enter a message to encrypt (ElGamal): ");
            scanf(" %[^\n]", input);  // Read string with spaces
            int c1[100], c2[100];  // Arrays to store the encrypted values
            int len;
            elgamal_encrypt(p, g, h, input, c1, c2, &len);
            elgamal_decrypt(p, x, c1, c2, len);
}

void main(){
	int choice;
	printf("List of System Security Technique:\n1. Diffy-Hellman Key Exchange.\n2. Encrypt/Decrypt using RSA.\n3. Encrypt/Decrypt using ElGamal.\n4.Exit\n");
	printf("Enter your choice: ");
	scanf("%d",&choice);
	while(1){
		switch(choice){
			case 1:
				diffyHellmanKeyExchange();
				break;
			case 2:
				RSA();
				break;
			case 3:
				ElGamal();
				break;
			case 4:
				printf("EXITING...");
				break;
			default:
				printf("Wrong choice entered.");
		}
	}
}
