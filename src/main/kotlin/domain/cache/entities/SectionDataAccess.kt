package domain.cache.entities

import domain.cache.DatabaseInstance
import bussiness.util.QueriesTemplate
import bussiness.pojo.Section

object SectionDataAccess : DatabaseAccessModel<Section> {

    override val ENTITY_NAME = "sections"

    override fun getById(id: Int): Section {
        val q = DatabaseInstance.stmt.executeQuery(QueriesTemplate.getById(ENTITY_NAME, id))
        q.next()
        return Section(q.getInt(1), q.getString(2), q.getString(3))
    }

    override fun getAll(): List<Section> {
        val q = DatabaseInstance.stmt.executeQuery(QueriesTemplate.getAllTemplate(ENTITY_NAME))
        val res = mutableListOf<Section>()
        while (q.next()) {
            res.add(Section(q.getInt(1), q.getString(2), q.getString(3)))
        }
        return res.toList()
    }

}