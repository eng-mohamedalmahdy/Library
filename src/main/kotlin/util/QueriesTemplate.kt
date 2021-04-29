package util

object QueriesTemplate {
    fun insertTemplate(entityName: String, argsNames: List<String>, args: List<String>): String {
        val prefix = if (argsNames[0].isDigit()) "" else "\'"
        val argsNamesString = argsNames.fold("") { acc, s -> "$acc$prefix$s$prefix, " }.removeLast(",")
        val argsString = args.fold("") { acc, s -> "$acc$s, " }.removeLast(",")
        return "INSERT INTO $entityName ($argsNamesString) VALUES ($argsString);"
    }

    fun updateTemplate(entityName: String, argsNames: List<String>, args: List<String>): String {
        val prefix = if (argsNames[0].isDigit()) "" else "\'"
        val zippedList = argsNames.zip(args) { argName: String, arg: String -> "$argName = $prefix$arg$prefix" }
        val values = zippedList.fold("") { acc, s -> "$acc$s, " }.removeLast(",")
        return "UPDATE $entityName SET $values"
    }

    fun updateById(entityName: String, argsNames: List<String>, args: List<String>, id: Int): String {
        val prefix = if (argsNames[0].isDigit()) "" else "\'"
        val zippedList = argsNames.zip(args) { argName: String, arg: String -> "$argName = $prefix$arg$prefix" }
        val values = zippedList.fold("") { acc, s -> "$acc$s, " }.removeLast(",")
        return "UPDATE $entityName SET $values WHERE id = $id ;"
    }

    fun getAllTemplate(entityName: String) = "SELECT * FROM $entityName ;"

    fun getById(entityName: String, id: Int) = "SELECT * FROM $entityName WHERE id = $id ;"

    fun deleteById(entityName: String, id: Int) = "DELETE FROM $entityName WHERE id = $id ;"


}

