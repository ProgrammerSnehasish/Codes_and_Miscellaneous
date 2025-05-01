//C programme to implement RSA Algorithm(perfect working code).
#include<stdio.h>
#include<string.h>
#include<ctype.h>
int prime_1,prime_2;
int generate_publicKey();
int generate_factor(int,int);
int generate_privateKey(int,int,int);
int modExp(int,int,int);
int encryptAndDecryptMessage(const char *,int,int,int,int);
void convertToNumberedString(const char *, char *);
char convertToChar(int);
int isPrime(int num);
void getPairPrime(int *prime1, int *prime2);
int generate_privateKey(int private_key_generatingFactor,int k,int e){
	return (k*private_key_generatingFactor + 1)/e;
}
int generate_publicKey(){
	printf("You have to give prime pair numbers...\n");
    getPairPrime(&prime_1, &prime_2);
	return prime_1*prime_2;
}
int generate_factor(int prime_1,int prime_2){
	return (prime_1 - 1)*(prime_2 - 1);
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
int modExp(int base, int exp, int mod){
	int result=1;
	base=base%mod;
	while(exp>0)
	{
		if(exp%2==1)
			result=(result*base)%mod;
		exp=exp>>1;	
		base=(base*base)%mod;
	}
	return result;
}
int encryptAndDecryptMessage(const char *input,int e,int public_key,int private_key,int len){
	int encrypted[265],i;
	printf("The encrypted message is(in the number format):");
	for(i=0;i<len;i++)
	{
		encrypted[i]=modExp((int)input[i],e,public_key);
		printf("%d ",encrypted[i]);
	}
	printf("\n");
	int length = sizeof(encrypted) / sizeof(encrypted[0]);
    char result[length + 1];
	for (i = 0; i < length; i++) {
        result[i] = convertToChar(encrypted[i]);
    }
    result[length] = '\0';
    printf("The encrypted message is: %s\n",result);
	printf("Decrypted Message: ");
	for(i=0;i<len;i++)
	{
		char decryptedChar=(char)modExp(encrypted[i],private_key,public_key);
		printf("%c",decryptedChar);
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
int main(){
	int e,k,i;
    char input[100],output[200];
    printf("___Encrypt and Decrypt Your Message using RSA Algorithm___\n");
	int public_key = generate_publicKey();
	printf("The generated public key is: %d\n",public_key);
	int private_key_generatingFactor = generate_factor(prime_1,prime_2);
	printf("The private key generating factor is:%d\n",private_key_generatingFactor);
	do {
        printf("Enter a public exponent 'e' (must be an integer greater than 1 and less than %d):", private_key_generatingFactor);
        scanf("%d", &e);
        if (e <= 1 || e >= private_key_generatingFactor) {
            printf("Invalid value for 'e'. Please try again.\n");
        }
    } while (e <= 1 || e >= private_key_generatingFactor);
	printf("Enter any key for generating private key(must be any integer):");
	scanf("%d",&k);
	int private_key = generate_privateKey(private_key_generatingFactor,k,e);
	getchar();
	printf("The private key is: %d\n",private_key);
	printf("Enter a message to encrypt: ");
    	fgets(input, sizeof(input), stdin);
    	size_t len = strlen(input);
	if (len > 0 && input[len - 1] == '\n') {
		input[len - 1] = '\0';
	}
	convertToNumberedString(input, output);
	printf("Numbered string(given message): %s\n", output);
	encryptAndDecryptMessage(input,e,public_key,private_key,len);
}