import greenfoot.*; 

/**
 * 菜单页面
 * 
 * @author zpf 
 * @version 0.1.0
 */
public class SelectWorld extends World
{
    public SelectWorld()
    {   
        // 地图大小为 12 * 10 ，每个单元格大小为40，场景总像素大小为12*40 * 10*40
        super(11, 9, 48);
        Greenfoot.setSpeed(30);
        
        this.addObject(new Actor(){
            {this.setImage(new GreenfootImage("推箱子",50,Color.BLACK,Color.LIGHT_GRAY));}
        },6,0);
        
        // 有多少关卡生成多少个选择图标
        int count = MapData.countMap(); 
        for(int i = 0; i < count; i++){
                this.addObject(new Selector(i), i%10+1, i/10+2);
        }
    }
}
