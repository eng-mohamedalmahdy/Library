package cache.entities

import cache.DatabaseInstance
import pojo.Author
import util.QueriesTemplate


object AuthorDataAccess : DatabaseAccessModel<Author> {
    override val ENTITY_NAME: String = "authors"

    override fun getById(id: Int): Author {
        val q = DatabaseInstance.stmt.executeQuery(
            QueriesTemplate.getById(SectionDataAccess.ENTITY_NAME, id)
        )
        q.next()
        return Author(q.getInt(1), q.getString(2), q.getString(3), q.getInt(4))
    }

    override fun getAll(): List<Author> {
        val q = DatabaseInstance.stmt.executeQuery(
            QueriesTemplate.getAllTemplate(SectionDataAccess.ENTITY_NAME)
        )
        val res = mutableListOf<Author>()
        while (q.next()) {
            res.add(Author(q.getInt(1), q.getString(2), q.getString(3), q.getInt(4)))
        }
        return res.toList()
    }
}