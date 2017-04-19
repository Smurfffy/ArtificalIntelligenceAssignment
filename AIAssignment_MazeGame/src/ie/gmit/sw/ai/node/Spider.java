package ie.gmit.sw.ai.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Spider extends Node {
	
	private long speed = 2000;
	private Random random = new Random();
	private Node[][] maze = null;
	private ExecutorService exec = Executors.newFixedThreadPool(1);

	public Spider(int row, int col, int id, Node[][] maze) {
		super(row, col, id);
		// TODO Auto-generated constructor stub
		
		this.maze = maze;
		
		exec.submit(() -> {
            while (true) {
                try {
                    Thread.sleep(speed);
                    move();
                } catch (Exception ex) {
                }
            }
        });
	}
	
	private void move(){

        Node[] adjacentNodes = null;
        List<Node> gameSpace = new ArrayList<>(); //Creates a list of Nodes the spiders can move to.

        //Gets the list of adjacent nodes for the spider
        adjacentNodes = adjacentNodes(maze);

        for (Node node : adjacentNodes) {

            //Checks the Id of the node and if its not a wall or another game object it will add it to the list of places it can move.
            if (node.getId() == -1) {
            	gameSpace.add(node);
            }
        }

        if (gameSpace.size() > 0) {
            int newRow, newCol, oldRow, oldCol;

            oldRow = getRow();
            oldCol = getCol();
            
            /*
             * Current bug in the code where spiders wont move if caught between a wall to the north and south of them.
             */

            // pick a random index to move to, either north, west, east or south.
            int index = random.nextInt(gameSpace.size());

            newRow = gameSpace.get(index).getRow();
            newCol = gameSpace.get(index).getCol();
            
            setRow(newRow);
            setCol(newCol);
            gameSpace.get(index).setRow(oldRow);
            gameSpace.get(index).setCol(oldCol);
            
            // Moves the spider to a new place in the map
            maze[newRow][newCol] = (Spider)this;
            
            //removes the spider sprite from where they moved from, stopping a multiplying effect we do not want in our game.
            maze[oldRow][oldCol] = gameSpace.get(index);
        }
	}
}
