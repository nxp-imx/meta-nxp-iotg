
inherit scons

DEPENDS += "openssl libxml2 "

CPU = "arm"
OS = "linux"
VARIANT = "debug"
BINDINGS = "c,cpp"

export CROSS_COMPILE = "${STAGING_BINDIR_TOOLCHAIN}/${TARGET_PREFIX}"
export CROSS_PREFIX = "${STAGING_BINDIR_TOOLCHAIN}/${TARGET_PREFIX}"

CROSS_CPPFLAGS_BASE  = "--sysroot=${STAGING_DIR_HOST} -march=armv7-a -mfloat-abi=hard -mfpu=neon"
CROSS_CPPFLAGS       = "${CROSS_CPPFLAGS_BASE} -mtune=cortex-a9"
CROSS_CPPFLAGS_mx6ul = "${CROSS_CPPFLAGS_BASE} -mtune=cortex-a7"

# AllJoyn cross-compilation is a bit of a mess. The 'Linux Cross-Compiling' section of this web page:
#
# https://wiki.allseenalliance.org/develop/building_and_running
#
# implies one mechanism, but not all the packages adhere to the mechanism.
EXTRA_OESCONS = " \
    V=10 \
    OS=${OS} \
	CPU=${CPU} \
	VARIANT=${VARIANT} \
	BINDINGS=${BINDINGS} \
	WS=off \
	SERVICES=about,notification,controlpanel,config,onboarding,sample_apps,lighting \
	'CROSS_CPPFLAGS=${CROSS_CPPFLAGS} -D__ARM_PCS_VFP -D_DEFAULT_SOURCE -O2 -pipe -g -feliminate-unused-debug-types -fPIC' \
	'CROSS_LINKFLAGS=${CROSS_CPPFLAGS}' \
	"

# Disable QA insanity checks that don't apply to this component
# ldflags: No GNU_HASH in the elf binary
INSANE_SKIP_${PN} += "ldflags"
