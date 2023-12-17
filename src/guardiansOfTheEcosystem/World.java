package guardiansOfTheEcosystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

public class World extends JLayeredPane implements MouseMotionListener {
	//bg image
    private Image bgImage;
    
    //guardian image
    private Image bearCatImage;
    private Image porcupineImage;
    private Image crabImage;
    
    //enemy image
    private Image sawManImage;
    
    // weapon image
    private Image bearCatweaponImage;
    private Image crabweaponImage;
    private Image porcupineweaponImage;
    
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
    private GameScreenPanel.GuardianType activeGuardian = GameScreenPanel.GuardianType.None;
    
    private Grid[] grids;


    public World(JLabel energyScoreBoard) {
        setSize(1000, 752);
        setLayout(null);
        addMouseMotionListener(this);
        this.energyScoreBoard = energyScoreBoard;
        
        // Set initial energy to 150
        setEnergyScore(150);
        
        try {
            bgImage = new ImageIcon(this.getClass().getResource("images/mainBG.png")).getImage();
            bearCatImage = new ImageIcon(this.getClass().getResource("images/bearcat.png")).getImage();
            porcupineImage = new ImageIcon(this.getClass().getResource("images/porcupine.png")).getImage();
            crabImage = new ImageIcon(this.getClass().getResource("images/crab.png")).getImage();
            bearCatweaponImage = new ImageIcon(this.getClass().getResource("images/bearcatweapon.png")).getImage();
            crabweaponImage = new ImageIcon(this.getClass().getResource("images/crabweapon.png")).getImage();
            porcupineweaponImage = new ImageIcon(this.getClass().getResource("images/porcupineweapon.png")).getImage();
            
            sawManImage = new ImageIcon(this.getClass().getResource("images/zombie1.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        // Set the grid for guardians
        grids = new Grid[45];
        for (int i = 0; i < 45; i++) {
            Grid g = new Grid();
            g.setLocation(44 + (i % 9) * 100, 109 + (i / 9) * 120);
            g.setAction(new GuardianActionListener((i % 9), (i / 9)));
            grids[i] = g;
            add(g);
        }
        
        // Set enemy lane
        enemyLane = new ArrayList<>();
        enemyLane.add(new ArrayList<>()); //line 1
        enemyLane.add(new ArrayList<>()); //line 2
        enemyLane.add(new ArrayList<>()); //line 3
        enemyLane.add(new ArrayList<>()); //line 4
        enemyLane.add(new ArrayList<>()); //line 5
        
        // Set throwable material lane
        throwableMaterialLane = new ArrayList<>();
        throwableMaterialLane.add(new ArrayList<>()); //line 1
        throwableMaterialLane.add(new ArrayList<>()); //line 2
        throwableMaterialLane.add(new ArrayList<>()); //line 3
        throwableMaterialLane.add(new ArrayList<>()); //line 4
        throwableMaterialLane.add(new ArrayList<>()); //line 5
        
        // Energys
        activeEnergys = new ArrayList<>();
    }

    protected void startGame() {
            // Redraw every 25 milisecond
            redrawTimer = new Timer(25, (ActionEvent e) -> {
                repaint();
            });
            redrawTimer.start();
            
            // Gameplay Timer
            gameplayTimer = new Timer(60, (ActionEvent e) -> gameplay());
            gameplayTimer.start();
            
            // Produce energy
            energyProducer = new Timer(2500, (ActionEvent e) -> {
                Random random = new Random();
                Energy energy = new Energy(this, random.nextInt(800) + 100, 0, random.nextInt(300) + 200);
                activeEnergys.add(energy);
                add(energy, 1);
            });
            
            energyProducer.start();
           
            
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
        }
        

    public void reset() {
        // Stop timers
        stopTimers();

        // Reset weapons
        resetWeapons();

        setEnergyScore(500);

        // Remove all components
        removeAllComponents();

        repaint();
    }

    private void stopTimers() {
    	if (redrawTimer != null && redrawTimer.isRunning()) {
            redrawTimer.stop();
        }
        if (energyProducer != null && energyProducer.isRunning()) {
            energyProducer.stop();
        }
        if (gameplayTimer != null && gameplayTimer.isRunning()) {
            gameplayTimer.stop();
        }
        if (enemyProducer != null && enemyProducer.isRunning()) {
            enemyProducer.stop();
        }
    }

    private void resetWeapons() {
        // Stop the energy production timer
        if (energyProducer != null && energyProducer.isRunning()) {
            energyProducer.stop();
        }

        // Clear the activeEnergys list
        activeEnergys.clear();
       
    }

    private void removeAllComponents() {
        // Set the grid for guardians
        grids = new Grid[45];
        for (int i = 0; i < 45; i++) {
            Grid g = new Grid();
            g.setLocation(44 + (i % 9) * 100, 109 + (i / 9) * 120);
            g.setAction(new GuardianActionListener((i % 9), (i / 9)));
            grids[i] = g;
            add(g);
        }

        // Reset guardians in grids
        for (Grid grid : grids) {
            grid.resetGuardian();
        }

        // Remove all elements from enemy lane
        enemyLane = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            enemyLane.add(new ArrayList<>());
        }

        // Remove all elements from throwable material lane
        throwableMaterialLane = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            throwableMaterialLane.add(new ArrayList<>());
        }

        // Remove all elements from activeEnergys
        activeEnergys = new ArrayList<>();
        
       
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

        // Draw Enemies and ThrowableMaterials
        for (int i = 0; i < 5; i++) {
            for (Enemy enemy : enemyLane.get(i)) {
                if (enemy instanceof SawMan) {
                    g.drawImage(sawManImage, enemy.getPosX(), 109 + (i * 120), null);
                }
            }

            for (int j = 0; j < throwableMaterialLane.get(i).size(); j++) {
                ThrowableMaterial tm = throwableMaterialLane.get(i).get(j);
                if (tm instanceof Rock) {
	                g.drawImage(bearCatweaponImage, tm.getPosX(), 130 + (i * 120), null);
	            } else if (tm instanceof WaterBall) {
	                g.drawImage(crabweaponImage, tm.getPosX(), 130 + (i * 120), null);
	            } else if (tm instanceof Spike) {
	                g.drawImage(porcupineweaponImage, tm.getPosX(), 130 + (i * 120), null);
	            }
            }
        }

        // Remove weapons that have reached the bottom
        activeEnergys.removeIf(energy -> energy.getY() >= getHeight());
    }

    private class GuardianActionListener implements ActionListener {

        int x, y;

        public GuardianActionListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (activeGuardian == GameScreenPanel.GuardianType.BearCat) {
        	    if (getEnergyScore() >= 100) {
        	    	Guardian bearcat = new BearCat(World.this, x, y, 100, 100);
        	        grids[x + y * 9].setGuardian(bearcat);
        	        setEnergyScore(getEnergyScore() - bearcat.getEnergyPrice());
        	    }
        	} else if (activeGuardian == GameScreenPanel.GuardianType.Porcupine) {
        	    if (getEnergyScore() >= 200) {
        	    	Guardian porcupine = new Porcupine(World.this, x, y, 100, 200);
        	        grids[x + y * 9].setGuardian(porcupine);
        	        setEnergyScore(getEnergyScore() - porcupine.getEnergyPrice());
        	    }
        	} else if (activeGuardian == GameScreenPanel.GuardianType.Crab) {
        	    if (getEnergyScore() >= 175) {
            	    Guardian crab = new Crab(World.this, x, y, 100, 175);
        	        grids[x + y * 9].setGuardian(crab);
        	        setEnergyScore(getEnergyScore() - crab.getEnergyPrice());
        	    }
        	}
        		
            activeGuardian = GameScreenPanel.GuardianType.None;
        }
    }

    public GameScreenPanel.GuardianType getActiveGuardian() {
        return activeGuardian;
    }
    
    public void setActiveGuardian(GameScreenPanel.GuardianType activeGuardian) {
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
    
    public Grid[] getGrids() {
        return grids;
    }

    public void setGrids(Grid[] grids) {
        this.grids = grids;
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
