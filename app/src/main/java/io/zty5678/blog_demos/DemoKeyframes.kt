package com.example.myapplication.demo

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.zty5678.blog_demos.R
import kotlinx.coroutines.launch

/**
 * 实现了摇头动画和点头动画
 * https://docs.helpscout.com/article/1250-beacon-jumpstart-guide
 */
@Preview
@Composable
fun YepAnimationpreview() {
    YepAnimation()
}

@Composable
fun YepAnimation() {
    var isYesAnimating by remember { mutableStateOf(false) }
    var isNoAnimating by remember { mutableStateOf(false) }

    val animationDuration = 1500 // 总动画时间（不包括延迟）
    val initialDelay = 50 // 初始延迟
    val totalDuration = animationDuration + initialDelay

    // 定义关键帧的相对时间点（百分比）
    val keyFrames = listOf(
        0f,
        0.1f, 0.2f,
        0.3f, 0.4f,
        0.5f, 0.6f,
        0.7f,
        0.8f, 0.9f,
        1f
    )

    // 定义每个关键帧的 Y 偏移值
    val multiply = 1.6f

    val offsets = listOf(
        0f,
        -3.5f * multiply, 3.5f * multiply,
        -3.5f * multiply, 3.5f * multiply,
        -3.5f * multiply, 3.5f * multiply,
        -3.5f * multiply,
        2.6f * multiply, -2.6f * multiply,
        0f
    )

    val offsetY by animateFloatAsState(
        targetValue = if (isYesAnimating) 1f else 0f,
        animationSpec = keyframes {
            durationMillis = totalDuration
            0f at 0 using LinearEasing // 初始延迟
            keyFrames.zip(offsets).forEach { (time, offset) ->
                offset at (initialDelay + (time * animationDuration).toInt()) using LinearEasing
            }
        }
    )

    val offsetX by animateFloatAsState(
        targetValue = if (isNoAnimating) 1f else 0f,
        animationSpec = keyframes {
            durationMillis = totalDuration
            0f at 0 using LinearEasing // 初始延迟
            keyFrames.zip(offsets).forEach { (time, offset) ->
                offset at (initialDelay + (time * animationDuration).toInt()) using LinearEasing
            }
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        )
    {
        Row {


            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = CircleShape
                    )

                    .background(Color(0xffFFE8B5))
                    .clickable {
                        isYesAnimating = !isYesAnimating
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_yes),
                    contentDescription = "Yes icon",
                    modifier = Modifier
                        .size(100.dp)
                        .offset(y = offsetY.dp)
                        .clip(CircleShape)
                )

            }
            Spacer(
                modifier =
                Modifier.width(20.dp)
            )


            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = CircleShape
                    )

                    .background(Color(0xffFFE8B5))
                    .clickable {

                        isNoAnimating = !isNoAnimating
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_no),
                    contentDescription = "No icon",
                    modifier = Modifier
                        .size(100.dp)
                        .offset(x = offsetX.dp)
                        .clip(CircleShape)
                )

            }
        }

    }


}