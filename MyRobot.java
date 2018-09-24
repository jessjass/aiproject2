// Feel free to use this java file as a template and extend it to write your solver.
// ---------------------------------------------------------------------------------

import world.Robot;
import world.World;

import java.awt.*;
import java.util.*;

public class MyRobot extends Robot {
    boolean isUncertain;
    int[][] start2finish;
    int[][] finish2start;
	
    @Override
    public void travelToDestination() {
        if (isUncertain) {
			// call function to deal with uncertainty
        }
        else {
			// call function to deal with certainty
        }
    }

    @Override
    public void addToWorld(World world) {
        isUncertain = world.getUncertain();
        super.addToWorld(world);
    }

    public static void main(String[] args) {
        try {
			World myWorld = new World("TestCases/myInputFile1.txt", true);
			
            MyRobot robot = new MyRobot();
            robot.addToWorld(myWorld);
			//myWorld.createGUI(400, 400, 200); // uncomment this and create a GUI; the last parameter is delay in msecs
			
            setNeg();
            //int[][] dist = setupDist(myWorld, robot);
            for(int i = 0; i < start2finish.length; i++){
            	for(int k = 0; k < start2finish[0].length; k++){
            		System.out.print(start2finish[i][k] + " ");
            	}
            	System.out.println();
            }
            for(int i = 0; i < finish2start.length; i++){
                for(int k = 0; k < finish2start[0].length; k++){
                    System.out.print(finish2start[i][k] + " ");
                }
                System.out.println();
            }
			//robot.travelToDestination();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int[][] setupDist(World w, MyRobot robot){
    	int[][] distarray = new int[w.numRows()][w.numCols()];
    	for(int r = 0;  r < w.numRows(); r++){
    		for(int c = 0; c < w.numCols(); c++){
    			if(robot.pingMap(new Point(r, c)).equals("O")){
    				distarray[r][c] = Math.abs(w.getStartPos().getX() - r) + Math.abs(w.getStartPos().getY() - c) + Math.abs(w.getEndPos().getX() - r) + Math.abs(w.getEndPos().getY() - c);
    			}
    		}
    	}
    }

    public void setNeg(){
        for(int i = 0;  i < start2finish.length; i++){
            for(int k = 0; k < finish2start.length; k++){
                start2finish[i][k] = -1;
                finish2start[i][k] = -1;
            }
        }
    }
    
    public void setVals(Robot r, Point p, boolean start){
    	if((start && p.equals("F")) || (!start && p.equals("S"))){
    		return;
    	}
    	if (start){
    		if((int)p.getY() + 1 <= numRows && r.pingMap(new Point(p.getX(), p.getY()+1)).equals("O") && (start2finish[(int)p.getX()][(int)p.getY()+1] == -1 || start2finish[(int)p.getX()][(int)p.getY()+1] > start2finish[(int)p.getX()][(int)p.getY()] + 1)){
                start2finish[(int)p.getX()][(int)p.getY()+1] = start2finish[(int)p.getX()][(int)p.getY()] + 1;
                setVals(r, new Point(p.getX(), p.getY()+1, start);
            } if((int)p.getX() + 1 <= numRows && (int)p.getY() + 1 <= numCols && r.pingMap(new Point(p.getX()+1, p.getY()+1)).equals("O") && (start2finish[(int)p.getX()+1][(int)p.getY()+1] == -1 || start2finish[(int)p.getX()+1][(int)p.getY()+1] > start2finish[(int)p.getX()][(int)p.getY()] + 1)){
                start2finish[(int)p.getX()+1][(int)p.getY()+1] = start2finish[(int)p.getX()][(int)p.getY()] + 1;
                setVals(r, new Point(p.getX()+1, p.getY()+1, start);
            } if((int)p.getX() + 1 <= numRows && r.pingMap(new Point(p.getX()+1, p.getY())).equals("O") && (start2finish[(int)p.getX()+1][(int)p.getY()] == -1 || start2finish[(int)p.getX()+1][(int)p.getY()] > start2finish[(int)p.getX()][(int)p.getY()] + 1)){
                start2finish[(int)p.getX()+1[(int)p.getY()] = start2finish[(int)p.getX()][(int)p.getY()] + 1;
                setVals(r, new Point(p.getX()+1, p.getY(), start);
            } if((int)p.getX() + 1 <= numRows && (int)p.getY() - 1 >= 0 && r.pingMap(new Point(p.getX()+1, p.getY()-1)).equals("O") && (start2finish[(int)p.getX()+1][(int)p.getY()-1] == -1 || start2finish[(int)p.getX()+1][(int)p.getY()-1] > start2finish[(int)p.getX()][(int)p.getY()] + 1)){
                start2finish[(int)p.getX()+1][(int)p.getY()-1] = start2finish[(int)p.getX()][(int)p.getY()] + 1;
                setVals(r, new Point(p.getX()+1, p.getY()-1, start);
            } if((int)p.getY() - 1 >= 0 && r.pingMap(new Point(p.getX(), p.getY()-1)).equals("O") && (start2finish[(int)p.getX()][(int)p.getY()-1] == -1 || start2finish[(int)p.getX()][(int)p.getY()-1] > start2finish[(int)p.getX()][(int)p.getY()] + 1)){
                start2finish[(int)p.getX()][(int)p.getY()-1] = start2finish[(int)p.getX()][(int)p.getY()] + 1;
                setVals(r, new Point(p.getX(), p.getY()-1, start);
            } if((int)p.getX() - 1 >= 0 && (int)p.getY() - 1 >= 0 && r.pingMap(new Point(p.getX()-1, p.getY()-1)).equals("O") && (start2finish[(int)p.getX()-1][(int)p.getY()-1] == -1 || start2finish[(int)p.getX()-1][(int)p.getY()-1] > start2finish[(int)p.getX()][(int)p.getY()] + 1)){
                start2finish[(int)p.getX()-1][(int)p.getY()-1] = start2finish[(int)p.getX()][(int)p.getY()] + 1;
                setVals(r, new Point(p.getX()-1, p.getY()-1, start);    
            } if((int)p.getX() - 1 >= 0 && r.pingMap(new Point(p.getX()-1, p.getY())).equals("O") && (start2finish[(int)p.getX()-1][(int)p.getY()] == -1 || start2finish[(int)p.getX()-1][(int)p.getY()] > start2finish[(int)p.getX()][(int)p.getY()] + 1)){
                start2finish[(int)p.getX()-1][(int)p.getY()] = start2finish[(int)p.getX()][(int)p.getY()] + 1;
                setVals(r, new Point(p.getX()-1, p.getY(), start);
            } if((int)p.getX() - 1 >= 0 && (int)p.getY() + 1 <= numCols && r.pingMap(new Point(p.getX()-1, p.getY()+1)).equals("O") && (start2finish[(int)p.getX()-1][(int)p.getY()+1] == -1 || start2finish[(int)p.getX()-1][(int)p.getY()+1] > start2finish[(int)p.getX()][(int)p.getY()] + 1)){
                start2finish[(int)p.getX()-1][(int)p.getY()+1] = start2finish[(int)p.getX()][(int)p.getY()] + 1;
                setVals(r, new Point(p.getX()-1, p.getY()+1, start);
            }
      	} else {
      		if((int)p.getY() + 1 <= numRows && r.pingMap(new Point(p.getX(), p.getY()+1)).equals("O") && (finish2start[(int)p.getX()][(int)p.getY()+1] == -1 || finish2start[(int)p.getX()][(int)p.getY()+1] > finish2start[(int)p.getX()][(int)p.getY()] + 1)){
                finish2start[(int)p.getX()][(int)p.getY()+1] = finish2start[(int)p.getX()][(int)p.getY()] + 1;
                setVals(r, new Point(p.getX(), p.getY()+1, start);
            } if((int)p.getX() + 1 <= numRows && (int)p.getY() + 1 <= numCols && r.pingMap(new Point(p.getX()+1, p.getY()+1)).equals("O") && (finish2start[(int)p.getX()+1][(int)p.getY()+1] == -1 || finish2start[(int)p.getX()+1][(int)p.getY()+1] > finish2start[(int)p.getX()][(int)p.getY()] + 1)){
                finish2start[(int)p.getX()+1][(int)p.getY()+1] = finish2start[(int)p.getX()][(int)p.getY()] + 1;
                setVals(r, new Point(p.getX()+1, p.getY()+1, start);
            } if((int)p.getX() + 1 <= numRows && r.pingMap(new Point(p.getX()+1, p.getY())).equals("O") && (finish2start[(int)p.getX()+1][(int)p.getY()] == -1 || finish2start[(int)p.getX()+1][(int)p.getY()] > finish2start[(int)p.getX()][(int)p.getY()] + 1)){
                finish2start[(int)p.getX()+1[(int)p.getY()] = finish2start[(int)p.getX()][(int)p.getY()] + 1;
                setVals(r, new Point(p.getX()+1, p.getY(), start);
            } if((int)p.getX() + 1 <= numRows && (int)p.getY() - 1 >= 0 && r.pingMap(new Point(p.getX()+1, p.getY()-1)).equals("O") && (finish2start[(int)p.getX()+1][(int)p.getY()-1] == -1 || finish2start[(int)p.getX()+1][(int)p.getY()-1] > finish2start[(int)p.getX()][(int)p.getY()] + 1)){
                finish2start[(int)p.getX()+1][(int)p.getY()-1] = finish2start[(int)p.getX()][(int)p.getY()] + 1;
                setVals(r, new Point(p.getX()+1, p.getY()-1, start);
            } if((int)p.getY() - 1 >= 0 && r.pingMap(new Point(p.getX(), p.getY()-1)).equals("O") && (finish2start[(int)p.getX()][(int)p.getY()-1] == -1 || finish2start[(int)p.getX()][(int)p.getY()-1] > finish2start[(int)p.getX()][(int)p.getY()] + 1)){
                finish2start[(int)p.getX()][(int)p.getY()-1] = finish2start[(int)p.getX()][(int)p.getY()] + 1;
                setVals(r, new Point(p.getX(), p.getY()-1, start);
            } if((int)p.getX() - 1 >= 0 && (int)p.getY() - 1 >= 0 && r.pingMap(new Point(p.getX()-1, p.getY()-1)).equals("O") && (finish2start[(int)p.getX()-1][(int)p.getY()-1] == -1 || finish2start[(int)p.getX()-1][(int)p.getY()-1] > finish2start[(int)p.getX()][(int)p.getY()] + 1)){
                finish2start[(int)p.getX()-1][(int)p.getY()-1] = finish2start[(int)p.getX()][(int)p.getY()] + 1;
                setVals(r, new Point(p.getX()-1, p.getY()-1, start);    
            } if((int)p.getX() - 1 >= 0 && r.pingMap(new Point(p.getX()-1, p.getY())).equals("O") && (finish2start[(int)p.getX()-1][(int)p.getY()] == -1 || finish2start[(int)p.getX()-1][(int)p.getY()] > finish2start[(int)p.getX()][(int)p.getY()] + 1)){
                finish2start[(int)p.getX()-1][(int)p.getY()] = finish2start[(int)p.getX()][(int)p.getY()] + 1;
                setVals(r, new Point(p.getX()-1, p.getY(), start);
            } if((int)p.getX() - 1 >= 0 && (int)p.getY() + 1 <= numCols && r.pingMap(new Point(p.getX()-1, p.getY()+1)).equals("O") && (finish2start[(int)p.getX()-1][(int)p.getY()+1] == -1 || finish2start[(int)p.getX()-1][(int)p.getY()+1] > finish2start[(int)p.getX()][(int)p.getY()] + 1)){
                finish2start[(int)p.getX()-1][(int)p.getY()+1] = finish2start[(int)p.getX()][(int)p.getY()] + 1;
                setVals(r, new Point(p.getX()-1, p.getY()+1, start);
            }
      	}
    }
}
