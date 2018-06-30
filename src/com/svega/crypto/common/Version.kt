package com.svega.crypto.common

import com.svega.common.version.Extra
import com.svega.common.version.Version

class Version: Version(0, 2, 0,
        makeExtra(Extra.ALPHA, 0)){
    init {
        com.svega.common.version.Version.requires("com.svega.common", 0, 2)
    }
}