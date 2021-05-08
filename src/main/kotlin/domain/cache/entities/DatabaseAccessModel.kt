package domain.cache.entities


import androidx.compose.runtime.toMutableStateList
import domain.cache.DatabaseInstance
import bussiness.pojo.ListedArgs
import bussiness.util.QueriesTemplate

interface DatabaseAccessModel<T : ListedArgs> {
    val ENTITY_NAME: String

    fun insert(t: T) =
        DatabaseInstance.stmt
            .execute(
                QueriesTemplate.insertTemplate(
                    ENTITY_NAME,
                    t.args.keys.toMutableStateList(),
                    t.args.values.toMutableStateList()
                )
            )


    fun getById(id: Int): T
    fun getAll(): List<T>

    fun update(oldItemId: Int, newItem: T) = DatabaseInstance.stmt.execute(
        QueriesTemplate.updateById(
            ENTITY_NAME,
            newItem.args.keys.toMutableStateList(),
            newItem.args.values.toMutableStateList(),
            oldItemId
        )

    )

    fun delete(id: Int) =
        DatabaseInstance.stmt.execute(QueriesTemplate.deleteById(ENTITY_NAME, id))
}