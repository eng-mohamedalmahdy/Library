package cache.entities


import cache.DatabaseInstance
import pojo.ListedArgs
import util.QueriesTemplate

interface DatabaseAccessModel<T : ListedArgs> {
    val ENTITY_NAME: String

    fun insert(t: T) =
        DatabaseInstance.stmt
            .execute(QueriesTemplate.insertTemplate(ENTITY_NAME, t.argsNames, t.args))


    fun getById(id: Int): T
    fun getAll(): List<T>

    fun update(oldItemId: Int, newItem: T) = DatabaseInstance.stmt.execute(
        QueriesTemplate.updateById(
            ENTITY_NAME,
            newItem.argsNames,
            newItem.args,
            oldItemId
        )

    )
    
    fun delete(id: Int) =
        DatabaseInstance.stmt.execute(QueriesTemplate.deleteById(ENTITY_NAME, id))
}