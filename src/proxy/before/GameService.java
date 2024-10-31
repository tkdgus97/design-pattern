package proxy.before;

import proxy.after2.GameServiceImpl;

public class GameService {
    public void start() {
        System.out.println("게임 시작");
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
