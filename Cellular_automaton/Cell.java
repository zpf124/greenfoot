import greenfoot.*;

public class Cell extends Actor
{
    // 0： 新生状态，1：生存状态，2：即将死亡
    private int status = 0;

    public void setStatus(int status)
    {
       this.status = status;
    }
    
    public int getStatus()
    {
        return status;
    }
    
    public Cell(){
        
        // setImage(new GreenfootImage(15,15));
        
    }

    public void act() 
    {
        if(status == 0){
            GreenfootImage img = new GreenfootImage(15,15);
            img.setColor(Color.GREEN);
            img.fillRect(0, 0, 15, 15);
            setImage(img);
            status ++;
        }else if(status == 2){
            getWorld().removeObject(this);
        }
        // else{
            // GreenfootImage img = new GreenfootImage(15,15);
            // img.setColor(Color.RED);
            // img.fillRect(0, 0, 15, 15);
            // setImage(img);
        // }
    }
}
