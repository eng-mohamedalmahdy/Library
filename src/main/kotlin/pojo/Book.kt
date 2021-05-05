package pojo

data class Book(
    private var _id: Int = 0,
    private var _title: String,
    private var _dataOfPublication: String,
    private var _numberOfPages: Int,
    private var _cover: String,
    private var _description: String,
    private var _sectionId: Int,
    private var _authorId: Int,
) : ListedArgs {

    var id: Int = _id

    var title: String = _title
        set(value) {
            field = value
            args["title"] = value
        }

    var dataOfPublication: String = _dataOfPublication
        set(value) {
            field = value
            args["date_of_publication"] = value
        }
    var numberOfPages: Int = _numberOfPages
        set(value) {
            field = value
            args["page_num"] = value.toString()
        }

    var cover: String = _cover
        set(value) {
            field = value
            args["page_num"] = numberOfPages.toString()
        }

    var description: String = _description
        set(value) {
            field = value
            args["description"] = description
        }
    var sectionId: Int = _sectionId
        set(value) {
            field = value
            args["sectionID"] = value.toString()
        }
    var authorId: Int = _authorId
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

}
