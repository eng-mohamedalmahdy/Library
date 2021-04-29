package pojo

data class Book(
    var id: Int = 0,
    var title: String,
    var dataOfPublication: String,
    var numberOfPages: Int,
    var cover: String,
    var description: String,
    var sectionId: Int,
    var authorId: Int,
) : ListedArgs {
    override val argsNames = listOf(
        "title",
        "date_of_publication",
        "page_num",
        "image",
        "description",
        "sectionID",
        "AutherID"
    )
    override val args = listOf(
        title,
        dataOfPublication,
        numberOfPages.toString(),
        cover,
        description,
        sectionId.toString(),
        authorId.toString()
    )
}
