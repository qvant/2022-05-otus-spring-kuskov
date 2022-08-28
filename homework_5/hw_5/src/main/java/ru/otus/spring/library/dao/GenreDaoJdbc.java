package ru.otus.spring.library.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbc = jdbcOperations;
    }

    @Override
    public long count() {
        Long count = jdbc.getJdbcOperations().queryForObject("select count(1) from genres", Long.class);
        return count == null ? 0 : count;
    }

    @Override
    public void insert(Genre genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", genre.getId());
        parameterSource.addValue("name", genre.getName());
        jdbc.update("insert into genres (id, name) values (NEXT VALUE FOR S_GENRES, :name)", parameterSource, keyHolder);
        genre.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void update(Genre genre) {
        jdbc.update("update genres set name =:name where id = :id", Map.of("id", genre.getId(), "name", genre.getName()));
    }

    @Override
    public void delete(Genre genre) {
        jdbc.update("delete from genres where id = :id", Map.of("id", genre.getId()));
    }

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbc.queryForObject("select id, name from genres where id = :id ", params, new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.getJdbcOperations().query("select id, name from genres", new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
