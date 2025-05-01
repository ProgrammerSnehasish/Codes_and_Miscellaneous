#!/usr/bin/perl

#Basic if else Condition.
$salary = 8000;
if($salary > 10000) {
 print "The employee is a Manager.\n";
}
elsif($salary <10000) {
 print "The employee is a Staff.\n";
}
else{
 print "The employee is a part time worker.\n";
}

#Unless statement.
$num = 22;
unless($num < 20) {
 print "The given number is greater than 20\n";
}

#Switch Statement.(not working due to absence of Switch Module)
#use Switch;
#$choice = 3;
#switch($choice) {
# case 1 {print "1"};
# case 2 {print "2"};
# case 3 {print "3"};
# else {print "Invalid Choice"};
#}

#Notes(CONTROL STATEMENTS):

# Sl.    Statements                       Control Statements & Descriptions
# =>1. next statement: Cause the loop to skip the remainder of its body and immediately retest its condition prior to reiterating.
# =>2. last statement: Terminates the loop statement and transfers execution to the statement immediately following the loop.
# =>3. redo statement: The redo command restrats the loop block without evaluating the conditional again. The continue block,if any,is not executed.
# =>4. goto statement: Perl supports a goto command with three forms: goto label, goto expr, and goto &name.
