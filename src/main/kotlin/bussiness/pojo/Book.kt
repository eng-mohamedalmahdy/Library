package bussiness.pojo

class Book(
    id: Int = 0,
    title: String = "",
    dataOfPublication: String = "",
    numberOfPages: Int = 0,
    cover: String = "",
    description: String = "",
    sectionId: Int = -1,
    authorId: Int = -1,
) : ListedArgs {


    var id: Int = id

    var title: String = title
        set(value) {
            field = value
            args["title"] = value
        }

    var dataOfPublication: String = dataOfPublication
        set(value) {
            field = value
            args["date_of_publication"] = value
        }
    var numberOfPages: Int = numberOfPages
        set(value) {
            field = value
            args["page_num"] = value.toString()
        }

    var cover: String = cover
        set(value) {
            field = value
            args["page_num"] = numberOfPages.toString()
        }

    var description: String = description
        set(value) {
            field = value
            args["description"] = description
        }
    var sectionId: Int = sectionId
        set(value) {
            field = value
            args["sectionID"] = value.toString()
        }
    var authorId: Int = authorId
        set(value) {
            field = value
            args["AutherID"] = value.toString()
        }

    override val args: MutableMap<String, String>
        get() = mutableMapOf(
            "title" to title,
            "date_of_publication" to dataOfPublication,
            "page_num" to numberOfPages.toString(),
            "image" to cover,
            "description" to description,
            "sectionID" to sectionId.toString(),
            "AutherID" to authorId.toString(),
        )

    override fun toString(): String {
        return "Book(id=$id, title='$title', dataOfPublication='$dataOfPublication', numberOfPages=$numberOfPages, cover='$cover', description='$description', sectionId=$sectionId, authorId=$authorId)"
    }


}
