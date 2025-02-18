package cat.itb.m78.exercices.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cat.itb.m78.exercices.screens.ScreenCalculator
import cat.itb.m78.exercices.screens.ScreenResult
import kotlinx.serialization.Serializable

data object Destination
{
    @Serializable
    data object ScreenCalculator
    @Serializable
    data object  ScreenResult
}

@Composable
fun navHost()
{
    val navController = rememberNavController()
    NavHost(
         navController = navController,
        startDestination = Destination.ScreenCalculator
    )
    {
        composable<Destination.ScreenCalculator>
        {
            ScreenCalculator { navController.navigate(Destination.ScreenResult) }
        }
        composable<Destination.ScreenResult>
        {
            ScreenResult()
        }
    }
}