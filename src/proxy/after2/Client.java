package proxy.after2;

public class Client {
    public static void main(String[] args) {
        GameService gameService = new GameServiceProxy(new GameServiceImpl());
        gameService.start();
    }
}
