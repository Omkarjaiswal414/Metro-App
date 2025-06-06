package com.example.metroapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.metroapp.ui.theme.MetroAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MetroAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val fromStation = remember { mutableStateOf("") }
    val toStation = remember { mutableStateOf("") }
    var dropdown1 = remember { mutableStateOf(false) }
    var dropdown2 = remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        //Header
        Text(
            text = "Travel Compare",
            color = Color(29, 78, 216),
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 0.dp, start = 40.dp,end= 20.dp, bottom = 40.dp),
            textAlign = TextAlign.Center
        )

        // Text
        Text(
            text = "From",
            color = Color(29, 78, 216),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )


        var t1= textfield(fromStation,dropdown1)
        t1



        // Swap button for swapping the destination and sources stations
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = {
                    val temp = fromStation.value
                    fromStation.value = toStation.value
                    toStation.value = temp
                     dropdown1.value=false
                    dropdown2.value = false
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(29, 78, 216)
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = androidx.compose.ui.graphics.SolidColor(Color(29, 78, 216))
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.width(200.dp)

            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Swap Stations",
                    fontSize = 16.sp
                )
            }

        }

        Spacer(modifier = Modifier.height(22.dp))

        // Text
        Text(
            text = "To",
            color = Color(29, 78, 216),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        var textfield2= textfield(toStation,dropdown2)

        textfield2

        Button(
            onClick = {
                if(fromStation.value!=toStation.value) {
                    showDialog = true
                }
                else{
                    Toast.makeText(context,"Source and Destination are Same", Toast.LENGTH_LONG).show()
                }
                },
            enabled = fromStation.value.isNotEmpty() && toStation.value.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(29, 78, 216)
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "Find Route",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }

        if(showDialog){
            AlertDialog(onDismissRequest = { showDialog=false },
                confirmButton = {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                        horizontalArrangement = Arrangement.Center) {

                        Button(onClick = {showDialog=false}) {
                            Text(text = "Dismiss",)
                        }
                    }
                },
                title = {Text(text = "Metro Route")},
                text={
                    Text("Your Metro Route :\n" +
                            " ${fromStation.value} -> ${toStation.value}",
                        style = TextStyle(fontSize = 18.sp),
                        modifier = Modifier.padding(20.dp),
                        textAlign = TextAlign.Center)
                })
        }



    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    MetroAppTheme {
        MyApp()
    }
}