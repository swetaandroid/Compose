package com.example.compose.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class OnBoardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnboardingScreen {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // 🔹 Background Pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) {
            OnboardingPage()
        }

        // 🔹 Top Indicator
        Indicator(pagerState.currentPage)

        // 🔹 Bottom Swipe Button Container
        Box(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .padding(bottom = 60.dp, start = 30.dp, end = 30.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            SwipeToStartButton(
                onSwipeComplete = {
                    if (pagerState.currentPage == 2) {
                        onFinish()
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun SwipeToStartButton(
    onSwipeComplete: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val dragAmount = remember { Animatable(0f) }
    val density = LocalDensity.current

    val buttonHeight = 64.dp 
    val outerPadding = 6.dp
    val circleSize = 52.dp // (64dp - 6dp * 2)

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height(buttonHeight)
            .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(100))
    ) {
        val maxWidth = constraints.maxWidth.toFloat()
        val circleSizePx = with(density) { circleSize.toPx() }
        val paddingPx = with(density) { outerPadding.toPx() }
        val maxOffset = maxWidth - circleSizePx - (paddingPx * 2)

        // 🔹 Background Content (Text + Arrows)
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(60.dp)) // Space for the circle

            Text(
                text = "Let's Started",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )

            // Triple Arrows >>> with overlapping effect
            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(1) { i ->
                    Icon(
                        painter = painterResource(id = com.example.compose.R.drawable.ic_right_arrow),
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.25f * (i + 1)),
                        modifier = Modifier
                            .size(50.dp)
                            .offset(x = (i * -4).dp)
                    )
                }
            }
        }

        // 🔹 Draggable Circle (White)
        Box(
            modifier = Modifier
                .padding(outerPadding)
                .offset { IntOffset(dragAmount.value.roundToInt(), 0) }
                .size(circleSize)
                .background(Color.White, CircleShape)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { change, dragDelta ->
                            change.consume()
                            coroutineScope.launch {
                                val newValue = (dragAmount.value + dragDelta)
                                    .coerceIn(0f, maxOffset)
                                dragAmount.snapTo(newValue)
                            }
                        },
                        onDragEnd = {
                            coroutineScope.launch {
                                if (dragAmount.value > maxOffset * 0.7f) {
                                    dragAmount.animateTo(maxOffset)
                                    onSwipeComplete()
                                    dragAmount.snapTo(0f)
                                } else {
                                    dragAmount.animateTo(0f)
                                }
                            }
                        }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            // Larger lock icon inside the white circle
            Image(
                painter = painterResource(id = com.example.compose.R.drawable.ic_lock),
                contentDescription = null,
                modifier = Modifier.size(32.dp) 
            )
        }
    }
}

@Composable
fun Indicator(currentPage: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 40.dp, start = 24.dp, end = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(3) { index ->
            Box(
                modifier = Modifier
                    .height(2.5.dp)
                    .weight(1f)
                    .background(
                        if (currentPage == index) Color.White else Color.White.copy(0.3f)
                    )
            )
        }
    }
}

@Composable
fun OnboardingPage() {
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = com.example.compose.R.drawable.ic_get_started),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(0.3f),
                            Color.Transparent,
                            Color.Black.copy(0.9f)
                        )
                    )
                )
        )

        Column(
            Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(100.dp))

            Text(
                "Marasam - Your\nWedding, Simplified",
                color = Color.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 42.sp
            )

            Spacer(Modifier.height(16.dp))

            Text(
                "Connecting Couples with Trusted Wedding Vendors",
                color = Color.White.copy(0.8f),
                fontSize = 17.sp,
                lineHeight = 24.sp
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewOnboarding() {
    OnboardingScreen {}
}
