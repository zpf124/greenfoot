import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 这是一个球类
 * 
 * 只是移动、反弹和触发砖块的损坏。
 * 
 * @author zpf
 * @version 0.2.0
 */
public class Ball extends Actor
{
    private static int diameter = 20;
    private int speedX = 4;
    private int speedY = 2;
    private boolean triggered;

    public static int getDiameter(){
        return diameter;
    }

    public int getSpeedX(){
        return speedX;
    }

    public void setSpeedX(int speedX){
        this.speedX = speedX;
    }

    public int getSpeedY(){
        return speedY;
    }

    public void setSpeedY(int speedY){
        this.speedY = speedY;
    }

    public void setTriggered(boolean triggered){
        this.triggered = triggered;
    }

    /**
     * 判断是否被触发，
     * 之后判断是否掉落到下边界，如果已到边界，则移除自身。
     * 如果还在游戏范围内，则移动到下一个位置
     * 如果发成碰撞则向反方向移动，如果撞击到砖块，则触发砖块的损坏方法。
     */
    public void act(){
        if(triggered){
            SpaceWorld world = getWorldOfType(SpaceWorld.class);
            if(getY() >=  getWorld().getHeight() - diameter/2){
                getWorld().removeObject(this);
            }else{
                int x = getX() + speedX * world.getLever()/2;
                int y = getY() - speedY * world.getLever()/2;
                int worldWidth = getWorld().getWidth();

                setLocation(x, y);

                if(x <= diameter/2 || x>= worldWidth - diameter/2){
                    speedX = -speedX;
                }
                if(y <= diameter/2){
                    speedY = -speedY;
                }
                
                // 判断是否与砖块碰撞
                Actor actor = getOneIntersectingObject(Brick.class);
                if(actor != null){
                    Brick brick = (Brick)actor;
                    int brickX = brick.getX();
                    int brickWidth = brick.getWidth();
                    if(brickX - brickWidth/2 < x && brickX + brickWidth/2 > x){
                        speedY = -speedY;
                    }else{
                        speedX = -speedX;
                    }
                    brick.damaged();
                }

                // 判断是否与挡板碰撞
                actor = getOneIntersectingObject(Paddle.class);
                if(actor != null && actor.getY()  > y){
                    speedY = Math.abs(speedY);
                    if(actor.getX() - 40 > x ){
                        speedX = -Math.abs(speedX);
                    }else if(actor.getX() + 40 < x){
                        speedX = Math.abs(speedX);
                    }
                }
            }
        }
    }
}
