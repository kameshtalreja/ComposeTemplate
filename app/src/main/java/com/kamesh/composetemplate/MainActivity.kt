package com.kamesh.composetemplate

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kamesh.composetemplate.ui.theme.ComposeTemplateTheme
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {

            testingUI()

        }
    }
}

@Composable
fun testingUI(){
    Example7List()
}

@Composable
fun Example7List(){

    val scrollState = rememberScrollState()

    /** Lazy Column */
    LazyColumn {
        itemsIndexed(
            listOf("this","is","jetpack","compose")
        ) { index, item ->
            Text(
                text = "$item",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                textAlign = TextAlign.Center
            )
        }

//        items(5000) {
//            Text(
//                text = "Item  $it",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 24.dp),
//                textAlign = TextAlign.Center
//            )
//        }
    }

    /** Simple Column */
//    Column (
//        modifier = Modifier.verticalScroll(scrollState)
//            ){
//        for (i in 1..50){
//            Text(
//            text = "Item  $i",
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 24.dp),
//            textAlign = TextAlign.Center
//            )
//        }
//        Text(text = "")
//    }

}

@Composable
fun Example6TextField(){

    val scaffoldState = rememberScaffoldState()
    val textFieldState =  remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            TextField(
                value = textFieldState.value,
                onValueChange = {
                    textFieldState.value = it
                                },
                label = {
                        Text(text = "Enter Name")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Hello ${textFieldState.value}")
                }

            }) {
                Text(text = "Please Greet Me")
            }
            
        }

    }

}

@Composable
fun  Example5State(){
    
    Column(modifier = Modifier.fillMaxSize()) {
        val color = remember {
            mutableStateOf(Color.Yellow)
        }

        ColorBox(
            Modifier
                .weight(1f)
                .fillMaxSize()
        ){
            color.value = it
        }
        Box(modifier = Modifier
            .background(color.value)
            .weight(1f)
            .fillMaxSize()) {
            
        }
    }
    
}

@Composable
fun Example4StyleText(){

    val fontFamily = FontFamily(
        Font(R.font.sofiasans_bold, FontWeight.Bold),
        Font(R.font.sofiasans_extrabold, FontWeight.ExtraBold),
        Font(R.font.sofiasans_regular, FontWeight.Normal),
        Font(R.font.sofiasans_semibold, FontWeight.SemiBold),
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF101010))){

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Red,
                        fontSize = 50.sp
                    )
                ){
                    append("J")
                }
                append("etpack ")
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = 50.sp
                    )
                ){
                    append("C")
                }
                append("ompose")
            },
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.LineThrough
        )
    }
}

@Composable
fun Example3ImageCard(){
    val painter = painterResource(id = R.drawable.flower)
    val description = "this is white flower"
    val title = "Flower"
    
    Box(modifier = Modifier
        .fillMaxWidth(0.5f)
        .padding(16.dp)) {
        ImageCard(painter = painter, title = title, description = description)
    }

}

@Composable
fun Example2Modifier(){
    Column(modifier = Modifier
        .background(Color.Green)
        .fillMaxHeight(0.5f)
        .width(300.dp)
//        .requiredWidth(600.dp)
        .padding(top = 10.dp)
        .border(5.dp, Color.DarkGray)
        .padding(5.dp)
        .border(5.dp, Color.Blue)
        .padding(5.dp)
        .border(10.dp, Color.Yellow)
        .padding(10.dp)
        .offset(20.dp, 0.dp)
    ) {
        Text(text = "Hello", modifier = Modifier
            .offset(0.dp, 20.dp)
            .background(Color.Red))
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "World", modifier = Modifier.clickable {
            //nothing
        })
    }
}

@Composable
fun Example1ColumnRow(){
    Column(modifier = Modifier
        .background(Color.Green)
        .fillMaxWidth(1f)
        .fillMaxHeight(.5f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(modifier = Modifier
            .width(300.dp)
            .background(Color.Cyan), text = "Hello World")
        Text(modifier = Modifier.background(Color.Gray) , text = "World ")

        Row(modifier = Modifier
            .background(Color.Red)
            .fillMaxWidth(1f)
            .height(150.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Hello World")
            Text(text = "World ")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun ImageCard(
    painter : Painter,
    title : String,
    description : String,
    modifier: Modifier = Modifier
){
    Card (
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ){
        Box(modifier = Modifier.height(220.dp)) {
            Image(
                painter = painter,
                contentDescription = description,
                contentScale = ContentScale.FillBounds
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 300f
                    )
                )
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(text = title, style = TextStyle(color = Color.White, fontSize = 16.sp))
            }

        }
    }
}

@Composable
fun ColorBox(modifier: Modifier = Modifier,
            updateColor: (Color) -> Unit){

    /* it remember after 1st composition*/


    Box(modifier = modifier
        .background(Color.Red)
        .clickable {
            updateColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            )
        })
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTemplateTheme {
        testingUI();
    }
}