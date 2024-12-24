package com.example.experimentalsnake

import android.R.attr.contentDescription
import android.R.attr.maxHeight
import android.R.attr.maxWidth
import android.R.attr.onClick
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.experimentalsnake.ui.theme.ExperimentalSnakeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExperimentalSnakeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Snake()
}

@Composable
fun Snake(modifier: Modifier = Modifier){
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Quadro(modifier)
        Botoes(modifier)
    }

}
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun Quadro(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier
            .padding(16.dp)
            .border(2.dp, Color.Black) // Borda do quadro
    ) {
        val larguraQuadro = maxWidth
        val alturaQuadro = maxWidth
        Box(
            modifier = Modifier
                .size(larguraQuadro, alturaQuadro) // Tamanho do quadro
                .padding(16.dp)
        ) {
            Box(
                Modifier
                    .offset(x = 100.dp, y = 100.dp)
                    .size(10.dp)
                    .background(Color.DarkGray, shape = CircleShape)
            )
            Box(
                Modifier
                    .offset(x = 200.dp, y = 200.dp)
                    .size(10.dp)
                    .background(Color.DarkGray, shape = CircleShape)
            )
        }
    }
}
@Composable
fun Botoes(modifier: Modifier = Modifier) {

    val mudaTamanhoBotao = Modifier.size(64.dp)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(24.dp)
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = mudaTamanhoBotao,
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.seta_cima),
                contentDescription = "cima"
            )
        }
        Row {
            Button(
                onClick = { /*TODO*/ },
                modifier = mudaTamanhoBotao,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.seta_esquerda),
                    contentDescription = "esquerda"
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = mudaTamanhoBotao,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.seta_direita),
                    contentDescription = "direita"
                )
            }
        }
    }
    Button(
        onClick = { /*TODO*/ },
        modifier = mudaTamanhoBotao,
        shape = RoundedCornerShape(16.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.seta_baixo),
            contentDescription = "baixo"
        )
    }
}

data class EstadoJogo(val food:Pair <Int,Int>,val snake:List<Pair<Int,Int>>)
class Game(private val scope: CoroutineScope) {
    private val mutex: Mutex = Mutex()
    private val mutableState: MutableStateFlow<EstadoJogo> =
        MutableStateFlow(EstadoJogo(food = Pair(5, 5), snake = listOf(Pair(7, 7))))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExperimentalSnakeTheme {
        Greeting("Android")
    }
}