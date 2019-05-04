package SandBox;

import Animations.Black;
import Animations.BossFallingAnim;
import Animations.BrickPeaces;
import Animations.FlagTop;
import Animations.MarioGoingDownAnimation;
import Animations.MarioGoingInPump;
import Bricks.Axe;
import Bricks.Bank;
import Bricks.BankWithItem;
import Bricks.Bouncer;
import Bricks.Brick;
import Bricks.BrickWithStar;
import Bricks.Flag;
import Bricks.InvisibleBrck;
import Bricks.Iron;
import Bricks.QuestionMark;
import Bricks.RocketLauncher;
import Bricks.RocketLauncherBody;
import Bricks.Spring;
import Bricks.Stone;
import Bricks.Tree;
import Bricks.WoodenBridge;
import Bricks.chocolate;
import Bricks.pump;
import CheckPoint.CheckPoints;
import Collusion.EnemyToEnemy;
import Collusion.Enemy_Brick;
import Collusion.Enemy_FlyingFishes;
import Collusion.FireBallToBricks;
import Collusion.FireBallToEnemys;
import Collusion.FireBall_FlyingFishes;
import Collusion.Hammer_Player;
import Collusion.Player_Brick;
import Collusion.Player_CheckPoint;
import Collusion.Player_EnemyGroup;
import Collusion.Player_Flag;
import Collusion.Player_InvisibleObjects;
import Collusion.Player_Lift;
import Collusion.Player_Teleport;
import Gears.Codes;
import Gears.Construct;
import Levels.BasicLevel;
import Levels.Eight.BonusArea.BonusArea81B;
import Levels.Eight.BonusArea.BonusArea82E;
import Levels.Eight.Level_81;
import Levels.Eight.Level_82;
import Levels.Eight.Level_83;
import Levels.Eight.Level_841;
import Levels.Eight.Level_842;
import Levels.Eight.Level_843;
import Levels.Eight.Level_844;
import Levels.Eight.Level_845;
import Levels.Extra.Level_Start;
import Levels.Extra.TestArea;
import Levels.Five.BonusArea.BonusArea51E;
import Levels.Five.BonusArea.BonusArea52F;
import Levels.Five.Level_51;
import Levels.Five.Level_52;
import Levels.Five.Level_53;
import Levels.Five.Level_54;
import Levels.Four.BonusArea.BonusArea41D;
import Levels.Four.BonusArea.BonusArea42E;
import Levels.Four.Level_41;
import Levels.Four.Level_42;
import Levels.Four.Level_43;
import Levels.Four.Level_44;
import Levels.One.BonusArea.BonusArea11A;
import Levels.One.BonusArea.BonusArea12B;
import Levels.One.Level_11;
import Levels.One.Level_12;
import Levels.One.Level_13;
import Levels.One.Level_14;
import Levels.Seven.BonusArea.BonusArea71A;
import Levels.Seven.Level_71;
import Levels.Seven.Level_72;
import Levels.Seven.Level_73;
import Levels.Seven.Level_74;
import Levels.Six.BonusArea.BonusArea62D;
import Levels.Six.BonusArea.BonusArea62E;
import Levels.Six.BonusArea.BonusArea62G;
import Levels.Six.Level_61;
import Levels.Six.Level_62;
import Levels.Six.Level_63;
import Levels.Six.Level_64;
import Levels.Three.BonusArea.BonusArea31C;
import Levels.Three.Level_31;
import Levels.Three.Level_32;
import Levels.Three.Level_33;
import Levels.Three.Level_34;
import Levels.Two.BonusArea.BonusArea21A;
import Levels.Two.Level_21;
import Levels.Two.Level_22;
import Levels.Two.Level_23;
import Levels.Two.Level_24;
import Lifts.BalenceLiftParent;
import Lifts.LiftDown;
import Lifts.LiftFall;
import Lifts.LiftUP;
import Lifts.Lift_LeftRight;
import Lifts.Lift_LeftRightInvert;
import Lifts.Lift_UpDown;
import Objects.Boss;
import Objects.Coin;
import Objects.EnemyFireBall;
import Objects.EnemyMashroom;
import Objects.EnemyTurtle;
import Objects.EnemyTurtlePatrol;
import Objects.Fire;
import Objects.FishyGround;
import Objects.FishyWater;
import Objects.FlyingTurtle;
import Objects.FlyingTurtlePatrol;
import Objects.Helmet;
import Objects.LavaBall;
import Objects.Monkey;
import Objects.MovingTurtelShell;
import Objects.OctoPussy;
import Objects.Player;
import Objects.Rocket;
import Objects.SonOfABuitch;
import Objects.TurtelShell;
import Objects.plant;
import Teleport.SpriteTeleport;
import Teleport.Teleport;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.util.ImageUtil;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Random;


public class Mario extends GameObject{

    
    PlayField  playfield ;
    
    ColorBackground color_background ;
    
    CollisionManager   Player_FlyingFishesCollisionManager  , FireBall_FlyingFishesCollisionManager , Enemy_FlyingFishesCollisionManager  ,
            Player_InvisibleObjects_CollisionManager , Player_Teleport_CollisionManager , Player_Lift_CollisionManager , Player_CheckPointCollisionManager ,  Player_Flag_CollisionManager ,  Enemy_Brick_CollisionManager , Player_Brick_CollisionManager , Hammer_Player_CollisionManager , 
            Player_Plant_CollisionManager  , Player_Enemy_CollisionManager  , Enemy_Enemy_CollisionManager ,FireBall_PlantCollisionManager ,
            FireBall_BricksCollisionManager , FireBall_Enemys_CollisionManager ;
    
    public SpriteGroup FlyingFishesGroup ,InvisibleObjects ,  PlantGroup , TeleportGroup ,LiftGroup , UnderGroup ,  FlagGroup , HammerGroup , FireBall , AnimationGroup , EnemyGroup ,    BrickGroup , PlayerGroup , VolitileGroup , BackGroundSpriteGroup ;
    
    public Player player ;
    private int degree = 0 ;
    private String Side = "up";
    
    int LevelNumber = 14 ;
    public int PlayetX = 3 ,  PlayerY = 6;
    private boolean PlayLivingThings = true ;
    private boolean FindDublicatesON = false;
    public double Distance = Math.PI ;
    public double InvertDistance = 0 ;
    public Boss boss;
    public Sprite DemoMario;
    private String GameAttribute;

    CheckPoints CurrentCheckpoint ;
    private boolean LevelComplete = false;
    private CheckPoints cp;
    public int DelayToNextCheckPoint = 120;
    private FlagTop flagtop;

    boolean Bombs = false ;
    private int BombsDelay = 50;
    public int BombsTurnOff = -1 ;
    
    public BasicLevel CurrentLevel  ;
    
    boolean PlayGame = true ;
    
    GameFont font ;
    private String WORLD;
    
    boolean StopScroll = true ;
    private boolean SmoothScroll = false ;
    
    public Color BackGroundColor = new Color(92 , 148 , 252);
    
    int StartWorld = 1 ;
    int StartLevel = 1 ;
    Sprite NAME ;
    
    int Screenshot = 1 ;
    
    boolean FlyingFishes = false ;
    private int FlyingFishesLength = 50;
    
    private int FlyingFishesdelay = 0;
    private boolean ShowInfo;
    private BufferedImage Info;
    
    Mario(WholeGame aThis, CheckPoints CP) {
        super(aThis) ;
        CurrentCheckpoint = CP ;
        
        LevelNumber = CurrentCheckpoint.GetNextLevel();
        
        PlayetX = CurrentCheckpoint.NextLocation.x * 32;
        PlayerY = CurrentCheckpoint.NextLocation.y * 32;
        
        
    }
    

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
   
    public void initResources() {
//        this.bsMusic.setExclusive(true);
//        this.bsSound.setVolume(0.5f);
        URL url = null;
        
        Info = this.bsLoader.getStoredImage("Info");
        try {        
            
            setFPS(40);
            
//            LoadImages();
            EnemyGroup  = new SpriteGroup("EnemyGroup");
//            EnemyGroup.add(new Enemy(31*32, 10*32 , this.bsLoader.getStoredImages("enemy") , this) );
//            EnemyGroup.add(new Enemy(32*32, 10*32 , this.bsLoader.getStoredImages("enemy") , this) );
//            EnemyGroup.add(new Enemy(33*32, 10*32 , this.bsLoader.getStoredImages("enemy") , this) );
//            EnemyGroup.add(new Enemy(34*32, 10*32 , this.bsLoader.getStoredImages("turtle") , "turtle" , this ) );
            
            BrickGroup  = new SpriteGroup("BrickGroup");
            HammerGroup  = new SpriteGroup("HammerGroup "); // only check collusion with player and kills him 
            FlagGroup  = new SpriteGroup("FlagGroup");
            AnimationGroup = new SpriteGroup("AnimationGroup"); // specially made to render animations on top of all
                                                               //  spritegroup
            FireBall  = new SpriteGroup("FireBall");
            UnderGroup = new SpriteGroup("UnderGroup");
            LiftGroup = new SpriteGroup("LiftGroup");
            TeleportGroup = new SpriteGroup("TeleportGroup");
            PlantGroup = new SpriteGroup("PlantGroup");
            InvisibleObjects = new SpriteGroup("InvisibleObjects");
            FlyingFishesGroup = new SpriteGroup("FlyingFishesGroup");
            
            
                    
            CurrentLevel = SelectLevel(LevelNumber);
            GameAttribute = CurrentLevel.attribute ;
            
            /*************** Level Other Data *****************/
            // Tiled background 
            BackGroundSpriteGroup   = new SpriteGroup("BackGroundSpriteGroup");
            int Tiles = 10 ;
            for (int i = 0; i < Tiles; i++) {
                // y =32 because those images are 32 pixels above ground level
                if("Mountain".equals(CurrentLevel.BackGroundImage)){
            BackGroundSpriteGroup.add(new Sprite(this.getImage("Mountain.png") , 1536 * i , 32 ));
                }
                else if("Clouds".equals(CurrentLevel.BackGroundImage)){
            BackGroundSpriteGroup.add(new Sprite(this.getImage("Clouds.png") , 1536 * i , 32 ));
                }
                else if("CloudsNight".equals(CurrentLevel.BackGroundImage)){
            BackGroundSpriteGroup.add(new Sprite(this.getImage("CloudsNight.png") , 1536 * i , 32 ));
                }
                else if("Fence".equals(CurrentLevel.BackGroundImage)){
            BackGroundSpriteGroup.add(new Sprite(this.getImage("Fence.png") , 1536 * i , 32 ));
                }
                else if("Fence2".equals(CurrentLevel.BackGroundImage)){
            BackGroundSpriteGroup.add(new Sprite(this.getImage("Fence2.png") , 1536 * i , 32 ));
                }
                 
                
            }
            
            // sea
            if("Sea".equals(CurrentLevel.attribute)){
            for (int i = 0; i < CurrentLevel.LevelLength/32; i++) {
                BackGroundSpriteGroup.add(new Sprite(this.getImage("Sea.png") , 32 * i , 0));
            }
            }
            
            LoadLevel(CurrentLevel);
            
            LoadCheckPoints(CurrentLevel);
            
            LoadTeleport(CurrentLevel);
            
            LoadInvisibleObjects();
            
            Bombs = CurrentLevel.Bombs ;
            BombsTurnOff = CurrentLevel.BombsTurnOff ;
            
            FlyingFishes = CurrentLevel.FlyingFishes ;
            FlyingFishesLength = CurrentLevel.FlyingFishesLength ;
            
            // Background Color
            if("Blue".equals(CurrentLevel.BackGroundColor)){
            color_background = new ColorBackground(this.BackGroundColor , CurrentLevel.LevelLength, 480);
            }
            else if ("Black".equals(CurrentLevel.BackGroundColor)){
            color_background = new ColorBackground(Color.black , CurrentLevel.LevelLength, 480);
            }else if("DarkBlue".equals(CurrentLevel.BackGroundColor)){
            color_background = new ColorBackground(new Color(32 , 56 , 236) , CurrentLevel.LevelLength, 480);
            }else{
            color_background = new ColorBackground(Color.GREEN , CurrentLevel.LevelLength, 480);
            }
            
            color_background.setClip(0, 0, 640, 480);
            /*************** Level Other Data Finish *****************/
            
            Enemy_Brick_CollisionManager = new Enemy_Brick(this);
            Enemy_Brick_CollisionManager.setCollisionGroup(EnemyGroup, BrickGroup);
            
            player = new Player( PlayetX , PlayerY , this.bsLoader.getStoredImages("player") , this);
            
            PlayerGroup  = new SpriteGroup("PlayerGroup"); 
            PlayerGroup.add(player);
            Player_Brick_CollisionManager = new Player_Brick(this);
            Player_Brick_CollisionManager.setCollisionGroup(PlayerGroup, BrickGroup);
            
            Player_Enemy_CollisionManager = new Player_EnemyGroup(this);
            Player_Enemy_CollisionManager.setCollisionGroup(PlayerGroup, EnemyGroup);
            
            Player_Plant_CollisionManager = new Player_EnemyGroup(this);
            Player_Plant_CollisionManager.setCollisionGroup(PlayerGroup, PlantGroup);
            
            Player_InvisibleObjects_CollisionManager = new Player_InvisibleObjects(this);
            Player_InvisibleObjects_CollisionManager.setCollisionGroup(PlayerGroup, InvisibleObjects);
            
                    
            Player_Lift_CollisionManager  = new Player_Lift(this);
            Player_Lift_CollisionManager.setCollisionGroup(PlayerGroup, LiftGroup);
            
            Player_Teleport_CollisionManager  = new Player_Teleport(this);
            Player_Teleport_CollisionManager.setCollisionGroup(PlayerGroup, TeleportGroup);
            
            Player_FlyingFishesCollisionManager  = new Player_EnemyGroup(this);
            Player_FlyingFishesCollisionManager.setCollisionGroup(PlayerGroup, FlyingFishesGroup);
            
            FireBall_FlyingFishesCollisionManager   = new FireBall_FlyingFishes(this);
            FireBall_FlyingFishesCollisionManager.setCollisionGroup(FireBall, FlyingFishesGroup);
            
            Enemy_FlyingFishesCollisionManager  = new Enemy_FlyingFishes(this);
            Enemy_FlyingFishesCollisionManager.setCollisionGroup(EnemyGroup, FlyingFishesGroup);
            
                    
                    
            VolitileGroup  = new SpriteGroup("VolitileGroup"); 
            
            
            playfield = new PlayField(color_background);
            
            playfield.addGroup(InvisibleObjects);
            playfield.addGroup(UnderGroup);
            playfield.addGroup(BackGroundSpriteGroup);
            playfield.addGroup(PlayerGroup);
            playfield.addGroup(VolitileGroup);
            playfield.addGroup(PlantGroup);
            playfield.addGroup(BrickGroup);
            playfield.addGroup(EnemyGroup);
            playfield.addGroup(LiftGroup);
            playfield.addGroup(HammerGroup);
            playfield.addGroup(FlagGroup);
            playfield.addGroup(TeleportGroup);
            playfield.addGroup(FlyingFishesGroup);
            playfield.addGroup(FireBall);
            playfield.addGroup(AnimationGroup);
            
            Enemy_Enemy_CollisionManager = new EnemyToEnemy(this);
            Enemy_Enemy_CollisionManager.setCollisionGroup(EnemyGroup, EnemyGroup);
            
            FireBall_BricksCollisionManager = new FireBallToBricks(this);
            FireBall_BricksCollisionManager.setCollisionGroup(FireBall, BrickGroup);
            
            FireBall_Enemys_CollisionManager = new FireBallToEnemys(this);
            FireBall_Enemys_CollisionManager.setCollisionGroup(FireBall, EnemyGroup);
            
            FireBall_PlantCollisionManager = new FireBallToEnemys(this);
            FireBall_PlantCollisionManager.setCollisionGroup(FireBall, PlantGroup);
            
                    
            
            Hammer_Player_CollisionManager  = new Hammer_Player(this);
            Hammer_Player_CollisionManager.setCollisionGroup(HammerGroup , PlayerGroup);
            
            Player_Flag_CollisionManager  = new Player_Flag(this);
            Player_Flag_CollisionManager.setCollisionGroup(FlagGroup , PlayerGroup);
            
            Player_CheckPointCollisionManager  = new Player_CheckPoint(this);
            Player_CheckPointCollisionManager.setCollisionGroup( PlayerGroup , FlagGroup);
            
            
            FindDublicates(CurrentLevel);
            
            
            if("Sea".equals(CurrentLevel.attribute)){
                this.player.Water= true ;
            }
            
            String stringfont = "_0123456789ABCDE"
                              + "FGHIJKLMNOPQRSTU"
                              + "VWXYZ @#*-x^!&=$";
            
            font = this.fontManager.getFont(this.bsLoader.getStoredImages("Font"), stringfont) ;
                        
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            
        }
            
        
//        player.SetStatus(2);
////        
//        
//        player.Grow();
//        player.Grow();
            
        if(this.LevelNumber == 10){ // start screen
            player.NoControlling();
            NAME = new Sprite(this.bsLoader.getStoredImage("Start"), 144 , 64  ); 
            
        }else{
            this.playMusic("music/"+this.CurrentLevel.attribute+".mp3");
        }
        
        
    }

    public void update(long l) {

        
        if(this.LevelNumber == 10){ // start screen
           if(this.bsInput.isKeyPressed(KeyEvent.VK_Z)){
            IncreaseWorld();
        }
           if(this.bsInput.isKeyPressed(KeyEvent.VK_X)){
            IncreaseLevel();
        }
           
           if(this.bsInput.isKeyPressed(KeyEvent.VK_ENTER)){
               
               CheckPoints oneone = new CheckPoints(4, 6 , (this.StartWorld*10)+this.StartLevel , new Point(4, 6));
               cp  =oneone ;

                parent.nextGameID = cp ;
                this.finish();
        
           }
        }
        

        if(this.bsInput.isKeyPressed(KeyEvent.VK_ENTER)){
            if(PlayGame){
                PlayGame = false ;
            }else{
                PlayGame = true ;
            }
            playSound("music/smb_pause.wav");
        }
        
        if(PlayGame){
//        Enemy.setLocation(this.getMouseX(), this.getMouseY());
        
        //      <<<<<<<         player Controls
        
//        if(player.getX() < )
         if(Bombs){
             if(this.player.getX()/32 < BombsTurnOff ){
             BombsDelay-- ;
             if(BombsDelay < 0){
             Random r = new Random();
             BombsDelay = (r.nextInt(5)+1)*50 ;
             if("CloudsNight".equals(this.CurrentLevel.BackGroundImage)){ // balck and white
          EnemyGroup.add(new Rocket((this.player.getX()-this.player.getScreenX() )+ 680 , (r.nextInt(10)+1)*32 , "gotoleft" , bsLoader.getStoredImages("BWRocketLauncher")[3], this ) );
  
//          System.out.println("bomb");
             }else {
//                 System.out.println("bomb");
          EnemyGroup.add(new Rocket((this.player.getX()-this.player.getScreenX() )+ 680 , (r.nextInt(10)+1)*32 , "gotoleft" , bsLoader.getStoredImages("RocketLauncher")[3], this ) );
             }}
             }
         }
         
         
         if(FlyingFishes  & player.getX() < FlyingFishesLength & player.getX() > 20*32){
             
             if(FlyingFishesGroup.getSize() < 10 ){
                 if(FlyingFishesdelay < 0){
                 FlyingFishesGroup.add(new FishyGround(this));
                 FlyingFishesdelay = 50 ;
                 }
                 
                 if(FlyingFishesdelay > -10){
                 FlyingFishesdelay-- ;
                 }
                 
             }
             
         }
         
        if(LevelComplete){
            DelayToNextCheckPoint-- ;
        }
        if(DelayToNextCheckPoint < 0){
            this.LoadNextCheckPoint();
        }

        
        if(player.getY() >500){
            this.Restart();
        }
        
        if(this.bsInput.isKeyDown(KeyEvent.VK_RIGHT)){
            player.GoToRight();
        }else{
            player.KeyPressedRight = false ;
        }
        
        
        if(this.bsInput.isKeyDown(KeyEvent.VK_LEFT)){
            player.GoToLeft();
        }
        if(this.bsInput.isKeyDown(KeyEvent.VK_DOWN)){
            player.Down();
        }else{
            player.KeyPressedDown = false ;
        }
        if(this.bsInput.isKeyDown(KeyEvent.VK_UP)){
            player.Up();
        }
        
        if(this.bsInput.isKeyPressed(KeyEvent.VK_Z)){
            player.Fire();
        }
        if(this.bsInput.isKeyDown(KeyEvent.VK_Z)){
            player.Speed(true);
            
        }else{
            player.Speed(false);
        }
        if(this.bsInput.isKeyDown(KeyEvent.VK_X)){
            if(!player.Water){
            player.Jump();
            }
        }
        if(this.bsInput.isKeyPressed(KeyEvent.VK_X)){
            if(player.Water){
            player.Jump();
            
            }
        }
        
        if(this.bsInput.isKeyPressed(KeyEvent.VK_R)){
            
            player.setX(172*32);
            
        }
        
        //                      player Controls   >>>>>>>
        
        
//        playfield.getBackground().move(1, 0);
        
        
//        if(player.getScreenX()+16 > this.getWidth()/2){
        if(StopScroll){
            color_background.setToCenter(player);
        }
        
        if(player.getScreenX() < 0){
            player.moveX(8);
            player.StopSpeed();
        }
        if(player.getScreenX() > 608){
            player.moveX(-8);
            player.StopSpeed();
        }
        
        if(this.SmoothScroll){
        if(this.player.getScreenX() > 320){
            this.color_background.move(5, 0);
        }
        }
//            
        
        
        if(PlayLivingThings){
        playfield.update(l);
        
        Player_Teleport_CollisionManager.checkCollision();
        Enemy_Brick_CollisionManager.checkCollision();
        Player_Brick_CollisionManager.checkCollision();
        Player_Plant_CollisionManager.checkCollision();
        Player_Enemy_CollisionManager.checkCollision();
        Enemy_Enemy_CollisionManager.checkCollision();
        FireBall_BricksCollisionManager.checkCollision();
        FireBall_Enemys_CollisionManager.checkCollision();
        FireBall_PlantCollisionManager.checkCollision();
        Hammer_Player_CollisionManager.checkCollision();
        Player_Flag_CollisionManager.checkCollision();
        Player_CheckPointCollisionManager.checkCollision();
        Player_Lift_CollisionManager.checkCollision();
        Player_InvisibleObjects_CollisionManager.checkCollision();
        Player_FlyingFishesCollisionManager.checkCollision();
        FireBall_FlyingFishesCollisionManager.checkCollision(); 
        Enemy_FlyingFishesCollisionManager.checkCollision();
        
        
        Distance = Distance+ 0.02;
        InvertDistance = InvertDistance- 0.02;
        if(Distance >= Math.PI*2){
            Distance = 0 ;
        }
//        if(InvertDistance <= -Math.PI*2){
//            InvertDistance = 0 ;
//        }
        
         }else{
            this.AnimationGroup.update(l);
//             for (int i = 0; i < playfield.getGroups().length; i++) {
//                 
//                 if(playfield.getGroups()[i] == this.VolitileGroup){ // render
//                     playfield.getGroups()[i].update(l);
//                 }
//             }
             
        }
        
//        color_background.update(l);
//        
//        EnemyGroup.update(l);
//        BrickGroup.update(l);
//        PlayerGroup.update(l);
//        VolitileGroup.update(l); 
        
        
        
        
        
        if(this.bsInput.isKeyPressed(KeyEvent.VK_B)){
            player.STAR();
        }
        
        
        
        RemoveOutOfScreenFireBalls();
        }
        
        
        if(this.bsInput.isKeyPressed(KeyEvent.VK_ESCAPE)){
            this.finish();
        }
        
        if(this.bsInput.isKeyDown(KeyEvent.VK_I)){
            ShowInfo();
        }
        
        if(this.bsInput.isKeyPressed(KeyEvent.VK_T)){
            File F = new File(Screenshot+".png");
            while(F.exists()){
                Screenshot++;
                F = new File(Screenshot+".png");
            }
            this.takeScreenShot(F);
        }
    }

    public void render(Graphics2D g) {
        
//        g.scale((double)this.getWidth()/640, (double)this.getHeight()/480);
        if(PlayGame){
        g.setColor(new Color(146 , 146 , 255));
        g.fillRect(0, 0, this.getWidth() , this.getHeight());
        
//        color_background.render(g);
//        EnemyGroup.render(g);
//        BrickGroup.render(g);
//        PlayerGroup.render(g);
//        VolitileGroup.render(g); 
        
        
         if(PlayLivingThings){
        playfield.render(g);
         }else{
             playfield.getBackground().render(g);
             
             for (int i = 0; i < playfield.getGroups().length; i++) {
                 
                 if(playfield.getGroups()[i] != PlayerGroup){ // render
                     playfield.getGroups()[i].render(g);
                 }
             }
             
        }
         
         if(this.LevelNumber == 10){ // start screen
            NAME.render(g);
        }
        
        
//         g.setColor(Color.GREEN);
         DrawScore(g);
        
//        g.setColor(Color.WHITE);
//        font.drawString(g ,"X  "+(int)player.getX()/32 + " Y "+(int)player.getY()/32, 20, 30 );
        //        g.drawString("degree : "+degree, 10, 10 );
         
         
        }else{
            font.drawString( g , " PAUSE " , 280, 232);
        }
        
        if(ShowInfo){
            g.drawImage(Info, null, 0, 0);
            ShowInfo = false ;
        }
                
//            g.setColor(Color.white);
//             g.drawString("degree :" +degree , 100,100 ); 
//        
    }
    

    public void setDegree(int angle) {
        degree = angle ;
    }

    public void setDegree(String side) {
        Side = side ;
    }

    public void addSomeBrickFragmends(double x, double y) {
        
        BrickPeaces UpLeft = null ;
        BrickPeaces UpRight = null ;
        BrickPeaces DownLeft = null ;
        BrickPeaces DownRight = null ;
        
        BufferedImage[] brik = new BufferedImage[2] ;
        
        if("Sea".equals(GetAttribute())){
        brik[0] = this.bsLoader.getStoredImages("BrickPeaces")[2];
        brik[1] = this.bsLoader.getStoredImages("BrickPeaces")[3];
    }else if("Ground".equals(GetAttribute())){
        brik[0] = this.bsLoader.getStoredImages("BrickPeaces")[2];
        brik[1] = this.bsLoader.getStoredImages("BrickPeaces")[3];
    }else if("UnderGround".equals(GetAttribute())){
        brik[0] = this.bsLoader.getStoredImages("BrickPeaces")[4];
        brik[1] = this.bsLoader.getStoredImages("BrickPeaces")[5];
    }else if("Castle".equals(GetAttribute())){
        brik[0] = this.bsLoader.getStoredImages("BrickPeaces")[6];
        brik[1] = this.bsLoader.getStoredImages("BrickPeaces")[7];
        
    } 
        UpLeft  = new BrickPeaces(x , y , -4 , -16 , brik);
        UpRight = new BrickPeaces(x , y , 4 , -16 , brik);
        DownLeft = new BrickPeaces(x , y , -3 , -12 , brik);
        DownRight = new BrickPeaces(x , y , 3 , -12 , brik);
        
        
        AnimationGroup.add(UpLeft);
        AnimationGroup.add(UpRight);
        AnimationGroup.add(DownLeft);
        AnimationGroup.add(DownRight);
        
        this.playSound("music/smb_breakblock.wav");
        
    }

    public void pauseEnemys() {
        PlayLivingThings = false ;
    }

    public void PlayEnemys() {
        PlayLivingThings = true ;
    }


    private void LoadLevel(BasicLevel CurrentLevel) {
        
            for(int i = 0 ; i < CurrentLevel.Get_Items_Amount() ; i++){ // how many items
                
                if(CurrentLevel.get_Item_Number(i) != null){ // dont throw null exception error
                Construct constr = CurrentLevel.get_Item_Number(i);
                
                        for (int x = 0 ; x < constr.Length_X ; x++){
                            for (int y = 0 ; y < constr.Length_Y ; y++){
    //                            
                            switch(Codes.GetCode(constr.getItem_Type())){
                                
                                case 3: // brick
                                        if("Sea".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new Brick((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick_Sea") , this) );
                                    }
                                    else if("Ground".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new Brick((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick")  , this) );
                                    }
                                    else if("UnderGround".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new Brick((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick_UnderGround") , this ) );
                                    }
                                    else if("Castle".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new Brick((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick_Castle") , this ) );
                                    }
                                     
                                    break;
                                case 4: // QuestionMark
                                    BrickGroup.add(new QuestionMark((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) ,this));
                                    break;
                                case 5: // QuestionMark &  Mashroom
                                    BrickGroup.add(new QuestionMark((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImages("QuestionMark"), "Mashroom" ,this));
                                    break;
                                case 6: // stone
                                    
                                    if("CloudsNight".equals(this.CurrentLevel.BackGroundImage)){
                                      
                                        BrickGroup.add(new Stone((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) ,this.bsLoader.getStoredImage("BWstone") ) );
                                    
                                            }else{
                                        if("Sea".equals(CurrentLevel.attribute)){
                                            if(this.LevelNumber == 844){
                                        BrickGroup.add(new Stone((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("stone_Castle_Sea") ) );

                                            }else{
                                        BrickGroup.add(new Stone((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("stone_Sea") ) );
                                            }
                                            }
                                    else if("Ground".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new Stone((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("stone") ) );
                                    }
                                    else if("UnderGround".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new Stone((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("stone_UnderGround") ) );
                                    }
                                    else if("Castle".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new Stone((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("stone_Castle") ) );
                                    }
                                    }
//                                    BrickGroup.add(new Stone((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) ,this.bsLoader.getStoredImage("") ));
//                                    // System.out.println("stone " + i +" "+ constr.getX() + " "+constr.getY());
                                    break;
                                case 7: // chocolate
                                    
                                    if("CloudsNight".equals(this.CurrentLevel.BackGroundImage)){ // balck and white
                                        BrickGroup.add(new chocolate((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("chocolate_Castle") ) );

                                    }else {
                                        if("Sea".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new chocolate((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("chocolate_Sea") ) );
                                    }
                                    else if("Ground".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new chocolate((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("chocolate") ) );
                                    }
                                    else if("UnderGround".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new chocolate((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("chocolate_UnderGround") ) );
                                    }
                                    else if("Castle".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new chocolate((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("chocolate_Castle") ) );
                                    }
                                     
                                    }
//                                    BrickGroup.add(new Stone((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) ,this.bsLoader.getStoredImage("chocolate") ));
//                                    // System.out.println("stone " + i +" "+ constr.getX() + " "+constr.getY());
                                    break;
//                                    // System.out.println("grass " + i +" "+ constr.getX() + " "+constr.getY());

                                 case 12: // pump
                                     if(y == 0){ // pump top with plant
                                         
                                         if("Ground".equals(CurrentLevel.attribute)){
                                         this.PlantGroup.add(new plant(this.bsLoader.getStoredImages("plant.png") ,(constr.getX()*32)+ (x*32)+16,  (constr.getY()*32)+ (y*32)+48 , this ));
                                         }else{
                                         this.PlantGroup.add(new plant(this.bsLoader.getStoredImages("plantdark.png") ,(constr.getX()*32)+ (x*32)+16,  (constr.getY()*32)+ (y*32)+48 , this ));
                                         }
                                             
                                    
                                         if("Sea".equals(CurrentLevel.attribute)){
                                             BrickGroup.add(new pump(this.bsLoader.getStoredImage("pump top Sea.png") ,(constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this ));
                                             
                                         }
                                         else if("Castle".equals(CurrentLevel.attribute) || "Fence".equals(CurrentLevel.BackGroundImage)){ // castle anf white fence same image
                                             BrickGroup.add(new pump(this.bsLoader.getStoredImage("pump top Castle") ,(constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this ));

                                         }else{
                                             BrickGroup.add(new pump(this.bsLoader.getStoredImage("pump top.png") ,(constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this ));
                                         }
                                         
                                     }else{
                                    if("Sea".equals(CurrentLevel.attribute)){
                                             BrickGroup.add(new pump(this.bsLoader.getStoredImage("pump Sea.png") ,(constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this ));
                                         }
                                         else if("Castle".equals(CurrentLevel.attribute) || "Fence".equals(CurrentLevel.BackGroundImage)){ // castle anf white fence same image
                                             BrickGroup.add(new pump(this.bsLoader.getStoredImage("pump Castle") ,(constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this ));

                                         }else{
                                             BrickGroup.add(new pump(this.bsLoader.getStoredImage("pump.png") ,(constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this ));
                                         }
                                     }
                                    break;
                                     
                                 case 13: // Mushroom
                                     BufferedImage[] temp = new BufferedImage[2];
                                        if("Sea".equals(CurrentLevel.attribute)){
                                            temp[0] = this.bsLoader.getStoredImages("enemy")[0];
                                            temp[1] = this.bsLoader.getStoredImages("enemy")[1];
                                            EnemyGroup.add(new EnemyMashroom( (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32) , temp , this) );                                    }
                                    else if("Ground".equals(CurrentLevel.attribute)){
                                            temp[0] = this.bsLoader.getStoredImages("enemy")[2];
                                            temp[1] = this.bsLoader.getStoredImages("enemy")[3];
                                            EnemyGroup.add(new EnemyMashroom( (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32) , temp , this) );                                    }
                                    else if("UnderGround".equals(CurrentLevel.attribute)){
                                            temp[0] = this.bsLoader.getStoredImages("enemy")[4];
                                            temp[1] = this.bsLoader.getStoredImages("enemy")[5];
                                            EnemyGroup.add(new EnemyMashroom( (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32) , temp , this) );                                    }
                                    else if("Castle".equals(CurrentLevel.attribute)){
                                            temp[0] = this.bsLoader.getStoredImages("enemy")[6];
                                            temp[1] = this.bsLoader.getStoredImages("enemy")[7];
                                            EnemyGroup.add(new EnemyMashroom( (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32) , temp , this) );                                    }
                                     break;
                                     
                                 case 14: // turtle
                                     if("UnderGround".equals(this.CurrentLevel.attribute)){
                                     EnemyGroup.add(new EnemyTurtle((constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32) , this , true , "UnderGround" ) );
                                     }else {
                                     EnemyGroup.add(new EnemyTurtle((constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32) , this , true ) );
                                             }
                                     break;
                                 case 15: // SmallCastle
                                     if("CloudsNight".equals(this.CurrentLevel.BackGroundImage)){ // balck and white
                                     BackGroundSpriteGroup.add(new Sprite(  this.bsLoader.getStoredImage("BWSmallCastle")  , constr.getX()*32  , constr.getY()*32  ) );
                                     }else{
                                     BackGroundSpriteGroup.add(new Sprite(  this.bsLoader.getStoredImage("SmallCastle")  , constr.getX()*32  , constr.getY()*32  ) );
                                     }
                                     break;
                                     
                                 case 16: // BigCastle
                                     if("CloudsNight".equals(this.CurrentLevel.BackGroundImage)){ // balck and white
                                     BackGroundSpriteGroup.add(new Sprite( this.bsLoader.getStoredImage("BWBigCastle") , constr.getX()*32 , constr.getY()*32   ) );
 
                                     }else{
                                     BackGroundSpriteGroup.add(new Sprite( this.bsLoader.getStoredImage("BigCastle") , constr.getX()*32 , constr.getY()*32   ) );
                                     }
                                     break;
                                     
                                 case 17 : // tree
                                     
                                     if("CloudsNight".equals(this.CurrentLevel.BackGroundImage)){ // balck and white
                                         
                                         if(y == 0){ // top part
                                         if(x == 0){ // first part
                                             BrickGroup.add(new Tree(this.bsLoader.getStoredImages("BWtree")[0] , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32)  ));
                                         }else if (x == constr.getLength_X()-1){ // last part
                                             BrickGroup.add(new Tree(this.bsLoader.getStoredImages("BWtree")[2] , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32)  ));
                                         }else{ // middle part
                                             BrickGroup.add(new Tree(this.bsLoader.getStoredImages("BWtree")[1] , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32)  ));
                                         }
                                         
                                     }else{ // down part
                                         if(x == 0){ // first part
//                                             BrickGroup.add(new Sprite()); // empty no trunks
                                         }else if (x == constr.getLength_X()-1){ // last part
//                                             BrickGroup.add(new Sprite()); // empty no trunks
                                         }else{ // middle part
                                             BackGroundSpriteGroup.add(new Sprite(this.bsLoader.getStoredImages("BWtree")[3] , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32)  ));   // this is (non-collided) trunk
                                         }
                                     }
                                         
                                     }else
                                     if("GreenAndTrees".equals(CurrentLevel.type)){ // green trees
                                         
                                     if(y == 0){ // top part
                                         if(x == 0){ // first part
                                             BrickGroup.add(new Tree(this.bsLoader.getStoredImages("tree")[0] , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32)  ));
                                         }else if (x == constr.getLength_X()-1){ // last part
                                             BrickGroup.add(new Tree(this.bsLoader.getStoredImages("tree")[2] , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32)  ));
                                         }else{ // middle part
                                             BrickGroup.add(new Tree(this.bsLoader.getStoredImages("tree")[1] , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32)  ));
                                         }
                                         
                                     }else{ // down part
                                         if(x == 0){ // first part
//                                             BrickGroup.add(new Sprite()); // empty no trunks
                                         }else if (x == constr.getLength_X()-1){ // last part
//                                             BrickGroup.add(new Sprite()); // empty no trunks
                                         }else{ // middle part
                                             BackGroundSpriteGroup.add(new Sprite(this.bsLoader.getStoredImages("tree")[3] , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32)  ));   // this is (non-collided) trunk
                                         }
                                     }
                                     
                                     }
                                     else if("OrangeAndMushroom".equals(CurrentLevel.type)){ // red trees
                                         
                                         if(y == 0){ // top part
                                         if(x == 0){ // first part
                                             BrickGroup.add(new Tree(this.bsLoader.getStoredImages("tree")[5] , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32)  ));
                                         }else if (x == constr.getLength_X()-1){ // last part
                                             BrickGroup.add(new Tree(this.bsLoader.getStoredImages("tree")[7] , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32)  ));
                                         }else{ // middle part
                                             BrickGroup.add(new Tree(this.bsLoader.getStoredImages("tree")[6] , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32)  ));
                                         }
                                         
                                     }else{ // down part
                                             
                                             int Middel = constr.getLength_X() / 2; // eg 3 - 1 ; (= 4) /2 ; (=1)Middle
                                             
                                         if(x == Middel){
                                             if(y == 1){ // strip
                                                 BackGroundSpriteGroup.add(new Sprite(this.bsLoader.getStoredImages("tree")[8] , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32)  ));   // this is (non-collided) trunk 
                                             }else{
                                                 BackGroundSpriteGroup.add(new Sprite(this.bsLoader.getStoredImages("tree")[9] , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32)  ));   // this is (non-collided) trunk 

                                         }}
                                     }
                                     
                                     }
                                     break ;
                                     
                                 case 18: // Wall
                                     if(y == 0){ // The up layer
                                 BackGroundSpriteGroup.add(new Sprite(this.bsLoader.getStoredImages("Wall")[0] , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32)  ));   // this is (non-collided) trunk
                                     }
                                     else{
                                 BackGroundSpriteGroup.add(new Sprite(this.bsLoader.getStoredImages("Wall")[1] , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32)  ));   // this is (non-collided) trunk
                                 
                                     }
                                     break ;
                                     
                                 case 19 : // RocketLauncher
                                     // y = 0 is Shooter , y = 1 is Maskmage , y =2 , 3 , 4... is balck
                                 
                                     if(y == 0){ // Shooter will update and shoot Rocket
                                         BrickGroup.add(new RocketLauncher(this.bsLoader.getStoredImages("RocketLauncher")[0] , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32) , this ));
                                     }else if(y == 1){ // Maskmage
                                         BrickGroup.add(new RocketLauncherBody(this.bsLoader.getStoredImages("RocketLauncher")[1] , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32), this   ));
                                     }else { // balck
                                         BrickGroup.add(new RocketLauncherBody(this.bsLoader.getStoredImages("RocketLauncher")[2] , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32), this   ));
                                     } 

                                     break ;
                                     
                                 case 20 : // FlyingTurtle
                                     if("Ground".equals(this.CurrentLevel.attribute)){
                                         
                                     this.EnemyGroup.add(new FlyingTurtle("normal" , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32) , this ));
                                     }else{
                                     this.EnemyGroup.add(new FlyingTurtle("dark" , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32) , this ));
                                     }
                                break ;
                                     
                                 case 21 : // Monkey
                                     this.EnemyGroup.add(new Monkey(this.bsLoader.getStoredImages("Monkey") , (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32) , this ));
                                     break ;
                            
                                 case 22 :
                                         if("Sea".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new Bank((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick_Sea")  , this) );
                                    }
                                    else if("Ground".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new Bank((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick") , this ) );
                                    }
                                    else if("UnderGround".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new Bank((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick_UnderGround"), this  ) );
                                    }
                                    else if("Castle".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new Bank((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick_Castle"), this  ) );
                                    }
                                     break ;
                                     
                                 case 23 : // BrickWithMushroom
                                     
                                          if("Sea".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new BankWithItem((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick_Sea") , "Mashroom" , this) );
                                    }
                                    else if("Ground".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new BankWithItem((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick") , "Mashroom", this ) );
                                    }
                                    else if("UnderGround".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new BankWithItem((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick_UnderGround"), "Mashroom" , this  ) );
                                    }
                                    else if("Castle".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new BankWithItem((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick_Castle"), "Mashroom" , this  ) );
                                    }
                                     break ;
                                     
                                 case 24 : // BrickWith1UP
                                     
                                           if("Sea".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new BankWithItem((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick_Sea") , "1UP" , this) );
                                    }
                                    else if("Ground".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new BankWithItem((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick") , "1UP", this ) );
                                    }
                                    else if("UnderGround".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new BankWithItem((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick_UnderGround"), "1UP" , this  ) );
                                    }
                                    else if("Castle".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new BankWithItem((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick_Castle"), "1UP" , this  ) );
                                    }
                                     
                                     break ;
                                     
                                 case 26: // BrickWithCoin
                                            if("Sea".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new BankWithItem((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick_Sea") , "CoinInside" , this) );
                                    }
                                    else if("Ground".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new BankWithItem((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick") , "CoinInside", this ) );
                                    }
                                    else if("UnderGround".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new BankWithItem((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick_UnderGround"), "CoinInside" , this  ) );
                                    }
                                    else if("Castle".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new BankWithItem((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick_Castle"), "CoinInside" , this  ) );
                                    }
                                     
                                     break ;
                                     
                                 case 27 : // Helmet
                                     if("UnderGround".equals(this.CurrentLevel.attribute)){                                         
                                     EnemyGroup.add(new Helmet( (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32) , "dark" , this ) );
                                     }else if("Castle".equals(this.CurrentLevel.attribute)){                                         
                                     EnemyGroup.add(new Helmet( (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32) , "white" , this ) );
                                     }else{
                                     EnemyGroup.add(new Helmet( (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32) , "normal" , this) );
                                     }

                                     
                                     break ;
                                     
                                 case 28 : //FireBar
                                         
                                     for (int j = 0; j < 6; j++) {
                                         if("CW".equals(constr.ExtraInfo)){
                                     EnemyGroup.add(new EnemyFireBall((constr.getX()*32)+ (x*32)+8 , (constr.getY()*32 ) + (y*32)+8 , j*16 , this , true));
                                         }else{
                                     EnemyGroup.add(new EnemyFireBall((constr.getX()*32)+ (x*32)+8 , (constr.getY()*32 ) + (y*32)+8 , j*16 , this , false));
                                         }
                                         
                                     }
                                         break ;
                                 case 29 : //       BigFireBar
                                     for (int j = 0; j < 12; j++) {
                                         if("CW".equals(constr.ExtraInfo)){
                                     EnemyGroup.add(new EnemyFireBall((constr.getX()*32)+ (x*32)+8 , (constr.getY()*32 ) + (y*32)+8 , j*16 , this , true));
                                         }else{
                                     EnemyGroup.add(new EnemyFireBall((constr.getX()*32)+ (x*32)+8 , (constr.getY()*32 ) + (y*32)+8 , j*16 , this , false));
                                         }
                                         
                                     }
                                         break ;
                                     
                                 case 30 : //SonOfABuitch
                                     
                                     EnemyGroup.add(new SonOfABuitch((constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32)  , this.bsLoader.getStoredImages("SonOfABuitch") , this ));
                                     
                                     break ;
                                     
                                  case 31 : //    Bouncer
                                      if("CloudsNight".equals(this.CurrentLevel.BackGroundImage)){ // balck and white
                                     BrickGroup.add(new Spring((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32)-32 , this.bsLoader.getStoredImages("Spring"), this  ) );
                                     BrickGroup.add(new Bouncer((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("BWBouncer"), this  ) );
                                      
                                      }else {
                                     BrickGroup.add(new Spring((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32)-32 , this.bsLoader.getStoredImages("Spring"), this  ) );
                                     BrickGroup.add(new Bouncer((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("Bouncer"), this  ) );
                                      }
                                     break ;
                                      
                                  case 32 : // Flag
                                      
                                      if("CloudsNight".equals(this.CurrentLevel.BackGroundImage)){ // balck and white
                                      FlagGroup.add(new Flag( (constr.getX()*32)+ (x*32)+14,  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("FlagFence"), this  ));
                                      flagtop = new FlagTop(this.bsLoader.getStoredImage("FlagTop"),(constr.getX()*32)+ (x*32)-16 , (constr.getY()*32)+ (y*32) , this);
                                      this.AnimationGroup.add(flagtop);
                                      this.AnimationGroup.add(new Sprite(this.bsLoader.getStoredImage("FlagSphereFence"), (constr.getX()*32)+ (x*32),(constr.getY()*32)+ (y*32)-32 ));
                                      
                                      }else {
                                      if("Fence".equals(CurrentLevel.BackGroundImage)){
                                      FlagGroup.add(new Flag( (constr.getX()*32)+ (x*32)+14,  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("FlagFence"), this  ));
                                      flagtop = new FlagTop(this.bsLoader.getStoredImage("FlagTop"),(constr.getX()*32)+ (x*32)-16 , (constr.getY()*32)+ (y*32) , this);
                                      this.AnimationGroup.add(flagtop);
                                      this.AnimationGroup.add(new Sprite(this.bsLoader.getStoredImage("FlagSphereFence"), (constr.getX()*32)+ (x*32),(constr.getY()*32)+ (y*32)-32 ));
                                      
                                      }else{
                                      FlagGroup.add(new Flag( (constr.getX()*32)+ (x*32)+14,  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("Flag"), this  ));
                                      flagtop = new FlagTop(this.bsLoader.getStoredImage("FlagTop"),(constr.getX()*32)+ (x*32)-16 , (constr.getY()*32)+ (y*32) , this);
                                      this.AnimationGroup.add(flagtop);
                                      this.AnimationGroup.add(new Sprite(this.bsLoader.getStoredImage("FlagSphere"), (constr.getX()*32)+ (x*32),(constr.getY()*32)+ (y*32)-32 ));
                                      }
                                      }
                                      break ;
                                      
                                  case 33 : // Axe
                                      
                                      BrickGroup.add(new Axe((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImages("Axe"), this  ) );

                                   break ;
                                      
                                  case 34 : // WhyYouDOThis
                                      
//                                      BrickGroup.add(new WhyYouDOThis((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImages("WhyYouDOThis"), this  ) );

                                  break ;
                                      
                                  case 35 : // Boss
                                       boss = new Boss( this.bsLoader.getStoredImages("Boss"), (constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) ,  this  , constr.PetrolLength);
                                      this.EnemyGroup.add(boss);
                                      
                                  break ;
                                      
                                  case 36 : // BridgeBloks
                                      
                                      this.BrickGroup.add(new Brick((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("BridgeBloks") ,   this ));

                                      break ;
                                      
                                  case 37 : // InvisibleBrckWith1Up
                                      
                                      this.BrickGroup.add(new InvisibleBrck((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) ,"1UP" ,   this ));
                                      
                                  break ;
                                      
                                  case 38 : // BrickWithStar
                                      // BrickWithCoin
                                            if("Sea".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new BrickWithStar((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick_Sea")  , this) );
                                    }
                                    else if("Ground".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new BrickWithStar((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick") , this ) );
                                    }
                                    else if("UnderGround".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new BrickWithStar((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick_UnderGround") , this  ) );
                                    }
                                    else if("Castle".equals(CurrentLevel.attribute)){
                                        BrickGroup.add(new BrickWithStar((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this.bsLoader.getStoredImage("brick_Castle") , this  ) );
                                    }
                                      
                                  break ;
                                      
                                  case -2 : // Coin
                                      
                                      EnemyGroup.add(new Coin( (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32) , this.bsLoader.getStoredImages("Coin") , this) );

                                      break ;
                                      
                                  case 39 : // Iron Default red
                                      
                                      BrickGroup.add(new Iron( (constr.getX()*32)+ (x*32) , (constr.getY()*32 )+ (y*32)  , this.bsLoader.getStoredImages("Iron"), "red" , "noBounce" , this));

                                      break ;
                                      
                                  case 40 : // InvisibleBrckWithCoin
                                      
                                      this.BrickGroup.add(new InvisibleBrck((constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) ,"Coin" ,   this ));

                                      break ;
                                      
                                  case 41 : // Fire

                                      HammerGroup.add(new Fire((int)(constr.getX()*32)+ (x*32),  (int)(constr.getY()*32)+ (y*32) , bsLoader.getStoredImages("Fire")));
                                      
                                      break ;
                                      
                                  case 42 : // Lava

                                      this.BackGroundSpriteGroup.add(new Sprite(  bsLoader.getStoredImage("Lava") , (constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32) ));
                                      
                                      break ;
                                      
                                  case 43 : // CheckPoint
                                      
//                                      this.CheckPointGroup.add(new CheckPoints(  ) );
                                      
                                      break ;
                                      
                                  case 44 : // HoriImage
                                      
                                      this.BrickGroup.add(new pump(this.bsLoader.getStoredImages("HoriImage")[0] ,(constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32) , this));
                                      this.BrickGroup.add(new pump(this.bsLoader.getStoredImages("HoriImage")[1] ,(constr.getX()*32)+ (x*32)+64, (constr.getY()*32)+ (y*32) , this));

                                      break ;
                                      
                                 case 45 : // PumpImage
                                      
                                     this.BrickGroup.add(new pump(this.bsLoader.getStoredImage("pump") ,(constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32) , this));
                                      
                                      break ;
                                      
                                case 46 : // Lift
                                      
//                                     this.LiftGroup.add(new Lift_UpDown(this.bsLoader.getStoredImage("Lift") ,(constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32) , this));
                                      
                                      break ; 
                                     
                                case 47 : // EnemyTurtlePatrol
                                      
                                     this.EnemyGroup.add(new EnemyTurtlePatrol((constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32) , this , constr.PetrolLength));
                                      
                                      break ; 
                                     
                                case 48 : // FlyingTurtlePatrol
                                      
                                     this.EnemyGroup.add(new FlyingTurtlePatrol((constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32) , this , constr.PetrolLength));
                                      
                                      break ; 
                                     
                               case 49 : // Lift_UpDown
                                      
                                     this.LiftGroup.add(new Lift_UpDown((constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32) , this , constr.PetrolLength));
                                      
                                      break ; 
                                    
                               case 50 : // Lift_LeftRight
                                      
                                     this.LiftGroup.add(new Lift_LeftRight((constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32) , this  , constr.PetrolLength));
                                      
                                      break ; 
                                    
                               case 51 : // Lift_LeftRightInvert
                                      
                                     this.LiftGroup.add(new Lift_LeftRightInvert((constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32) , this  , constr.PetrolLength));
                                      
                                      break ; 
                                   
                                case 52 : // LiftUP
                                      
                                     this.LiftGroup.add(new LiftUP((constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32) , this  , constr.PetrolLength));
                                      
                                      break ; 
                                  
                                case 53 : // LiftDown
                                      
                                     this.LiftGroup.add(new LiftDown((constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32) , this  , constr.PetrolLength));
                                      
                                      break ; 
                                  
                                case 54 : // FishGrey
                                      
                                     this.EnemyGroup.add(new FishyWater((constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32), this  ,1));
                                      
                                      break ; 
                                case 55 : // FishRed
                                      
                                     this.EnemyGroup.add(new FishyWater((constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32), this  ,3));
                                      
                                      break ; 
                                case 56 : // FishGreyUpDown
                                      
                                     this.EnemyGroup.add(new FishyWater((constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32), this  ,2));
                                      
                                      break ; 
                                case 57 : // FishRedUpDown
                                      
                                     this.EnemyGroup.add(new FishyWater((constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32), this  ,4));
                                      
                                      break ; 
                                case 58 : // OctoPussy
                                      
                                     this.EnemyGroup.add(new OctoPussy((constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32), this  ));
                                      
                                      break ; 
                                
                                case 59 : // WoodenBridge
                                      
                                    if("Fence".equals(CurrentLevel.BackGroundImage)){
                                     this.BackGroundSpriteGroup.add(new Sprite(this.bsLoader.getStoredImage("RopeFence"),(constr.getX()*32)+ (x*32) , (constr.getY()*32)+ (y*32)- 32 ));
                                     this.BrickGroup.add(new WoodenBridge((constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32), this  ));
                                    }else{
                                     this.BackGroundSpriteGroup.add(new Sprite(this.bsLoader.getStoredImage("Rope"),(constr.getX()*32)+ (x*32) , (constr.getY()*32)+ (y*32)- 32 ));
                                     this.BrickGroup.add(new WoodenBridge((constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32), this  ));
                                    }
                                      break ; 
                                
                                 case 60 : // LavaBall
                                      
                                     this.EnemyGroup.add(new LavaBall((constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32), this  ));
                                      
                                      break ; 
                                 
                                case 61 : // WhiteLine
                                      
                                     this.BackGroundSpriteGroup.add(new Sprite(ImageUtil.resize(this.bsLoader.getStoredImage("WhiteLine") ,32 , 13*32),(constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32) ));
                                      
                                      break ;  
                                     
                                 case 62 : // Water
                                      
                                     this.BackGroundSpriteGroup.add(new Sprite(this.bsLoader.getStoredImage("Water"),(constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32) ));
                                      
                                      break ;  
                                     
                                case 63 : // BalenceLift
                                      
                                     this.LiftGroup.add(new BalenceLiftParent((constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32) , this , constr.getBridgeLength()));
                                      
                                      break ;  
                                     
                                case 64 : // LiftFall
                                      
                                     this.LiftGroup.add(new LiftFall((constr.getX()*32)+ (x*32), (constr.getY()*32)+ (y*32) , this , constr.PetrolLength ));
                                      
                                      break ; 
                                
                                case 65 : // BossHammer
                                     boss = new Boss( this.bsLoader.getStoredImages("Boss"), (constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) ,  this , constr.PetrolLength );
                                     boss.SetHammer(true);
                                      this.EnemyGroup.add(boss);
                                    break;
                                case 66 : // PumpWarp
                                     if(y == 0){ // pump top with plant
//                                         this.EnemyGroup.add(new plant(this.bsLoader.getStoredImages("plant.png") ,(constr.getX()*32)+ (x*32)+16,  (constr.getY()*32)+ (y*32)+48 , this ));
                                    
                                         if("Sea".equals(CurrentLevel.attribute)){
                                             BrickGroup.add(new pump(this.bsLoader.getStoredImage("pump top Sea.png") ,(constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this ));
                                         }
                                         else if("Castle".equals(CurrentLevel.attribute) || "Fence".equals(CurrentLevel.BackGroundImage)){ // castle anf white fence same image
                                             BrickGroup.add(new pump(this.bsLoader.getStoredImage("pump top Castle") ,(constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this ));

                                         }else{
                                             BrickGroup.add(new pump(this.bsLoader.getStoredImage("pump top.png") ,(constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this ));
                                         }
                                         
                                     }else{
                                    if("Sea".equals(CurrentLevel.attribute)){
                                             BrickGroup.add(new pump(this.bsLoader.getStoredImage("pump Sea.png") ,(constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this ));
                                         }
                                         else if("Castle".equals(CurrentLevel.attribute) || "Fence".equals(CurrentLevel.BackGroundImage)){ // castle anf white fence same image
                                             BrickGroup.add(new pump(this.bsLoader.getStoredImage("pump Castle") ,(constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this ));

                                         }else{
                                             BrickGroup.add(new pump(this.bsLoader.getStoredImage("pump.png") ,(constr.getX()*32)+ (x*32),  (constr.getY()*32)+ (y*32) , this ));
                                         }
                                     }
                                    break;
                                    
                                    
                            }
                            }
                }
            }
            
            }
    }

    private void RemoveOutOfScreenFireBalls() {
        
        for (int i = 0; i < this.FireBall.getSize(); i++) {
            
            if(FireBall.getSprites()[i].getScreenX() < - 50 |
                    FireBall.getSprites()[i].getScreenX() > 700){
                FireBall.getSprites()[i].setActive(false);
            }
        }
    }

    private BasicLevel SelectLevel(int LevelNumber1) {
        
        BasicLevel newLevel = null ;
        
        switch(LevelNumber1){
            case 10: newLevel = new Level_Start(); WORLD = "1-1"; break ;
            case 11: newLevel = new Level_11(); WORLD = "1-1"; break ;
            case 12: newLevel = new Level_12(); WORLD = "1-2"; break ;
            case 13: newLevel = new Level_13(); WORLD = "1-3"; break ;
            case 14: newLevel = new Level_14(); WORLD = "1-4"; break ;
            
            case 21: newLevel = new Level_21();  WORLD = "2-1";break ;
            case 22: newLevel = new Level_22();  WORLD = "2-2";break ;
            case 23: newLevel = new Level_23();  WORLD = "2-3";break ;
            case 24: newLevel = new Level_24();  WORLD = "2-4";break ;
                
            case 31: newLevel = new Level_31(); WORLD = "3-1"; break ;
            case 32: newLevel = new Level_32(); WORLD = "3-2"; break ;
            case 33: newLevel = new Level_33(); WORLD = "3-3"; break ;
            case 34: newLevel = new Level_34(); WORLD = "3-4"; break ;
                
            case 41: newLevel = new Level_41(); WORLD = "4-1"; break ;
            case 42: newLevel = new Level_42(); WORLD = "4-2"; break ;
            case 43: newLevel = new Level_43(); WORLD = "4-3"; break ;
            case 44: newLevel = new Level_44(); WORLD = "4-4"; break ;
                
            case 51: newLevel = new Level_51(); WORLD = "5-1"; break ;
            case 52: newLevel = new Level_52(); WORLD = "5-2"; break ;
            case 53: newLevel = new Level_53(); WORLD = "5-3"; break ;
            case 54: newLevel = new Level_54(); WORLD = "5-4"; break ;
            
            case 61: newLevel = new Level_61(); WORLD = "6-1"; break ;
            case 62: newLevel = new Level_62(); WORLD = "6-2"; break ;
            case 63: newLevel = new Level_63(); WORLD = "6-3"; break ;
            case 64: newLevel = new Level_64(); WORLD = "6-4"; break ;
                
            case 71: newLevel = new Level_71(); WORLD = "7-1"; break ;
            case 72: newLevel = new Level_72(); WORLD = "7-2"; break ;
            case 73: newLevel = new Level_73(); WORLD = "7-3"; break ;
            case 74: newLevel = new Level_74(); WORLD = "7-4"; break ;
                
            case 81: newLevel = new Level_81(); WORLD = "8-1"; break ;
            case 82: newLevel = new Level_82(); WORLD = "8-2"; break ;
            case 83: newLevel = new Level_83(); WORLD = "8-3"; break ;
            case 84:
            case 841: newLevel = new Level_841(); WORLD = "8-4 1"; break ;
            case 842: newLevel = new Level_842(); WORLD = "8-4 2"; break ;
            case 843: newLevel = new Level_843(); WORLD = "8-4 3"; break ;
            case 844: newLevel = new Level_844(); WORLD = "8-4 4"; break ;
            case 845: newLevel = new Level_845(); WORLD = "8-4 5"; break ;
            
                
            case 555: newLevel = new TestArea(); WORLD = "555"; break ;
                
            case 'a': newLevel = new BonusArea11A();WORLD = "1-1" ; break ;
            case 'b': newLevel = new BonusArea12B();WORLD = "1-2" ;  break ;
                
            case 'c': newLevel = new BonusArea21A(); WORLD = "2-1" ; break ;
                
            case 'd': newLevel = new BonusArea31C(); WORLD = "3-1" ; break ;
                
            case 'e': newLevel = new BonusArea41D(); WORLD = "4-1" ; break ;
            case 'f': newLevel = new BonusArea42E(); WORLD = "4-2" ; break ;
                
            case 'g': newLevel = new BonusArea51E(); WORLD = "5-1" ; break ;
            case 'h': newLevel = new BonusArea52F(); WORLD = "5-2" ; break ;
                
            case 'i': newLevel = new BonusArea62E(); WORLD = "6-2" ; break ;
            case 'j': newLevel = new BonusArea62G(); WORLD = "6-2" ; break ;
            case 'k': newLevel = new BonusArea62D(); WORLD = "6-2" ; break ;
                
            case 'l': newLevel = new BonusArea71A(); WORLD = "7-1" ; break ; 
                
            case 'm': newLevel = new BonusArea81B(); WORLD = "8-1" ; break ;   
            case 'n': newLevel = new BonusArea82E(); WORLD = "8-2" ; break ;     
                
        }
        
        return newLevel;
    }

    private void FindDublicates(BasicLevel CurrentLevel) throws InterruptedException {
        
        if(FindDublicatesON){
            
            for (int a = 0; a < playfield.getGroups().length; a++) {
               Thread.sleep(100);
                // find items in BrickGroup
            for (int i = 0; i < playfield.getGroups()[a].getSprites().length; i++) {
            Thread.sleep(10);
            if(playfield.getGroups()[a].getSprites()[i]!= null );
            
            for (int j = 0; j < playfield.getGroups()[a].getSprites().length; j++) {
                
                if(playfield.getGroups()[a].getSprites()[j]!= null && playfield.getGroups()[a].getSprites()[i]!= null && 
                   j != i ){
                
                if(playfield.getGroups()[a].getSprites()[i].getX() == playfield.getGroups()[a].getSprites()[j].getX() && 
                   playfield.getGroups()[a].getSprites()[i].getY() == playfield.getGroups()[a].getSprites()[j].getY()     ){
                    
                     System.out.println(playfield.getGroups()[a].getName()+": "+playfield.getGroups()[a].getSprites()[i].getX()/32 +"  "+
                                        playfield.getGroups()[a].getSprites()[i].getY() /32 );
                
                    
            }
                }
            
            }
            
        }
            }
            
            
        // find items in level
        for (int i = 0; i < CurrentLevel.Get_Items_Amount(); i++) {
            Thread.sleep(10);
            if(CurrentLevel.get_Item_Number(i)!= null );
            
            for (int j = 0; j < CurrentLevel.Get_Items_Amount(); j++) {
                
                if(CurrentLevel.get_Item_Number(j)!= null && CurrentLevel.get_Item_Number(i)!= null && 
                   j != i ){
                
                if(CurrentLevel.get_Item_Number(i).getX() == CurrentLevel.get_Item_Number(j).getX() && 
                   CurrentLevel.get_Item_Number(i).getY() == CurrentLevel.get_Item_Number(j).getY()     ){
                    
                     System.out.println("Duplicate "+CurrentLevel.get_Item_Number(i).getX() +"  "+
                                        CurrentLevel.get_Item_Number(i).getY());
                
                    
            }
                }
            
            }
            
        }
        }
    }

    public void RemoveBridge(Sprite axe) {
        
        boss.setActive(false);
        
        DemoMario = new Sprite(this.player.getImage() , this.player.getX() , this.player.getY() );
        
        this.AnimationGroup.add( DemoMario );
        
//        this.pauseEnemys();
        int X  = (int) (axe.getX()/32) - 13 ;
        
        for (int i = 0; i < 13; i++) {
            
            int delay = 26 - i*2;
            
            this.AnimationGroup.add(new Black(this.getImage("Black.png") ,(i+X)*32 , 10*32 , delay) );
                    
        }
        
        this.AnimationGroup.add(new BossFallingAnim(this.bsLoader.getStoredImages("Boss") ,boss.getX() , boss.getY() , this )  );
        
    }

    public void removeDemoMario() {
        DemoMario.setActive(false);
    }

    public String GetAttribute() {
        return GameAttribute;
    }

    private void LoadCheckPoints(BasicLevel CurrentLevel) {
        
                    for(int i = 0 ; i < 10  ; i++){ // how many items
                
                if(CurrentLevel.get_CheckPoints_Number(i) != null){ // dont throw null exception error
                
                    CheckPoints cp = CurrentLevel.get_CheckPoints_Number(i);
                    
//                    if(cp.getID() == 23){
//                        cp.setImage(this.getImage("chocolate_Sea.png"));
//                    }
//                    else 
                    if(cp.getID() == 16){
                        cp.setImage(this.getImage("WhyYouDOThis.png"));
                    }else if(cp.getID() == 26){
                        cp.setImage(this.bsLoader.getStoredImage("Princess.png"));
                    }else {
                        cp.setImage(ImageUtil.createImage(32, 32 , 3));
                    }
                this.FlagGroup.add(cp);
                
                // System.out.println("CheckPoints Loaded " +"x"+cp.getX() +" , Width "+cp.getWidth() );
                }
                
                    }
                    
    }

    private void LoadTeleport(BasicLevel CurrentLevel) {
        
                    for(int i = 0 ; i < 20  ; i++){ // how many items
                
                if(CurrentLevel.get_Teleport_Number(i) != null){ // dont throw null exception error
                
                    Teleport teleport = CurrentLevel.get_Teleport_Number(i);
                    
                    
                    SpriteTeleport spriteteleport = new SpriteTeleport(teleport , this);
//                    
                    
                this.TeleportGroup.add(spriteteleport);
                
                // System.out.println("CheckPoints Loaded " +"x"+cp.getX() +" , Width "+cp.getWidth() );
                }
                
                    }
                    
    }
    
    public void LevelComplete(Sprite checkpoint) {
        
        LevelComplete = true ;
        cp  = (CheckPoints) checkpoint ;
        
        
    }

    public void LoadNextCheckPoint() {
        
        parent.nextGameID = cp ;
        this.finish();
        
    }

    public void MarioGoingDownAnimation() {
        
        MarioGoingDownAnimation ani = new MarioGoingDownAnimation() ;
        ani.setLocation(this.player.getX(), this.player.getY());
        ani.setImage(this.player.getImage());
//        ani.setAnimate(true);
//        ani.setLoopAnim(true);
//        ani.setAnimationFrame(4, 6);
//        ani.setAnimationTimer(new Timer(200));
        
        this.UnderGroup.add(ani);
        
        
    }

    public void MarioGoingInsidePumpToRight() {
        
        MarioGoingInPump ani = new MarioGoingInPump() ;
        ani.setLocation(this.player.getX(), this.player.getY());
        ani.setImages(this.player.getImages());
        ani.setAnimate(true);
        ani.setLoopAnim(true);
        ani.setAnimationFrame(4, 6);
        ani.setAnimationTimer(new Timer(200));
        
        this.UnderGroup.add(ani);
        
        // System.out.println(this.player.getX()+" MarioGoingInsidePumpToRight "+ this.player.getY());
        
        // now overlap mario with pump image
        
        
    }

    public void Restart() {
            this.PlayEnemys();
            System.out.println(PlayetX +" "+PlayerY);
            player.setLocation(PlayetX , PlayerY) ;
            player.speed = 0 ;
            player.GoDown();
            PlayLivingThings = true ;
            DelayToNextCheckPoint = 120;
            LevelComplete  = false;
            player.ResetStatus();
            
            StopScroll = true ;
            
            this.playMusic("music/"+this.CurrentLevel.attribute+".mp3");
    }

    public void StartFlagScrollingDown() {
        flagtop.ScrollDown();
    }

    public void LevelComplete(Sprite checkpoint, int waitFor) {
        LevelComplete = true ;
        cp  = (CheckPoints) checkpoint ;
        this.DelayToNextCheckPoint = waitFor ;
        
    }

    public void PrintString(String string, int x, int y) {
        
    }

    private void DrawScore(Graphics2D g) {
        
//        font.drawString(g ,"SCORE" , 128, 32 );
        font.drawString(g ,"WORLD" , 368, 32 );
        
//        font.drawString(g ,"TIME" , 480, 32 );
        
//        font.drawString(g ,"" +parent.getScore(), 128, 48 ); // SCORE
        
        if(parent.getCoin()< 10 ){
            font.drawString(g ,"$x0"+parent.getCoin() , 256 , 48 ); // coin
        }else{
            font.drawString(g ,"$x"+parent.getCoin() , 256 , 48 ); // coin
        }
        
        if(this.LevelNumber == 10){
            font.drawString(g ,""+this.StartWorld+"-"+this.StartLevel, 384, 48 ); // world
            
            font.drawString(g ,"PRESS ENTER TO", 6*32, 11*32 );
            font.drawString(g ,"START GAME", 7*32+16, 12*32 );
        }else{
        font.drawString(g ,""+WORLD, 384, 48 ); // world
        }
        
//        if(parent.getTime() < 1){
//            font.drawString(g ,"000"+parent.getTime() , 496, 48 ); // time
//        }else if(parent.getTime()<10){
//            font.drawString(g ,"00"+parent.getTime() , 496, 48 ); // time
//        }else if(parent.getTime() < 100){
//            font.drawString(g ,"0"+parent.getTime() , 496, 48 ); // time
//        }else{
//            font.drawString(g ,""+parent.getTime() , 496, 48 ); // time
//        }
//        
        
        
//        font.drawString(g ,"X  "+(int)player.getX()/32 + " Y "+(int)player.getY()/32, 21, 64 );
        
        
    }

    private void LoadInvisibleObjects() {
        switch(this.LevelNumber){
            case 11:  break ;
            case 12: Sprite noscroll = new Sprite(ImageUtil.createImage(3*32 , 1*32 ));
                     noscroll.setLocation(187*32, 3*32);
                     noscroll.setDataID("StopScrollAndNumber");
                     this.InvisibleObjects.add(noscroll);
            break ;
            case 13: ; break ;
            case 14: ; Sprite noscroll2 = new Sprite(ImageUtil.createImage(1*32 , 7*32 ));
                     noscroll2.setLocation(134*32, 3*32);
                     noscroll2.setDataID("StopScroll");
                     this.InvisibleObjects.add(noscroll2);
                     
                      Sprite scroll = new Sprite(ImageUtil.createImage(1*32 , 3*32 ));
                     scroll.setLocation(143*32, 6*32);
                     scroll.setDataID("scroll");
                     this.InvisibleObjects.add(scroll);
                     
            break ;
            
            case 21: ;break ;
            case 22: ;break ;
            case 23: ;break ;
            case 24: ; Sprite noscroll3 = new Sprite(ImageUtil.createImage(1*32 , 7*32 ));
                     noscroll3.setLocation(134*32, 3*32);
                     noscroll3.setDataID("StopScroll");
                     this.InvisibleObjects.add(noscroll3);
                     
                      Sprite scroll2 = new Sprite(ImageUtil.createImage(1*32 , 3*32 ));
                     scroll2.setLocation(143*32, 6*32);
                     scroll2.setDataID("scroll");
                     this.InvisibleObjects.add(scroll2);
                     break ;
                
            case 31: ; break ;
            case 32: ; break ;
            case 33: ; break ;
            case 34:  Sprite noscroll4 = new Sprite(ImageUtil.createImage(1*32 , 7*32 ));
                     noscroll4.setLocation(134*32, 3*32);
                     noscroll4.setDataID("StopScroll");
                     this.InvisibleObjects.add(noscroll4);
                     
                      Sprite scroll3 = new Sprite(ImageUtil.createImage(1*32 , 3*32 ));
                     scroll3.setLocation(143*32, 6*32);
                     scroll3.setDataID("scroll");
                     this.InvisibleObjects.add(scroll3);
                     ; break ;
                
            case 41: ; break ;
            case 42: ; break ;
            case 43: ; break ;
            case 44:  Sprite noscroll5 = new Sprite(ImageUtil.createImage(1*32 , 7*32 ));
                     noscroll5.setLocation(291*32, 3*32);
                     noscroll5.setDataID("StopScroll");
                     this.InvisibleObjects.add(noscroll5);
                     
                      Sprite scroll4 = new Sprite(ImageUtil.createImage(1*32 , 3*32 ));
                     scroll4.setLocation(300*32, 6*32);
                     scroll4.setDataID("scroll");
                     this.InvisibleObjects.add(scroll4);
                     ; break ;
                
            case 51: ; break ;
            case 52: ; break ;
            case 53: ; break ;
            case 54: ;  Sprite noscroll6 = new Sprite(ImageUtil.createImage(1*32 , 7*32 ));
                     noscroll6.setLocation(134*32, 3*32);
                     noscroll6.setDataID("StopScroll");
                     this.InvisibleObjects.add(noscroll6);
                     
                      Sprite scroll5 = new Sprite(ImageUtil.createImage(1*32 , 3*32 ));
                     scroll5.setLocation(143*32, 6*32);
                     scroll5.setDataID("scroll");
                     this.InvisibleObjects.add(scroll5);
                     break ;
            
            case 61: ; break ;
            case 62: ; break ;
            case 63: ; break ;
            case 64: ; Sprite noscroll7 = new Sprite(ImageUtil.createImage(1*32 , 7*32 ));
                     noscroll7.setLocation(134*32, 3*32);
                     noscroll7.setDataID("StopScroll");
                     this.InvisibleObjects.add(noscroll7);
                     
                      Sprite scroll7 = new Sprite(ImageUtil.createImage(1*32 , 3*32 ));
                     scroll7.setLocation(143*32, 6*32);
                     scroll7.setDataID("scroll");
                     this.InvisibleObjects.add(scroll7);; break ;
                
            case 71: ; break ;
            case 72: ; break ;
            case 73: ; break ;
            case 74:  Sprite noscroll8 = new Sprite(ImageUtil.createImage(1*32 , 7*32 ));
                     noscroll8.setLocation(323*32, 3*32);
                     noscroll8.setDataID("StopScroll");
                     this.InvisibleObjects.add(noscroll8);
                     
                      Sprite scroll8 = new Sprite(ImageUtil.createImage(1*32 , 3*32 ));
                     scroll8.setLocation(332*32, 6*32);
                     scroll8.setDataID("scroll");
                     this.InvisibleObjects.add(scroll8);
                     ; break ;
                
            case 81: ; break ;
            case 82: ; break ;
            case 83: ; break ;
            case 841: ; break ;
            case 842: ; break ;
            case 843: ; break ;
            case 844: ; break ;
            case 845:  Sprite noscroll9 = new Sprite(ImageUtil.createImage(1*32 , 7*32 ));
                     noscroll9.setLocation(39*32, 3*32);
                     noscroll9.setDataID("StopScroll");
                     this.InvisibleObjects.add(noscroll9);
                     
                      Sprite scroll9 = new Sprite(ImageUtil.createImage(1*32 , 3*32 ));
                     scroll9.setLocation(47*32, 6*32);
                     scroll9.setDataID("scroll");
                     this.InvisibleObjects.add(scroll9);
                     ; break ;
            
                
            case 555: break ;
                
            case 'a':  break ;
            case 'b':   break ;
                
            case 'c': ; break ;
                
            case 'd': ; break ;
                
            case 'e': ; break ;
            case 'f': ; break ;
                
            case 'g': ; break ;
            case 'h': ; break ;
                
            case 'i': ; break ;
            case 'j': ; break ;
            case 'k': ; break ;
                
            case 'l': ; break ; 
                
            case 'm': ; break ; // todo   
            case 'n': ; break ;     
                
    }





}

    public void Scroll(boolean command) {
        StopScroll = command ;
    }

    public void SmoothScroll() {
        
        SmoothScroll = true ;
        this.bsMusic.stopAll();
        this.playSound("music/smb_world_clear.wav");
    }

    private void IncreaseWorld() {
         StartWorld++ ;
         if(StartWorld >8){
             StartWorld = 1;
         }
    
    }

    private void IncreaseLevel() {
         StartLevel++ ;
         if(StartLevel >4){
             StartLevel = 1;
         }
    }

    private void ShowInfo() {
        ShowInfo = true ;
    }

}
