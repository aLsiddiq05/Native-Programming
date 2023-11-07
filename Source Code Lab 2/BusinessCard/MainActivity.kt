package com.example.businesscard
/*
Muhammad Amier
S63650
 */
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // Call your custom Composable function here
                BusinessCard()
            }
        }
    }
}

@Composable
fun BusinessCard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.androindicon),
                contentDescription = null, // Set contentDescription to null for decorative images
                modifier = Modifier
                    .size(96.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Amier",
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Student",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                ContactItem("Phone: +60 14-7740779")
                ContactItem("Email: amieralsiddiq.com", Color(0xFF3ddc84))
                ContactItem("Instagram: @aLSiddiq")
            }
        }
    }
}

@Composable
fun ContactItem(text: String, textColor: Color = Color.Black) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            color = textColor
        )
    }
}

