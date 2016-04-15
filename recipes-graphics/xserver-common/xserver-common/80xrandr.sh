#!/bin/sh -x
#
# X can be in a strange state if xrandr doesn't first set the default
# resolution.
/usr/bin/xrandr -s 0
