package com.edisonsanchez.pruebatecnicatopaz.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.edisonsanchez.pruebatecnicatopaz.presentation.view.theme.PruebaTecnicaTopazTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaTecnicaTopazTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(navController = navController, startDestination = Home.route) {
                            composable(Home.route) {
                                ProductsView(navController = navController)
                            }
                            composable(Favorite.route) {
                                FavoriteProductsView(navController = navController)
                            }
                            composable("${Details.route}/{productId}",
                                arguments = listOf(navArgument("productId") {
                                    type = NavType.IntType
                                })
                            ) { backStackEntry ->
                                val productId = backStackEntry.arguments?.getInt("productId") ?:0
                                DetailsProductView(productId)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val destinations = listOf(
        Home,
        Favorite
    )
    val selectedIndex = rememberSaveable { mutableStateOf(0) }
    BottomNavigation{
        destinations.forEachIndexed { index, destination ->
            BottomNavigationItem(
                label = { Text(text = destination.title) },
                icon = { Icon(imageVector = destination.icon, contentDescription = destination.title) },
                selected = selectedIndex.value == index,
                onClick = {
                    selectedIndex.value = index
                    navController.navigate(destination.route){
                        popUpTo(Home.route)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

