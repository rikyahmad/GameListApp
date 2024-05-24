package com.staygrateful.core.component

import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.staygrateful.core.extension.toAndroidFontWeight
import com.staygrateful.core.extension.toAndroidGravity

@Composable
fun HtmlText(
    text: String,
    fontSize: TextUnit = TextUnit.Unspecified,
    color: Color = Color.Unspecified,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = TextAlign.Start
) {
    AndroidView(factory = { context ->
        TextView(context).apply {
            setText(HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY))
            if (fontSize != TextUnit.Unspecified) {
                textSize = fontSize.value
            }
            if (color != Color.Unspecified) {
                setTextColor(color.toArgb())
            }
            if (fontWeight != null) {
                setTypeface(typeface, fontWeight.toAndroidFontWeight())
            }
            if (textAlign != null) {
                gravity = textAlign.toAndroidGravity()
            }
        }
    })
}