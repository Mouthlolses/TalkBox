package com.mytalkbox.talkbox.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mytalkbox.talkbox.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Dica Simples", showBackground = true)
@Composable
fun PlainToolTip(
    modifier: Modifier = Modifier,
    plainTooltipText: String = "Add to favorites"
) {

    val tooltipState = rememberTooltipState()
    val scope = rememberCoroutineScope()

    TooltipBox(
        modifier = modifier,
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            PlainTooltip { Text(plainTooltipText) }
        },
        state = tooltipState
    ) {
        IconButton(
            onClick = {
                scope.launch {
                    tooltipState.show()
                }
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_favorite),
                contentDescription = "Add to favorites"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Dica Avançada", showBackground = true)
@Composable
fun RichTooltipExample(
    modifier: Modifier = Modifier,
    richTooltipSubheadText: String = "Rich Tooltip",
    richTooltipText: String = "Rich tooltips support multiple lines of informational text."
) {
    val tooltipState = rememberTooltipState()
    val scope = rememberCoroutineScope()

    TooltipBox(
        modifier = modifier,
        positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
        tooltip = {
            RichTooltip(
                title = { Text(richTooltipSubheadText) }
            ) {
                Text(richTooltipText)
            }
        },
        state = tooltipState
    ) {
        IconButton(onClick = {
            scope.launch {
                tooltipState.show()
                delay(6000)
                tooltipState.dismiss()

            }
        }) {
            Icon(
                painter = painterResource(R.drawable.ic_info),
                contentDescription = "Show more information"
            )
        }
    }
}