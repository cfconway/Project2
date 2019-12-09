import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.util.ArrayList;
import java.util.List;


public class importActors
{

   public static Graph parser(String path)
   {
       Graph graph = new Graph();

       try (BufferedReader reader = Files.newBufferedReader(Paths.get(path));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader()))  //parsing csv file
       {

           JSONParser parser = new JSONParser(); //creates json parser
           for (CSVRecord csvRecord : csvParser) { //for each record in the saved csvparser,
               JSONArray array = (JSONArray) parser.parse(csvRecord.get(2)); //we create an array of the "3rd" column in the csv record
               List<String> actors = new ArrayList<>();
               for (Object object : array) { //for each json object in the json array, we add that object to the actors array list

                   JSONObject obj = (JSONObject) object; //
                   actors.add((String) obj.get("name"));


               }

               //for each record, add to the graph
               for (String actor1 : actors) { //for each actor in the actor array, we loop through and add an edge between these actors associated with eachother
                   for (String actor2 : actors) {
                       graph.addEdge(actor1.toLowerCase(), actor2.toLowerCase());
                   }
               }


           }

       } catch (Exception e) {
           e.printStackTrace();
       }

       return graph;

   }

}
