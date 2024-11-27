package com.gopaddi.app.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel

class WhereToViewModel : ViewModel() {
    val searchValue = mutableStateOf(TextFieldValue(""))
}