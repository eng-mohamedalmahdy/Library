import cache.entities.SectionDataAccess

fun main() {
    val section = SectionDataAccess.getById(4)
    section.location = "Second Floor"
    SectionDataAccess.update(section.id, section)

}