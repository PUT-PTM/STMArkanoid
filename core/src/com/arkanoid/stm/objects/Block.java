package com.arkanoid.stm.objects;

import com.arkanoid.stm.interfaces.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

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

            if(type != -1)  lifeCounter = type;
            else            lifeCounter = -1;

            initBlock(type, sizeX, sizeY);

            block_rectangle = sprite.getBoundingRectangle();
        }

        /** Chooses the right type of block- 1, 2, 3 or 9 -solid */
        public void initBlock(int number, float width,float height)
        {
            switch(number)
            {
                case 1:
                {
                    texture = new Texture(Gdx.files.internal("core/assets/sprites/blocks/block_1.gif"));
                    sprite = new Sprite(texture);
                }break;

                case 2:{
                    texture = new Texture(Gdx.files.internal("core/assets/sprites/blocks/block_2.gif"));
                    sprite = new Sprite(texture);
                }break;

                case 3:{
                    texture = new Texture(Gdx.files.internal("core/assets/sprites/blocks/block_3.gif"));
                    sprite = new Sprite(texture);
                }break;

                default: {
                    texture = new Texture(Gdx.files.internal("core/assets/sprites/blocks/block_9.gif"));
                    sprite = new Sprite(texture);
                }
            }
            sprite.setSize(width, height);
            sprite.setPosition(x, y);

        }

        @Override
        public void drawSprite(SpriteBatch spriteBatch) {
            switch (lifeCounter)
            {
                case -1:texture = new Texture(Gdx.files.internal("core/assets/sprites/blocks/block_9.gif"));break;
                case 1:texture = new Texture(Gdx.files.internal("core/assets/sprites/blocks/block_1.gif"));break;
                case 2:texture = new Texture(Gdx.files.internal("core/assets/sprites/blocks/block_2.gif"));break;
                case 3:texture = new Texture(Gdx.files.internal("core/assets/sprites/blocks/block_3.gif"));break;
            }
            sprite.setTexture(texture);
            sprite.draw(spriteBatch);
        }

        @Override
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
        public void destroy() {

            texture.dispose();
        }
}
