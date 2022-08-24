package ru.otus.spring.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.library.service.LibraryService;

@ShellComponent
@RequiredArgsConstructor
public class LibraryApplicationCommands {
    private final LibraryService libraryService;

    @ShellMethod(value = "Show authors", key = {"a", "authors"})
    public void showAuthors() {
        libraryService.showAuthors();
    }

}
