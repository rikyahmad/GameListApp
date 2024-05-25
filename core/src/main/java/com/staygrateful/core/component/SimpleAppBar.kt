package com.staygrateful.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.staygrateful.core.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    onActionClick: () -> Unit = {},
    onLeadingClick: () -> Unit = {},
    leadingIconSize: Dp = 49.dp,
    actionIconSize: Dp = 49.dp,
    paddingHorizontal: Dp = 10.dp,
    actionIcon: ImageVector? = null,
    leadingIcon: ImageVector? = null,
    fontSize: TextUnit = 15.sp,
    iconBackgroundColor: Color = Color.Transparent,
    fontWeight: FontWeight? = FontWeight.W700,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    actionIconColor: Color = MaterialTheme.colorScheme.onPrimary,
    leadingIconColor: Color = MaterialTheme.colorScheme.onPrimary,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    scrolledContainerColor: Color = Color.Unspecified,
    navigationIconContentColor: Color = Color.Unspecified,
    titleContentColor: Color = Color.Unspecified,
    actionIconContentColor: Color = Color.Unspecified,
) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = title,
                    textAlign = TextAlign.Start,
                    fontWeight = fontWeight,
                    fontSize = fontSize,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterStart)
                        .fillMaxWidth()
                        .padding(start = if (leadingIcon != null) 5.dp else paddingHorizontal),
                    color = textColor
                )
            }
        },
        navigationIcon = {
            if (leadingIcon != null) {
                Box(modifier = Modifier.fillMaxHeight()) {
                    IconButton(
                        modifier = Modifier
                            .align(alignment = Alignment.Center)
                            .padding(start = paddingHorizontal)
                            .size(leadingIconSize)
                            .background(iconBackgroundColor, shape = CircleShape),
                        onClick = onLeadingClick
                    ) {
                        Icon(
                            leadingIcon,
                            contentDescription = stringResource(R.string.desc_menu),
                            tint = leadingIconColor
                        )
                    }
                }
            }
        },
        actions = {
            if (actionIcon != null) {
                Box(modifier = Modifier.fillMaxHeight()) {
                    IconButton(
                        modifier = Modifier
                            .align(alignment = Alignment.Center)
                            .padding(end = paddingHorizontal)
                            .size(actionIconSize)
                            .background(iconBackgroundColor, shape = CircleShape),
                        onClick = onActionClick
                    ) {
                        Icon(
                            actionIcon,
                            contentDescription = stringResource(R.string.desc_action),
                            tint = actionIconColor
                        )
                    }
                }
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            scrolledContainerColor = scrolledContainerColor,
            navigationIconContentColor = navigationIconContentColor,
            titleContentColor = titleContentColor,
            actionIconContentColor = actionIconContentColor
        )
    )
}
