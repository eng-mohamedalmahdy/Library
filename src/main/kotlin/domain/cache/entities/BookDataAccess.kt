package domain.cache.entities

import domain.cache.DatabaseInstance
import bussiness.pojo.Book
import bussiness.util.QueriesTemplate

object BookDataAccess : DatabaseAccessModel<Book> {
    override val ENTITY_NAME = "books"

    override fun getById(id: Int): Book {
        val q = DatabaseInstance.stmt.executeQuery(QueriesTemplate.getById(ENTITY_NAME, id))
        q.next()
        return Book(
            q.getInt(1),
            q.getString(2),
            q.getString(3),
            q.getInt(4),
            q.getString(5),
            q.getString(6),
            q.getInt(7),
            q.getInt(8)
        )
    }

    override fun getAll(): List<Book> {
        val q = DatabaseInstance.stmt.executeQuery(QueriesTemplate.getAllTemplate(ENTITY_NAME))
        val res = mutableListOf<Book>()
        while (q.next()) {
            res.add(
                Book(
                    q.getInt(1),
                    q.getString(2),
                    q.getString(3),
                    q.getInt(4),
                    q.getString(5),
                    q.getString(6),
                    q.getInt(7),
                    q.getInt(8)
                )
            )
        }
        return res.toList()
    }

}