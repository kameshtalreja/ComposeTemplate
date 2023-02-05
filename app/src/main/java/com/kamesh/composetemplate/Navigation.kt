package com.kamesh.composetemplate

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(route = Screens.MainScreen.route) {

        }

        composable(route = Screens.DetailScreen.route,
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "Kamesh"
                    nullable = true
                }
        )) {
            
        }
    }
}

@Composable
fun MainScreen(navController: NavController){

    var text by remember {
        mutableStateOf("")
    }

    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
        ) {

        TextField(value = text, onValueChange = {
            text = it
        }, modifier = Modifier.fillMaxWidth() )
        
        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            navController.navigate(Screens.DetailScreen.route)
        }
        , modifier = Modifier.align(Alignment.End)) {
            Text(text = "To Detail Screen")
        }
    }
}


@Composable
fun DetailScreen(name : String?){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = "Hello, $name")
    }
}