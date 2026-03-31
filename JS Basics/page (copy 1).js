class Fraction {
    constructor(nume, denome) {
        this.nume = nume;
        this.denome = denome;
    }

    display() {
        console.log("The fraction is:", this.nume, "/", this.denome);
    }

    summationOfFraction(f2) {
        const numeratorSum = (this.nume * f2.denome) + (f2.nume * this.denome);
        const denominatorSum = this.denome * f2.denome;
        const gcdValue = this.gcd(numeratorSum, denominatorSum);
        const simplifiedNumerator = numeratorSum / gcdValue;
        const simplifiedDenominator = denominatorSum / gcdValue;
        return new Fraction(simplifiedNumerator, simplifiedDenominator);
    }

    gcd(a, b) {
        return b === 0 ? a : this.gcd(b, a % b);
    }
}

let f1 = new Fraction(12, 34);
let f2 = new Fraction(34, 56);

f1.display();
f2.display();

let sum = f1.summationOfFraction(f2);
sum.display();
