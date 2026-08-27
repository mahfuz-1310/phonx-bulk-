package com.example.ui.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.model.FontOption

object FontHelper {

    fun getFontFamily(font: FontOption): FontFamily {
        return when (font) {
            FontOption.DEFAULT -> FontFamily.Default
            FontOption.PLAYFAIR_DISPLAY,
            FontOption.MERRIWEATHER,
            FontOption.CINZEL,
            FontOption.CORMORANT_GARAMOND,
            FontOption.LIBRE_BASKERVILLE,
            FontOption.PLAYFAIR_DISPLAY_SC,
            FontOption.CINZEL_DECORATIVE,
            FontOption.ROBOTO_SLAB,
            FontOption.ABRIL_FATFACE -> FontFamily.Serif

            FontOption.DANCING_SCRIPT,
            FontOption.PACIFICO,
            FontOption.LOBSTER,
            FontOption.PERMANENT_MARKER,
            FontOption.CAVEAT,
            FontOption.GREAT_VIBES -> FontFamily.Cursive

            FontOption.FIRA_CODE,
            FontOption.BUNGEE -> FontFamily.Monospace

            else -> FontFamily.SansSerif
        }
    }

    fun getFontWeight(font: FontOption): FontWeight {
        return when (font) {
            FontOption.OSWALD, FontOption.BEBAS_NEUE, FontOption.ANTON -> FontWeight.ExtraBold
            FontOption.PLAYFAIR_DISPLAY, FontOption.CINZEL, FontOption.ABRIL_FATFACE -> FontWeight.Bold
            FontOption.INTER, FontOption.MONTSERRAT, FontOption.SPACE_GROTESK, FontOption.PLUS_JAKARTA_SANS -> FontWeight.SemiBold
            FontOption.RALEWAY -> FontWeight.Light
            FontOption.DANCING_SCRIPT, FontOption.CAVEAT -> FontWeight.Normal
            else -> FontWeight.Medium
        }
    }

    fun getLetterSpacing(font: FontOption): TextUnit {
        return when (font) {
            FontOption.BEBAS_NEUE -> 2.0.sp
            FontOption.CINZEL, FontOption.CINZEL_DECORATIVE -> 1.5.sp
            FontOption.MONTSERRAT, FontOption.SPACE_GROTESK -> 1.0.sp
            FontOption.RALEWAY -> 1.2.sp
            FontOption.OSWALD -> 0.8.sp
            FontOption.BUNGEE -> 1.8.sp
            else -> 0.3.sp
        }
    }

    fun getFontStyle(font: FontOption): FontStyle {
        return when (font) {
            FontOption.DANCING_SCRIPT, FontOption.CAVEAT -> FontStyle.Italic
            else -> FontStyle.Normal
        }
    }
}
