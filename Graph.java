import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;
import java.util.Queue;


public class Graph {
    HashMap<String, HashSet<String>> graph = new HashMap<String, HashSet<String>>();

    public Graph()
    {
        this.graph = new HashMap<String, HashSet<String>>(); //constructs the graph as a hashmap with a string as its key and
                                                            //hashset of strings as its value

    }

    public void addEdge(String actor1, String actor2)
    {
        graph.putIfAbsent(actor1, new HashSet<>()); //this adds the actor, if it doesnt already exists, with a new hashset as its value
        graph.get(actor1).add(actor2); //this adds the actor2 into the hashset of actor1(key)
    }
    public boolean checkExists(String actor) //this method checks if the actors entered by the user exists, and returns no such actor if they dont
    {
        if(!graph.containsKey(actor))
        {
            System.out.println("No such actor");
            return false;
        }
        return true;
    }


    public ArrayList<String> BFS(String actor1, String actor2) //this is the breadth first search function that traverses the graph of connected actors
    {
        actor1=actor1.toLowerCase(); //converts entered actors to lowercase
        actor2=actor2.toLowerCase();
            Queue<String> q = new LinkedList<String>(); //creates a queue
            ArrayList<String> visited = new ArrayList<String>(); //creates an arraylist of strings, will tell if the actor has been visited already
            HashMap<String, String> ds = new HashMap<String, String>();  //creates a hashmap, the key will be the actor, the value will be if it exists or not
            ds.put(actor1, null); //puts the actor into the hashmap
            visited.add(actor1); //adds the actor into the visted array
            q.add(actor1); //adds actor to queue

            while (!q.isEmpty()) { //while the queue isnt empty, I pull the first value and save it
                String current = q.poll();
                HashSet<String> edges = graph.get(current); //I get the edges connected to that value
                for (String e : edges) { //now I check for each string in the edges hashset

                    if (!visited.contains(e)) { //if the visited array doesnt contain e, I add the edge to the queue, and to the visited array
                        q.add(e);
                        visited.add(e);
                        ds.put(e, current); //I put the value as the key in the hashmap ds, and the values of that key are the current actor, so they are fully connected
                        if (e.equals(actor2)) { //then I check if the value equals the second actor, we call get path, where we pass the set and the actor#2
                            return getPath(ds, actor2);
                        }
                    }
                }

            }
            return getPath(ds, actor2); //returning path from getPath

    }


    public ArrayList<String> getPath(HashMap<String, String> map, String actor2) { //this method takes the hashmap and string actor2
        ArrayList<String> path = new ArrayList<>(); //creates an array list,
        String current = actor2; //saving the actor2

        while (map.get(current) != null) { //while the value in the map isnt null, we add the first name, then we add the value associated with that key
            path.add(0, current);
            current = map.get(current);
        }
        path.add(0, current);
        return path; //returns arraylist path
    }

}




