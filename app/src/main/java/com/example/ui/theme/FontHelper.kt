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
            FontOption.INTER -> FontFamily.SansSerif
            FontOption.ROBOTO -> FontFamily.SansSerif
            FontOption.POPPINS -> FontFamily.SansSerif
            FontOption.MONTSERRAT -> FontFamily.SansSerif
            FontOption.NUNITO -> FontFamily.SansSerif
            FontOption.LATO -> FontFamily.SansSerif
            FontOption.OPEN_SANS -> FontFamily.SansSerif
            FontOption.OSWALD -> FontFamily.SansSerif
            FontOption.PLAYFAIR_DISPLAY -> FontFamily.Serif
            FontOption.MERRIWEATHER -> FontFamily.Serif
            FontOption.RALEWAY -> FontFamily.SansSerif
            FontOption.QUICKSAND -> FontFamily.SansSerif
            FontOption.BEBAS_NEUE -> FontFamily.SansSerif
            FontOption.CINZEL -> FontFamily.Serif
            FontOption.DANCING_SCRIPT -> FontFamily.Cursive
        }
    }

    fun getFontWeight(font: FontOption): FontWeight {
        return when (font) {
            FontOption.OSWALD, FontOption.BEBAS_NEUE -> FontWeight.ExtraBold
            FontOption.PLAYFAIR_DISPLAY, FontOption.CINZEL -> FontWeight.Bold
            FontOption.INTER, FontOption.MONTSERRAT -> FontWeight.SemiBold
            FontOption.RALEWAY -> FontWeight.Light
            FontOption.DANCING_SCRIPT -> FontWeight.Normal
            else -> FontWeight.Medium
        }
    }

    fun getLetterSpacing(font: FontOption): TextUnit {
        return when (font) {
            FontOption.BEBAS_NEUE -> 2.0.sp
            FontOption.CINZEL -> 1.5.sp
            FontOption.MONTSERRAT -> 1.0.sp
            FontOption.RALEWAY -> 1.2.sp
            FontOption.OSWALD -> 0.8.sp
            FontOption.DANCING_SCRIPT -> 0.sp
            else -> 0.3.sp
        }
    }

    fun getFontStyle(font: FontOption): FontStyle {
        return when (font) {
            FontOption.DANCING_SCRIPT -> FontStyle.Italic
            FontOption.PLAYFAIR_DISPLAY -> FontStyle.Normal
            else -> FontStyle.Normal
        }
    }
}
