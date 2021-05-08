package ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier

@Composable
inline fun <reified T> table(list: Iterable<T>) {
    val args = T::class.java.declaredFields
    val privates =
        args.filter { Modifier.isPrivate(it.modifiers) && !it.name.contains("id", ignoreCase = true) }.toList()
    var fraction = args.size.toFloat() / 10f
    if (fraction == 0f) fraction += 1

    Row(modifier = androidx.compose.ui.Modifier.padding(10.dp, 0.dp)) {
        Text(
            "No.",
            modifier = androidx.compose.ui.Modifier
                .border(BorderStroke(1.dp, Color.Black)).padding(10.dp)
                .weight(fraction).fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        for (arg in privates) {
            Text(
                arg.name,
                modifier = androidx.compose.ui.Modifier
                    .border(BorderStroke(1.dp, Color.Black)).padding(10.dp)
                    .weight(fraction).fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }

    ScrollableColumn(modifier = androidx.compose.ui.Modifier.padding(10.dp, 0.dp)) {
        list.forEachIndexed { idx, v ->
            Row {
                Text(
                    "${idx + 1}",
                    modifier = androidx.compose.ui.Modifier.border(BorderStroke(1.dp, Color.Black))
                        .padding(10.dp)
                        .weight(fraction).fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                for (arg in privates) {
                    val field = T::class.java.getDeclaredField(arg.name)
                    field.isAccessible = true
                    val value = field.get(v).toString()
                    Text(
                        value,
                        modifier = androidx.compose.ui.Modifier.border(BorderStroke(1.dp, Color.Black))
                            .padding(10.dp)
                            .weight(fraction).fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                }
            }
        }
    }
}

@Composable
fun dropDownList(
    requestToOpen: Boolean = false,
    list: List<String>,
    request: (Boolean) -> Unit,
    selectedString: (String) -> Unit
) {
    DropdownMenu(
        dropdownModifier = androidx.compose.ui.Modifier
            .preferredHeight(100.dp)
            .border(BorderStroke(2.dp, Color.Black)),
        toggle = {
            // Implement your toggle
        },
        expanded = requestToOpen,
        onDismissRequest = { request(false) },
    ) {
        list.forEachIndexed { idx, value ->
            DropdownMenuItem(
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                onClick = {
                    request(false)
                    selectedString(value)
                }
            ) {
                Text(
                    "$idx- $value", modifier =
                    androidx.compose.ui.Modifier.align(Alignment.Start)
                )
            }
        }
    }
}