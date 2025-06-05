package com.example.metroapp

import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
    var dropdown1 by remember { mutableStateOf(false) }
    var dropdown2 by remember { mutableStateOf(false) }




    val metroStations = remember {mutableListOf<String>(
        "Andheri", "DN Nagar", "Versova", "Marol Naka", "Saki Naka",
        "Chakala", "Airport Road", "JB Nagar", "Marol Depot",
        "Asalpha", "Jagruti Nagar", "SEEPZ", "Chhatrapati Shivaji International Airport",
        "Ghatkopar", "Subhash Nagar", "Charkop", "Kandivali", "Malad"
    )}







    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "From",
            color = Color(29, 78, 216),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        ExposedDropdownMenuBox(
            expanded = dropdown1,
            onExpandedChange = { dropdown1 = it },
            modifier = Modifier.fillMaxWidth()
        ){
            OutlinedTextField(
                modifier = Modifier .menuAnchor().fillMaxWidth(),
                value = fromStation.value,
                onValueChange = {
                    fromStation.value=it
                    if(it.isEmpty()){
                        dropdown1=false
                    }
                },
                singleLine = true,
                label = {
                    Text(
                        "Select Source Station",
                        color = Color(29, 78, 216),
                        style = TextStyle(fontSize = 18.sp)
                    )
                },
                trailingIcon = {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        if (fromStation.value.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    fromStation.value = ""
                                    dropdown1 = false
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear",
                                    tint = Color(29, 78, 216)
                                )
                            }
                        }

                        IconButton(onClick = {
                            dropdown1 = !dropdown1
                            Log.e("button clicked ${dropdown1}", "dropdown")
                        }) {
                            Icon(
                                imageVector = if (dropdown1) Icons.Default.KeyboardArrowUp
                                else Icons.Default.ArrowDropDown,
                                contentDescription = null,
                                tint = Color(29, 78, 216)
                            )
                        }
                        //ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdown1)
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color(29, 78, 216),
                    unfocusedTextColor = Color(29, 78, 216),
                    focusedBorderColor = Color(29, 78, 216),
                    unfocusedBorderColor = Color(29, 78, 216)
                ),
            )

            ExposedDropdownMenu(
                expanded = dropdown1,
                onDismissRequest = { dropdown1 = false },
                modifier = Modifier
                    .heightIn(max = 200.dp)
            )  {
                metroStations.forEach { station ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                station,
                                color = Color(29, 78, 216)
                            )
                        },
                        onClick = {
                            fromStation.value = station
                            dropdown1 = !dropdown1
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

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
                    dropdown1 = false
                    dropdown2 = false
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

        Text(
            text = "To",
            color = Color(29, 78, 216),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )


        ExposedDropdownMenuBox(
            expanded = dropdown2,
            onExpandedChange = { dropdown2 = it },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = toStation.value,
                onValueChange = {
                    toStation.value = it

                    if(it.isEmpty()){
                        dropdown2=false
                    }

                },
                singleLine = true,
                label = {
                    Text(
                        "Select Destination Station",
                        color = Color(29, 78, 216),
                        style = TextStyle(fontSize = 18.sp)
                    )
                },
                trailingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        if (toStation.value.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    toStation.value = ""
                                    dropdown2 = false
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear",
                                    tint = Color(29, 78, 216)
                                )
                            }
                        }
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdown2)
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color(29, 78, 216),
                    unfocusedTextColor = Color(29, 78, 216),
                    focusedBorderColor = Color(29, 78, 216),
                    unfocusedBorderColor = Color(29, 78, 216)
                ),
            )

            ExposedDropdownMenu(
                expanded = dropdown2,
                onDismissRequest = { dropdown2 = false },
                modifier = Modifier.heightIn(max = 200.dp)
            ) {
                metroStations.forEach { station ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                station,
                                color = Color(29, 78, 216)
                            )
                        },
                        onClick = {
                            toStation.value = station
                            dropdown2 = false
                        }
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(32.dp))


        Button(
            onClick = {
                // Add your search logic here
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



    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    MetroAppTheme {
        MyApp()
    }
}