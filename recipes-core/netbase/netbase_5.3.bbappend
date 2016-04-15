# The hostname of the image is set to the MACHINE. Some Java code looking for the localhost
# uses the hostname. The lookup of the MACHINE name does then sometimes fail
# with MalformedUrlException and UnknownHostException. We fix this by adding MACHINE
# to the localhost definition in /etc/hosts
do_install_append() {
	sed --in-place "/^127\.0\.0\.1/s/\$/\     ${MACHINE}/" ${D}${sysconfdir}/hosts
}
