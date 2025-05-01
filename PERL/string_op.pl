#!/usr/bin/perl
$string1 = "Roses are";
$string2 = " Red";

#Concat of Strings.
$Concated_String = $string1.$string2;
print "\$Concated_String = $Concated_String.\n";
print "$string1$string2\n"; #Although Invalid Process.

$str = "z";
$str2 = "1.2P34";
$stringvar = "abc";
$stringvar_1 = "Snehasish";
$stringvar_2 = "25_Snehasish_2004";

#Multiplication of Strings.
print "Result 1 (\$stringvar_1 * 2)=>($stringvar_1 * 2): ";
print  ($stringvar_1 * 2);
print "\nResult 2 (\$stringvar_2 * 2)=>($stringvar_2 * 2): ";
print  ($stringvar_2 * 2);
print "\n";

#Increament Operations of String(++)[not working].
print "Increamented Result of \$stringvar => $stringvar: ";
print ++$stringvar; 
print "\nIncreamented Result of \$str => $str: ";
print ++$str;
print "\nIncreamented Result of \$str2 => $str2: ";
print ++$str2;

#Repeat Operation.
$Repeat = "S" x 5;
print "\nRepeated result of \$Repeat: ";
print $Repeat;

#Notes:
#For concatinating two string variables into a new string variable we use "." operator. 
# => format: $Concated_String = $String1.$String2;

#Multiplications of Strings(OPerator => *).
# => if there is no number in start of the variable it will show result 0.
# => if there is a number in beginning in the string it will show some output, To be noted that if there is some other numbers as well after characters it will be ignored.(See the above done example by running.")

#Increment Operation of Strings(Operator => ++).
# => To print the incremented string use "++" before the variable.
# To be noted that if there is a long string, only the last character will be incremented.
# To be noted that if there is alpha numeric string it will increase the numeric part untill the first occurence of the character.

#Repeat Operation of Strings(Operator => x)
# => To repeat a string we use the "x".
# => format: "String" x no_of_times_to_repeat;
