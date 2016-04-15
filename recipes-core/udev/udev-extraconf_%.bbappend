FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " file://local.rules \
	file://blacklist.conf \
	file://bluetooth.sh \
	file://10-rfkill.rules \
	file://bt-device.rules \
	file://wlan-device.rules \
	file://wlan.sh "

do_install_prepend_mx6 () {

	install -d ${D}${sysconfdir}/udev/rules.d

	if [ -e "${WORKDIR}/local.rules" ]; then
		install -m 0644 ${WORKDIR}/local.rules ${D}${sysconfdir}/udev/rules.d
	fi
	
	if [ -e "${WORKDIR}/10-rfkill.rules" ]; then
		install -m 0644 ${WORKDIR}/10-rfkill.rules ${D}${sysconfdir}/udev/rules.d
	fi
	
	if [ -e "${WORKDIR}/bt-device.rules" ]; then
		install -m 0644 ${WORKDIR}/bt-device.rules ${D}${sysconfdir}/udev/rules.d
	fi
	
	if [ -e "${WORKDIR}/wlan-device.rules" ]; then
		install -m 0644 ${WORKDIR}/wlan-device.rules ${D}${sysconfdir}/udev/rules.d
	fi
	
	install -d ${D}${sysconfdir}/modprobe.d

	if [ -e "${WORKDIR}/blacklist.conf" ]; then
		install -m 0644 ${WORKDIR}/blacklist.conf ${D}${sysconfdir}/modprobe.d
	fi
	   
	install -d ${D}${sysconfdir}/udev/scripts/

	if [ -e "${WORKDIR}/bluetooth.sh" ]; then
		install -m 0755 ${WORKDIR}/bluetooth.sh ${D}${sysconfdir}/udev/scripts
	fi
	 
	if [ -e "${WORKDIR}/wlan.sh" ]; then
		install -m 0755 ${WORKDIR}/wlan.sh ${D}${sysconfdir}/udev/scripts
	fi
			
}

FILES_${PN}_append = " ${sysconfdir}/modprobe.d"

PACKAGE_ARCH_mx6 = "${MACHINE_ARCH}"
