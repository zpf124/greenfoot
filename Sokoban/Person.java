import greenfoot.*;
/**
 * 游戏中控制的人物，通过单例模式保证地图上只会有一个可控制对象
 * 
 * @author zpf
 * @version 0.1.0
 */
public class Person extends Actor {
    private static Person person;                           // 保存人物对象，当别处用时，不创建新的，直接将这个交给调用者。
    private GreenfootImage[][] personImage;                 // 人物的图片，每个方向4张共16张。                                    PS：其实每个方向的4张应该可以做成一个gif动图，那样就只需要一个一位数组了。
    private PersonState personState;                        // 用户的当前状态（朝向），共4个值 DOWN，LEFT , RIGHT, UP。           PS：其实这里我为了装逼使用了相对高端一点的枚举，不使用枚举，用0-3表示依旧可以。
    private int imageIndex;                                 // 当前显示图片序号
    
    public static Person getInstance() {
        if(person == null){
            person = new Person();
        }
        return person;
    }
    
    private Person() {
        personState = PersonState.DOWN;
        loadPicture();
    }
    
    /**
     *  加载所有人物动作图片
     */
    private void loadPicture() {
        personImage = new GreenfootImage[4][4];
        for (int i = 0; i < personImage.length; i++) {
            for (int j = 0; j < personImage[i].length; j++) {
                personImage[i][j] = new GreenfootImage("person_" + PersonState.getName(i) + "_" + j + ".png");
            }
        }
    }
    
    public void act() {
        //         // 通过获取最后一次按下的键来判断，移动操作。 代码简单，但有缺点，用户按住不放时，不能检测到，所以不能连续移动。
        //         String keyName = Greenfoot.getKey();
        //         if (keyName != null) {
        //             personState = PersonState.getPersonStateByName(keyName);
        //             trymoving();
        //         }

        if (Greenfoot.isKeyDown(PersonState.DOWN.name)){
            personState = PersonState.DOWN;
            tryMoving();
        }else if (Greenfoot.isKeyDown(PersonState.LEFT.name)){
            personState = PersonState.LEFT;
            tryMoving();
        }else if (Greenfoot.isKeyDown(PersonState.RIGHT.name)){
            personState = PersonState.RIGHT;
            tryMoving();
        }else if (Greenfoot.isKeyDown(PersonState.UP.name)){
            personState = PersonState.UP;
            tryMoving();
        }
        animation();
    }
    
    private boolean tryMoving(){
        int x = 0;
        int y = 0;
        switch(personState) {
            case DOWN:
                y = 1;
                break;
            case LEFT:
                x = -1;
                break;    
            case RIGHT:
                x = +1;
                break;
            case UP:
                y = -1;
                break;
        }
        
        if (x != 0 || y != 0){
            if(moveable(x,y)){
                this.setLocation(this.getX() + x, this.getY() + y);
                return true;
            }
        }
        return false;
    }
    
    /**
     *  判断是否可移动<BR>
     *  如果遇到墙，那么就不可移动；如果没有遇到墙也没有遇到箱子，就可移动；如果遇到了箱子，那么就判断箱子是否可以移动。
     */
    private boolean moveable(int x,int y){

        Actor wall = this.getOneObjectAtOffset(x, y, Wall.class);
        if(wall != null) {
            return false;
        }
        
        Actor actor = this.getOneObjectAtOffset(x , y, Box.class);
        if(actor == null){
            return true;
        }
        
        Box box = (Box)actor;
        return box.tryMoving(x,y);
    }
    
    /**
     * 人物动画
     */
    private void animation() {
        this.setImage(personImage[personState.index][imageIndex++ % 4]);
    }
    
    /**
     * 表示人物当前状态的枚举类
     */
    private enum PersonState {
        DOWN("down",0), LEFT("left",1), RIGHT("right",2), UP("up",3);
        
        public final String name;
        public final int index;
        
        private PersonState(String name, int index) {
            this.name = name;
            this.index = index;
        }
        
        public static PersonState getPersonStateByName(String name) {
            for (PersonState personState : PersonState.values()) {
                if (personState.name.equals(name)) {
                    return personState;
                }
            }
            return null;
        }
        
         public static String getName(int index) {
            for (PersonState personState : PersonState.values()) {
                if (personState.index == index) {
                    return personState.name;
                }
            }
            return null;
        }
    }
}
