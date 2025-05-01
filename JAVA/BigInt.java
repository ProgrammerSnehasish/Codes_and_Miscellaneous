//Big Int
public class BigInt {
    private static final int MAX_SIZE = 20;
    private int[] digits;
    private int numSigDigits;
    private boolean overflow;
    public BigInt() {
        digits = new int[MAX_SIZE];
        numSigDigits = 1;
        overflow = false;
    }
    public BigInt(int[] arr) {
        if (arr == null || arr.length > MAX_SIZE) {
            throw new IllegalArgumentException("Array size exceeds maximum limit or is in null state.");
        }
        digits = new int[MAX_SIZE];
        int index = MAX_SIZE - arr.length;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0 || arr[i] > 9) {
                throw new IllegalArgumentException("Invalid digit in array. Digits should be in (0-9).");
            }
            digits[index + i] = arr[i];
        }
        numSigDigits = arr.length;
    }
    public int getNumSigDigits() {
        return numSigDigits;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;
        for (int i = 0; i < MAX_SIZE; i++) {
            if (leadingZero && digits[i] == 0) {
                continue;
            }
            leadingZero = false;
            sb.append(digits[i]);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
    public BigInt add(BigInt otherarr) {
        int[] result = new int[MAX_SIZE];
        int carry = 0;
        for (int i = MAX_SIZE - 1; i >= 0; i--) {
            int sum = this.digits[i] + otherarr.digits[i] + carry;
            result[i] = sum % 10;
            carry = sum / 10;
        }
        if (carry > 0) {
            throw new ArithmeticException("Overflow occurred during addition.");
        }
        return new BigInt(result);
    }
    public int compareTo(BigInt otherarr) {
        if (this.numSigDigits > otherarr.numSigDigits) {
            return 1;
        } else if (this.numSigDigits < otherarr.numSigDigits) {
            return -1;
        }
        for (int i = 0; i < MAX_SIZE; i++) {
            if (this.digits[i] > otherarr.digits[i]) {
                return 1;
            } else if (this.digits[i] < otherarr.digits[i]) {
                return -1;
            }
        }
        return 0;
    }
}
