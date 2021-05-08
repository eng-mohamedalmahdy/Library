package bussiness.pojo

import domain.cache.entities.AuthorDataAccess
import domain.cache.entities.SectionDataAccess

class BookSummary(book: Book) {
    var id = book.id
    var title = book.title
    var author = AuthorDataAccess.getById(book.authorId).name
    var section = SectionDataAccess.getById(book.sectionId).name

}