package ru.otus.spring.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.library.service.GenreService;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {

    private final GenreService genreService;

    @ShellMethod(value = "Show genres", key = {"g", "genres"})
    public void showGenres() {
        genreService.showGenres();
    }

    @ShellMethod(value = "Add genre", key = {"ga", "add_genre"})
    public void addGenre(@ShellOption String name) {
        genreService.addGenre(name);
    }

    @ShellMethod(value = "Update genre", key = {"gu", "update_genre"})
    public void updateGenre(@ShellOption String id, String name) {
        genreService.updateGenre(id, name);
    }

    @ShellMethod(value = "Delete genre", key = {"gd", "delete_genre"})
    public void deleteGenre(@ShellOption String id) {
        genreService.deleteGenre(id);
    }
}
