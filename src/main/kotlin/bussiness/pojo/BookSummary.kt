package bussiness.pojo

import domain.cache.entities.AuthorDataAccess
import domain.cache.entities.SectionDataAccess

class BookSummary(book: Book) {
    var id = book.id
    var title: String = book.title
    var author: String = AuthorDataAccess.getById(book.authorId).name
    var section: String = SectionDataAccess.getById(book.sectionId).name

}