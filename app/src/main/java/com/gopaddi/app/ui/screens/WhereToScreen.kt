package com.gopaddi.app.ui.screens

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gopaddi.app.R
import com.gopaddi.app.data.SearchData
import com.gopaddi.app.ui.components.CustomTextField
import com.gopaddi.app.ui.components.CustomTopBar
import com.gopaddi.app.viewModel.WhereToViewModel

@Composable
fun WhereToScreen(navController: NavController, viewModel: WhereToViewModel) {
    Column {
        CustomTopBar(
            leadingIconResource = R.drawable.x,
            leadingIconOnClick = {
                navController.popBackStack()
            },
            leadingIconContentDescription = R.string.close_screen_icon,
            textResource = R.string.where
        )

        val cities = listOf(
            SearchData(
                "Paris, France",
                "Charles de Gaulle Airport",
                "FR",
                R.drawable.fr,
                "https://images.unsplash.com/photo-1522093007474-d86e9bf7ba6f?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8cGFyaXN8ZW58MHx8MHx8fDA%3D"
            ), SearchData(
                "New York City, United States",
                "John F. Kennedy International Airport",
                "US",
                R.drawable.us,
                "https://images.unsplash.com/photo-1602940659805-770d1b3b9911?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTF8fE5ldyUyMFlvcmslMjBDaXR5fGVufDB8fDB8fHww"
            ), SearchData(
                "Venice, Italy",
                "Venice Marco Polo Airport",
                "IT",
                R.drawable.it,
                "https://images.unsplash.com/photo-1574530638414-88578d1f73a2?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTJ8fFZlbmljZSUyQyUyMEl0YWx5fGVufDB8fDB8fHww"
            ), SearchData(
                "Tokyo, Japan",
                "Narita International Airport",
                "JP",
                R.drawable.jp,
                "https://plus.unsplash.com/premium_photo-1715783495625-1da3a04fd8f6?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8VG9reW8lMkMlMjBKYXBhbnxlbnwwfHwwfHx8MA%3D%3D"
            ), SearchData(
                "Cape Town, South Africa",
                "Cape Town International Airport",
                "ZA",
                R.drawable.za,
                "https://images.unsplash.com/photo-1647244080521-bf2231c0c87f?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Q2FwZSUyMFRvd24lMkMlMjBTb3V0aCUyMEFmcmljYXxlbnwwfHwwfHx8MA%3D%3D"
            ), SearchData(
                "Sydney, Australia",
                "Sydney Kingsford Smith Airport",
                "AU",
                R.drawable.au,
                "https://images.unsplash.com/photo-1711525699610-05b24a446495?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8U3lkbmV5JTJDJTIwQXVzdHJhbGlhfGVufDB8fDB8fHww"
            ), SearchData(
                "Lagos, Nigeria",
                "Murtala Muhammed International Airport",
                "NG",
                R.drawable.ng,
                "https://images.unsplash.com/photo-1648023199223-25d3622bcb13?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTZ8fExhZ29zJTJDJTIwTmlnZXJpYXxlbnwwfHwwfHx8MA%3D%3D"
            ), SearchData(
                "Abuja, Nigeria",
                "Nnamdi Azikiwe International Airport",
                "NG",
                R.drawable.ng,
                "https://images.unsplash.com/photo-1696744999160-02e02ab8754a?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTJ8fEFidWphJTJDJTIwTmlnZXJpYXxlbnwwfHwwfHx8MA%3D%3D"
            ), SearchData(
                "Port Harcourt, Nigeria",
                "Port Harcourt International Airport",
                "NG",
                R.drawable.ng,
                "https://images.unsplash.com/photo-1555686367-56d5186965d5?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8UG9ydCUyMEhhcmNvdXJ0JTJDJTIwTmlnZXJpYXxlbnwwfHwwfHx8MA%3D%3D"
            ), SearchData(
                "Calabar, Nigeria",
                "Margaret Ekpo International Airport",
                "NG",
                R.drawable.ng,
                "https://images.unsplash.com/photo-1703883374092-3e0b71b31139?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjZ8fENhbGFiYXIlMkMlMjBOaWdlcmlhfGVufDB8fDB8fHww"
            ), SearchData(
                "London, United Kingdom",
                "Heathrow Airport",
                "GB",
                R.drawable.gb,
                "https://plus.unsplash.com/premium_photo-1694475482575-fcf2e5e64655?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8TG9uZG9uJTJDJTIwVW5pdGVkJTIwS2luZ2RvbXxlbnwwfHwwfHx8MA%3D%3D"
            ), SearchData(
                "Dubai, United Arab Emirates",
                "Dubai International Airport",
                "AE",
                R.drawable.ae,
                "https://images.unsplash.com/photo-1721974302208-1bc7b15897eb?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTF8fER1YmFpJTJDJTIwVW5pdGVkJTIwQXJhYiUyMEVtaXJhdGVzfGVufDB8fDB8fHww"
            ), SearchData(
                "Rio de Janeiro, Brazil",
                "Rio de Janeiro-Galeão International Airport",
                "BR",
                R.drawable.br,
                "https://images.unsplash.com/photo-1669318720974-41b76fed277b?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTZ8fFJpbyUyMGRlJTIwSmFuZWlybyUyQyUyMEJyYXppbHxlbnwwfHwwfHx8MA%3D%3D"
            ), SearchData(
                "Rome, Italy",
                "Leonardo da Vinci–Fiumicino Airport",
                "IT",
                R.drawable.it,
                "https://plus.unsplash.com/premium_photo-1661964123160-8d049fa07f0c?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTd8fFJvbWUlMkMlMjBJdGFseXxlbnwwfHwwfHx8MA%3D%3D"
            ), SearchData(
                "Barcelona, Spain",
                "Barcelona-El Prat Airport",
                "ES",
                R.drawable.es,
                "https://images.unsplash.com/photo-1654684784883-3423612c0629?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTZ8fEJhcmNlbG9uYSUyQyUyMFNwYWlufGVufDB8fDB8fHww"
            ), SearchData(
                "Istanbul, Turkey",
                "Istanbul Airport",
                "TR",
                R.drawable.tr,
                "https://plus.unsplash.com/premium_photo-1661955588369-b0d28de38b45?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjF8fElzdGFuYnVsJTJDJTIwVHVya2V5fGVufDB8fDB8fHww"
            ), SearchData(
                "Bangkok, Thailand",
                "Suvarnabhumi Airport",
                "TH",
                R.drawable.th,
                "https://images.unsplash.com/photo-1613672803979-a6edfc5a179b?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTh8fEJhbmdrb2slMkMlMjBUaGFpbGFuZHxlbnwwfHwwfHx8MA%3D%3D"
            ), SearchData(
                "Hong Kong, China",
                "Hong Kong International Airport",
                "CN",
                R.drawable.cn,
                "https://plus.unsplash.com/premium_photo-1676467962567-3d80fc5b5a64?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8SG9uZyUyMEtvbmclMkMlMjBDaGluYXxlbnwwfHwwfHx8MA%3D%3D"
            ), SearchData(
                "Amsterdam, Netherlands",
                "Amsterdam Schiphol Airport",
                "NL",
                R.drawable.nl,
                "https://images.unsplash.com/photo-1635449357910-1dd5e6106bd1?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8QW1zdGVyZGFtJTJDJTIwTmV0aGVybGFuZHN8ZW58MHx8MHx8fDA%3D"
            ), SearchData(
                "Singapore, Singapore",
                "Singapore Changi Airport",
                "SG",
                R.drawable.sg,
                "https://images.unsplash.com/photo-1525625293386-3f8f99389edd?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8U2luZ2Fwb3JlJTJDJTIwU2luZ2Fwb3JlfGVufDB8fDB8fHww"
            ), SearchData(
                "Berlin, Germany",
                "Berlin Brandenburg Airport",
                "DE",
                R.drawable.de,
                "https://images.unsplash.com/photo-1642324085025-1635fe6015cc?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTV8fEJlcmxpbiUyQyUyMEdlcm1hbnl8ZW58MHx8MHx8fDA%3D"
            ), SearchData(
                "San Francisco, United States",
                "San Francisco International Airport",
                "US",
                R.drawable.us,
                "https://images.unsplash.com/photo-1501594907352-04cda38ebc29?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8U2FuJTIwRnJhbmNpc2NvJTJDJTIwVW5pdGVkJTIwU3RhdGVzfGVufDB8fDB8fHww"
            ), SearchData(
                "Moscow, Russia",
                "Sheremetyevo International Airport",
                "RU",
                R.drawable.ru,
                "https://images.unsplash.com/photo-1523509433743-6f42a58221df?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Njh8fE1vc2NvdyUyQyUyMFJ1c3NpYXxlbnwwfHwwfHx8MA%3D%3D"
            ), SearchData(
                "Toronto, Canada",
                "Toronto Pearson International Airport",
                "CA",
                R.drawable.ca,
                "https://plus.unsplash.com/premium_photo-1694475481348-7cbe417be129?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8VG9yb250byUyQyUyMENhbmFkYXxlbnwwfHwwfHx8MA%3D%3D"
            ), SearchData(
                "Seoul, South Korea",
                "Incheon International Airport",
                "KR",
                R.drawable.kr,
                "https://plus.unsplash.com/premium_photo-1661886333708-877148b43ae1?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8U2VvdWwlMkMlMjBTb3V0aCUyMEtvcmVhfGVufDB8fDB8fHww"
            ), SearchData(
                "Shanghai, China",
                "Shanghai Pudong International Airport",
                "CN",
                R.drawable.cn,
                "https://images.unsplash.com/photo-1720651928491-5ce8a53f044e?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OTl8fFNoYW5naGFpJTJDJTIwQ2hpbmF8ZW58MHx8MHx8fDA%3D"
            ), SearchData(
                "Mumbai, India",
                "Chhatrapati Shivaji Maharaj International Airport",
                "IN",
                R.drawable.inn,
                "https://images.unsplash.com/photo-1506461883276-594a12b11cf3?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NzB8fE11bWJhaSUyQyUyMEluZGlhfGVufDB8fDB8fHww"
            ), SearchData(
                "Nairobi, Kenya",
                "Jomo Kenyatta International Airport",
                "KE",
                R.drawable.ke,
                "https://images.unsplash.com/photo-1664181220731-06219378d8c7?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8TmFpcm9iaSUyQyUyMEtlbnlhfGVufDB8fDB8fHww"
            ), SearchData(
                "Mexico City, Mexico",
                "Benito Juárez International Airport",
                "MX",
                R.drawable.mx,
                "https://images.unsplash.com/photo-1518105779142-d975f22f1b0a?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fE1leGljbyUyMENpdHklMkMlMjBNZXhpY298ZW58MHx8MHx8fDA%3D"
            ), SearchData(
                "Buenos Aires, Argentina",
                "Ministro Pistarini International Airport",
                "AR",
                R.drawable.ar,
                "https://images.unsplash.com/photo-1638358001519-5f456443f9e8?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NzZ8fEJ1ZW5vcyUyMEFpcmVzJTJDJTIwQXJnZW50aW5hfGVufDB8fDB8fHww"
            ), SearchData(
                "Cairo, Egypt",
                "Cairo International Airport",
                "EG",
                R.drawable.eg,
                "https://images.unsplash.com/photo-1553913861-c0fddf2619ee?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjZ8fENhaXJvJTJDJTIwRWd5cHR8ZW58MHx8MHx8fDA%3D"
            ), SearchData(
                "Athens, Greece",
                "Athens International Airport",
                "GR",
                R.drawable.gr,
                "https://plus.unsplash.com/premium_photo-1697730100869-f3606d1e717c?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjF8fEF0aGVucyUyQyUyMEdyZWVjZXxlbnwwfHwwfHx8MA%3D%3D"
            ), SearchData(
                "Prague, Czech Republic",
                "Václav Havel Airport Prague",
                "CZ",
                R.drawable.cz,
                "https://images.unsplash.com/photo-1594492256402-1463c14e0317?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8UHJhZ3VlJTJDJTIwQ3plY2glMjBSZXB1YmxpY3xlbnwwfHwwfHx8MA%3D%3D"
            ), SearchData(
                "Lisbon, Portugal",
                "Humberto Delgado Airport",
                "PT",
                R.drawable.pt,
                "https://images.unsplash.com/photo-1682616789002-1d169a8e4d94?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8TGlzYm9uJTJDJTIwUG9ydHVnYWx8ZW58MHx8MHx8fDA%3D"
            ), SearchData(
                "Edinburgh, United Kingdom",
                "Edinburgh Airport",
                "GB",
                R.drawable.gb,
                "https://images.unsplash.com/photo-1729083070713-73202f3fe6eb?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NDV8fEVkaW5idXJnaCUyQyUyMFVuaXRlZCUyMEtpbmdkb218ZW58MHx8MHx8fDA%3D"
            ), SearchData(
                "Vienna, Austria",
                "Vienna International Airport",
                "AT",
                R.drawable.at,
                "https://images.unsplash.com/photo-1664289497235-e76534cb69c2?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NDB8fFZpZW5uYSUyQyUyMEF1c3RyaWF8ZW58MHx8MHx8fDA%3D"
            ), SearchData(
                "Kuala Lumpur, Malaysia",
                "Kuala Lumpur International Airport",
                "MY",
                R.drawable.my,
                "https://images.unsplash.com/photo-1689198920834-1d3e5627b9a2?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8S3VhbGElMjBMdW1wdXIlMkMlMjBNYWxheXNpYXxlbnwwfHwwfHx8MA%3D%3D"
            ), SearchData(
                "Oslo, Norway",
                "Oslo Gardermoen Airport",
                "NO",
                R.drawable.no,
                "https://images.unsplash.com/photo-1638114420788-e9aea0b4d62e?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fE9zbG8lMkMlMjBOb3J3YXl8ZW58MHx8MHx8fDA%3D"
            ), SearchData(
                "Stockholm, Sweden",
                "Stockholm Arlanda Airport",
                "SE",
                R.drawable.se,
                "https://images.unsplash.com/photo-1610023709601-2a7ac10c691c?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjB8fFN0b2NraG9sbSUyQyUyMFN3ZWRlbnxlbnwwfHwwfHx8MA%3D%3D"
            ), SearchData(
                "Dublin, Ireland",
                "Dublin Airport",
                "IE",
                R.drawable.ie,
                "https://images.unsplash.com/photo-1660913382424-ce6902d0fe36?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTZ8fER1YmxpbiUyQyUyMElyZWxhbmR8ZW58MHx8MHx8fDA%3D"
            ), SearchData(
                "Auckland, New Zealand",
                "Auckland Airport",
                "NZ",
                R.drawable.nz,
                "https://images.unsplash.com/photo-1636342991904-42e3580c3d37?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fEF1Y2tsYW5kJTJDJTIwTmV3JTIwWmVhbGFuZHxlbnwwfHwwfHx8MA%3D%3D"
            ), SearchData(
                "Helsinki, Finland",
                "Helsinki-Vantaa Airport",
                "FI",
                R.drawable.fi,
                "https://images.unsplash.com/photo-1702592337244-33242fd6000a?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjR8fEhlbHNpbmtpJTJDJTIwRmlubGFuZHxlbnwwfHwwfHx8MA%3D%3D"
            ), SearchData(
                "Reykjavik, Iceland",
                "Keflavik International Airport",
                "IS",
                R.drawable.iss,
                "https://images.unsplash.com/photo-1669349530875-44984d720c55?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjR8fFJleWtqYXZpayUyQyUyMEljZWxhbmR8ZW58MHx8MHx8fDA%3D"
            ), SearchData(
                "Marrakech, Morocco",
                "Marrakech Menara Airport",
                "MA",
                R.drawable.ma,
                "https://images.unsplash.com/photo-1508589066756-c5dfb2cb7587?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTR8fE1hcnJha2VjaCUyQyUyME1vcm9jY298ZW58MHx8MHx8fDA%3D"
            ), SearchData(
                "Jakarta, Indonesia",
                "Soekarno–Hatta International Airport",
                "ID",
                R.drawable.id,
                "https://images.unsplash.com/photo-1567585020186-eda60ad1d21f?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MzJ8fEpha2FydGElMkMlMjBJbmRvbmVzaWF8ZW58MHx8MHx8fDA%3D"
            ), SearchData(
                "Zürich, Switzerland",
                "Zürich Airport",
                "CH",
                R.drawable.ch,
                "https://images.unsplash.com/photo-1515488764276-beab7607c1e6?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTAzfHxaJUMzJUJDcmljaCUyQyUyMFN3aXR6ZXJsYW5kfGVufDB8fDB8fHww"
            ), SearchData(
                "Doha, Qatar",
                "Hamad International Airport",
                "QA",
                R.drawable.qa,
                "https://images.unsplash.com/photo-1682953329199-1d4a39a46685?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NjB8fERvaGElMkMlMjBRYXRhcnxlbnwwfHwwfHx8MA%3D%3D"
            )
        )

        val filteredSuggestions = cities.filter {
            it.city.contains(
                viewModel.searchValue.value.text, ignoreCase = true
            ) || it.airport.contains(
                viewModel.searchValue.value.text, ignoreCase = true
            ) || it.isoCode.contains(viewModel.searchValue.value.text, ignoreCase = true)
        }.sortedBy { it.city }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = dimensionResource(id = R.dimen.iconSize),
                    horizontal = dimensionResource(id = R.dimen.topBarHorizontalPadding)
                ),
            verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.topBarHorizontalPadding))
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = dimensionResource(R.dimen.spacingXxs)),
                text = stringResource(R.string.please_select_a_city),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 22.sp,
                textAlign = TextAlign.Start
            )

            CustomTextField(
                placeHolderResource = R.string.search_for_a_city,
                value = viewModel.searchValue,
                focusRequester = remember {
                    FocusRequester()
                },
                paddingVertical = dimensionResource(id = R.dimen.spacingMd3),
                paddingHorizontal = dimensionResource(id = R.dimen.iconSize)
            )
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(filteredSuggestions) { _, suggestion ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.topBarHorizontalPadding)
                    )
                    .clickable {
                        navController.previousBackStackEntry?.savedStateHandle?.set(
                            "citySelected", suggestion.city
                        )
                        navController.previousBackStackEntry?.savedStateHandle?.set(
                            "imageUrlSelected", suggestion.imageUrl
                        )
                        navController.popBackStack()
                    }
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(
                        vertical = dimensionResource(id = R.dimen.spacingMd3),
                        horizontal = dimensionResource(id = R.dimen.iconSize)
                    ), verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier.size(size = dimensionResource(id = R.dimen.topBarVerticalPadding)),
                        painter = painterResource(id = R.drawable.mappin2),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onTertiary
                    )

                    Column(
                        modifier = Modifier
                            .weight(weight = 1f)
                            .padding(horizontal = dimensionResource(id = R.dimen.topBarHorizontalPadding))
                    ) {
                        Text(
                            text = highlightText(suggestion.city, viewModel.searchValue.value.text),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.onBackground,
                            lineHeight = 24.sp,
                            textAlign = TextAlign.Start
                        )

                        Text(
                            modifier = Modifier.padding(top = dimensionResource(R.dimen.spacingXxxs)),
                            text = highlightText(
                                suggestion.airport, viewModel.searchValue.value.text
                            ),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.surfaceTint,
                            lineHeight = 22.sp,
                            textAlign = TextAlign.Start
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(space = 2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier
                                .size(size = dimensionResource(id = R.dimen.topBarVerticalPadding))
                                .border(width = 1.dp, color = MaterialTheme.colorScheme.surface),
                            painter = painterResource(id = suggestion.countryIconResource),
                            contentDescription = null
                        )

                        Text(
                            text = highlightText(
                                suggestion.isoCode, viewModel.searchValue.value.text
                            ),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.surfaceTint,
                            lineHeight = 22.sp,
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        val view = LocalView.current
        val hapticFeedbackPerformed = rememberSaveable { mutableStateOf(false) }
        LaunchedEffect(key1 = hapticFeedbackPerformed.value) {
            if (!hapticFeedbackPerformed.value) {
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                hapticFeedbackPerformed.value = true
            }
        }
    }
}

fun highlightText(text: String, query: String): AnnotatedString {
    if (query.isBlank()) {
        return AnnotatedString(text)
    }

    val annotatedString = buildAnnotatedString {
        var currentIndex = 0
        var startIndex: Int
        while (currentIndex < text.length) {
            startIndex = text.indexOf(query, currentIndex, ignoreCase = true)
            if (startIndex == -1) {
                append(text.substring(currentIndex))
                break
            }

            append(text.substring(currentIndex, startIndex))
            pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
            append(text.substring(startIndex, startIndex + query.length))
            pop()
            currentIndex = startIndex + query.length
        }
    }
    return annotatedString
}