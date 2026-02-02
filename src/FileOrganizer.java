import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileOrganizer {

    private static final List<String> IMAGES = Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".bmp", ".svg", ".webp");
    private static final List<String> DOCUMENTS = Arrays.asList(".pdf", ".doc", ".docx", ".txt", ".xls", ".xlsx", ".ppt", ".pptx", ".csv", ".rtf");
    private static final List<String> ARCHIVES = Arrays.asList(".zip", ".rar", ".7z", ".tar", ".gz", ".iso");
    private static final List<String> EXECUTABLES = Arrays.asList(".exe", ".msi", ".bat", ".sh");
    private static final List<String> DESIGN_3D = Arrays.asList(".blend", ".obj", ".fbx", ".stl", ".psd", ".ai");
    private static final List<String> MEDIA = Arrays.asList(".mp4", ".mkv", ".avi", ".mov", ".mp3", ".wav", ".flac");
    private static final List<String> CODE = Arrays.asList(".java", ".py", ".js", ".html", ".css", ".cpp", ".c", ".json", ".xml");

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


        Path pdfFolder = drawerPath.resolve("Documents");
        Path imagesFolder = drawerPath.resolve("Images");
        Path zipFolder = drawerPath.resolve("Archives");
        Path exeFolder = drawerPath.resolve("Programs");
        Path designFolder = drawerPath.resolve("Design_3D");
        Path mediaFolder = drawerPath.resolve("Media");
        Path codeFolder = drawerPath.resolve("Code_Dev");

        List<Path> allFolders = Arrays.asList(pdfFolder, imagesFolder, zipFolder, exeFolder, designFolder, mediaFolder, codeFolder);
        for (Path folder : allFolders) {
            if (!Files.exists(folder)) Files.createDirectory(folder);
        }

        try(Stream<Path> stream = Files.list(downloadsPath)){
            List<Path> myfiles = stream.collect(Collectors.toList());
            for(Path filePaths : myfiles){
                if(Files.isRegularFile(filePaths)){
                    String fileName = filePaths.getFileName().toString();
                    int index = fileName.lastIndexOf(".");
                    String extension="";
                    if(index>0){
                        extension = fileName.substring(index).toLowerCase();
                    }
                    try{
                        Path target = null;

                        if (IMAGES.contains(extension)) {
                            target = imagesFolder;
                        } else if (DOCUMENTS.contains(extension)) {
                            target = pdfFolder;
                        } else if (ARCHIVES.contains(extension)) {
                            target = zipFolder;
                        } else if (EXECUTABLES.contains(extension)) {
                            target = exeFolder;
                        } else if (DESIGN_3D.contains(extension)) {
                            target = designFolder;
                        } else if (MEDIA.contains(extension)) {
                            target = mediaFolder;
                        } else if (CODE.contains(extension)) {
                            target = codeFolder;
                        }

                        if (target != null && Files.exists(target)) {
                            Path destination = getDest(target, fileName);
                            Files.move(filePaths, destination);
                            System.out.println("Moved: " + fileName + " -> " + target.getFileName());
                        }

                    }
                    catch (Exception e){
                        System.out.println("error in moving files");
                    }
                }
            }
            javax.swing.JOptionPane.showMessageDialog(null, "Organization Complete!");
        }
        catch (IOException e){
            System.out.println("error in reading files");
        }
    }
}
