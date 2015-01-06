package me.montecode.games.swipeball.playservices;

/**
 * Created by pc on 5.1.2015.
 */
public interface IGoogleServices {
    public void signIn();
    public void signOut();
    public void rateGame();
    public void submitScore(long score);
    public void showScores();
    public boolean isSignedIn();
}
