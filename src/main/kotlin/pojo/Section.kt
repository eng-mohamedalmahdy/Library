package pojo

data class Section(
    var id: Int = 0,
    var name: String,
    var location: String
) : ListedArgs {
    override val argsNames = listOf("name", "location")
    override val args = listOf(name, location)
}
