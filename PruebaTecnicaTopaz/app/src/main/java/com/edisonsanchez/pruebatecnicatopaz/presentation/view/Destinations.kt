package com.edisonsanchez.pruebatecnicatopaz.presentation.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

interface Destinations {
    val route: String
    val icon: ImageVector
    val title: String
}

object Home: Destinations {
    override val route = "home"
    override val icon = Icons.Filled.Home
    override val title = "Home"
}

object Favorite: Destinations {
    override val route = "favorite"
    override val icon = Icons.Filled.Favorite
    override val title = "Favoritos"
}

object Details: Destinations {
    override val route = "details"
    override val icon = Icons.Filled.Favorite
    override val title = "Detalles"
}