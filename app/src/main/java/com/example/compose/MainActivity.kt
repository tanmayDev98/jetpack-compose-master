package com.example.compose

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.compose.ui.theme.ComposeMasterTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMasterTheme  {
                ComposeApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun ComposeApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = MainScreen) {
        composable<MainScreen> {navBackStackEntry ->
            val mainScreenArgs: MainScreen = navBackStackEntry.toRoute()
            MainScreen(
                modifier,
                mainScreenArgs,
                onListClicked = {
                    navController.navigate(ListDetails(it))
                }
            )
        }
        composable<ListDetails> {
            val listDetailsArgs = it.toRoute<ListDetails>()
            ListDetailsScreen(modifier, ListDetails(listDetailsArgs.valuePassed), onBackClicked = {
                navController.popBackStack()
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier, mainScreenArgs: MainScreen, onListClicked: (number: String) -> Unit) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    var onboardingName by rememberSaveable {  mutableStateOf("") }
    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false }, name = onboardingName, onNameChange = {onboardingName = it})
        } else {
            val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
            Scaffold (modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
                ,topBar = {LargeTopAppBarCustom(scrollBehavior) }, bottomBar = { BottomAppBarCustom() }) {
                    innerPadding -> Greetings(modifier = Modifier.padding(innerPadding), onListClicked=onListClicked)
            }
            ShowToast(onboardingName)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeTopAppBarCustom(scrollBehavior: TopAppBarScrollBehavior) {
    LargeTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                "Large Top App Bar",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun BottomAppBarCustom() {
    BottomAppBar(
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(Icons.Filled.Check, contentDescription = "Localized description")
            }
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "Localized description",
                )
            }
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = "Localized description",
                )
            }
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    Icons.Filled.Share,
                    contentDescription = "Localized description",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* do something */ },
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        }
    )
}

@Composable
fun OnboardingContent(modifier: Modifier, onContinueClicked: () -> Unit, name: String, onNameChange: (String) -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the compose master app!")
        OnBoardingNameInput(name, onNameChange = onNameChange)
        Button(
            modifier = modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked,
            enabled = name.isNotBlank()
        ) {
            Text("Continue")
        }
    }
}

//State Hoisting the Input component
@Composable
fun OnBoardingNameInput(name: String, onNameChange:(String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Enter your name") },
        modifier = Modifier.padding(vertical = 24.dp)
    )
}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onContinueClicked: () -> Unit,
    name: String,
    onNameChange: (String) -> Unit
) {
    OnboardingContent(modifier = modifier, onContinueClicked, name =name, onNameChange = onNameChange)
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    numbers: List<String> = List(100) { "$it" },
    onListClicked: (number: String) -> Unit
) {
    var selectedNumber by rememberSaveable { mutableStateOf<String>("") }
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = numbers) { number ->
            Greeting(
                number = number, isSelected = (selectedNumber == number),
                onShowClicked = { selected ->
                    selectedNumber = if (selectedNumber == selected) "" else selected
                },
                onListClicked = onListClicked)
        }
    }
}

@Composable
private fun Greeting(
    modifier: Modifier = Modifier,
    number: String, isSelected: Boolean,
    onShowClicked: (name: String) -> Unit,
    onListClicked: (number: String) -> Unit
) {

    val extraPadding by animateDpAsState(
        if (isSelected) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        onClick = {onListClicked(number)}
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello, ")
                Text(text = number)
                if (isSelected) {
                    Text(text = "This is additional detail about $number")
                }
            }
            ElevatedButton(
                onClick = { onShowClicked(number) }
            ) {
                Text(if (isSelected) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun ShowToast(name: String) {
    val context = LocalContext.current
    Toast.makeText(context, "Hello! $name", Toast.LENGTH_LONG).show()
}

@Composable
fun ListDetailsScreen(modifier: Modifier, listDetails: ListDetails, onBackClicked: () -> Unit) {
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Hello, ${listDetails.valuePassed}")
        Button(onClick = onBackClicked) {
            Text("Back")
        }
    }
}

//Data class that holds argument to be passed to route
@Serializable
data class ListDetails(val valuePassed: String)

//object to have main screen as the start screen in the route
@Serializable
object MainScreen

/*Previews*/
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnBoardingScreenPreview() {
    ComposeMasterTheme  {
        OnboardingScreen(modifier = Modifier, onContinueClicked = {}, name = "", onNameChange = {})
    }
}

@Preview(showBackground = true, name = "GreetingPreviewDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    widthDp = 320)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    ComposeMasterTheme {
        Greetings(onListClicked = {})
    }
}

@Preview
@Composable
fun ComposeAppPreview() {
    ComposeMasterTheme  {
        ComposeApp(Modifier.fillMaxSize())
    }
}

@Preview
@Composable
fun ListDetailsScreenPreview() {
    ComposeMasterTheme {
        ListDetailsScreen(Modifier.fillMaxSize(), ListDetails("20"), onBackClicked = {})
    }
}