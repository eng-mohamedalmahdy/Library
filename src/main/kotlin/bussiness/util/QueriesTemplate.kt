package bussiness.util

object QueriesTemplate {
    fun insertTemplate(entityName: String, argsNames: List<String>, args: List<String>): String {
        val argsNamesString = argsNames.fold("") { acc, s -> "$acc$s, " }.removeLast(",")
        val argsString = args.fold("") { acc, s ->
            val prefix = if (s.isDigit()&&s.isNotEmpty()) "" else "\'"
            "$acc$prefix$s$prefix, "
        }.removeLast(",")
        return "INSERT INTO $entityName ($argsNamesString) VALUES ($argsString);"
    }

    fun updateTemplate(entityName: String, argsNames: List<String>, args: List<String>): String {
        val zippedList = argsNames.zip(args) { argName: String, arg: String ->
            val prefix = if (arg.isDigit()&&arg.isNotEmpty()) "" else "\'"
            "$argName = $prefix$arg$prefix"
        }
        val values = zippedList.fold("") { acc, s -> "$acc$s, " }.removeLast(",")
        return "UPDATE $entityName SET $values"
    }

    fun updateById(entityName: String, argsNames: List<String>, args: List<String>, id: Int): String {

        val zippedList = argsNames.zip(args) { argName: String, arg: String ->
            val prefix = if (arg.isDigit()&&arg.isNotEmpty()) "" else "\'"
            "$argName = $prefix$arg$prefix"
        }
        val values = zippedList.fold("") { acc, s -> "$acc$s, " }.removeLast(",")
        return "UPDATE $entityName SET $values WHERE id = $id ;"
    }

    fun getAllTemplate(entityName: String) = "SELECT * FROM $entityName ;"

    fun getById(entityName: String, id: Int) = "SELECT * FROM $entityName WHERE id = $id ;"

    fun deleteById(entityName: String, id: Int) = "DELETE FROM $entityName WHERE id = $id ;"


}

