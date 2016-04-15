#!/bin/sh

# This script is designed to control the CSR BT 4.0 USB dongle.  It could
# be called for any bluetooth USB dongle.  It is designed to enable
# the wireless interface of this device during boot-up and hotplug of the USB
# device and then bring-up the device.

# Need to get the actual index tracked by RFKILL.  The logic below is tightly
# coupled to the order/arrangement of the environment variables during this
# call.  If they change...this script breaks until a better solution can be
# determined which is not dependent on this order.
my_env=`env`
index=`echo $my_env | awk 'BEGIN{FS=" "}{print $4}' | awk 'BEGIN{FS="rfkill"}{print $2}'`

# Default operation is to block the interface
state="block"

if [ $SUBSYSTEM == "rfkill" ]; then

   # Convert environment to proper commands
   if [ $ACTION == "add" ]; then
      state="unblock"

      # Block/Un-block the wireless interface
      /usr/sbin/rfkill $state $index

      # Startup HCI if unblocked
      if [ $RFKILL_STATE == "1" ]; then
         /usr/bin/hciconfig $RFKILL_NAME up
      fi
   fi
fi

exit 1
