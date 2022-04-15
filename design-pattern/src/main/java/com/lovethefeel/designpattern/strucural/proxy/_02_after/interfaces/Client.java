package com.lovethefeel.designpattern.strucural.proxy._02_after.interfaces;

public class Client {
    public static void main(String[] args) {
        GameService gameService = new GameServiceProxy();
        gameService.startGame();
    }
}
