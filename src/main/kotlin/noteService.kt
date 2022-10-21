object noteService {
    var allNotes = mutableListOf<Note>()
    fun addNote(note: Note): Note {
        note.id = allNotes.lastIndex + 2
        allNotes += note
        return allNotes.last()
    }

    fun deleteNote(id: Int) {
        allNotes.remove(findById(id))
    }

    fun addComment(id: Int, newComment: Note.Comment) {
        findById(id)?.comment?.add(newComment)
    }

    fun deleteComment(CommentId: Int, NoteId: Int) {
        val newDeleteComment = findCommentById(CommentId, NoteId)
        findById(NoteId)?.deleteComment?.add(newDeleteComment!!)
        getComment(NoteId)!!.remove(findCommentById(CommentId, NoteId))

    }

    fun restoreComment(CommentId: Int, NoteId: Int) {
        val restoreComment = findDeleteCommentById(CommentId, NoteId)
        findById(NoteId)?.comment?.add(restoreComment!!)
        getDeleteComment(NoteId)!!.remove(findDeleteCommentById(CommentId, NoteId))

    }

    fun findById(id: Int): Note? {
        var result = false
        for (note in allNotes) {
            if (note.id === id) {
                result = true
                return note
            }
        }
        if (result === false) {
            throw NoteNotFoundException("Не найден Элемент с номером ID $id")
        }
        return null

    }

    fun findCommentById(CommentId: Int, NoteId: Int): Note.Comment? {
        var result = false
        for (comment in findById(NoteId)?.comment!!) {
            if (comment.commentId === CommentId) {
                result = true
                return comment
            }
        }
        if (result === false) {
            throw NoteNotFoundException("Не найден Элемент с номером ID $CommentId")
        }
        return null
    }

    fun findDeleteCommentById(CommentId: Int, NoteId: Int): Note.Comment? {
        var result = false
        for (comment in findById(NoteId)?.deleteComment!!) {
            if (comment.commentId === CommentId) {
                result = true
                return comment
            }
        }
        if (result === false) {
            throw NoteNotFoundException("Не найден Элемент с номером ID $CommentId")
        }
        return null
    }

    fun getComment(id: Int): MutableList<Note.Comment>? {
        return findById(id)?.comment
    }

    fun getDeleteComment(id: Int): MutableList<Note.Comment>? {
        return findById(id)?.deleteComment
    }

    fun update(id: Int): Boolean {
        var result = false
        findById(id)?.text = "Заметка обновлена"
        return result
    }

    fun updateComment(CommentId: Int, NoteId: Int): Boolean {
        var result = false
        findCommentById(CommentId, NoteId)?.textComment = "Комментарий обновлен"
        return result
    }


}


class NoteNotFoundException(message: String) : RuntimeException(message)