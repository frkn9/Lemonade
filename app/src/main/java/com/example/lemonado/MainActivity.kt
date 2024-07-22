package com.example.lemonado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonado.ui.theme.LemonadoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonApp(
                        modifier = Modifier
                            .padding(innerPadding)
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun LemonApp(modifier: Modifier = Modifier) {
    var lemonImage by remember { mutableIntStateOf(1)  }
    var clicksSoFar by remember { mutableIntStateOf(0)  }
    val lemonText : String
    val lemonDescr : String
    val imageResource: Int
    val requiredClicks = (2..4).random()


    when(lemonImage){
        1 -> {
            imageResource = R.drawable.lemon_tree
            lemonText = stringResource(id = R.string.lemon_tree)
            lemonDescr = stringResource(id = R.string.name_img1)
            }
        2 -> {
            imageResource = R.drawable.lemon_squeeze
            lemonText = stringResource(id = R.string.lemon)
            lemonDescr = stringResource(id = R.string.name_img2)
        }
        3 -> {
            imageResource = R.drawable.lemon_drink
            lemonText = stringResource(id = R.string.glass_of_lemonade)
            lemonDescr = stringResource(id = R.string.name_img3)
        }
        else -> {
            imageResource = R.drawable.lemon_restart
            lemonText = stringResource(id = R.string.empty_glass)
            lemonDescr = stringResource(id = R.string.name_img4)
        }
    }


    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFEEFFEE))) {
        Text(
            text = "Lemonade",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .background(Color(0xffffffc0))
                .padding(top = 15.dp, bottom = 15.dp)
                .fillMaxWidth()
                )
        Spacer(modifier = Modifier.height(250.dp))
        Button(
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF332221)),
            onClick = {
                if(lemonImage == 2){
                    clicksSoFar++
                    if(clicksSoFar >= requiredClicks){
                        lemonImage++
                        clicksSoFar = 0
                    }
                }
                else if(lemonImage == 4)
                    lemonImage = 1
                else
                    lemonImage++
            }
        ) {
            Image(painter = painterResource(id = imageResource), contentDescription = lemonDescr)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = lemonText, fontSize = 20.sp, textAlign = TextAlign.Justify )
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    LemonadoTheme {
        LemonApp(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center))
    }
}