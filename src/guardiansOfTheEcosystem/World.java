package guardiansOfTheEcosystem;

import javax.swing.*;

import guardiansOfTheEcosystem.GameWindow.GuardianType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

public class World extends JLayeredPane implements MouseMotionListener {
	
	// Variable to store image
	
	//bg image
    private Image bgImage;
    
    //guardian image
    private Image bearCatImage;
    private Image porcupineImage;
    private Image crabImage;
    
    //enemy image
    private Image sawManImage;
    
    //shoot image
    private Image bearCatweaponImage;
    
    // List of energy
    private ArrayList<Energy> activeEnergys;
    
    // Label for energy score
    private JLabel energyScoreBoard;
    
    // Variable to track mouse position
    private int mouseX, mouseY;
    
    // Variable to store energy
    private int energyScore;
    
    // All variables to store timer
    private Timer redrawTimer;
    private Timer energyProducer;
    private Timer gameplayTimer;
    private Timer enemyProducer;
    private Timer advancerTimer;
    
    // List of lane
    private ArrayList<ArrayList<Enemy>> enemyLane;
    private ArrayList<ArrayList<ThrowableMaterial>> throwableMaterialLane;
    
    
    
    /*
     * dificultyEnemyList:
     * 	Determine the list of enemy for each level (index = level - 1)
     * 
     * dificultyValue:
     * 	Determine the probabiilty of enemy to summon
     * 
     * dificulty:
     * 	initial dificulty set to 1
     * 
     * */ 
    public String[][] dificultyEnemyList = {{"SawMan"}};
    public int[][][] dificultyValue = {{{0, 99}}};
    private int dificulty = 1;
    
    // Variable to store current guardian
    private GameWindow.GuardianType activeGuardian = GameWindow.GuardianType.None;
    
    private Grid[] grids;

    public World(JLabel energyScoreBoard) {
        setSize(1000, 752);
        setLayout(null);
        addMouseMotionListener(this);
        this.energyScoreBoard = energyScoreBoard;
        
        // Set initial energy to 150
        setEnergyScore(500);
        
        try {
            bgImage = new ImageIcon(this.getClass().getResource("images/mainBG.png")).getImage();
            bearCatImage = new ImageIcon(this.getClass().getResource("images/peashooter.gif")).getImage();
            porcupineImage = new ImageIcon(this.getClass().getResource("images/freezepeashooter.gif")).getImage();
            crabImage = new ImageIcon(this.getClass().getResource("images/freezepeashooter.gif")).getImage();
            bearCatweaponImage = new ImageIcon(this.getClass().getResource("images/pea.png")).getImage();
            
            sawManImage = new ImageIcon(this.getClass().getResource("images/zombie1.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        // Redraw every 25 milisecond
        redrawTimer = new Timer(25, (ActionEvent e) -> {
            repaint();
        });
        redrawTimer.start();
        
        
        // Produce energys
        activeEnergys = new ArrayList<>();
        
        gameplayTimer = new Timer(60, (ActionEvent e) -> gameplay());
        gameplayTimer.start();
        
        energyProducer = new Timer(2500, (ActionEvent e) -> {
            Random random = new Random();
            Energy energy = new Energy(this, random.nextInt(800) + 100, 0, random.nextInt(300) + 200);
            activeEnergys.add(energy);
            add(energy, 1);
        });
        
        energyProducer.start();
        
        // Enemy lane
        enemyLane = new ArrayList<>();
        enemyLane.add(new ArrayList<>()); //line 1
        enemyLane.add(new ArrayList<>()); //line 2
        enemyLane.add(new ArrayList<>()); //line 3
        enemyLane.add(new ArrayList<>()); //line 4
        enemyLane.add(new ArrayList<>()); //line 5
        
        // Throwable material lane
        throwableMaterialLane = new ArrayList<>();
        throwableMaterialLane.add(new ArrayList<>()); //line 1
        throwableMaterialLane.add(new ArrayList<>()); //line 2
        throwableMaterialLane.add(new ArrayList<>()); //line 3
        throwableMaterialLane.add(new ArrayList<>()); //line 4
        throwableMaterialLane.add(new ArrayList<>()); //line 5
        
        // Produce enemys
        enemyProducer = new Timer(7000, (ActionEvent e) -> {            
            String[] enemyList = dificultyEnemyList[dificulty - 1];
            int[][] dValue = dificultyValue[dificulty - 1];
            
            Enemy enemy = null;
            
            Random random = new Random();
            int randomLane = random.nextInt(5);
            int randomNumber = random.nextInt(100);
            
            for (int i = 0; i < dValue.length; i++) {
                if (randomNumber >= dValue[i][0] && randomNumber <= dValue[i][1]) {
                    enemy = Enemy.getEnemy(enemyList[i], this, randomLane);
                }
            }
            
            enemyLane.get(randomLane).add(enemy);
        });
        
        enemyProducer.start();
        
        
        
        grids = new Grid[45];
        for (int i = 0; i < 45; i++) {
            Grid g = new Grid();
            g.setLocation(44 + (i % 9) * 100, 109 + (i / 9) * 120);
            g.setAction(new GuardianActionListener((i % 9), (i / 9)));
            grids[i] = g;
            add(g);
        }
        
    }
    
    private void gameplay() {
        for (int i = 0; i < 5; i++) {
            for (Enemy enemy : enemyLane.get(i)) {
                enemy.move();
            }

            for (int j = 0; j < throwableMaterialLane.get(i).size(); j++) {
                ThrowableMaterial tm = throwableMaterialLane.get(i).get(j);
                tm.advance();
            }
        }

        for (int i = 0; i < activeEnergys.size(); i++) {
            activeEnergys.get(i).energyFall();
        }

    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);
        
        //Draw Guardians
        for (int i = 0; i < 45; i++) {
            Grid c = grids[i];
            if (c.assignedGuardian != null) {
                Guardian guardian = c.assignedGuardian;
                if (guardian instanceof BearCat) {
                    g.drawImage(bearCatImage, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                }
                if (guardian instanceof Porcupine) {
                    g.drawImage(porcupineImage, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                }
                if (guardian instanceof Crab) {
                    g.drawImage(crabImage, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                }
            }
        }
        
        for (int i = 0; i < 5; i++) {
            for (Enemy enemy : enemyLane.get(i)) {
                if (enemy instanceof SawMan) {
                    g.drawImage(sawManImage, enemy.getPosX(), 109 + (i * 120), null);
                }
            }
            
	        for (int j = 0; j < throwableMaterialLane.get(i).size(); j++) {
	            ThrowableMaterial tm = throwableMaterialLane.get(i).get(j);
	            if (tm instanceof ThrowableMaterial) {
	                g.drawImage(bearCatweaponImage, tm.getPosX(), 130 + (i * 120), null);
	            }
	        }
        }
    }
    
    private class GuardianActionListener implements ActionListener {

        int x, y;

        public GuardianActionListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        	if (activeGuardian == GameWindow.GuardianType.BearCat) {
        	    Guardian bearcat = new BearCat(World.this, x, y, 100, 100);
        	    if (getEnergyScore() >= bearcat.getEnergyPrice()) {
        	        grids[x + y * 9].setGuardian(bearcat);
        	        setEnergyScore(getEnergyScore() - bearcat.getEnergyPrice());
        	    }
        	} else if (activeGuardian == GameWindow.GuardianType.Porcupine) {
        	    Guardian porcupine = new Porcupine(World.this, x, y, 100, 200);
        	    if (getEnergyScore() >= porcupine.getEnergyPrice()) {
        	        grids[x + y * 9].setGuardian(porcupine);
        	        setEnergyScore(getEnergyScore() - porcupine.getEnergyPrice());
        	    }
        	} else if (activeGuardian == GameWindow.GuardianType.Crab) {
        	    Guardian crab = new Porcupine(World.this, x, y, 100, 100);
        	    if (getEnergyScore() >= crab.getEnergyPrice()) {
        	        grids[x + y * 9].setGuardian(crab);
        	        setEnergyScore(getEnergyScore() - crab.getEnergyPrice());
        	    }
        	}
        		
            activeGuardian = GameWindow.GuardianType.None;
        }
    }
    
    static int progress = 0;

    public static void setProgress(int num) {
        progress = progress + num;
        System.out.println(progress);
        if (progress >= 150) {
            if ("1".equals(LevelData.LEVEL_NUMBER)) {
                JOptionPane.showMessageDialog(null, "LEVEL_CONTENT Completed !!!" + '\n' + "Starting next LEVEL_CONTENT");
                GameWindow.gw.dispose();
                LevelData.write("2");
                GameWindow.gw = new GameWindow();
            } else {
                JOptionPane.showMessageDialog(null, "LEVEL_CONTENT Completed !!!" + '\n' + "More Levels will come soon !!!" + '\n' + "Resetting data");
                LevelData.write("1");
                System.exit(0);
            }
            progress = 0;
        }
    }
    
    
    public GameWindow.GuardianType getActiveGuardian() {
        return activeGuardian;
    }
    
    public void setActiveGuardian(GameWindow.GuardianType activeGuardian) {
        this.activeGuardian = activeGuardian;
    }
    
    public ArrayList<Energy> getActiveEnergys() {
        return activeEnergys;
    }
    
    public void setActiveEnergys(ArrayList<Energy> activeEnergys) {
        this.activeEnergys = activeEnergys;
    }
    
    public void setEnergyScore(int energyScore) {
        this.energyScore = energyScore;
        energyScoreBoard.setText(String.valueOf(energyScore));
    }
    
    public int getEnergyScore() {
        return energyScore;
    }
    
    public ArrayList<ArrayList<Enemy>> getEnemyLane() {
        return enemyLane;
    }
    
    public void setEnemyLane(ArrayList<ArrayList<Enemy>> enemyLane) {
        this.enemyLane = enemyLane;
    }
    
    public ArrayList<ArrayList<ThrowableMaterial>> getThrowableMaterialLane() {
        return throwableMaterialLane;
    }

    public void setThrowableMaterialLane(ArrayList<ArrayList<ThrowableMaterial>> throwableMaterialLane) {
        this.throwableMaterialLane = throwableMaterialLane;
    }

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
}
