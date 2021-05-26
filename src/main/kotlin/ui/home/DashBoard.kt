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
    var addingInDatabase by remember { mutableStateOf(false) }
    var updatingDatabase by remember { mutableStateOf(false) }

    var updating by remember { mutableStateOf(false) }
    var values: List<ListedArgs> by remember {
        mutableStateOf(DataRepository.getAll(AuthorDataAccess).toMutableList())
    }
    val authors = DataRepository.getAuthors()
    val sections = DataRepository.getSections()

    var newValue: ListedArgs by remember { mutableStateOf(Author()) }
    val filter = remember { mutableStateOf("") }

    fun applyFilter() =
        values.filter { listedArg ->
            var res = false
            listedArg.args.forEach { entry ->
                res = entry.value.contains(filter.value, true) || res
            }
            res
        }


    @Composable
    fun addAuthorDialogContent(): @Composable (() -> Unit) = {
        var authorName by remember { mutableStateOf(if (newValue is Author) (newValue as Author).name else "") }
        var birthDate by remember { mutableStateOf(if (newValue is Author) (newValue as Author).birthDate else "") }
        var age by remember { mutableStateOf(if (newValue is Author) (newValue as Author).age.toString() else "") }

        fun updateAuthor() {
            val id = if (newValue is Author) (newValue as Author).id else -1
            newValue =
                Author(
                    _id = id,
                    _name = authorName,
                    _birthDate = birthDate,
                    _age = if (age.isEmpty()) 0 else age.toInt()
                )
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

        var sectionName by remember { mutableStateOf(if (newValue is Section) (newValue as Section).name else "") }
        var sectionLocation by remember { mutableStateOf(if (newValue is Section) (newValue as Section).location else "") }

        fun updateSection() {
            val id = if (newValue is Section) (newValue as Section).id else -1
            newValue = Section(id = id, name = sectionName, location = sectionLocation)
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

        val authorsNames = authors.map { it.name }

        val sectionsNames = sections.map { it.name }

        val isAuthorOpen = remember { mutableStateOf(false) }
        val isSectionOpen = remember { mutableStateOf(false) }

        val bookTitle = remember { mutableStateOf(if (newValue is Book) (newValue as Book).title else "") }

        val dateOfPublication =
            remember { mutableStateOf(if (newValue is Book) (newValue as Book).dataOfPublication else "") }

        val numberOfPages = remember { mutableStateOf(if (newValue is Book) (newValue as Book).numberOfPages else 0) }
        val cover = remember { mutableStateOf(if (newValue is Book) (newValue as Book).cover else "") }
        val description = remember { mutableStateOf(if (newValue is Book) (newValue as Book).description else "") }
        val currentSection = remember {
            mutableStateOf(if (newValue is Book) authors.first {
                (newValue as Book).sectionId == it.id
            } else "")
        }
        val currentAuthor = remember {
            mutableStateOf(if (newValue is Book) authors.first {
                (newValue as Book).authorId == it.id
            } else "")
        }


        fun updateBook() {
            val id = if (newValue is Book) (newValue as Book).id else -1

            newValue = Book(
                id, bookTitle.value, dateOfPublication.value,
                numberOfPages.value, cover.value, description.value,
                authors.firstOrNull { it.name.contains(currentAuthor.value.toString(), true) }?.id ?: -1,
                sections.firstOrNull { it.name.contains(currentSection.value.toString(), true) }?.id ?: -1

            )
            println(newValue)
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
                        { isAuthorOpen.value = it }, {
                            currentAuthor.value = it
                            updateBook()
                        })
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
                        { isSectionOpen.value = it }, {
                            currentSection.value = it
                            updateBook()
                        })
                }
            }
        }
    }

    @Composable
    fun showAddDialog() {
        val addOrEdit = if (updatingDatabase) "Edit" else "Add"
        AlertDialog(
            properties = DesktopDialogProperties(
                title = "$addOrEdit ${currentTab.toLowerCase().capitalize()}",
                size = IntSize(500, 450)
            ),
            onDismissRequest = { addingInDatabase = false },
            title = { Text("$addOrEdit ${currentTab.toLowerCase().capitalize()}") },
            confirmButton = {
                Button(onClick = {
                    updating = true
                    when (currentTab) {
                        Constants.PAGE_AUTHOR -> {
                            val author = newValue as Author
                            if (updatingDatabase) {
                                DataRepository.update(AuthorDataAccess, author.id, author)
                            } else DataRepository.add(AuthorDataAccess, author)
                        }
                        Constants.PAGE_SECTION -> {
                            val section = newValue as Section
                            if (updatingDatabase) {
                                DataRepository.update(SectionDataAccess, section.id, section)
                            } else DataRepository.add(SectionDataAccess, section)
                        }
                        Constants.PAGE_BOOK -> {
                            val book = newValue as Book
                            if (updatingDatabase) {
                                DataRepository.update(BookDataAccess, book.id, book)
                            } else DataRepository.add(BookDataAccess, book)
                        }
                    }
                    addingInDatabase = false
                    updatingDatabase = false
                    updating = false
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(onClick = {
                    addingInDatabase = false
                    updatingDatabase = false
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

        Row {
            Button(modifier = Modifier.padding(25.dp), onClick = { addingInDatabase = true }) {
                Text("Add new ${currentTab.toLowerCase().capitalize()}")
            }

            OutlinedTextField(
                modifier = Modifier.padding(10.dp),
                value = filter.value,
                onValueChange = {
                    updating = true
                    filter.value = it
                    updating = false
                },
                label = { Text("Search") }
            )

        }

        if (addingInDatabase || updatingDatabase) showAddDialog()

        if (!updating) {
            when (currentTab) {
                Constants.PAGE_AUTHOR -> {
                    values = DataRepository.getAll(AuthorDataAccess).toMutableList()
                    table(applyFilter() as MutableList<Author>, { author: Author ->
                        DataRepository.deleteById(AuthorDataAccess, author.id)
                    }) { author ->
                        newValue = author
                        updatingDatabase = true
                    }
                }
                Constants.PAGE_SECTION -> {
                    values = DataRepository.getAll(SectionDataAccess).toMutableList()
                    table(applyFilter() as MutableList<Section>, { section ->
                        DataRepository.deleteById(SectionDataAccess, section.id)

                    }) { section ->
                        newValue = section
                        updatingDatabase = true
                    }
                }
                Constants.PAGE_BOOK -> {
                    values = DataRepository.getAll(BookDataAccess).toMutableList()
                    table(applyFilter().map { BookSummary(it as Book) } as MutableList<BookSummary>,
                        { bookSummary ->
                            DataRepository.deleteById(BookDataAccess, bookSummary.id)
                        }) { bookSummary ->
                        val author = authors.first { it.name.equals(bookSummary.author, true) }
                        val section = sections.first { it.name.equals(bookSummary.section, true) }
                        newValue = Book(
                            id = bookSummary.id,
                            title = bookSummary.title,
                            authorId = author.id,
                            sectionId = section.id
                        )
                        updatingDatabase = true

                    }
                }
            }

        }
    }

}


