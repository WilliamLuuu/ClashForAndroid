package com.github.kr328.clash.design.settings

import android.content.Context
import android.os.Bundle
import android.view.View

abstract class Base(val screen: SettingsScreen) {
    val context: Context
        get() = screen.layout.context

    var id: String? = null

    var dependOn: Base? = null

    var isEnabled: Boolean = true
        get() = field && (dependOn?.isEnabled ?: true)
        set(value) {
            field = value
            screen.postReapplyAttribute()
        }
    var isHidden: Boolean = false
        get() = field || (dependOn?.isHidden ?: false)
        set(value) {
            field = value
            screen.postReapplyAttribute()
        }

    fun reapplyAttribute() {
        applyAttribute(isEnabled, isHidden)
    }

    abstract val view: View
    abstract fun saveState(bundle: Bundle)
    abstract fun restoreState(bundle: Bundle)
    protected abstract fun applyAttribute(enabled: Boolean, hidden: Boolean)
}