import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Random;

/**
 * 道具类
 * 
 * 实现功能:
 * 1、自动下落
 * 2、掉出地图后，移除自身
 * 3、可获得道具
 * 4、根据道具实现对应的效果
 * 
 * @author zpf
 * @version 0.2.0
 */
public class Item extends Actor
{
    private Type type; // 道具的类型
    
    /**
     *  目前类型，加速，减速，分裂，磁性
     */
    enum Type{
        FASTER(Color.BLUE), SLOWER(Color.ORANGE), SPLIT(Color.RED), MAGNETIC(Color.DARK_GRAY);

        private Color color;

        public Color getColor(){
            return color;
        }

        private Type(Color color){
            this.color = color;
        }

    }
    
    /**
     * 随机获取一个道具对象
     */
    public static Item getItem(){
        Type[] types= Type.values();
        int index = new Random().nextInt(types.length);
        return new Item(types[index]);
    }
    
    /**
     * UI图标制作太麻烦，所以采用同一个图标根据颜色区分调取效果。
     * 根据类型的不同，为对应的道具图标添加不同的颜色区分
     */
    public Item(Type type){
        Color color = type.getColor();
        GreenfootImage image = getImage();
        image.setColor(color);
        image.fillOval(10,10, 10, 10);
        setImage(image);
        this.type = type;
    }

    /**
     * 判断是否吃到道具，触发道具效果
     */
    public void act() 
    {
        SpaceWorld world = getWorldOfType(SpaceWorld.class);
        // 判断是否吃到道具，并触发道具效果
        if(getOneIntersectingObject(Paddle.class) != null){
            // 吃道具固定获得30分
            world.addScore(30);
            // 触发道具效果
            switch(type){
                case FASTER:
                    world.faster();
                    break;
                case SLOWER:
                    world.slower();
                    break;
                case SPLIT:
                    List<Ball> allBalls = world.getObjects(Ball.class);
                    for(Ball ball : allBalls){
                        Ball nBall = new Ball();
                        nBall.setSpeedX(-ball.getSpeedX());
                        nBall.setSpeedY(-ball.getSpeedY());
                        nBall.setTriggered(true);
                        world.addObject(nBall, ball.getX(), ball.getY());
                    }
                    break;
                case MAGNETIC:
                    getIntersectingObjects(Paddle.class).get(0).setCanStopBall(true);
                    break;
            }
            world.removeObject(this);
            return;
        }
        
        if(getY() >=  world.getHeight() - 15){
            world.removeObject(this);
        }else{
            setLocation(getX(), getY() + 2);
        }
    }    
}
