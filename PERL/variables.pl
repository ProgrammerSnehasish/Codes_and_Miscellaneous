#!/usr/bin/perl
$name = "Snehasish Das"; #String Assignment.
$age = 20; #Integer Assignment.
$salary = 40000.50; #Float Assignment.

print "Name = $name\n";
print "Age = $age\n";
print "Salary = $salary\n";

@Names = ("Snehasish","Ankita","Soumili"); #Array Assignment(Sring Type)
@Ages = (20,19,19); #Array Assignment(Integer Type)

#To access each element.
print "Name : \$Names[0] = $Names[0]\n"; #escape sequence "\" will show the $ sign.
print "Age : \$Ages[0] = $Ages[0]\n";

#To acces whole array at a time.
print "Name : [@Names]\n";
print "Age : [@Ages]\n";

#Declaring/Assigning Hash.
%emp_data = ('Snehasish',20,'Soumili',19,'Ankita',18);

#To access each data by key.
print "\$data{'Snehasish'} = $emp_data{'Snehasish'}\n";

#Another type of assigning Hash using "my" keyword.
my %person = ("name" => "Snehasish", "age" => 20, "City" => "Kolkata");

#To access the whole Hash.
print "Employee Data:\n";
while(my ($key, $value) = each %person){
	print "$Key => $value\n";
}

#Notes
#To assign Scalar Type variables we use $.

# => format for String type: $variable_name = "data";
# => format for Integer and Float type: $variable_name = data;

#To assign Array Type variables we use @.

# => format for String type array: @array_name = ("data","data","data",...);
# => format for Other types array: @array_name = (data,data,data,...);

#To assign Basic Hash Type variables we use %.

# => format: %Hash_name = ('Key',data(other than string),'Key',data,'Key',data,...);

#To print some special symbols we use "\" esacape sequence and "\n" of next line.
#To add commnets we use "#" Symbol in the very beginning. 
