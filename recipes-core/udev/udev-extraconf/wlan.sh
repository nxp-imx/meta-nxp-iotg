#!/bin/sh

# This script is designed to control the Marvel 802.11 SDIO card.  It could
# be used for any internal WiFi card.  It is designed to enable
# the wireless interface of this device during boot-up and bring-up the device.

# Need to get the actual index tracked by RFKILL.  The logic below is tightly
# coupled to the order/arrangement of the environment variables during this
# call.  If they change...this script breaks until a better solution can be
# determined which is not dependent on this order.
my_env=`env`
logger `echo $my_env | awk 'BEGIN{FS=" "}{print $1}'`
logger `echo $my_env | awk 'BEGIN{FS=" "}{print $2}'`
logger `echo $my_env | awk 'BEGIN{FS=" "}{print $3}'`
logger `echo $my_env | awk 'BEGIN{FS=" "}{print $4}'`
logger `echo $my_env | awk 'BEGIN{FS=" "}{print $5}'`
logger `echo $my_env | awk 'BEGIN{FS=" "}{print $6}'`
logger `echo $my_env | awk 'BEGIN{FS=" "}{print $7}'`
index=`echo $my_env | awk 'BEGIN{FS=" "}{print $4}' | awk 'BEGIN{FS="rfkill"}{print $2}'`
logger "rfkill index is $index"

# Default operation is to block the interface
state="block"

      logger "trying to do RFKILL logic"
if [ $SUBSYSTEM == "rfkill" ]; then

      logger "inside RFKILL logic"
   # Convert environment to proper commands
   if [ $ACTION == "add" ]; then
      logger "we are doing an add"
      state="unblock"

      # Block/Un-block the wireless interface
      logger "/usr/sbin/rfkill $state $index"
      /usr/sbin/rfkill $state $index
   fi
fi

exit 1
