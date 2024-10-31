package proxy.after2;

public class GameServiceImpl implements GameService{
    @Override
    public void start() {
        System.out.println("게임 시작!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
