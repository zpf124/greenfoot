import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * 主要的世界类，显示整个游戏界面，
 * 其他细节界面未制作
 * 
 * @author zpf
 * @version 0.1.0
 */
public class SpaceWorld extends World
{
    private GreenfootSound bgm;
    private int score;
    private int lever;  //球运行的速度倍率

    /**
     * 初始化，添加砖块
     */
    public SpaceWorld()
    {    
        super(880, 600, 1); 
        lever = 3;
        score = 0;
        bgm = new GreenfootSound("Ghidorah - Toilet Story 4.mp3");
        for(int i = 0;i < 11;i++){
            for(int j = 0; j < 10; j++){
                if(j == 0){
                    addObject(new Brick(-1),40 + 80 * i , 20 + 30 * j + 15);
                }else{
                    int durability = 10 -j -i;
                    while(durability <= 0){
                        durability = durability + 9;
                    }
                    addObject(new Brick(durability),40 + 80 * i,20 + 30 * j + 15);
                }
            }
        }
        
        addObject(new Paddle(),440,getHeight() - 8 - 15*2 );
        addObject(new Ball(),450,getHeight() - 10 - 15*3);
    }
    
    public void addScore(int score){
        this.score += score;
    }
    
    public int getLever(){
        return lever;
    }
    
    public void faster(){
        lever = lever < 5 ? lever + 1 : lever;
    }
    
    public void slower(){
        lever = lever > 1 ? lever -1 : lever ;
    }
    
    
    /**
     * 判断是否还有球，没有即游戏结束
     */
    public void act(){
        showText("得分:" + score, 800, 10);
        
        List<Ball> balls = getObjects(Ball.class);
        if(balls.isEmpty()){
            Greenfoot.stop();
        }
    }
    
    /**
     * 开始时，初始化挡板和播放背景音乐
     */
    public void started(){
        bgm.playLoop();
    }
    
    /**
     * 结束的时候停止bgm
     */
    public void stopped(){
        bgm.stop();
    }
}
