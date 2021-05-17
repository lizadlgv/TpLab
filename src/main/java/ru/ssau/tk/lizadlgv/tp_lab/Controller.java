package ru.ssau.tk.lizadlgv.tp_lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ssau.tk.lizadlgv.tp_lab.game.Game;
import ru.ssau.tk.lizadlgv.tp_lab.game.GameException;

@RestController
public class Controller {
    private final Game game;

    @Autowired
    public Controller(Game game) {
        this.game = game;
    }

    @ResponseBody
    @GetMapping(value = "/connect")
    public Answer connect() {
        try {
            return Answer.ok(game.addNewPlayer());
        } catch (GameException e) {
            return Answer.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return Answer.error("Произошла ошибка на сервере");
        }
    }

    @ResponseBody
    @GetMapping(value = "/go")
    public Answer go(@RequestParam double angle, @RequestParam String token) {
        try {
            return Answer.ok(game.step(token, angle));
        } catch (GameException e) {
            return Answer.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return Answer.error("Произошла ошибка на сервере");
        }
    }

    @ResponseBody
    @GetMapping(value = "/canGo")
    public Answer canGo(@RequestParam String token) {
        try {
            return Answer.ok(game.canStep(token));
        } catch (GameException e) {
            return Answer.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return Answer.error("Произошла ошибка на сервере");
        }
    }

    @ResponseBody
    @GetMapping(value = "/validateToken")
    public Answer validateToken(@RequestParam String token) {
        return Answer.ok(game.validateToken(token));
    }

    @ResponseBody
    @GetMapping(value = "/getState")
    public Answer getSate(@RequestParam String token) {
        try {
            return Answer.ok(game.getState(token));
        } catch (GameException e) {
            return Answer.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return Answer.error("Произошла ошибка на сервере");
        }
    }
}
