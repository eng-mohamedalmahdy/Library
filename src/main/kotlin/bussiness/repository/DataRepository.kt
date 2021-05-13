package bussiness.repository

import bussiness.pojo.ListedArgs
import domain.cache.entities.AuthorDataAccess
import domain.cache.entities.DatabaseAccessModel
import domain.cache.entities.SectionDataAccess

object DataRepository {

    fun <T : ListedArgs>
            getAll(databaseAccessModel: DatabaseAccessModel<T>) = databaseAccessModel.getAll()

    fun <T : ListedArgs> add(databaseAccessModel: DatabaseAccessModel<T>, value: T) = databaseAccessModel.insert(value)

    fun getSections() = SectionDataAccess.getAll()

    fun getAuthors() = AuthorDataAccess.getAll()

    fun <T : ListedArgs> deleteById(databaseAccessModel: DatabaseAccessModel<T>, id: Int) =
        databaseAccessModel.delete(id)

    fun <T : ListedArgs> update(databaseAccessModel: DatabaseAccessModel<T>, id: Int, edited: T) =
        databaseAccessModel.update(id, edited)
}