use strict;
use warnings;
use EC::Plugin::Core;

my $text = q{
    [% task.id %]
};
print EC::Plugin::Core->render_template(text => $text, render_params => {task => {id => 1}});
