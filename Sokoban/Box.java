import greenfoot.*;

/**
 * 箱子类<BR>
 * 判断箱子是否到达目标点，判断箱子是否        
 * 
 * @author zpf
 * @version 0.1.0
 */
public class Box extends Actor
{
    private boolean hited;
    
    public boolean getHited(){
        return hited;
    }
    
    public void act() 
    {
        hited = this.getOneObjectAtOffset(0,0,Target.class) != null;
        if(hited){
            this.setImage(new GreenfootImage("box_hit.png"));
        }else{
            this.setImage(new GreenfootImage("box.png"));
        }
    }
    
    public boolean tryMoving(int x, int y)
    {
        Actor wall = this.getOneObjectAtOffset(x, y, Wall.class);
        if(wall != null) {
            return false;
        }
        
        Actor actor = this.getOneObjectAtOffset(x , y, Box.class);
        if(actor != null){
            return false;
        }
        
        this.setLocation(this.getX() + x, this.getY() + y);
        return true;
    }

}
