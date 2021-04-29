package pojo

data class Author(
    var id: Int = 0,
    var name: String,
    var birthDate: String,
    var age: Int,
) : ListedArgs {
    override val argsNames = listOf("name", "birthDate", "age")
    override val args = listOf(name, birthDate, age.toString())
}
