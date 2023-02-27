import java.util.List;

import greenfoot.*;
/**
 * 游戏场景<BR>
 * 加载地图判断，是否结束游戏
 * 
 * @author zpf(<A href="mailto:596792787@qq.com">596792787@qq.com</A>)
 * @version 0.1.0
 */
public class GameWorld extends World
{
    private int level;
    
     public GameWorld(int level)
    {    
        super(11, 9, 48);
        this.level = level;
        // 设置绘制的顺序，由高到低。
        this.setPaintOrder(Wall.class,Box.class,Person.class,Target.class,Floor.class);
        loadMap();
    }
    
    /**
     *  判断是否过关
     */
    public void act(){
        List<Box> boxList = this.getObjects(Box.class);
        boolean success = true;
        for(Box box : boxList){
            success &= box.getHited();
        }
        if(success){
            level++;
            if(level < MapData.countMap() - 1){
//                 Greenfoot.setWorld(new GameWorld(level));
                this.removeObjects(this.getObjects(null));
                loadMap();
            }else{
                Greenfoot.setWorld(new SelectWorld());
            }
        }
    }
    
    private void loadMap(){
        int[][] map = MapData.getMap(level);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] != MapData.TYPE_EMPTY){
                    this.addObject(new Floor(), j, i);
                }
                switch (map[i][j]) {
                    case MapData.TYPE_WAll:
                        this.addObject(new Wall(), j, i);
                        break;
                    case MapData.TYPE_BOX:
                        this.addObject(new Box(), j, i);
                        break;
                    case MapData.TYPE_TARGET:
                        this.addObject(new Target(), j, i);
                        break;
                    case MapData.TYPE_PERSON:
                        this.addObject(Person.getInstance(), j, i);
                        break;
                    case MapData.TYPE_BOX +  MapData.TYPE_TARGET:
                        this.addObject(new Target(), j, i);
                        this.addObject(new Box(), j, i);
                        break;
                    case MapData.TYPE_PERSON +  MapData.TYPE_TARGET:
                        this.addObject(Person.getInstance(), j, i);
                        break;
                }
            }
        }
    }
}
