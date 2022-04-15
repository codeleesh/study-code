package com.lovethefeel.designpattern.strucural.proxy._02_after;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        GameService gameService = new GameServiceProxy();
        gameService.startGame();
    }
}
