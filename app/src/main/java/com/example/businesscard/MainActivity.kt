package com.example.businesscard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCard(
                        Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    CardElements(
        profilePic = painterResource(R.drawable.android_logo),
        fullName = stringResource(R.string.full_name),
        title = stringResource(R.string.title),
    )
}

@Composable
private fun CardElements(
    profilePic: Painter,
    fullName: String,
    title: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = profilePic,
            contentDescription = null,
            modifier = Modifier
                .size(320.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = fullName,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = title,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MediaCards(
                infoText = stringResource(R.string.phone_number),
                icon = painterResource(R.drawable.baseline_contact_phone_24),
                onClick = {
                    val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = "tel:+527122347473".toUri()
                    }
                    context.startActivity(intent)
                }
            )
            MediaCards(
                infoText = stringResource(R.string.github_user_name),
                icon = painterResource(R.drawable.outline_folder_code_24),
                onClick = {
                    val intent =
                        Intent(Intent.ACTION_VIEW, "https://github.com/AdrianCR08".toUri())
                    context.startActivity(intent)
                }
            )
            MediaCards(
                infoText = stringResource(R.string.email_address),
                icon = painterResource(R.drawable.outline_mail_24),
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = "mailto:adrianrmz917@gmail.com".toUri()
                    }
                    context.startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun MediaCards(
    infoText: String,
    icon: Painter,
    onClick: (() -> Unit)? = null
) {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 32.dp, vertical = 8.dp)
        .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .size(45.dp)
                .padding(end = 16.dp, top = 16.dp),
            tint = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = infoText,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        BusinessCard()
    }
}