import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.List;
/**
 * 整个项目写的非常不好，效率很差
 * @author 堕天使
 * @version 1.0 
 */
public class MyWorld extends World
{
    private boolean notFirst;
    public MyWorld()
    {    
        super(40, 30, 20);  
    }

    public void started()
    {
        Greenfoot.setSpeed(25);
        for(int i=0; i<150;i++)
        {
            Random random = new Random();
            int x = random.nextInt(getWidth());
            int y = random.nextInt(getHeight());
            if(getObjectsAt(x, y, Cell.class).size() > 0)
            {
                i--;
            }else
            {
                addObject(new Cell(), x, y);
            }
        }
    }

    public void act()
    {
        if(notFirst)
        {
            for(int x = 0; x < getWidth(); x++)
            {
                for(int y = 0; y < getHeight(); y++)
                {
                    int count = aroundCount(x,y);
                    List<Cell> list = getObjectsAt(x, y, Cell.class);
                    if (count == 3 && list.size() == 0) 
                    {
                        addObject(new Cell(), x, y);
                    } else if((count < 2 || count > 3) && list.size() > 0)
                    {
                        list.get(0).setStatus(2);
                    }
                }
            }
        }else
        {
            notFirst = true;
        }
    }

    private int aroundCount(int x,int y) 
    {
        int count = 0;
        for (int i = -1; i < 2; i++) 
        {
            for (int j = -1; j < 2; j++) 
            {
                // 判断这个点周围的八个点位置是否超出边界，超出就跳过。
                if (x + i < 0 || x + i >= getWidth() || y + j < 0 || y + j >= getHeight() || i == 0 && j == 0) 
                {
                    continue;
                }
                List<Cell> list = getObjectsAt(x + i, y + j, Cell.class);
                // 判断点是否是在这一轮存在，等于0说明是刚刚创建的，也就是下一轮才要显示的点。
                if (list.size() > 0 && list.get(0).getStatus() != 0) 
                {
                    count++;
                }
            }
        }
        return count;
    }
}
