#!/usr/bin/perl

#Basic Example:(Passing Scalars)
sub Average {
 #get total numbers of arguments passed.
 $n = scalar(@_);
 $first_arg = $_[0];
 $second_arg = $_[1];
 $sum = 0;
 foreach $item(@_) {
  $sum += $item;
 }
 $average = $sum / $n;
 print "Average is: $average\n";
}

Average(10,20,30); #Calling Function Average.

#Example 2:(Passing Array)

sub PrintMyList {
 my @list = @_;
 print "Give List is\n";
 print ("@list");
}
$x = 10;
@y = (1,2,3,4);

PrintMyList($x, @y); #Calling Function PrintMyList.

#Notes:(Functions Or Subroutine in PERL)
#Basic Syntax:

#sub subroutine_name {
# Body
#}

#Calling a Function:

#subroutine_name(list of arguments);

#We don't specify the arguments while defining the subroutine.
#We use 'sub' keyword to define an Function or Subroutine.
#In function definition you don't have to specify the number of arguments to be passed.
#To call a function, we just have to write the function name and the arguments, this is very similar to how we call in C or C++.
#To access the arguments inside the function, use the special array "@_". This stores all the argument values passed to this function.
# => Let us consider the first argument to the function is in $_[0], the second is in $_[1], and so on. So while calling the function, whatever the arguments that have passed to this function, they all will be stored one by one in this array.

