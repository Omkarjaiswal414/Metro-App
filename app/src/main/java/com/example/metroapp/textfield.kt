package com.example.metroapp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

 @OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textfield(
        Station: MutableState<String>,
        dropdown: MutableState<Boolean>,
) {
        val metroStations: List<String> = listOf(
        "Andheri", "DN Nagar", "Versova", "Marol Naka", "Saki Naka",
        "Chakala", "Airport Road", "JB Nagar", "Marol Depot",
        "Asalpha", "Jagruti Nagar", "SEEPZ", "Chhatrapati Shivaji International Airport",
        "Ghatkopar", "Subhash Nagar", "Charkop", "Kandivali", "Malad"
        )

        ExposedDropdownMenuBox(
            expanded = dropdown.value,
            onExpandedChange = { dropdown.value = it },
            modifier = Modifier.fillMaxWidth()
        ){
            // textfield
            OutlinedTextField(
                modifier = Modifier .menuAnchor().fillMaxWidth(),
                value = Station.value,
                onValueChange = {
                    Station.value=it
                    if(it.isEmpty()){
                        dropdown.value=false
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

                        if (Station.value.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    Station.value = ""
                                    dropdown.value = false
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
                            dropdown.value = !dropdown.value
                        }) {
                            Icon(
                                imageVector = if (dropdown.value) Icons.Default.KeyboardArrowUp
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

            // dropdown options for textfield
            ExposedDropdownMenu(
                expanded = dropdown.value,
                onDismissRequest = { dropdown.value = false },
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
                            Station.value = station
                            dropdown.value = !dropdown.value
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

}
