fun main() {
    val note = Note(0, "Точкаточка", mutableListOf(), mutableListOf())
    val note2 = Note(0, "ТочкаточкаЗапятая", mutableListOf(), mutableListOf())
    noteService.addNote(note)
    noteService.addNote(note2)
    noteService.addComment(1, Note.Comment(55, "Тестовый комментарий"))
    noteService.addComment(1, Note.Comment(56, "Тестовый комментарий 2"))
    println(noteService.findCommentById(56, 1))
    println(noteService.getComment(1))
    noteService.deleteComment(55, 1)
    println(noteService.getComment(1))
    noteService.restoreComment(55,1)
    println(noteService.getComment(1))
    println(noteService.allNotes)
    noteService.deleteNote(2)
    noteService.update(1)
    noteService.updateComment(56, 1)
    println(noteService.allNotes)

}

data class Note(
    var id: Int,
    var text: String,
    var comment: MutableList<Comment>,
    var deleteComment: MutableList<Comment>,
) {
    data class Comment(val commentId: Int, var textComment: String)
}

