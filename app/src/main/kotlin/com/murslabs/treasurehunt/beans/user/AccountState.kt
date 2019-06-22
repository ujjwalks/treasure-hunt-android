package com.murslabs.treasurehunt.beans.user

import android.arch.persistence.room.Ignore

data class AccountState(
        val timestamp: Long,
        val current: String,
        val prev: String
) {
    @Ignore
    constructor() : this(1L, "", "")
}
