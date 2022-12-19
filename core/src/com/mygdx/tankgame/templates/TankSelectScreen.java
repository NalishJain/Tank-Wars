package com.mygdx.tankgame.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankgame.ClassGame;
import com.mygdx.tankgame.GameInputController;
import com.mygdx.tankgame.Player;
import com.mygdx.tankgame.TankGame;

import static com.mygdx.tankgame.TankGame.SCREEN_HEIGHT;
import static com.mygdx.tankgame.TankGame.SCREEN_WIDTH;

public class TankSelectScreen implements Screen {

    private static final int BUTTON_WIDTH = 350;
    public Player player1, player2;
    private static final int BUTTON_HEIGHT = 100;
    private static final int LEFT_WIDTH = SCREEN_WIDTH/2;
    private static final int RIGHT_WIDTH = SCREEN_WIDTH/2;
    private static final int BUTTON_RIGHT_LOCATION =  LEFT_WIDTH + (RIGHT_WIDTH/2 - BUTTON_WIDTH/2);

    private static final float tank1Height = 226*0.8f;
    private static final float tank1Width = 446*0.8f;

    private static final float tank2Height = 207;
    private static final float tank2Width = 375;

    private static final float tank3Height = 644*0.3f;
    private static final float tank3Width = 1080*0.3f;

    float[] tankWidth = {tank1Width,tank2Width, tank3Width};
    float[] tankHeight = {tank1Height, tank2Height, tank3Height};
    TankGame game;
    Texture chooseButtonInactive;
//    Texture chooseButtonActive;
    Texture tank;
    Texture currentTank;
    Texture bg;
    Texture terrain;
    Texture tank2;
//    Texture kPlane;
    Texture aShip;
    Texture terrain_texture;

    Texture Shield;
    Texture[] tankName = {new Texture("tank2Name.png"), new Texture("tank1Name.png"), new Texture("tank3Name.png")};
    Texture[] tankBg = {new Texture("tank2Bg.png"), new Texture("tank1Bg.png"), new Texture("tank3Bg.png")};
    Texture bomb;
    TextureRegion rbomb;
    Texture hpBar;
    Texture CircleBg;
    Texture[] tankBackground = {new Texture("tank2Circle.png"), new Texture("tank1Circle.png"), new Texture("tank3Circle.png")};
    Texture[][] currentPlayer = {{new Texture("tank2P1.png"), new Texture("tank1P1.png"), new Texture("tank3P1.png")}, {new Texture("tank2P2.png"), new Texture("tank1P2.png"), new Texture("tank3P2.png")}};
    Texture arrow;
    int playerNumber = 0;

    Texture[] TextureArray = {new Texture("Tank1.png"), new Texture("Tank2.png"), new Texture("tank3.png")};
    int TextureArrayCounter = 0;
    public TankSelectScreen(TankGame game){
        this.game = game;
//        chooseButtonActive = new Texture("choose4x.png");
        chooseButtonInactive = new Texture("SelectTankButton.png");
        currentTank = new Texture("Tank1.png");
//        bg = new Texture("bg4.png");
        terrain = new Texture("terrain2.png");
//        kPlane = new Texture("Plane.png");
        aShip = new Texture("AirShip.png");
        Shield = new Texture("Shield.png");
        terrain_texture = new Texture("Terrain_texture.png");
        arrow = new Texture("Arrow.png");
        hpBar = new Texture("HPBar.png");
        bomb = new Texture("Bomb.png");
        rbomb = new TextureRegion(bomb);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(new GameInputController(){
            public boolean keyDown(int keycode) {
                switch (keycode){
                    case Input.Keys.RIGHT:
                        TextureArrayCounter = (TextureArrayCounter + 1)%3;
                        currentTank = TextureArray[TextureArrayCounter];
                        break;
                    case Input.Keys.LEFT:
                        if(TextureArrayCounter == 0){
                            TextureArrayCounter = 3;
                        }
                        TextureArrayCounter = (TextureArrayCounter - 1)%3;
                        currentTank = TextureArray[TextureArrayCounter];
                        break;
                    case Input.Keys.ENTER:
                        if(playerNumber == 0){
                            if(TextureArrayCounter == 0){
                                player1 = new Player(playerNumber + 1, 2);
                            }
                            else if(TextureArrayCounter == 1){
                                player1 = new Player(playerNumber + 1, 1);
                            }
                            else{
                                player1 = new Player(playerNumber + 1, 3);
                            }
//                            System.out.println(TextureArrayCounter);
                            playerNumber++;

                        }
                        else if(playerNumber == 1){
                            if(TextureArrayCounter == 0){
                                player2 = new Player(playerNumber + 1, 2);
                            }
                            else if(TextureArrayCounter == 1){
                                player2 = new Player(playerNumber + 1, 1);
                            }
                            else{
                                player2 = new Player(playerNumber + 1, 3);
                            }//                    playerNumber++;
                            game.setScreen(new PlayGame(game, new ClassGame(player1, player2)));
                        }

                }
                return true;
            }
            public boolean keyUp(int keycode) {
                switch (keycode){
                    case Input.Keys.LEFT:
                    case Input.Keys.RIGHT:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.44f,0.31f,0.96f, 0.5f);
        game.batch.begin();

        game.batch.draw(tankBg[TextureArrayCounter], 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        game.batch.draw(tankBackground[TextureArrayCounter],(SCREEN_WIDTH/2 - 382/2)-7,120,440,440);

        game.batch.draw(terrain, 0,-295, SCREEN_WIDTH, 500);
        for(int i = 0; i < 1600; i += 35) {
            game.batch.draw(terrain_texture, i, 196, 967*0.05f, 262*0.05f);
        }
        game.batch.draw(chooseButtonInactive, (SCREEN_WIDTH/2 - BUTTON_WIDTH/2), 50, BUTTON_WIDTH, BUTTON_HEIGHT);
//        game.batch.draw(kPlane, 900, 530, 1132*0.2f, 346*0.2f);
        game.batch.draw(aShip, 350, 500, -1890*0.17f, 897*0.17f);
        game.batch.draw(rbomb,165,460,0,0,744*0.13f,195*0.13f,1,1,-2f);
        game.batch.draw(rbomb,165,420,0,0,744*0.13f,195*0.13f,1,1,-8f);
        game.batch.draw(rbomb,165,380,0,0,744*0.13f,195*0.13f,1,1,-13f);

        if(Gdx.input.getX() < (SCREEN_WIDTH/2 - BUTTON_WIDTH/2) + BUTTON_WIDTH && Gdx.input.getX() >(SCREEN_WIDTH/2 - BUTTON_WIDTH/2)  && SCREEN_HEIGHT - Gdx.input.getY() < 50 + BUTTON_HEIGHT && SCREEN_HEIGHT - Gdx.input.getY() > 50){
            if(Gdx.input.isTouched()){
                if(playerNumber == 0){
                    player1 = new Player(playerNumber + 1, TextureArrayCounter + 1);
                    System.out.println(TextureArrayCounter);
                    playerNumber++;

                }
                else if(playerNumber == 1){
                    player2 = new Player(playerNumber + 1, TextureArrayCounter + 1);
//                    playerNumber++;
                    game.setScreen(new PlayGame(game, new ClassGame(player1, player2)));
                }
            }
        }
        game.batch.draw(arrow, 400 + 15, 280, -60, 60);
        game.batch.draw(arrow, SCREEN_WIDTH-395 - 20, 280, 60, 60);


        game.batch.draw(tankName[TextureArrayCounter], (SCREEN_WIDTH/2 - 944*0.5f/2), 580, 944*0.5f, 216*0.5f);
//        game.batch.draw(hpBar, (SCREEN_WIDTH/2 - 3831*0.07f/2), 460, 3831*0.07f, 1072*0.07f);
//        game.batch.draw(Shield,(SCREEN_WIDTH/2 - 3831*0.07f/2) -40,430,153*0.9f,157*0.9f);

        game.batch.draw(currentPlayer[playerNumber][TextureArrayCounter], 597, 457, 1128*0.4f, 262*0.4f);
        if(TextureArrayCounter == 1){
            game.batch.draw(currentTank, (SCREEN_WIDTH/2 - tankWidth[TextureArrayCounter]/2) - 5, 195, tankWidth[TextureArrayCounter], tankHeight[TextureArrayCounter]);
        }
        else if(TextureArrayCounter == 2){
            game.batch.draw(currentTank, (SCREEN_WIDTH/2 - tankWidth[TextureArrayCounter]/2) + 19, 195, tankWidth[TextureArrayCounter], tankHeight[TextureArrayCounter]);
        }
        else if(TextureArrayCounter == 0){
            game.batch.draw(currentTank, (SCREEN_WIDTH/2 - tankWidth[TextureArrayCounter]/2) + 19, 195, tankWidth[TextureArrayCounter], tankHeight[TextureArrayCounter]);
        }

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
