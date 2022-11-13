package ru.otus.spring.library.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.library.domain.Genre;
import ru.otus.spring.library.dto.GenreDto;
import ru.otus.spring.library.service.GenreService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping("api/genres")
    public List<GenreDto> getAllGenres() {
        return genreService.findAll().stream().map(GenreDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("api/genres/{id}")
    public Optional<GenreDto> getGenre(@PathVariable("id") Long id) {
        return genreService.findById(id).map(GenreDto::toDto);
    }

    @PostMapping("api/genres")
    public void saveGenre(@RequestBody GenreDto genreDto) {
        Genre genre = genreDto.toDomain();
        genreService.addGenre(genre.getName());
    }

    @PutMapping({"api/genres", "api/genres/{id}"})
    public void editGenre(@RequestBody GenreDto genreDto) {
        Genre genre = genreDto.toDomain();
        genreService.updateGenre(genre.getId(), genre.getName());
    }

    @DeleteMapping("api/genres/{id}")
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
    }


}
