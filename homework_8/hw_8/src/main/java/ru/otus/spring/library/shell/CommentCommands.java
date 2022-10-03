package ru.otus.spring.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.library.service.CommentService;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommands {

    private final CommentService commentService;

    @ShellMethod(value = "Show comments to book", key = {"cb", "book_comments"})
    public void showBookComments(@ShellOption String id) {
        commentService.showBookComments(id);
    }

    @ShellMethod(value = "Add comment", key = {"ca", "add_comment"})
    public void addComment(@ShellOption String id, @ShellOption String text) {
        commentService.addComment(id, text);
    }

    @ShellMethod(value = "Update comment", key = {"cu", "update_comment"})
    public void updateComment(@ShellOption String book_id, @ShellOption int comment_id, @ShellOption String text) {
        commentService.updateComment(book_id, comment_id, text);
    }

    @ShellMethod(value = "Delete comment", key = {"cd", "delete_comment"})
    public void deleteComment(@ShellOption String book_id, @ShellOption int comment_id) {
        commentService.deleteComment(book_id, comment_id);
    }


}
