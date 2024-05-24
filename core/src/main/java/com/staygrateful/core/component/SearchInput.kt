package com.staygrateful.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchInput(
    text: String = "",
    hint: String = "",
    height: Dp = 56.dp,
    border: Dp = 0.5.dp,
    elevation: Dp = 5.dp,
    color: Color = Color.DarkGray,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight? = FontWeight.Normal,
    isFocus: Boolean = false,
    readOnly: Boolean = false,
    margin: PaddingValues = PaddingValues(0.dp),
    borderColor: Color = Color.LightGray.copy(0.5f),
    shape: Shape = RoundedCornerShape(15.dp),
    onValueChange: (TextFieldValue) -> Unit = {},
    onFocusChange: (FocusState) -> Unit = {},
) {
    val searchQuery = remember { mutableStateOf(TextFieldValue(text)) }
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier
            .padding(margin)
            .fillMaxWidth()
            .height(height)
            .border(border, borderColor, shape)
            .shadow(elevation, shape),
        shape = shape,
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.White)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray
            )
            TextField(
                value = searchQuery.value,
                onValueChange = {
                    searchQuery.value = it
                    onValueChange.invoke(it)
                },
                readOnly = readOnly,
                singleLine = true,
                maxLines = 1,
                textStyle = TextStyle(
                    fontWeight = fontWeight,
                    fontSize = fontSize,
                    color = color
                ),
                placeholder = {
                    Text(
                        text = hint,
                        maxLines = 1,
                        style = TextStyle(
                            fontWeight = fontWeight,
                            fontSize = fontSize,
                            color = color
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                        onFocusChange.invoke(focusState)
                    },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )
        }
    }

    LaunchedEffect(isFocus) {
        if(isFocus) {
            focusRequester.requestFocus()
        } else {
            focusRequester.freeFocus()
            focusManager.clearFocus()
        }
    }
}
