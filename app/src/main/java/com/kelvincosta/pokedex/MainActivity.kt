package com.kelvincosta.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelvincosta.pokedex.ui.theme.PokeDexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeDexTheme {
                myAppPokeDex(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Center)
                )
            }
        }
    }
}

@Composable
fun myAppPokeDex(modifier: Modifier = Modifier) {

    val listPokemon = arrayListOf<Pokemon>()

    listPokemon.add(Pokemon("Chamander", "Fogo", R.drawable.chamander))
    listPokemon.add(Pokemon("Pikachu", "Raio", R.drawable.pikachu))
    listPokemon.add(Pokemon("Squirtle", "Agua", R.drawable.squirtle))

    var previous by remember { mutableStateOf(1) }

    val pokemon = when (previous) {
        1 -> listPokemon[0]
        2 -> listPokemon[1]
        else -> {
            listPokemon[2]
        }
    }

    val colorBorder = when(pokemon) {
        listPokemon[0] -> Color.Red
        listPokemon[1] -> Color.Yellow
        else -> {
            Color.Blue
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = CenterHorizontally,
    ) {
        Text(
            text = pokemon.name,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(pokemon.image),
            contentDescription = pokemon.name,
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)
                .border(2.dp, colorBorder)
        )


        Spacer(modifier = Modifier.height(16.dp))

        Card() {
            Text(text = "Natureza: " + pokemon.nature, modifier = Modifier.padding(20.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            Button(
                onClick = {
                    if (previous != 1) {
                        previous--
                    } else {
                        previous = 3
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(text = "Previous")
            }
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                onClick = {
                    if (previous < 3) {
                        previous++
                    } else {
                        previous = 1
                    }
                }) {
                Text(text = "Next")
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun AppPokeDexPreview() {
    PokeDexTheme {
        myAppPokeDex(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Center)
        )
    }
}

data class Pokemon(
    val name: String,
    val nature: String,
    val image: Int
)