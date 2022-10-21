import org.junit.Test

import org.junit.Assert.*
import java.time.Instant

class noteServiceTest {
    @Test
    fun addNotes() {
        val note = Note(0, "Точкаточка", mutableListOf(), mutableListOf())
        val result = noteService.addNote(note).id
        assertEquals(3, result)
    }

    @Test
    fun updateTrue() {
        val note = Note(0, "Точкаточка", mutableListOf(), mutableListOf())
        noteService.addNote(note)
        val id = 1
        assertTrue(noteService.update(id))
    }

    @Test
    fun addComments() {
        val note = Note(0, "Точкаточка", mutableListOf(), mutableListOf())
        noteService.addNote(note)
        val note2 = Note(0, "ТочкаточкаЗапятая", mutableListOf(), mutableListOf())
        noteService.addNote(note2)
        noteService.addComment(2, Note.Comment(15, "Новый комментарий"))
        assert(note2.comment.isNotEmpty())
    }


    @Test(expected = NoteNotFoundException::class)
    fun deleteNotes(){
    val note = Note(0, "Точкаточка", mutableListOf(), mutableListOf())
        noteService.addNote(note)
        noteService.deleteNote(3)
        noteService.findById(3)
    }


    @Test
    fun deleteComments() {
        val note = Note(0, "Точкаточка", mutableListOf(), mutableListOf())
        noteService.addNote(note)
        noteService.addComment(1, Note.Comment(15, "Новый комментарий"))
        noteService.deleteComment(15, 1)
        assert(note.comment.isEmpty())
    }


    @Test
    fun restoreComment() {
        val note = Note(0, "Точкаточка", mutableListOf(), mutableListOf())
        noteService.addNote(note)
        noteService.addComment(1, Note.Comment(15, "Новый комментарий"))
        noteService.deleteComment(15, 1)
        noteService.restoreComment(15, 1)
        assert(note.deleteComment.isEmpty())
    }
}
