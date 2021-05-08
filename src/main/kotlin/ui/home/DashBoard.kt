package ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import bussiness.repository.DataRepository
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DesktopDialogProperties
import bussiness.pojo.*
import bussiness.util.Constants
import domain.cache.entities.AuthorDataAccess
import domain.cache.entities.BookDataAccess
import domain.cache.entities.SectionDataAccess
import ui.dropDownList
import ui.table


@Composable
fun dashboardSection(currentTab: String) {
    var adding by remember { mutableStateOf(false) }
    var updating by remember { mutableStateOf(false) }
    var values: MutableList<ListedArgs> by remember {
        mutableStateOf(DataRepository.getAll(AuthorDataAccess).toMutableList())
    }
    var newValue: ListedArgs by remember { mutableStateOf(Author()) }


    @Composable
    fun addAuthorDialogContent(): @Composable (() -> Unit) = {
        var authorName by remember { mutableStateOf("") }
        var birthDate by remember { mutableStateOf("") }
        var age by remember { mutableStateOf("") }

        fun updateAuthor() {
            newValue =
                Author(_name = authorName, _birthDate = birthDate, _age = if (age.isEmpty()) 0 else age.toInt())
        }
        Column {

            OutlinedTextField(
                value = authorName,
                onValueChange = {
                    authorName = it
                    updateAuthor()
                },
                label = { Text("Author Name") }
            )
            OutlinedTextField(
                value = birthDate,
                onValueChange = {
                    birthDate = it
                    updateAuthor()
                },
                label = { Text("Birth date") }
            )
            OutlinedTextField(
                value = age,
                onValueChange = {
                    age = it
                    updateAuthor()
                },
                label = { Text("Age") }
            )

        }

    }

    @Composable
    fun addSectionDialogContent(): @Composable (() -> Unit) = {

        var sectionName by remember { mutableStateOf("") }
        var sectionLocation by remember { mutableStateOf("") }

        fun updateSection() {
            newValue = Section(name = sectionName, location = sectionLocation)
        }
        Column {
            OutlinedTextField(
                value = sectionName,
                onValueChange = {
                    sectionName = it
                    updateSection()
                },
                label = { Text("Section Name") }
            )
            OutlinedTextField(
                value = sectionLocation,
                onValueChange = {
                    sectionLocation = it
                    updateSection()
                },
                label = { Text("Section Location") }
            )
        }
    }

    @Composable
    fun addBookDialogContent(): @Composable (() -> Unit) = {

        val authors = DataRepository.getAuthors()
        val authorsNames = authors.map { it.name }

        val sections = DataRepository.getSections()
        val sectionsNames = sections.map { it.name }

        val isAuthorOpen = remember { mutableStateOf(false) }
        val isSectionOpen = remember { mutableStateOf(false) }

        val bookTitle = remember { mutableStateOf("") }
        val dateOfPublication = remember { mutableStateOf("") }
        val numberOfPages = remember { mutableStateOf(1) }
        val cover = remember { mutableStateOf("") }
        val description = remember { mutableStateOf("") }
        val currentSection = remember { mutableStateOf("         ") }
        val currentAuthor = remember { mutableStateOf("         ") }


        fun updateBook() {
            newValue = Book(
                0, bookTitle.value, dateOfPublication.value,
                numberOfPages.value, cover.value, description.value,
                authors.firstOrNull { it.name == currentAuthor.value }?.id ?: -1,
                sections.firstOrNull { it.name == currentSection.value }?.id ?: -1

            )
        }
        Column {
            OutlinedTextField(
                value = bookTitle.value,
                onValueChange = {
                    bookTitle.value = it
                    updateBook()
                },
                label = { Text("Book Title") }
            )
            OutlinedTextField(
                value = description.value,
                onValueChange = {
                    description.value = it
                    updateBook()
                },
                label = { Text("Description") }
            )
            OutlinedTextField(
                value = numberOfPages.value.toString(),
                onValueChange = {
                    numberOfPages.value = it.toInt()
                    updateBook()
                },
                label = { Text("Number of pages") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = dateOfPublication.value,
                onValueChange = {
                    dateOfPublication.value = it
                    updateBook()
                },
                label = { Text("date of publication") }
            )

            Row {
                Column {
                    Box(
                        modifier = Modifier.padding(5.dp)
                            .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(20)),
                        Alignment.CenterStart
                    ) {
                        TextButton(
                            onClick = { isAuthorOpen.value = true },
                        ) {
                            Text("Author: ${currentAuthor.value}")
                        }
                    }

                    dropDownList(
                        isAuthorOpen.value,
                        authorsNames,
                        { isAuthorOpen.value = it }, { currentAuthor.value = it })
                }
                Column {
                    Box(
                        modifier = Modifier.padding(5.dp)
                            .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(20)),
                        Alignment.CenterStart
                    ) {
                        TextButton(
                            onClick = { isSectionOpen.value = true },
                        ) {
                            Text("Section: ${currentSection.value}")
                        }
                    }
                    dropDownList(
                        isSectionOpen.value,
                        sectionsNames,
                        { isSectionOpen.value = it }, { currentSection.value = it })
                }
            }
        }
    }

    @Composable
    fun showAddDialog() {
        AlertDialog(
            properties = DesktopDialogProperties(
                title = "Add ${currentTab.toLowerCase().capitalize()}",
                size = IntSize(500, 450)
            ),
            onDismissRequest = { adding = false },
            title = { Text("Add ${currentTab.toLowerCase().capitalize()}") },
            confirmButton = {
                Button(onClick = {
                    updating = true
                    when (currentTab) {
                        Constants.PAGE_AUTHOR -> {
                            DataRepository.add(AuthorDataAccess, newValue as Author)
                            values.add(newValue)
                        }
                        Constants.PAGE_SECTION -> {
                            DataRepository.add(SectionDataAccess, newValue as Section)
                            values.add(newValue)
                        }
                        Constants.PAGE_BOOK -> {
                            DataRepository.add(BookDataAccess, newValue as Book)
                            values.add(newValue)
                        }
                    }
                    adding = false
                    updating = false
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(onClick = {
                    adding = false
                }) {
                    Text("Dismiss")
                }
            },
            text =
            when (currentTab) {
                Constants.PAGE_AUTHOR -> addAuthorDialogContent()
                Constants.PAGE_SECTION -> addSectionDialogContent()
                Constants.PAGE_BOOK -> addBookDialogContent()
                else -> emptyContent()
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Button(modifier = Modifier.padding(10.dp), onClick = { adding = true }) {
            Text("Add new ${currentTab.toLowerCase().capitalize()}")
        }
        if (adding) showAddDialog()

        if (!updating) {
            when (currentTab) {
                Constants.PAGE_AUTHOR -> {
                    values = DataRepository.getAll(AuthorDataAccess).toMutableList()
                    table(values as MutableList<Author>)
                }
                Constants.PAGE_SECTION -> {
                    values = DataRepository.getAll(SectionDataAccess).toMutableList()
                    table(values as MutableList<Section>)
                }
                Constants.PAGE_BOOK -> {
                    values = DataRepository.getAll(BookDataAccess).toMutableList()
                    values.map { BookSummary(it as Book) }
                    table(values as MutableList<BookSummary>)
                }
            }

        }
    }
}