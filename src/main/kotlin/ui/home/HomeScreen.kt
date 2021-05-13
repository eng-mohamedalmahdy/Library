package ui.home

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import bussiness.util.Constants.PAGE_AUTHOR
import bussiness.util.Constants.PAGE_BOOK
import bussiness.util.Constants.PAGE_SECTION


object HomeScreen {

    fun home() = Window(title = "Library") {
        var currentPage by remember { mutableStateOf(PAGE_BOOK) }
        MaterialTheme {
            Row {

                homeTabs({ currentPage = PAGE_BOOK },
                    { currentPage = PAGE_AUTHOR },
                    { currentPage = PAGE_SECTION })

                homePages(currentPage)


            }

        }
    }

}