package com.example.quotegenerator.screen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotegenerator.R
import com.example.quotegenerator.model.MainViewModel
import com.example.quotegenerator.model.Quote

@Composable
fun QuoteScreen(modifier: Modifier = Modifier) {
    val viewQuote: MainViewModel = viewModel()
    val quoteState by viewQuote.quoteState
    val context = LocalContext.current

    val authorQueue = remember {
        mutableStateOf("")
    }

    val queue = remember {
        mutableStateOf("")
    }

    when {
        quoteState.list.isNotEmpty() -> {
            authorQueue.value = quoteState.list[0].a
            queue.value = quoteState.list[0].q
        }
    }


    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(android.graphics.Color.parseColor("#75BDFF")))
    ) {
        val (title, cloud, box, topQuotes, bottomQuotes, boxActions, button, developed) = createRefs()


        Image(
            painter = painterResource(id = R.drawable.nuvem),
            contentDescription = null,
            modifier = Modifier
                .size(280.dp)
                .constrainAs(cloud) {
                    top.linkTo(title.top)
                    bottom.linkTo(title.bottom)
                    start.linkTo(title.start)
                    end.linkTo(title.end)
                }
        )

        Text(
            text = "Citação\ndo\nDia",
            style = TextStyle(
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top, margin = 180.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )



        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(20.dp)
                .defaultMinSize(minHeight = 100.dp)
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(10.dp)
                )
                .background(
                    color = Color(android.graphics.Color.parseColor("#C9EEEE")),
                    shape = RoundedCornerShape(10.dp)
                )

                .constrainAs(box) {
                    top.linkTo(title.bottom, margin = 50.dp)
                }
        ) {
            Column {
                Text(
                    text = queue.value,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Serif,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(
                            top = 30.dp,
                            start = 40.dp,
                            end = 40.dp
                        )
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = authorQueue.value,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Serif,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            end = 20.dp,
                            bottom = 20.dp,
                            top = 10.dp
                        )
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.top_quotes),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .constrainAs(topQuotes) {
                    top.linkTo(box.top, margin = 25.dp)
                    start.linkTo(box.start, margin = 25.dp)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.bottom_quotes),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .constrainAs(bottomQuotes) {
                    bottom.linkTo(box.bottom, margin = 60.dp)
                    end.linkTo(box.end, margin = 25.dp)
                }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 130.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(60.dp)
                )
                .constrainAs(boxActions) {
                    top.linkTo(box.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            ) {

                Box(
                    modifier = Modifier
                        .border(
                            width = 3.dp,
                            color = Color(android.graphics.Color.parseColor("#5AB0FF")),
                            shape = CircleShape
                        )
                        .size(50.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.copy_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() },
                                onClick = {
                                    copyTextToClipboard(context, queue.value)
                                }
                            )

                    )
                }

                Box(
                    modifier = Modifier
                        .border(
                            width = 3.dp,
                            color = Color(android.graphics.Color.parseColor("#5AB0FF")),
                            shape = CircleShape
                        )
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                val sendIntent: Intent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, queue.value)
                                    type = "text/plain"
                                }

                                val shareIntent = Intent.createChooser(sendIntent, null)
                                context.startActivity(shareIntent)
                            }
                        )
                        .size(50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.share_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    )
                }
            }
        }

        Text(
            text = "Desenvolvido por @iago-oliveira-andrade",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                color = Color.White,
                fontSize = 12.sp
            ),
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(developed) {
                    top.linkTo(button.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Button(
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(android.graphics.Color.parseColor("#99CCFB")),
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp
            ),
            modifier = Modifier
                .size(width = 230.dp, height = 60.dp)
                .constrainAs(button) {
                    top.linkTo(boxActions.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onClick = {
                viewQuote.fetchQuote()

                val quote: Quote = quoteState.list[0]

                authorQueue.value = quote.a
                queue.value = quote.q
            }
        ) {

            when {
                quoteState.loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp)
                    )
                }
                else -> {
                    Text(
                        text = "GERAR CITAÇÃO",
                        fontSize = 23.sp,
                        fontWeight = FontWeight.W900
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    QuoteScreen()
}

fun copyTextToClipboard(context: Context, text: String) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("Texto copiado", text)
    clipboardManager.setPrimaryClip(clip)
}