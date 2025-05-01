//C code for hash method encryption(MD5).
#include <stdio.h>
#include <openssl/md5.h>
#include <string.h>

void compute_md5(const unsigned char *message, unsigned char *digest) {
    MD5_CTX context;
    MD5_Init(&context);
    MD5_Update(&context, message, strlen((const char*)message));
    MD5_Final(digest, &context);
}

void print_md5(unsigned char *digest) {
    for (int i = 0; i < MD5_DIGEST_LENGTH; i++) {
        printf("%02x", digest[i]);
    }
    printf("\n");
}

int main() {
    char input[1024];
    unsigned char digest[MD5_DIGEST_LENGTH];

    printf("Enter message: ");
    fgets(input, sizeof(input), stdin);
    input[strcspn(input, "\n")] = '\0';  // Remove newline character

    compute_md5((unsigned char*)input, digest);
    
    printf("MD5 hash: ");
    print_md5(digest);

    return 0;

}
//INVALID CODE.
