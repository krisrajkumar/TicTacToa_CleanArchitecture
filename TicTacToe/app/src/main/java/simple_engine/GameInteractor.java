package simple_engine;

public interface GameInteractor {
    void init();

    void makePosAsVisited(int pos);

    void startGame();

    void resetGame();
}
