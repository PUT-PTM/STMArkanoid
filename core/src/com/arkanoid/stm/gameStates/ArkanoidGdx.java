package com.arkanoid.stm.gameStates;

import com.arkanoid.stm.ScreenProperties;
import com.arkanoid.stm.objects.Balls;
import com.arkanoid.stm.objects.Block;
import com.arkanoid.stm.objects.Blocks;
import com.arkanoid.stm.objects.PipeBoard;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Iterator;

//still exists ball bug when hits between two blocks
//TODO change textures , add more themes and background wallpaper
//TODO loading lvl from file
//TODO main menu
//TODO STM and JAVA -- JAVA ME 8 ?

public class ArkanoidGdx extends Game
{
	BitmapFont font;
	public SpriteBatch batch;
	protected Texture img;
	private Rectangle screenBoundries_Down,screenBoundries_Left,
			screenBoundries_Up, screenBoundries_Right;

	boolean victory= false;
	boolean lose =false;

	boolean spacePressed =false;

	PipeBoard pipeBoard;
	Balls ball;
	Blocks blocks;

	int lifesLeft;

	public boolean drawBlocks=false;

	@Override
	public void create ()
	{
		batch = new SpriteBatch();

		font= new BitmapFont();
		font.scale(2);

		this.setScreen(new MainMenuScreen(this));

	}
	/** Initializes balls, pipe, screen boundries*/
	protected void initArkanoidPart(int gameMode)
	{
		//Screen collision boundries
		screenBoundries_Down = new Rectangle(0,0,ScreenProperties.widthFit,1);
		screenBoundries_Up = new Rectangle(0,ScreenProperties.heightFit,ScreenProperties.widthFit,1);
		screenBoundries_Left= new Rectangle(0,0,1,ScreenProperties.heightFit);
		screenBoundries_Right= new Rectangle(ScreenProperties.widthFit,0,1,ScreenProperties.heightFit);

		lifesLeft=3;
		// switch do wyboru koloru tla
		img = new Texture(Gdx.files.internal("core/assets/sprites/backgrounds/newBackground.jpg"));

		pipeBoard= new PipeBoard(ScreenProperties.widthFit/2);
		ball = new Balls(pipeBoard);

		blocks= new Blocks();
		switch (gameMode)
		{
			//Freestyle
			case 1:{blocks.loadBlocks_randomly();}break;

			//Classic
			case 2:{}break;
		}

	}

	@Override
	public void render() {
		super.render();

	}

	/**Enables movement of pipeboard*/
	protected void controls()
	{
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			pipeBoard.moveLeft(Gdx.graphics.getDeltaTime());
		}

		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			pipeBoard.moveRight(Gdx.graphics.getDeltaTime());
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
		{
			if( !spacePressed )
			{
				spacePressed = true;
				pipeBoard.pushBall();
			}
		}
	}

	/**Enables collision detection*/
	protected void collision()
	{
		pipe();

		ball();

		block();
	}

	/**Pipe collision behavior*/
	private void pipe()
	{
		/**Enables bouncing pipe at the beggining*/
		if(pipeBoard.collision(screenBoundries_Down) == 1 && !spacePressed)
		{
			spacePressed = pipeBoard.action(1, 2);
		}
	}

	/**Ball collision behavior*/
	private void ball()
	{
		if(ball.collision(pipeBoard.getPipeRectangle()) == 1)
		{
			ball.action(1,1);
			pipeBoard.ballMoved = true;
		}

		if(ball.collision(screenBoundries_Left) == 1 || ball.collision(screenBoundries_Right) == 1)
		{
			ball.action(3, 1);
		}
		if(ball.collision(screenBoundries_Up) == 1 )
		{
			ball.action(2, 1);
		}
		if(ball.collision(screenBoundries_Down) == 1)
		{
			ball.action(4,1);
			spacePressed=false;
			lifesLeft--;
		}
	}

	/**Block collision behavior. Includes part of ball behavior.*/
	private void block()
	{
		if(!blocks.getActiveBlocksList().isEmpty())
		{

			Block block;
			Iterator<Block> it= blocks.getActiveBlocksList().iterator();
			while(it.hasNext()) {
				block = it.next();

				if (block.collision(ball.vertical) == 1) {
					ball.collision(block.getBlock_rectangle());
					ball.action(2, 1);
					drawBlocks = true;

				}
				if (block.collision(ball.horizontal) == 1) {
					ball.collision(block.getBlock_rectangle());
					ball.action(3, 1);
					drawBlocks=true;
				}

				if(block.lifeCounter <= 0) it.remove();
			}
		}
		else
		{
			victory=true;
		}

		for(Block passiveBlock: blocks.getPassiveBlocksList())
		{
			if (passiveBlock.collision(ball.vertical) == 1) {
				ball.collision(passiveBlock.getBlock_rectangle());
				ball.action(2, 1);
			}
			if (passiveBlock.collision(ball.horizontal) == 1) {
				ball.collision(passiveBlock.getBlock_rectangle());
				ball.action(3, 1);

			}
		}

	}

	public void dispose()
	{
		blocks.destroy();
		pipeBoard.destroy();
		ball.destroy();
		//batch.dispose();
	}

}
