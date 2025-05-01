#include<stdio.h>
#include<ctype.h>

void substitution();
void transposition();

void substitution(){
	char text[500],ch;
	int key;
	printf("Enter a message to encrypt: ");
	scanf("%s",text);
	printf("Enter the key to encrypt: ");
	scanf("%d",&key);
	for(int i=0;text[i]!='\0',++i){
		ch = text[i];
		if(isalnum(ch)){
			if(islower(ch)){
				ch=(ch-'a'+key)%26+'a';
			}
			if(issuper(ch)){
				ch=(ch-'A'+key)%26+'A';
			}
			if(isdigit(ch)){
				ch=(ch-'0'+key)%10+'0';
			}
		}
		else{
			printf("Invalid Message");
		}
		text[i]=ch;
	}
	printf("Encrypted message: %s",text);
	printf("\n");
}

void transposition(){
	int l1,l2,i,j,d;
	printf("\nEnter the legth of the key: ");
	scanf("%d",&l1);
	int sequence[l1];
	printf("\nEnter the sequence key: ");
	for(i=0;i<l1;++i){
		scanf("%d",&sequence[i]);
	}
	int order[l1];
	for(i=1;i<l1;++i){
		for(j=0;j<l1;++j){
			if(sequence[j]==i){
				order[i-1]=j;
			}
		}
	}
	printf("\nEnter the depth: ");
	scanf("%d",&d);
	printf("Enter the length ofstring without spaces:");
	scanf("%d",&l2);
	int temp = check(l2,l1);
	int r=(l2+temp)/l1;
	char p[l2+temp1];
	char p1[r][1];
	if(temp1>0)
}
