package pojo

data class Section(
    private var _id: Int = 0,
    private var _name: String,
    private var _location: String
) : ListedArgs {
    var id: Int = _id


    var name: String = _name
        set(value) {
            field = value
            args["name"] = value
        }

    var location: String = _location
        set(value) {
            field = value
            args["location"] = value
        }
    override val args
        get() = mutableMapOf(
            "name" to name, "location" to location
        )
}
