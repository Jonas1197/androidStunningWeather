package com.example.stunningweather.presentation.main_screen.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    hint: String = "Hint example",
    shouldClear: MutableState<Boolean>,
    valueTyped: ((String) -> Unit)? = null
) {

    var textState by remember {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        textState = ""
    }


    if(shouldClear.value) {
        print("\n~~> Cleared!!!")
        textState = ""
        shouldClear.value = false
    }

    Column(modifier) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = textState,
            colors = TextFieldDefaults.textFieldColors(textColor = Color.White),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            onValueChange = { textState = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )

        Text(
            hint,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.7f),
            color = Color.White,
            fontSize = 12.sp
        )

        valueTyped?.let { it(textState) }
    }
}