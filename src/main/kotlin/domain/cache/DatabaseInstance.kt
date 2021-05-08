package domain.cache

import java.sql.DriverManager

object DatabaseInstance {
    private val dataBaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "")
    val stmt = dataBaseConnection.createStatement()!!
}