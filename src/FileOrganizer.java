import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileOrganizer {

    private static Path getDest(Path targetFolder, String fileName) {
        Path destination = targetFolder.resolve(fileName);

        if (!Files.exists(destination)) {
            return destination;
        }

        String namePart = fileName;
        String extPart = "";
        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex > 0) {
            namePart = fileName.substring(0, dotIndex);
            extPart = fileName.substring(dotIndex);
        }

        int counter = 1;
        while (Files.exists(destination)) {
            String newName = namePart + " (" + counter + ")" + extPart;
            destination = targetFolder.resolve(newName);
            counter++;
        }

        return destination;
    }

    public static void main(String[] args) throws IOException {
        String userName = System.getProperty("user.home");
        Path downloadsPath = Paths.get(userName,"Downloads");

        Path drawerPath = Paths.get(downloadsPath.toString(),"FilesDrawer");
        if(! Files.exists(drawerPath)){
            Files.createDirectory(drawerPath);
        }


        Path pdfFolder = Paths.get(drawerPath.toString(),"PDF");
        Path imagesFolder = Paths.get(drawerPath.toString(),"Images");
        Path documentsFolder = Paths.get(drawerPath.toString(),"Documents");
        Path executableFolder = Paths.get(drawerPath.toString(),"ExecutableFiles");
        Path zipFolder = Paths.get(drawerPath.toString(),"ZipFiles");

        if(! Files.exists(pdfFolder)){
            Files.createDirectory(pdfFolder);
        }
        if(! Files.exists(imagesFolder)){
            Files.createDirectory(imagesFolder);
        }
        if(! Files.exists(documentsFolder)){
            Files.createDirectory(documentsFolder);
        }
        if(! Files.exists(executableFolder)){
            Files.createDirectory(executableFolder);
        }
        if(! Files.exists(zipFolder)){
            Files.createDirectory(zipFolder);
        }


        try(Stream<Path> stream = Files.list(downloadsPath)){
            List<Path> myfiles = stream.collect(Collectors.toList());
            for(Path filePaths : myfiles){
                if(Files.isRegularFile(filePaths)){
                    String fileName = filePaths.getFileName().toString();
                    int index = fileName.lastIndexOf(".");
                    String extension="";
                    if(index>0){
                        extension = fileName.substring(index);
                    }
                    try{
                        if(extension.equalsIgnoreCase(".pdf") && Files.exists(pdfFolder)){
                            Path destination = getDest(pdfFolder, fileName);
                            Files.move(filePaths,destination);
                        }
                        if((extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg") || extension.equalsIgnoreCase(".png")) && Files.exists(imagesFolder)){
                            Path destination = getDest(imagesFolder, fileName);
                            Files.move(filePaths,destination);
                        }
                        if((extension.equalsIgnoreCase(".doc")  || extension.equalsIgnoreCase(".docx")|| extension.equalsIgnoreCase(".txt") || extension.equalsIgnoreCase(".xls") || extension.equalsIgnoreCase(".xlsx") || extension.equalsIgnoreCase(".ppt") || extension.equalsIgnoreCase(".pptx")) && Files.exists(documentsFolder)){
                            Path destination = getDest(documentsFolder, fileName);
                            Files.move(filePaths,destination);
                        }
                        if(extension.equalsIgnoreCase(".exe") && Files.exists(executableFolder)){
                            Path destination = getDest(executableFolder, fileName);
                            Files.move(filePaths,destination);
                        }if((extension.equalsIgnoreCase(".zip") || extension.equalsIgnoreCase(".rar"))  && Files.exists(zipFolder)){
                            Path destination = getDest(zipFolder, fileName);
                            Files.move(filePaths,destination);
                        }
                    }
                    catch (Exception e){
                        System.out.println("error in moving files");
                    }
                }
            }
        }
        catch (IOException e){
            System.out.println("error in reading files");
        }
    }
}
