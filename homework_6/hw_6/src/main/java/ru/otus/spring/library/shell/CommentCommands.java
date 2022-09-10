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
    public void showBookComments(@ShellOption long id) {
        commentService.showBookComments(id);
    }

    @ShellMethod(value = "Add comment", key = {"ca", "add_comment"})
    public void addComment(@ShellOption long id, @ShellOption String text) {
        commentService.addComment(id, text);
    }

    @ShellMethod(value = "Update comment", key = {"cu", "update_comment"})
    public void updateComment(@ShellOption long id, @ShellOption String text) {
        commentService.updateComment(id, text);
    }

    @ShellMethod(value = "Delete comment", key = {"cd", "delete_comment"})
    public void updateComment(@ShellOption long id) {
        commentService.deleteComment(id);
    }


}
