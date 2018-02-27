public class Paths {

    private String filePath;

    public Paths(String f) {
        filePath = f;
    }

    public String toString(){
        String toReturn = filePath;
        return toReturn;
    }

    public String getFilePath(){
        return filePath;
    }

}
