package com.example.recipe_ui_design

import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_ui_design.data.Ingredient
import com.example.recipe_ui_design.data.Recipe
import com.example.recipe_ui_design.data.strawberryCake
import com.example.recipe_ui_design.ui.theme.*
import com.google.accompanist.insets.LocalWindowInsets
import kotlin.math.max
import kotlin.math.min

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Recipe_UI_DesignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainFragment(strawberryCake)
                }
            }
        }
    }
}

@Composable
fun MainFragment(recipe: Recipe) {
    val scrollState= rememberLazyListState()
    Box{
        content(recipe,scrollState)
        parallaxToolbar(recipe,scrollState)
    }
}
@Composable
fun parallaxToolbar(recipe: Recipe,scrollState: LazyListState) {
    val imageHeight=AppBarExpendedHeight- AppBarCollapsedHeight

    val maxOffset =
        with(LocalDensity.current) { imageHeight.roundToPx() } - LocalWindowInsets.current.systemBars.layoutInsets.top

    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)

    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset


    TopAppBar(
        contentPadding = PaddingValues(),
        backgroundColor = White,
        modifier = Modifier
            .height(
                AppBarExpendedHeight
            )
            .offset{IntOffset(x=0,y=-offset)},
        elevation = if (offset==maxOffset) 4.dp else 0.dp


    ) {
        Column {
            Box(Modifier.height(imageHeight)) {

                Image(
                    painter = painterResource(id = R.drawable.strawberry_pie_1),
                    contentDescription =null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    Pair(0.4f, Transparent),
                                    Pair(1f, White)
                                )
                            )
                        )
                )
                Row(modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp, vertical = 8.dp), verticalAlignment = Alignment.Bottom){
                    Text(recipe.category,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .clip(Shapes.small)
                            .background(LightGray)
                            .padding(vertical = 6.dp, horizontal = 16.dp))
                }

            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(AppBarCollapsedHeight),
                verticalArrangement = Arrangement.Center
            ) {
                Text(recipe.title,
                    color = Color.Black,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp))
            }
        }

    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
//            .statusBarPadding()
            .height(AppBarCollapsedHeight)
            .padding(horizontal = 16.dp)

    ) {
        CircularButton(R.drawable.ic_arrow_back)
        CircularButton(R.drawable.ic_favorite)
    }
}

@Composable
fun CircularButton(@DrawableRes iconResource:Int,
                   color: Color= Gray,
                   elevation: ButtonElevation?=ButtonDefaults.elevation(),
                   onClick:()->Unit={}
                   )
{
    Button(
        onClick =  onClick,
        contentPadding = PaddingValues(),
        shape = Shapes.small,
        colors = ButtonDefaults.buttonColors(backgroundColor = White, contentColor = color),
        elevation = elevation,
        modifier = Modifier
            .width(38.dp)
            .height(38.dp)

    ) {
        Icon(painterResource(id = iconResource),null)

        
    }

}

@Composable
fun content(recipe: Recipe,scrollState:LazyListState) {
    LazyColumn(contentPadding = PaddingValues(top = AppBarExpendedHeight), state = scrollState){
        item {
            BasicInfo(recipe)
            Description(recipe)
            ServingCalculator()
            IngredientsHeader()
            IngredientList(recipe)
            ShoppingListButton()
            Reviews(recipe)
            Images()
        }
    }
}

@Composable
fun Images() {
    Row(Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween,) {
        Image(
            painter = painterResource(id = R.drawable.strawberry_pie_2),
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .clip(Shapes.small)
        )
        Spacer(modifier = Modifier.weight(0.1f))
        Image(
            painter = painterResource(id = R.drawable.strawberry_pie_3),
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .clip(Shapes.small)
        )
    }
}

@Composable
fun Reviews(recipe: Recipe) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,

    ){
        Column {
            Text(text = "Reviews", fontWeight = FontWeight.Bold)
            Text(text = recipe.reviews, color = DarkGray)
        }
        Button(onClick = { /*TODO*/ }, elevation = null, colors = ButtonDefaults.buttonColors(
            backgroundColor = Transparent, contentColor = Pink
        )) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text ="Sea all")
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = null
                )
                
            }

        }
    }


}

@Composable
fun ShoppingListButton() {
    Button(onClick = {}, elevation = null,
        shape = Shapes.small,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = LightGray,
            contentColor = Color.Black
                ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Add To Shopping List", modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun IngredientList(recipe: Recipe) {
    EasyGrid(nColumns = 3, items =recipe.ingredients){
        IngredientCart(it.image,it.title, it.subtitle,Modifier)
    }

}

@Composable
fun <T> EasyGrid(nColumns: Int, items: List<T>, content: @Composable (T) -> Unit) {
    Column(Modifier.padding(16.dp)) {
        for (i in items.indices step nColumns) {
            Row {
                for (j in 0 until nColumns) {
                    if (i + j < items.size) {
                        Box(
                            contentAlignment = Alignment.TopCenter,
                            modifier = Modifier.weight(1f)
                        ) {
                            content(items[i + j])
                        }
                    } else {
                        Spacer(Modifier.weight(1f, fill = true))
                    }
                }
            }
        }
    }
}


@Composable
fun IngredientCart(@DrawableRes iconResource: Int,
                   title:String,
                   subtitle:String,
                   modifier: Modifier
                   ) {
     Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(bottom = 16.dp)) {
         
         Card(
             shape = Shapes.large,
             backgroundColor = LightGray,
             modifier = Modifier
                 .width(100.dp)
                 .height(100.dp)
                 .padding(bottom = 8.dp)

         ) {
             Image(painter = painterResource(id = iconResource),
                 contentDescription = null,
                 modifier = Modifier.padding(16.dp)
             )
         }
         Text(text = title, modifier = Modifier.width(100.dp), fontSize = 14.sp, fontWeight = FontWeight.Medium)
         Text(text = subtitle, color = DarkGray, modifier = Modifier.width(100.dp), fontSize = 14.sp)
         
     }
}

@Composable
fun IngredientsHeader() {
Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 16.dp)
        .clip(Shapes.medium)
        .background(LightGray)
        .fillMaxWidth()
        .height(44.dp)
) {
    TabButton(text ="Ingredients",active = true, modifier = Modifier.weight(1f))
    TabButton(text ="Tools",active = false, modifier = Modifier.weight(1f))
    TabButton(text ="Steps",active = false, modifier = Modifier.weight(1f))
}
}

@Composable
fun TabButton(text:String,active:Boolean,modifier: Modifier) {
    Button(onClick = { /*TODO*/ },
        shape = Shapes.medium,
        modifier = modifier.fillMaxHeight(),
        elevation = null,
        colors = if (active) ButtonDefaults.buttonColors(
            backgroundColor = Pink,
            contentColor = White,
        )else ButtonDefaults.buttonColors(
            backgroundColor = LightGray,
            contentColor = DarkGray
        )
    ) {
        Text(text = text)
        
    }
}

@Composable
fun ServingCalculator() {
    var value by remember { mutableStateOf(6) }
    Row(verticalAlignment =Alignment.CenterVertically, modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 16.dp)
        .clip(Shapes.medium)
        .background(LightGray)
        .padding(16.dp)
     ){
        Text(text = "Serving",Modifier.weight(1f), fontWeight = FontWeight.Medium)
        CircularButton(iconResource = R.drawable.ic_minus, elevation = null, color = Pink){value--}
        Text(text = "$value",Modifier.padding(16.dp), fontWeight = FontWeight.Medium)
        CircularButton(iconResource = R.drawable.ic_plus, elevation = null, color = Pink){value++}
    }
}

@Composable
fun Description(recipe: Recipe) {
    Text(text = recipe.description, fontWeight = FontWeight.Medium, modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 16.dp)
    )
}

@Composable
fun BasicInfo(recipe: Recipe) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp)
    ){
        InfoColumn(R.drawable.ic_clock,recipe.cookingTime)
        InfoColumn(R.drawable.ic_flame,recipe.energy)
        InfoColumn(R.drawable.ic_star,recipe.rating)
    }
}

@Composable
fun InfoColumn(@DrawableRes iconResource: Int,text:String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally ) {
        Icon(
            painter = painterResource(id = iconResource), 
            contentDescription =null , 
            tint = Pink,
            modifier = Modifier
            .height(24.dp)
        )
        Text(text = text, fontWeight = FontWeight.Bold)
    }

}

@Preview(showBackground = true, widthDp = 380, heightDp = 1400)
@Composable
fun DefaultPreview() {
    Recipe_UI_DesignTheme {
        MainFragment(strawberryCake)
    }
}