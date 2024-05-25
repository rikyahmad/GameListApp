package com.staygrateful.core.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.staygrateful.core.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SearchInput(
    hint: String = "",
    height: Dp = 56.dp,
    border: Dp = 0.5.dp,
    elevation: Dp = 5.dp,
    color: Color = Color.White,
    iconColor: Color = Color.DarkGray,
    fontColor: Color = Color.DarkGray,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight? = FontWeight.Normal,
    isFocus: Boolean = false,
    readOnly: Boolean = false,
    enableClear: Boolean = false,
    leadingIconSize: Dp = 45.dp,
    leadingIcon: ImageVector = Icons.Default.Search,
    padding: PaddingValues = PaddingValues(horizontal = 5.dp),
    margin: PaddingValues = PaddingValues(0.dp),
    borderColor: Color = Color.LightGray.copy(0.5f),
    shape: Shape = RoundedCornerShape(15.dp),
    animatedVisibilityScope: AnimatedVisibilityScope,
    onLeadingClick: () -> Unit = {},
    onValueChange: (TextFieldValue) -> Unit = {},
    onSearch: (TextFieldValue) -> Unit = {},
    onFocusChange: (FocusState) -> Unit = {},
) {
    val searchText = rememberSaveable { mutableStateOf("") }
    val searchQuery = remember { mutableStateOf(TextFieldValue(searchText.value)) }
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Card(
        modifier = Modifier
            .padding(margin)
            .fillMaxWidth()
            .height(height)
            .sharedElement(
                state = rememberSharedContentState(key = stringResource(R.string.key_search)),
                animatedVisibilityScope = animatedVisibilityScope,
                boundsTransform = { _, _ ->
                    tween(durationMillis = 300)
                }
            ),
        shape = shape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        ),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {
        TextField(
            value = searchQuery.value,
            onValueChange = {
                searchText.value = it.text
                searchQuery.value = it
                onValueChange.invoke(it)
            },
            leadingIcon = {
                IconButton(
                    modifier = Modifier.size(leadingIconSize),
                    onClick = onLeadingClick
                ) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = stringResource(R.string.desc_search_icon),
                        tint = iconColor
                    )
                }
            },
            trailingIcon = {
                if(enableClear) {
                    Icon(
                        modifier = Modifier
                            .alpha(
                                if (searchQuery.value.text.isEmpty()) 0f else 1f
                            )
                            .size(40.dp)
                            .clip(CircleShape)
                            .clickable {
                                searchText.value = ""
                                searchQuery.value = TextFieldValue("")
                                onValueChange.invoke(TextFieldValue(""))
                            }
                            .padding(8.dp)
                            .background(Color.LightGray.copy(0.3f), CircleShape)
                            .padding(3.dp),
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(R.string.desc_search_clear),
                        tint = iconColor
                    )
                }
            },
            readOnly = readOnly,
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(
                fontWeight = fontWeight,
                fontSize = fontSize,
                color = fontColor
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus()
                    onSearch.invoke(searchQuery.value)
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            placeholder = {
                Text(
                    text = hint,
                    maxLines = 1,
                    style = TextStyle(
                        fontWeight = fontWeight,
                        fontSize = fontSize,
                        color = fontColor
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(padding)
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                    onFocusChange.invoke(focusState)
                },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = color,
                unfocusedContainerColor = color
            )
        )
    }

    LaunchedEffect(isFocus) {
        if (isFocus) {
            focusRequester.requestFocus()
        } else {
            focusRequester.freeFocus()
            focusManager.clearFocus()
        }
    }
}
