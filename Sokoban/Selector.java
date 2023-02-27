import greenfoot.*;
/**
 * 推箱子关卡选择器
 * 
 * @author zpf
 * @version 0.1.0
 */
public class Selector extends Actor {

    public final int level;

    public Selector(int level){
        this.level = level;
        
        // 在图片上显示关卡
        GreenfootImage image = this.getImage();
        image.setColor(Color.WHITE);
        image.setFont(new Font("宋体",true, false, 14));
        image.drawString(String.valueOf(level+1), 10, 30);
        this.setImage(image);
    }

    public void act(){
        if(Greenfoot.mouseClicked(this)){
            Greenfoot.setWorld(new GameWorld(level));
        }
    }
}
