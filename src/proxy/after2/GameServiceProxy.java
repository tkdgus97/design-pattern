package proxy.after2;

public class GameServiceProxy implements GameService{
    private final GameService gameService;

    public GameServiceProxy(GameServiceImpl gameService) {
        this.gameService = gameService;
    }

    @Override
    public void start() {
        long startTime = System.currentTimeMillis();
        gameService.start();
        System.out.println("실행 시간 : " + (System.currentTimeMillis() - startTime));
    }
}
