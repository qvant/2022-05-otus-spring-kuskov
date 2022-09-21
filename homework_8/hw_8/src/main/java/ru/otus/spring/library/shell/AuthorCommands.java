package ru.otus.spring.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.UnexpectedRollbackException;
import ru.otus.spring.library.service.AuthorService;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {
    private final AuthorService authorService;

    @ShellMethod(value = "Show authors", key = {"a", "authors"})
    public void showAuthors() {
        authorService.showAuthors();
    }

    @ShellMethod(value = "Add author", key = {"aa", "add_author"})
    public void addAuthor(@ShellOption String name) {
        authorService.addAuthor(name);
    }

    @ShellMethod(value = "Update author", key = {"au", "update_author"})
    public void updateAuthor(@ShellOption long id, String name) {
        authorService.updateAuthor(id, name);
    }

    @ShellMethod(value = "Delete author", key = {"ad", "delete_author"})
    public void deleteAuthor(@ShellOption long id) {
        try {
            authorService.deleteAuthor(id);
        } catch (UnexpectedRollbackException exception) {
        }
    }
}
