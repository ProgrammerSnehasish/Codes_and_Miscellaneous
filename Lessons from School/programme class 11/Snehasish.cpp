#include<stdio.h>
int main()
{
	int n,i,c=0;
	printf("Enter a number");
	scanf("%d",&n);
	i=1;
	while(i<=n)
	{
		if(n%i==0)
		c=c+i;
		i++;
	}
	if (c==2)
	printf("prime");
	else
	printf("not prime");
}
