#!/usr/bin/perl

#While Loop.
print "After Executing While Loop.\n";
$a = 10;
while($a < 20) {
 print("Value of a: $a\n");
 $a = $a + 1;
}

#For Loop.
#Example 1:
print "After Executing For Loop.\n";
for($num = 1; $num < 20; $num = $num + 1) {
 print "value of a: $a\n";
}

#Exmaple 2:
print "Printing names form the \@name array:\n";
@names = ("Raghu","Kabita","Ankit","Vijay","Pradeep","Ravan");
$size = @names;
for($i = 0;$i < $size;$i = $i + 1) {
 print "$names[$i] ";
}

#Until Loop.
print "After executing Until Loop.\n";
$b = 5;
until($b > 10) {
 print "Value of b: $b\n";
 $b = $b + 1;
}

#Foreach Loop.
print "After executing Foreach loop.\n";
foreach $employee (@names) {
 print "name of employee: $employee\n";
}

#Notes:

# =>1. While Loop: It repeatedly executes a target statement as long as a given condition is true.
# =>2. For Loop: Executes a sequence of statements multiple times and abbreviates the code that manages the loop variables.
# =>3. Until Loop: Repeatedly executes a target statement as long as a given condition is false.
# =>4. Foreach Loop: The foreach loop iterates over array by referencing the value directly instead of referencing the index of array.
# Diff Between For and Foreach loop:
# Foreach loop is nothing but, a loop that accesses the array value itself, instead of it's index. Whereas a normal for loop, we have to access the value of the array through it's index.
