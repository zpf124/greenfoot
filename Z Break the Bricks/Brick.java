import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * 这是一个砖块类
 * 
 * 这个类要实现如下功能:
 * 
 * 1、当碰撞的时候触发对应行为，降低耐久/被破坏
 * 2、当耐久降低时触发变色等外观变化
 * 3、当碰撞的时候触发对应行为的音效(非主要，后期完成)
 * 4、初始化时指定是否掉落道具，当被损坏后随机掉落道具(后期完成)
 * 
 * @author zpf
 * @version 0.1.0
 */
public class Brick extends Actor
{
    private int width = 80;
    private int height = 30;
    private int durability;             // 耐久度
    private int prevDurability;         // 前一次耐久度

    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    /**
     *  生成砖块，如果 耐久度是负值表示是无敌的铁块
     */
    public Brick(int durability){
        this.durability = durability;
        if(durability < 0){
            this.setImage(new GreenfootImage("gray_brick.png"));
        }else{
            this.synImage();
        }
    }

    public void act(){
        synImage();
    }
    
    /**
     *  根据耐久度显示不同的颜色
     *  如果当前耐久度和上一次一致则不换颜色
     */
    private void synImage(){
        if(durability != prevDurability && durability > 0){
            GreenfootImage image = null;
            switch(durability){
                case 1:
                image = new GreenfootImage("azure_brick.png");
                break;
                case 2:
                image = new GreenfootImage("lime_brick.png");
                break;
                case 3:
                image = new GreenfootImage("yellow_brick.png");
                break;
                case 4:
                image = new GreenfootImage("purple_brick.png");
                break;
                case 5:
                image = new GreenfootImage("blue_brick.png");
                break;
                case 6:
                image = new GreenfootImage("green_brick.png");
                break;
                case 7:
                image = new GreenfootImage("gold_brick.png");
                break;
                case 8:
                image = new GreenfootImage("pink_brick.png");
                break;
                case 9:
                image = new GreenfootImage("red_brick.png");
                break;
            }
            prevDurability = durability;
            this.setImage(image);
        }
    }
    
    /**
     * 砖块被破坏的方法
     * 砖块耐久-1 并且获得积分，分值为当前耐久 * 10。
     * 如果耐久为0，则随机掉落道具
     */
    public void damaged(){
        if(durability > 0){
            SpaceWorld world = getWorldOfType(SpaceWorld.class);
            world.addScore(durability * 10); 
            durability --;
            if(durability == 0){
                int probability = new Random().nextInt(100);
                // 掉落概率  (目前为10%)
                if(probability < 10){
                    world.addObject(Item.getItem(), getX(), getY());
                }
                world.removeObject(this);
            }
        }
    }

}