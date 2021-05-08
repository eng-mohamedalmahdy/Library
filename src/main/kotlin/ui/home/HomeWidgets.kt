package ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ui.ColorPalate.primaryColor

@Composable
fun homeTabs(bookAction: () -> Unit, authorAction: () -> Unit, sectionAction: () -> Unit) =
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            bookAction,
            Modifier.size(180.dp, 80.dp).padding(16.dp),
            colors = ButtonConstants.defaultButtonColors(primaryColor)
        )
        { Text("Books") }
        Button(
            authorAction,
            Modifier.size(180.dp, 80.dp).padding(16.dp),
            colors = ButtonConstants.defaultButtonColors(primaryColor)
        )
        { Text("Authors") }
        Button(
            sectionAction,
            Modifier.size(180.dp, 80.dp).padding(16.dp),
            colors = ButtonConstants.defaultButtonColors(primaryColor)
        )
        { Text("Sections") }
    }

@Composable
fun homePages(currentPage: String) = Column(
    Modifier.padding(20.dp)
        .clip(RoundedCornerShape(10.dp))
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.End,
    ) {
        dashboardSection(currentPage)
    }
}



