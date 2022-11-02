package ru.otus.spring.library.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
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

    @GetMapping("api/genre")
    public Optional<GenreDto> getGenre(Long id) {
        return genreService.findById(id).map(GenreDto::toDto);
    }

    @PostMapping("api/genreEdit")
    public RedirectView saveGenre(GenreDto genreDto) {
        Genre genre = genreDto.toDomain();
        if (genre.getId() == null) {
            genreService.addGenre(genre.getName());
        } else {
            genreService.updateGenre(genre.getId(), genre.getName());
        }
        return new RedirectView("/genres");
    }

    @PostMapping("api/genreDelete")
    public RedirectView deleteGenre(Long id) {
        genreService.deleteGenre(id);
        return new RedirectView("/genres");
    }


}
