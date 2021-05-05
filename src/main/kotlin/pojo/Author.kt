package pojo

data class Author(
    private var _id: Int = 0,
    private var _name: String,
    private var _birthDate: String,
    private var _age: Int,
) : ListedArgs {
    var id: Int = _id

    var name: String = _name
        set(value) {
            field = value
            args["name"] = value
        }

    var birthDate: String = _birthDate
        set(value) {
            field = value
            args["birthDate"] = value
        }

    var age: Int = _age
        set(value) {
            field = value
            args["age"] = value.toString()
        }

    override val args: MutableMap<String, String>
        get() = mutableMapOf(
            "name" to name,
            "birthDate" to birthDate,
            "age" to age.toString()
        )
}
