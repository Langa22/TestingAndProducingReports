import java.io. * ;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
public class ReadCSV {
        public static ArrayList<GetDTO>readNamesCSV(String fileNames){
            ArrayList<GetDTO>getNames=new ArrayList<>();
            Path pathToFile = Paths.get(fileNames);
            try (BufferedReader br = Files.newBufferedReader(pathToFile,
                    StandardCharsets.US_ASCII)) {
                String line = br.readLine();
                while (line != null) {
                    String[] attributes = line.split(",");
                    GetDTO name = createBook(attributes);
                    getNames.add(name);
                    line = br.readLine();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return getNames;
        }
        private static GetDTO createBook(String[] attributes) {
            String id =attributes[0];
            String name =attributes[1];
            String location =attributes[2];
            String position =attributes[3];
            String age =attributes[4];
            return new GetDTO(id,name,location,position,age);
    }
}

