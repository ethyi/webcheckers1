package com.webcheckers.ui;

import com.google.gson.Gson;

import com.webcheckers.model.Checkers;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import javax.management.InstanceNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static spark.Spark.halt;

public class PostSubmitTurn implements Route {

    private final Gson gson;
    private final TemplateEngine templateEngine;

    public PostSubmitTurn(final Gson gson,final TemplateEngine templateEngine){
        this.gson = gson;
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");

    }

    @Override
    public Object handle(Request request, Response response){
        //TODO
        System.out.println("PORNOGRAPHY");
//        if (response.type().equals("INFO")){
//            //get game
//        }
//        else{
//            //TODO explain why move cannot be valid
//            return Message.info("Invalid Move");
//        }
        response.type("INFO");
        return null;
//        final  Session session = request.session();
//        final Player player = session.attribute("currentPlayer");
//        final Checkers checkers = player.getCheckers();
//        Map<String, Object> vm = new HashMap<>();
//        vm.put("title","Checkers");
//        vm.put("currentUser",player);
//        vm.put("board", checkers.getBoard());
//        vm.put("viewMode", GetHomeRoute.mode.PLAY);
//        vm.put("redPlayer",checkers.getRedPlayer());
//        vm.put("whitePlayer",checkers.getWhitePlayer());
//        vm.put("activeColor",checkers.getActiveColor());
//
//        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }

}
