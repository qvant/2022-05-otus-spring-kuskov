package ru.otus.spring.library.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.domain.Book;
import ru.otus.spring.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbcOperations
    ) {
        this.jdbc = jdbcOperations;
    }

    @Override
    public long count() {
        Long count = jdbc.getJdbcOperations().queryForObject("select count(1) from books", Long.class);
        return count == null ? 0 : count;
    }

    @Override
    public void insert(Book book) {
        jdbc.update("insert into books (id, title, isbn, genre_id, author_id) values(:id, :title, :isbn, :genre_id, :author_id)",
                Map.of("id", book.getId(), "title", book.getTitle(), "isbn", book.getIsbn(), "genre_id",
                        book.getGenre().getId(), "author_id", book.getAuthor().getId()));
    }

    @Override
    public void update(Book book) {
        jdbc.update("update books set title = :title, isbn = :isbn, genre_id = :genre_id, author_id = :author_id where id = :id",
                Map.of("id", book.getId(), "title", book.getTitle(), "isbn", book.getIsbn(), "genre_id",
                        book.getGenre().getId(), "author_id", book.getAuthor().getId()));
    }

    @Override
    public void delete(Book book) {
        Map<String, Object> params = Collections.singletonMap("id", book.getId());
        jdbc.update("delete from books where id = :id", params);
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbc.queryForObject("select b.id, b.title, b.isbn, b.genre_id, b.author_id, a.id as author_id," +
                " a.name as author_name, g.id as genre_id, g.name as genre_name from books b left join authors a on a.id = b.author_id" +
                " left join genres g on g.id = b.genre_id where b.id = :id ", params, new BookDaoJdbc.BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbc.getJdbcOperations().query("select b.id, b.title, b.isbn, b.genre_id, b.author_id, a.id as author_id," +
                " a.name as author_name, g.id as genre_id, g.name as genre_name from books b left join authors a on a.id = b.author_id" +
                " left join genres g on g.id = b.genre_id ", new BookDaoJdbc.BookMapper());
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            long authorId = resultSet.getLong("author_id");
            long genreId = resultSet.getLong("genre_id");
            String title = resultSet.getString("title");
            String isbn = resultSet.getString("isbn");
            String genreName = resultSet.getString("genre_name");
            String authorName = resultSet.getString("author_name");
            return new Book(id, title, new Author(authorId, authorName), new Genre(genreId, genreName), isbn);
        }
    }
}
