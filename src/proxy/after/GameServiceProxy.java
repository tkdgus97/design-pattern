package proxy.after;

import proxy.before.GameService;

public class GameServiceProxy extends GameService {
    @Override
    public void start() {
        long before = System.currentTimeMillis();
        super.start();
        System.out.println(System.currentTimeMillis() - before);
    }
}
