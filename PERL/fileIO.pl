#!/usr/bin/perl
use List::Util qw(min max);
@price;
open($fh, '<',"leaders.rpt");
while($line = <$fh>) {
 @data = split(' ',$line);
 if($data[-1] != 'price') {
  push(@price,$data[-1]); #Price value extractor.
  $name = $data[0];
  $leaders{$data[-1]} = $name;
 }
}
 print("@price\n");
 $maxprice = max @price;
 print "$maxprice\n";
 print ($leaders{$maxprice});

#Notes:

# => Modes of File opening: 1. Read Mode: '<', 2. Write Mode: '>'
