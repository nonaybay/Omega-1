/*
 *  This file is part of Omega Launcher
 *  Copyright (c) 2021   Omega Launcher Team
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.saggitt.omega.theme.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.android.launcher3.R
import com.saggitt.omega.preferences.CustomDialogPreference
import com.saggitt.omega.settings.SettingsActivity
import com.saggitt.omega.util.getThemeAttr

class ThemeListDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_preference_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val content = requireArguments().getInt(KEY_CONTENT)
        val fragment = SettingsActivity.DialogSettingsFragment.newInstance("", content)
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_content, fragment)
            .commit()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireActivity(), requireActivity().getThemeAttr(R.attr.alertDialogTheme))
    }

    companion object {

        private const val KEY_THEME = "theme"
        private const val KEY_CONTENT = "content"

        fun newInstance(preference: CustomDialogPreference) = ThemeListDialogFragment().apply {
            arguments = Bundle(2).apply {
                putInt(KEY_THEME, preference.context.getThemeAttr(R.attr.alertDialogTheme))
                putInt(KEY_CONTENT, preference.content)
            }
        }
    }
}
