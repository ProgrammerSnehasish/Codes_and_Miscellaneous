#!/usr/bin/perl

#list of characters.
@list1 = (a,b,c,d);

#list of integers.
@list2 = (1,2,3,4);

#list of strings
@list3 = ("This", "is", "a", "List");

#LIST OPERATIONS.
@names = ("Snehasish", "Puskar", "Rounak", "Soumili", "Chinmay", "Amrik");
print "The list is: [@names]\n";

#Accessing elements by index number.
$list_element = $names[2];
print "Printing list element in the index number 2: $list_element\n";

#Accessing last element of the list.
$last_element = $names[-1];
print "Printing the last element of the list: $last_element\n";

#Slicing of list.
@sliced_list[0,1,2] = @names[1,3,5];
print "The new Sliced list is: [@sliced_list]\n";

#Printing integers upto range n.
@Int_list = (1..10);
print "Example 1(printing upto 10): ";
print ("@Int_list");
@Int_list2 = (2,5..9,11);
print "\nExample 2(Defining a part of the list): ";
print ("@Int_list2");

#Printing greatest integers within Floats upto range n.
@Float_list = (2.1..6.3);
print "\nPrinting greatest integers within float list form 2.1 to 6.3: ";
print ("@Float_list");

#Printing Character List.
@Char_list = (a..z);
print "\nPrinting characters from a to z(without space): ";
print (@Char_list);
print "\nPrinting characters from a to z(with space): ";
print ("@Char_list");

#Taking out values form the list name  without index.
@list = (aa..ad);
print "\nThe new list:";
print ("@list");
$var1, $var2 = @list;
print "\nElement: $var2";

#Sort List.
@ul = (9,2,8,4,1);
print "\nThe unsorted list is: @ul\n";
@as_ol = sort @ul;
print "The list after sorting(ascending order): @as_ol\n";
@des_ol = reverse sort @ul;
print "The list after sorting(descending order): @des_ol\n";
@ol_names = sort @names;
print "The list of names after sorting:@ol_names\n";

#Merge Elements.
$string = join("::","Words","and","colons");
print "The merged list:";
print ($string);
@line = ("Here","is","a");
$string1 = (" ", @line, "String");
print "\nThe new merged list with the string:";
print ($string1); #not working

#Spliting of the list.
@array = split(/::/, $string);
print "\nSplited String: ";
print ("@array");

#Notes:
#All Arrays are lists.
#List is a collection of scalars and can be odered and referenced by index values.
#All of the elements in the list is called "List elements".
#To access the last element of the list we use index number as "-1".
#To slice a list we should assign the index numbers to the new list index positions.
#To print number(Integer, Float or Character) list upto range n, we can use ".." the  the starting number to ending range. => format: @list = (starting_range..ending_range); 
#There will be different outputs for print (@list_name) and print ("@list_name'), the first one will not give white spaces but the the second one will.
#To sort the list we use the "sort" keyword.(Applicable for numbers and also Strings)[To sort the Strings it takes the precedence of Characters]{by default ascending order}.
#To sort the list in descending order we use "reverse" keyword before "sort" keyword.
#To Merge element in the list we use "join" keyword, => format: $string = join(array);
