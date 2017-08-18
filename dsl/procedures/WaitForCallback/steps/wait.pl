use strict;
use warnings;
use ElectricCommander;


my $ec = ElectricCommander->new;

my $property = '$[propertyName]';
my $timeout = '$[procedureTimeout]';


my $time = 0;
my $delay = 10;

my $plugin_name = '@PLUGIN_NAME@';

print "Using plugin $plugin_name\n";
print "Waiting for property $property to be set\n";

while($time < $timeout * 60 ) {
    my $value = eval { $ec->getProperty($property)->findvalue('//value')->string_value };
    if ($value) {
        if ($value eq 'success') {
            $ec->setProperty('/myJobStep/summary', "Callback successully finished");
            print "Callback finished with success\n";
            exit 0;
        }
        else {
            $ec->setProperty('/myJobStep/summary', "Callback finished with status $value");
            print "Callback finished with status $value\n";
            exit -1;
        }
    }
    else {
        print "No value yet\n";
    }
    sleep $delay;
    $time += $delay;
}

$ec->setProperty('/myJobStep/summary', "Polling has timed out");
exit -1;
