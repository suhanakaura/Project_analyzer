import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class ProjectCategorisation {
    public static void main(String[] args) {
        List<String> ls = new ArrayList<>();
        Map<String,List<String>> categorisedProducts = new HashMap<>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("data_srs1.csv"));
            String line = reader.readLine();
            line = reader.readLine();
            int countClone=0;
            while(line!=null){

                String[] projectData = line.split(",");
                String category = "";
                String name = "";

                if(projectData.length>1) {
                    category = projectData[1].trim();
                    if(projectData.length>3){
                        name = projectData[3].trim();
                    }
                    if(category!=""){
                        categorisedProducts.putIfAbsent(category,new ArrayList<>());
                        if(name!=""){
                            categorisedProducts.get(category).add(name);
                        }

                    }
                    if (projectData.length>5 && projectData[5].trim().equals("YES")) countClone++;
                }

                line = reader.readLine();
            }
            printProjectsStats(categorisedProducts);
            System.out.println("Cloned projects are: "+countClone);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    private static void printProjectsStats(Map<String,List<String>> categorisedProducts){
        System.out.println("\nCategorised Projects: ");
        System.out.println();

        for(Map.Entry<String,List<String>> entry: categorisedProducts.entrySet()){
            System.out.println(entry.getKey()+": "+entry.getValue().size()+" Projects");
            System.out.println(entry.getValue());
            System.out.println();
        }
    }
}
