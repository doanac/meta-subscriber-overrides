SUMMARY = "A tool for analayzing device status"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

DEPENDS = "boost curl glib-2.0 systemd"

SRCREV = "60d0b021460596acb98744b74b171ccfd53aaf99"

SRC_URI = "git://github.com/doanac/fioras;protocol=https;branch=main \
	file://fioras.service \
"

S = "${WORKDIR}/git"

inherit cmake systemd

SYSTEMD_PACKAGES += "${PN}"
SYSTEMD_SERVICE_${PN} = "fioras.service"

EXTRA_OECMAKE += "\
    -DGIT_COMMIT=${SRCREV} \
"

do_install_append() {
	install -d ${D}${systemd_system_unitdir}
	install -m 0644 ${WORKDIR}/fioras.service ${D}${systemd_system_unitdir}/
}

FILES_${PN} += " \
	${systemd_unitdir}/system/fioras.service \
"
