package ru.otus.spring.library.dao;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbc = jdbcOperations;
    }

    @Override
    public long count() {
        Long count = jdbc.getJdbcOperations().queryForObject("select count(1) from authors", Long.class);
        return count == null ? 0 : count;
    }

    @Override
    public void insert(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", author.getId());
        parameterSource.addValue("name", author.getName());
        jdbc.update("insert into authors (id, name) values (NEXT VALUE FOR S_AUTHORS, :name)", parameterSource, keyHolder);
        author.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void update(Author author) {
        jdbc.update("update authors set name =:name where id = :id", Map.of("id", author.getId(), "name", author.getName()));
    }

    @Override
    public void delete(Author author) throws HasDependentObjectsException {
        try {
            jdbc.update("delete from authors where id = :id", Map.of("id", author.getId()));
        } catch (DataIntegrityViolationException exception) {
            throw new HasDependentObjectsException("Нельзя удалить автора с id " + author.getId() + ", есть зависимости");
        }


    }

    @Override
    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbc.queryForObject("select id, name from authors where id = :id ", params, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbc.getJdbcOperations().query("select id, name from authors", new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }
}
