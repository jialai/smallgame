package com.example.smallgame.game.Service;


public class Game {

    public static double win = 0.30;

    public Integer Gambling(){

        double randomNumberForGame;
        Integer record;
        randomNumberForGame = Math.random();
        if (randomNumberForGame >= 0 && randomNumberForGame <= win) {

            record=20;
        }
        else {
            record=0;
        }


        return record;

    }

}
