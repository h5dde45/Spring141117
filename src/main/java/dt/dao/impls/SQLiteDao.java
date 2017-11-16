package dt.dao.impls;

import dt.dao.interfaces.MP3Dao;
import dt.dao.objects.Author;
import dt.dao.objects.MP3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class SQLiteDao implements MP3Dao {
    private static final String MP3TABLE = "mp3";
    private static final String MP3VIEW = "mp3_view";

    //    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbcTemplate;
//    private SimpleJdbcInsert insertMp3;

    @Autowired
    public void setDataSource(DataSource dataSource) {
//        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//        insertMp3 = new SimpleJdbcInsert(dataSource).withTableName("mp3")
//                .usingColumns("name", "author_id");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertMP3(MP3 mp3) {
//        String sql = "INSERT INTO mp3(name, author) VALUES(?,?)";
//        String sql = "INSERT INTO mp3(name, author) VALUES(:namePar,:authorPar)";
//        KeyHolder keyHolder=new GeneratedKeyHolder();

//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("namePar",mp3.getName());
//        params.addValue("authorPar",mp3.getAuthor());
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("name", mp3.getName());
//        params.addValue("author", mp3.getAuthor());
//        jdbcTemplate.update(sql, new Object[]{mp3.getName(), mp3.getAuthor()});
//        jdbcTemplate.update(sql, params,keyHolder);
//        return keyHolder.getKey().intValue();
//        return insertMp3.execute(params);
        System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
        String sql = "INSERT INTO mp3(name, author_id) VALUES(:namePar,:authorIdPar)";
        int id=insertAuthor(mp3);
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("namePar",mp3.getName());
        params.addValue("authorIdPar",id);
        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params,keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    private int insertAuthor(MP3 mp3) {
        System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
        String sql = "INSERT INTO author(name) VALUES(:namePar)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        Author author=mp3.getAuthor();
        params.addValue("namePar",author.getName());
        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params,keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public void insertMP3(List<MP3> mp3List) {
        for (MP3 mp3 : mp3List) {
            insertMP3(mp3);
        }
    }

    @Override
    public Map<String, Integer> getStat() {
        String sql = "select author_name,count(*) as count from " + MP3VIEW + " group by author_name";
        return jdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Map<String, Integer> map = new TreeMap<String, Integer>();
                while (resultSet.next()) {
                    String author = resultSet.getString("author_name");
                    int count = resultSet.getInt("count");
                    map.put(author, count);
                }
                return map;
            }
        });
    }

    @Override
    public void delete(int id) {
//        String sql = "delete from mp3 where id=?";
        String sql = "DELETE FROM mp3 WHERE id=:idPar";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idPar", id);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(MP3 mp3) {
        delete(mp3.getId());
    }

    @Override
    public MP3 getMP3ById(int id) {
        String sql = "SELECT * FROM " + MP3VIEW + " WHERE mp3_id=:idPar";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idPar", id);
        return jdbcTemplate.queryForObject(sql, params, new MP3RowMapper());
    }

    @Override
    public List<MP3> getMP3ListByName(String name) {
        String sql = "SELECT * FROM " + MP3VIEW + " WHERE upper(mp3_name) LIKE :namePar";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("namePar", "%" + name.toUpperCase() + "%");
        return jdbcTemplate.query(sql, params, new MP3RowMapper());
    }

    @Override
    public List<MP3> getMP3ListByAuthor(String author) {
        String sql = "SELECT * FROM " + MP3VIEW + " WHERE upper(author_name) LIKE :authorPar";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("authorPar", "%" + author.toUpperCase() + "%");
        return jdbcTemplate.query(sql, params, new MP3RowMapper());
    }

    @Override
    public int getMP3Count() {
        String sql = "SELECT count(*) FROM " + MP3TABLE;
        return jdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class);
    }

    private static final class MP3RowMapper implements RowMapper<MP3> {
        @Override
        public MP3 mapRow(ResultSet resultSet, int i) throws SQLException {
            Author author = new Author();
            author.setId(resultSet.getInt("author_id"));
            author.setName(resultSet.getString("author_name"));

            MP3 mp3 = new MP3();
            mp3.setId(resultSet.getInt("mp3_id"));
            mp3.setName(resultSet.getString("mp3_name"));
            mp3.setAuthor(author);
            return mp3;
        }
    }
}
