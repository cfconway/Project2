import java.util.ArrayList;
import java.util.*;

public class Main
{
    public static void main(String[] args) {

        Graph graph = importActors.parser(args[0]);

        Scanner scan = new Scanner(System.in);
        System.out.println("Actor 1: ");
        String actor1 = scan.nextLine();
        boolean exists1 = graph.checkExists(actor1.toLowerCase()); //checking if actor exits
        System.out.println("Actor 2: ");
        String actor2 = scan.nextLine();
        boolean exists2 = graph.checkExists(actor2.toLowerCase()); //checking if actor exists
        if (exists1 == true && exists2 == true)  //if both actors exists, we call BFS and search the graph.
        {
            ArrayList<String> path = graph.BFS(actor1, actor2);
            if (path.isEmpty()) {
                System.out.println("No path between actors");
            }
            System.out.println("Path between: "+ actor1 +" and "+ actor2 +" is: " + path);


        }
    }

}


