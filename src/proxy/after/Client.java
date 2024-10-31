package proxy.after;

import proxy.before.GameService;

public class Client {
    public static void main(String[] args) {
        GameService gameService = new GameServiceProxy();
        gameService.start();
    }
}
