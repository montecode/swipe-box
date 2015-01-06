package me.montecode.games.swipeball.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;

import me.montecode.games.swipeball.SwipeBallGame;
import me.montecode.games.swipeball.gameobjects.Box;
import me.montecode.games.swipeball.playservices.IGoogleServices;

public class AndroidLauncher extends AndroidApplication implements IGoogleServices {

    private static final String AD_UNIT_ID = "ca-app-pub-8314659346500868/2586726833";
    private AdView adView;
    private View gameView;
    private GameHelper gameHelper;
    private final static int REQUEST_CODE_UNUSED = 9002;
    final Context context = this;


    @Override
	protected void onCreate (Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);

        // Create the GameHelper.

        gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
        gameHelper.enableDebugLog(false);

        GameHelperListener gameHelperListener = new GameHelper.GameHelperListener()
        {
            @Override
            public void onSignInSucceeded()
            {
            }

            @Override
            public void onSignInFailed()
            {
            }
        };
        gameHelper.setMaxAutoSignInAttempts(0);
        gameHelper.setup(gameHelperListener);

        // The rest of your onCreate() code here..


		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new SwipeBallGame(this), config);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        RelativeLayout layout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);

        adView = new AdView(getApplicationContext());
        adView.setAdUnitId(AD_UNIT_ID);
        adView.setAdSize(AdSize.BANNER);
        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        adParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        adView.setBackgroundColor(Color.BLACK);
        adView.setLayoutParams(adParams);

        View gameView = createGameView(config);

        layout.addView(adView);
        layout.addView(gameView);

        setContentView(layout);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adView.bringToFront();
            }
        });
    }
    private View createGameView(AndroidApplicationConfiguration cfg) {
        gameView = initializeForView(new SwipeBallGame(this), cfg);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.BELOW, adView.getId());
        gameView.setLayoutParams(params);
        return gameView;
    }

    protected void onStart()
    {
        super.onStart();
        gameHelper.onStart(this);
    }

    protected void onStop()
    {
        super.onStop();
        gameHelper.onStop();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        gameHelper.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onPause() {
        if (adView != null) adView.pause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (adView != null) adView.destroy();
        super.onDestroy();
    }

    @Override
    public void signIn()
    {
        try
        {
            runOnUiThread(new Runnable()
            {
                //@Override
                public void run()
                {
                    gameHelper.beginUserInitiatedSignIn();
                }
            });
        }
        catch (Exception e)
        {
            Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void signOut()
    {
        try
        {
            runOnUiThread(new Runnable()
            {
                //@Override
                public void run()
                {
                    gameHelper.signOut();
                }
            });
        }
        catch (Exception e)
        {
            Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void rateGame()
    {
        // Replace the end of the URL with the package of your game

        //show alert dialog ****************************************************

        new Thread()
        {
            public void run()
            {
                AndroidLauncher.this.runOnUiThread(new Runnable()
                {
                    public void run()
                    {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                context);

                        // set title
                        alertDialogBuilder.setTitle("Rate app");

                        // set dialog message
                        alertDialogBuilder
                                .setMessage("If you like it rate please!")
                                .setCancelable(false)
                                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        //Rate
                                        String str ="https://play.google.com/store/apps/details?id=me.montecode.games.swipeball.android";
                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
                                    }
                                })
                                .setNegativeButton("Don't show this again",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {

                                        Box.prefs.putBoolean("showRate", false);
                                        Box.prefs.flush();
                                        dialog.cancel();

                                    }
                                });

                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();

                    }
                });
            }
        }.start();






        //end ***********************************************************


    }

    @Override
    public void submitScore(long score)
    {
        if (isSignedIn() == true)
        {
            Games.Leaderboards.submitScore(gameHelper.getApiClient(), getString(R.string.leaderboard_id), score);
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), getString(R.string.leaderboard_id)), REQUEST_CODE_UNUSED);
        }
        else
        {
        // Maybe sign in here then redirect to submitting score?
        }
    }

    @Override
    public void showScores()
    {
        if (isSignedIn() == true)
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), getString(R.string.leaderboard_id)), REQUEST_CODE_UNUSED);
        else
        {
        // Maybe sign in here then redirect to showing scores?
        }
    }

    @Override
    public boolean isSignedIn()
    {
        return gameHelper.isSignedIn();
    }


}
