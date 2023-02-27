import java.util.List;
import java.util.ArrayList;
/**
 * 地图数据.
 * 
 * @author zpf 
 * @version 0.1.0
 */
public class MapData  
{
    private static List<int[][]> map;
    public static final int TYPE_EMPTY = -1;                // 空白
    public static final int TYPE_FLOOR = 0;                 // 地板 （目前没用）
    public static final int TYPE_WAll = 1;                  // 墙壁
    public static final int TYPE_BOX = 2;                   // 箱子
    public static final int TYPE_PERSON = 3;                // 人物
    public static final int TYPE_TARGET = 4;                // 目标点（可与箱子或者人重合，以加和表示）
    
    static {
        map = new ArrayList<int[][]>();
        
        map.add(new int[][]{
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},  
            {-1,-1, 1, 1, 1, 1, 1, 1, 1,-1,-1},  
            {-1,-1, 1, 0, 0, 4, 0, 0, 1,-1,-1},  
            {-1,-1, 1, 0, 0, 2, 0, 0, 1,-1,-1},  
            {-1,-1, 1, 4, 2, 3, 2, 4, 1,-1,-1},  
            {-1,-1, 1, 0, 0, 2, 0, 0, 1,-1,-1},  
            {-1,-1, 1, 0, 0, 4, 0, 0, 1,-1,-1},  
            {-1,-1, 1, 1, 1, 1, 1, 1, 1,-1,-1},  
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}  
        });
        
        map.add(new int[][]{
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},  
            {-1,-1, 1, 1, 1, 1, 1, 1,-1,-1,-1},  
            {-1,-1, 1, 0, 0, 0, 0, 1, 1,-1,-1},  
            {-1,-1, 1, 2, 2, 6, 0, 0, 1,-1,-1},  
            {-1,-1, 1, 4, 4, 3, 4, 4, 1,-1,-1},  
            {-1,-1, 1, 0, 0, 6, 2, 2, 1,-1,-1},  
            {-1,-1, 1, 1, 0, 0, 0, 0, 1,-1,-1},  
            {-1,-1,-1, 1, 1, 1, 1, 1, 1,-1,-1},  
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}  
        });
        
        map.add(new int[][]{
            {-1,-1, 1, 1, 1, 1, 1,-1,-1,-1,-1},
            {-1,-1, 1, 0, 0, 0, 1, 1, 1,-1,-1},
            {-1, 1, 1, 2, 1, 0, 2, 0, 1,-1,-1},
            {-1, 1, 0, 4, 6, 3, 4, 0, 1,-1,-1},
            {-1, 1, 0, 0, 0, 0, 2, 1, 1,-1,-1},
            {-1, 1, 1, 1, 0, 1, 4, 1,-1,-1,-1},
            {-1,-1,-1, 1, 0, 0, 0, 1,-1,-1,-1},
            {-1,-1,-1, 1, 1, 1, 1, 1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}
        });
        
        map.add(new int[][]{
            {-1,-1, 1, 1, 1, 1, 1, 1, 1, 1,-1},
            {-1, 1, 1, 0, 0, 1, 0, 0, 0, 1,-1},
            {-1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1},
            {-1, 1, 0, 1, 0, 6, 3, 1, 0, 0, 1},
            {-1, 1, 4, 4, 0, 4, 4, 0, 1, 0, 1},
            {-1, 1, 0, 1, 2, 2, 1, 2, 2, 0, 1},
            {-1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1},
            {-1, 1, 0, 0, 0, 1, 1, 1, 1, 1,-1},
            {-1, 1, 1, 1, 1, 1,-1,-1,-1,-1,-1}
        });
        
        map.add(new int[][]{
            {-1,-1,-1, 1, 1, 1, 1, 1,-1,-1,-1},
            {-1, 1, 1, 1, 0, 0, 0, 1, 1, 1,-1},
            {-1, 1, 0, 2, 4, 2, 4, 2, 0, 1,-1},
            {-1, 1, 0, 0, 4, 6, 4, 0, 3, 1,-1},
            {-1, 1, 0, 2, 4, 2, 4, 2, 0, 1,-1},
            {-1, 1, 1, 1, 0, 0, 0, 1, 1, 1,-1},
            {-1,-1,-1, 1, 1, 1, 1, 1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}
        });
        
        map.add(new int[][]{
            {-1,-1, 1, 1, 1, 1, 1, 1, 1,-1,-1},
            {-1,-1, 1, 0, 0, 4, 0, 3, 1,-1,-1},
            {-1, 1, 1, 2, 2, 4, 2, 2, 1, 1,-1},
            {-1, 1, 0, 4, 4, 6, 4, 4, 0, 1,-1},
            {-1, 1, 0, 2, 2, 4, 2, 2, 0, 1,-1},
            {-1, 1, 0, 0, 0, 4, 0, 0, 0, 1,-1},
            {-1, 1, 1, 1, 1, 1, 1, 1, 1, 1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}
        });
        
        map.add(new int[][]{
            {-1, 1, 1, 1, 1, 1,-1,-1,-1,-1,-1},
            {-1, 1, 0, 0, 0, 1,-1,-1,-1,-1,-1},
            {-1, 1, 0, 2, 2, 1, 1, 1, 1, 1,-1},
            {-1, 1, 0, 2, 4, 4, 4, 2, 0, 1,-1},
            {-1, 1, 0, 2, 4, 4, 4, 2, 0, 1,-1},
            {-1, 1, 0, 0, 4, 4, 4, 2, 0, 1,-1},
            {-1, 1, 1, 1, 1, 1, 2, 2, 0, 1,-1},
            {-1,-1,-1,-1,-1, 1, 3, 0, 0, 1,-1},
            {-1,-1,-1,-1,-1, 1, 1, 1, 1, 1,-1}
        });
    }
    
    /**
     * 禁止创建对象 
     */              
    private MapData()
    {
    }
    
    public static int[][] getMap(int index){
        return map.get(index);
    }
    
    public static int countMap(){
        return map.size();
    }
}
