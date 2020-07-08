package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;

public class PostCheckTurn implements Route {
        private final Gson gson;
        public PostCheckTurn(final Gson gson){
            this.gson = gson;
        }

    @Override
    public Object handle(Request request, Response response){

        final String data = request.queryParams("actionData");
//        Move move = new Move(data);
        if (true){
            return true;
        }
        else{
            //TODO Should say why it is invalid, utilize Move class to do this
            return Message.error("Invalid Move");
        }

    }

}
