package pl.fificrud.gameslibrary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Game> getAllGames(){
        return jdbcTemplate.query("SELECT id, game, rating FROM game", BeanPropertyRowMapper.newInstance(Game.class));
    }

    public Game getGameById(int gameId){
       return jdbcTemplate.queryForObject("SELECT id, game, rating FROM game WHERE id = ?",
               BeanPropertyRowMapper.newInstance(Game.class), gameId);
    }

    public List<Game> getGamesByRating(int rating){
        return jdbcTemplate.query("SELECT id, game, rating FROM game WHERE rating = ?",
                BeanPropertyRowMapper.newInstance(Game.class), rating);
    }

    public int saveGame(List<Game> games) {
        games.forEach(game -> jdbcTemplate.update("INSERT INTO game(game, rating) VALUES (?, ?)",
                game.getGame(),game.getRating()));
        return 1;
    }

    public int updateGame(Game game){
        return jdbcTemplate.update("UPDATE game SET game=?, rating =? WHERE id=? ",
                game.getGame(), game.getRating(), game.getId());
    }

    public int deleteGame(int gameId){
        return jdbcTemplate.update("DELETE FROM game WHERE id=?", gameId);
    }
}
