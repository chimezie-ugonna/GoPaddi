package com.gopaddi.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.gopaddi.app.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val Typography = Typography(
    labelSmall = TextStyle(
        fontFamily = FontFamily(
            Font(
                googleFont = GoogleFont("Satoshi"), fontProvider = provider
            )
        ), fontWeight = FontWeight.Normal, fontSize = 12.sp, fontStyle = FontStyle.Normal
    ), labelMedium = TextStyle(
        fontFamily = FontFamily(
            Font(
                googleFont = GoogleFont("Satoshi"), fontProvider = provider
            )
        ), fontWeight = FontWeight.Normal, fontSize = 14.sp, fontStyle = FontStyle.Normal
    ), labelLarge = TextStyle(
        fontFamily = FontFamily(
            Font(
                googleFont = GoogleFont("Satoshi"), fontProvider = provider
            )
        ), fontWeight = FontWeight.Normal, fontSize = 18.sp, fontStyle = FontStyle.Normal
    ), bodySmall = TextStyle(
        fontFamily = FontFamily(
            Font(
                googleFont = GoogleFont("Satoshi"), fontProvider = provider
            )
        ), fontWeight = FontWeight.Normal, fontSize = 16.sp, fontStyle = FontStyle.Normal
    )
)