package com.kamesh.composetemplate

sealed class Screens(val route : String) {
    object MainScreen : Screens("main_screen")
    object DetailScreen : Screens("detail_screen")
}
