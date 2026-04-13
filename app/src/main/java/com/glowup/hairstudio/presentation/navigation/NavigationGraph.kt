package com.glowup.hairstudio.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.glowup.hairstudio.presentation.screens.home.HomeScreen
import com.glowup.hairstudio.presentation.screens.login.LoginScreen
import com.glowup.hairstudio.presentation.screens.services.ServicesScreen
import com.glowup.hairstudio.presentation.screens.splash.SplashScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Splash Screen
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        
        // Login Screen
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUp.route)
                }
            )
        }
        
        // Home Screen
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToServices = {
                    navController.navigate(Screen.Services.route)
                },
                onNavigateToAppointments = {
                    navController.navigate(Screen.Appointments.route)
                },
                onNavigateToReviews = {
                    navController.navigate(Screen.Reviews.route)
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }
        
        // Services Screen
        composable(Screen.Services.route) {
            ServicesScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onBookService = { serviceId ->
                    navController.navigate(Screen.Booking.createRoute(serviceId))
                }
            )
        }
        
        // Add more screens here as needed
    }
}
