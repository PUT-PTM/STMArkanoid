package com.arkanoid.stm.objects;

import com.arkanoid.stm.interfaces.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

/**
 * Created by Grzegorz on 2015-04-12.
 */
/**
 * Single block - pipeRectangle.
 * COLLISION: When ball hits block, lifeCounter decreases by one.
 * lifeCounter can be 1,2,3 or 9- every number has its own color;
 * 9- not destroyable;
 * */
public class Block  extends GameObject{

        Sprite sprite;
        Texture texture;
        ArrayList<Texture> textures;
        boolean solid=false;

        Rectangle block_rectangle;

        public Rectangle getBlock_rectangle(){ return this.block_rectangle;}

        float x,y,width,height;

        int type;
        public int lifeCounter;


        public Block(int type, float x, float y, float sizeX, float sizeY)
        {
            this.x = x;
            this.y = y;
            this.width = sizeX;
            this.height = sizeY;
            this.type = type;


            lifeCounter = type;

            textures = new ArrayList<Texture>();
            textures.add(new Texture(Gdx.files.internal("core/assets/sprites/blocks/block_1.gif")));
            textures.add(new Texture(Gdx.files.internal("core/assets/sprites/blocks/block_2.gif")));
            textures.add(new Texture(Gdx.files.internal("core/assets/sprites/blocks/block_3.gif")));
            textures.add(new Texture(Gdx.files.internal("core/assets/sprites/blocks/block_9.gif")));

            initBlock(type, sizeX, sizeY);

            block_rectangle = sprite.getBoundingRectangle();

        }

        /** Chooses the right type of block- 1, 2, 3 or 9 -solid */
        public void initBlock(int number, float width,float height)
        {
            switch(number)
            {
                case 1: texture = textures.get(0);break;
                case 2: texture = textures.get(1);break;
                case 3: texture = textures.get(2);break;
                default:{texture = textures.get(3);solid=true;}
            }
            sprite = new Sprite(texture);
            sprite.setSize(width, height);
            sprite.setPosition(x, y);
        }

        @Override
        /** Draws sprite texture which is block-life dependent*/
        public void drawSprite(SpriteBatch spriteBatch) {
            switch (lifeCounter)
            {
                case 1:texture = textures.get(0);break; // block 1
                case 2:texture = textures.get(1);break; // block 2
                case 3:texture = textures.get(2);break; // block 3
                default:{texture= textures.get(3);}break; // solid
            }
            sprite.setTexture(texture);
            sprite.draw(spriteBatch);
        }

        @Override
        /** Destroys the block when there is no life left*/
        public boolean action(int type, float newY) {
            if(lifeCounter == 0)
            {
                destroy();
                return true;
            }
            else return false;
        }

        @Override
        public void update(float delta) {

        }

        @Override
        public int collision(Rectangle rectangle) {
            if(block_rectangle.overlaps(rectangle)) {
                action(1,1);
                lifeCounter--;
                return 1;
            }
            return 0;
        }

        @Override
        public void setPosition(float x, float y) {
            sprite.setPosition(x,y);
        }

        @Override
        public float getX() {
            return x;
        }

        @Override
        public float getY() {
            return y;
        }

        @Override
        public void destroy()
        {
            for(Texture txture: textures)
                if(lifeCounter>=0 && !solid)txture.dispose();
        }
}
