import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

/**
 * 挡板类
 * 
 * 实现功能:
 * 1、可被操控 (鼠标/键盘)
 * 2、可带球移动
 * 3、可获得道具（在 道具类中实现）
 * 4、根据道具实现对应的效果(在 道具类中完成)
 * 
 * 
 * @author zpf
 * @version 0.2.0
 */
public class Paddle extends Actor
{   
    private int width = 120;
    private int height = 15;
    private boolean triggered;   //是否被触发
    private boolean canStopBall; // 能否停球
    private List<Ball> ballsInPaddle = new ArrayList<>(); //挡板上已存在的球
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public void setCanStopBall(boolean canStopBall){
        this.canStopBall = canStopBall;
    }
    
    public void act()
    {
        // 获取移动前的x坐标
        int preX = getX();
        // 获取鼠标位置，将挡板移动到鼠标处
        // 鼠标不在gf窗体内的时候会返回null
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if(mouseInfo != null){
            int x = mouseInfo.getX();
            if(x - width/2 < 0 ){
                x = width/2;
            }else if(x + width/2 > getWorld().getWidth()){
                x = getWorld().getWidth() - width/2;
            }
            setLocation(x, getY());
        }
    
        // 未被触发时，获取挡板上的球,球跟着挡板一起移动，保持相对静止
        if(!triggered){
            List<Ball> balls = getIntersectingBalls();
            
            for(Ball ball:balls){
                ball.setTriggered(false);
                // getX() - preX 即挡板移动的距离
                ball.setLocation(ball.getX() + getX() - preX,ball.getY());
            }

            // 如果点击则激发，球弹出
            if(Greenfoot.mouseClicked(null)){
                for(Ball ball:balls){
                    ball.setTriggered(true);
                }
                triggered = true;
            }
        }else if(canStopBall){ // 如果已经被触发并且可以停球，则在挡板上无球时重置未触发的状态
            if(getIntersectingBalls().isEmpty()){
                triggered = false;
            }else{ //挡板上有球则 清空之前记录，下次重新获取挡板上的球
                ballsInPaddle.clear();
            }
        }
    }
    
    /**
     * 获取在挡板上的球
     * 为了解决移动过快导致球悬空，将挡板上的球保存在了挡板里，没有触发前不清空
     */
    private List<Ball> getIntersectingBalls(){
        List<Ball> allBalls = getWorld().getObjects(Ball.class);
        // 挡板附近的球
        for(Ball ball:allBalls){
            if(!ballsInPaddle.contains(ball)){
                int minX = getX() - width/2;
                int maxX = getX() + width/2;
                int minY = getY() - height/2 - Ball.getDiameter()/2 - 10;
                int bx = ball.getX();
                int by = ball.getY();
                int maxY = getY() + Ball.getDiameter()/2;
                if(ball.getSpeedY() > 0 &&  ball.getX() > minX && ball.getX() < maxX && ball.getY() > minY && ball.getY() < maxY){
                    ballsInPaddle.add(ball);
                }
            }
        }
        return ballsInPaddle;
    }
}
