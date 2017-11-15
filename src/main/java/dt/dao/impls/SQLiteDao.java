package dt.dao.impls;

import dt.dao.interfaces.MP3Dao;
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

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class SQLiteDao implements MP3Dao {
//    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
//        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public int insert(MP3 mp3) {
//        String sql = "INSERT INTO mp3(name, author) VALUES(?,?)";
        String sql = "INSERT INTO mp3(name, author) VALUES(:namePar,:authorPar)";
        KeyHolder keyHolder=new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("namePar",mp3.getName());
        params.addValue("authorPar",mp3.getAuthor());
//        jdbcTemplate.update(sql, new Object[]{mp3.getName(), mp3.getAuthor()});
        jdbcTemplate.update(sql, params,keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void insert(List<MP3> mp3List) {
        for (MP3 mp3 : mp3List) {
            insert(mp3);
        }
    }

    @Override
    public Map<String,Integer> getStat(){
        String sql="select author,count(*) as count from mp3 group by author";
        return  jdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Map<String, Integer> map=new TreeMap<String, Integer>();
                while (resultSet.next()){
                    String author=resultSet.getString("author");
                    int count=resultSet.getInt("count");
                    map.put(author,count);
                }
                return map;
            }
        });
    }

    @Override
    public void delete(int id) {
//        String sql = "delete from mp3 where id=?";
        String sql = "delete from mp3 where id=:idPar";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idPar",id);
        jdbcTemplate.update(sql,params);
    }

    @Override
    public void delete(MP3 mp3) {
        delete(mp3.getId());
    }

    @Override
    public MP3 getMP3ById(int id) {
        String sql = "select * from mp3 where id=:idPar";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idPar",id);
        return jdbcTemplate.queryForObject(sql,params,new MP3RowMapper());
    }

    @Override
    public List<MP3> getMP3ListByName(String name) {
        String sql = "select * from mp3 where upper(name) LIKE :namePar";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("namePar","%"+name.toUpperCase()+"%");
        return jdbcTemplate.query(sql,params,new MP3RowMapper());
    }

    @Override
    public List<MP3> getMP3ListByAuthor(String author) {
        String sql = "select * from mp3 where upper(author) LIKE :authorPar";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("authorPar","%"+author.toUpperCase()+"%");
        return jdbcTemplate.query(sql,params,new MP3RowMapper());
    }

    @Override
    public int getMP3Count(){
        String sql="select count(*) from mp3";
        return jdbcTemplate.getJdbcOperations().queryForObject(sql,Integer.class);
    }

    private static final class MP3RowMapper implements RowMapper<MP3> {
        @Override
        public MP3 mapRow(ResultSet resultSet, int i) throws SQLException {
            MP3 mp3=new MP3();
            mp3.setId(resultSet.getInt("id"));
            mp3.setName(resultSet.getString("name"));
            mp3.setAuthor(resultSet.getString("author"));
            return mp3;
        }
    }
}
