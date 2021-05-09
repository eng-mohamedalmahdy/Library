package bussiness.pojo

class Author(_id: Int = 0, _name: String, _birthDate: String, _age: Int) : ListedArgs {

    constructor() : this(0, "", "", 0)

    var id: Int = _id

    var name: String = _name
        set(value) {
            field = value
            args["name"] = value
        }

    var birthDate: String = _birthDate
        set(value) {
            field = value
            args["birthday"] = value
        }

    var age: Int = _age
        set(value) {
            field = value
            args["age"] = value.toString()
        }

    override val args: MutableMap<String, String>
        get() = mutableMapOf(
            "name" to name,
            "birthday" to birthDate,
            "age" to age.toString()
        )

    override fun toString(): String {
        return "Author(id=$id, name='$name', birthDate='$birthDate', age=$age)"
    }


}
