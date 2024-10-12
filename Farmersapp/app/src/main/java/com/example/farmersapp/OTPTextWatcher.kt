package com.example.farmersapp

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class OTPTextWatcher(private val currentEditText: EditText, private val nextEditText: EditText?) :
    TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (s?.length == 1) {
            nextEditText?.requestFocus()
        }
    }

    override fun afterTextChanged(s: Editable?) {}
}
