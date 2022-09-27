package ru.otus.spring.library.shell;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.UnexpectedRollbackException;
import ru.otus.spring.library.service.CommentService;
import ru.otus.spring.library.service.IdConverterService;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommands {

    private final CommentService commentService;
    private final IdConverterService idConverterService;

    @ShellMethod(value = "Show comments to book", key = {"cb", "book_comments"})
    public void showBookComments(@ShellOption String id) {
        ObjectId objectId = idConverterService.convertToObjectId(id);
        if (objectId == null){
            return;
        }
        commentService.showBookComments(objectId);
    }

    @ShellMethod(value = "Add comment", key = {"ca", "add_comment"})
    public void addComment(@ShellOption String id, @ShellOption String text) {
        ObjectId objectId = idConverterService.convertToObjectId(id);
        if (objectId == null){
            return;
        }
        commentService.addComment(objectId, text);
    }

    @ShellMethod(value = "Update comment", key = {"cu", "update_comment"})
    public void updateComment(@ShellOption String book_id, @ShellOption String comment_id, @ShellOption String text) {
        ObjectId bookId = idConverterService.convertToObjectId(book_id);
        if (bookId == null){
            return;
        }
        Integer commentId = idConverterService.convertToInteger(comment_id);
        if (commentId == null){
            return;
        }
        commentService.updateComment(bookId, commentId, text);
    }

    @ShellMethod(value = "Delete comment", key = {"cd", "delete_comment"})
    public void deleteComment(@ShellOption String book_id, @ShellOption String comment_id) {
        ObjectId bookId = idConverterService.convertToObjectId(book_id);
        if (bookId == null){
            return;
        }
        Integer commentId = idConverterService.convertToInteger(comment_id);
        if (commentId == null){
            return;
        }
        commentService.deleteComment(bookId, commentId);
    }


}
