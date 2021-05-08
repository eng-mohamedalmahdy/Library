package bussiness.pojo

class Section(id: Int = 0, name: String, location: String) : ListedArgs {
    var id: Int = id


    var name: String = name
        set(value) {
            field = value
            args["name"] = value
        }

    var location: String = location
        set(value) {
            field = value
            args["location"] = value
        }
    override val args
        get() = mutableMapOf(
            "name" to name, "location" to location
        )
}
