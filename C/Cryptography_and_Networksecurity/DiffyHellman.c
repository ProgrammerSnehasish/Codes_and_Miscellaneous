//Diffy-Hellman Key Exchange.
#include<stdio.h>
#include<stdlib.h>
int diffyHellmanKeyExchange(int,int,int,int,int,int);
int modularExponentiation(int,int,int); 
int modularExponentiation(int base, int exp, int mod) 
{
    int result = 1;
    base = base % mod;
    while (exp > 0){
        if (exp % 2 == 1) {
            result = (result * base) % mod;
        }
        exp = exp >> 1;
        base = (base * base) % mod;
    }
    return result;
}
int diffyHellmanKeyExchange(int P1_Pub1,int P1_Pub2,int P1_Pvt,int P2_Pub1,int P2_Pub2,int P2_Pvt){
	int newCombinationKey_P1 = modularExponentiation(P1_Pub2, P1_Pvt, P1_Pub1);
	printf("The new combination key of Person 1: %d\n",newCombinationKey_P1);
	int newCombinationKey_P2 = modularExponentiation(P2_Pub2, P2_Pvt, P2_Pub1);
	printf("The new combination key of Person 2: %d\n",newCombinationKey_P2);
	int excahngedKey_P1 = modularExponentiation(newCombinationKey_P2, P1_Pvt, P1_Pub1);
	printf("The new exchanged key of Person 1: %d\n",excahngedKey_P1);
	int excahngedKey_P2 = modularExponentiation(newCombinationKey_P1, P2_Pvt, P2_Pub1);
	printf("The new exchanged key of Person 2: %d\n",excahngedKey_P2);
	if(excahngedKey_P1 == excahngedKey_P2)
		printf("The key exchange between person 1 and person 2 is successful.\n");
	else
		printf("The key exchange between person 1 and person 2 is not successful.\n");
}
int main(){
	int P1_Pub1,P1_Pub2,P1_Pvt,P2_Pub1,P2_Pub2,P2_Pvt;
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
	diffyHellmanKeyExchange(P1_Pub1,P1_Pub2,P1_Pvt,P2_Pub1,P2_Pub2,P2_Pvt);
	return 0;
}
