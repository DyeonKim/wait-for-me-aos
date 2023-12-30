package com.jukco.waitforme.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.jukco.waitforme.ui.theme.MainBlue
import com.jukco.waitforme.ui.theme.NotoSansKR
import com.jukco.waitforme.ui.theme.WaitForMeTheme

@Composable
fun FilterButtonGroup(
    items: List<String>,
    defaultSelectedItemIndex: Int = 0,
    color: Color,
    onItemSelected: (selectedItemIndex: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedIndex by remember {
        mutableStateOf(defaultSelectedItemIndex)
    }

    Row {
        items.forEachIndexed { index, item ->
            OutlinedButton(
                shape = RoundedCornerShape(
                    topStart = 4.dp,
                    topEnd = 10.dp,
                    bottomStart = 4.dp,
                    bottomEnd = 4.dp,
                ),
                border = BorderStroke(1.dp, color),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (selectedIndex == index) color else Color.Transparent,
                ),
                contentPadding = PaddingValues(8.dp),
                onClick = {
                    selectedIndex = index
                    onItemSelected(selectedIndex)
                },
            ) {
                Text(
                    text = item,
                    style = TextStyle(
                        fontFamily = NotoSansKR,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                        letterSpacing = (-0.05).em,
                    ),
                    color = if (selectedIndex == index) Color.White else color,
                )
            }
            if (index != items.size - 1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Preview
@Composable
private fun FilterButtonGroupPreview() {
    WaitForMeTheme {
        FilterButtonGroup(items = listOf("1", "2", "3"), color = MainBlue, onItemSelected = {})
    }
}
