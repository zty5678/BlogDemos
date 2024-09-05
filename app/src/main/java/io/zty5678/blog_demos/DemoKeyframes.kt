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
 * implement the animation of "Yep" and "Nope" in page:
 * https://docs.helpscout.com/article/1250-beacon-jumpstart-guide
 */
@Preview
@Composable
fun YepAndNopeAnimationPreview() {
    YepAndNopeAnimation()
}

@Composable
fun YepAndNopeAnimation() {
    var isYesAnimating by remember { mutableStateOf(false) }
    var isNopeAnimating by remember { mutableStateOf(false) }

    val animationDuration = 1500 //total animation duration ms

    //defines 11 key points in time for the animation, from 0 (start) to 1 (end).
    val keyFrames = listOf(
        0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1f
    )

    //This defines the offset values for each keyframe.
    val multiply = 1.6f /*The multiply factor allows easy scaling of the animation intensity.*/
    val offsets = listOf(
        0f,
        -3.5f * multiply,
        3.5f * multiply,
        -3.5f * multiply,
        3.5f * multiply,
        -3.5f * multiply,
        3.5f * multiply,
        -3.5f * multiply,
        2.6f * multiply,
        -2.6f * multiply,
        0f
    )
    //This creates an animated float value for vertical movement.
    //It's triggered when isYesAnimating becomes true.
    val offsetY by animateFloatAsState(
        targetValue = if (isYesAnimating) 0.01f else 0f,
        animationSpec = keyframes {
            durationMillis = animationDuration
            //Each keyframe is mapped to its corresponding offset.
            keyFrames.zip(offsets).forEach { (time, offset) ->
                //The timing for each keyframe is calculated as a proportion of the total duration
                offset at (time * animationDuration).toInt() using LinearEasing
            }
        })
    //This creates an animated float value for horizontal movement.
    //It's triggered when isNopeAnimating becomes true.
    val offsetX by animateFloatAsState(
        targetValue = if (isNopeAnimating) 0.01f else 0f,
        animationSpec = keyframes {
            durationMillis = animationDuration
            //Each keyframe is mapped to its corresponding offset.
            keyFrames.zip(offsets).forEach { (time, offset) ->
                //The timing for each keyframe is calculated as a proportion of the total duration
                offset at (time * animationDuration).toInt() using LinearEasing
            }
        })

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Row {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp, color = Color.White, shape = CircleShape
                    )

                    .background(Color(0xffFFE8B5))
                    .clickable {
                        isYesAnimating = !isYesAnimating
                    }, contentAlignment = Alignment.Center
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
                modifier = Modifier.width(20.dp)
            )


            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp, color = Color.White, shape = CircleShape
                    )

                    .background(Color(0xffFFE8B5))
                    .clickable {

                        isNopeAnimating = !isNopeAnimating
                    }, contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_no),
                    contentDescription = "Nope icon",
                    modifier = Modifier
                        .size(100.dp)
                        .offset(x = offsetX.dp)
                        .clip(CircleShape)
                )

            }
        }

    }


}