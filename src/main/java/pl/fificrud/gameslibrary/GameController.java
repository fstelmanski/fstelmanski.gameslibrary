package pl.fificrud.gameslibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    GameRepository gameRepository;

    @GetMapping("")
    public List<Game> getAll(){
        return gameRepository.getAllGames();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable ("id")int id){
        return gameRepository.getGameById(id);
    }

    @GetMapping("/rating/{rating}")
    public List<Game> getGamesByRating(@PathVariable ("rating")int rating){
        return gameRepository.getGamesByRating(rating);
    }

    @PostMapping("")
    public int saveGame(@RequestBody List<Game> games){
        return gameRepository.saveGame(games);
    }

    @PutMapping("/{id}")
    public int updateGame(@PathVariable("id")int id, @RequestBody Game updateGame){
        Game game = gameRepository.getGameById(id);
        if(game!=null){
            game.setGame(updateGame.getGame());
            game.setRating(updateGame.getRating());
            gameRepository.updateGame(game);
            return 1;
        }
        else{
            return -1;
        }
    }
    @PatchMapping("/{id}")
    public int updateGamePartially(@PathVariable("id")int id, @RequestBody Game updateGame){
        Game game = gameRepository.getGameById(id);
        if(game!=null){
            if(updateGame.getGame()!= null) {
                game.setGame(updateGame.getGame());
            }
            if(updateGame.getRating() > 0) {
                game.setRating(updateGame.getRating());
            }
            gameRepository.updateGame(game);
            return 1;
        }
        else{
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int deleteGame(@PathVariable("id")int id){
        return gameRepository.deleteGame(id);
    }


}
