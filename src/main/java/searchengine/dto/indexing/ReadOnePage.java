package searchengine.dto.indexing;
import lombok.Getter;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;


@Getter
public class ReadOnePage extends RecursiveTask<OnePageLinks>{

    private static final String anchorTag = "a";

    private String indent; // "\t"+..+"\t"
    private String link; // адрес самой страницы

    public ReadOnePage(String parentIndent, String link) {
        this.indent = parentIndent + "\t";
        this.link = link;
    }


    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected OnePageLinks compute() {
        String pathFile1 = "Data/file2.txt";
        OnePageLinks result = new OnePageLinks();
        result.add(this.link);
        List<ReadOnePage> taskList = new ArrayList<>();
        Document document = null;
        try {
            document = readHtml(this.link);
            ArrayList<String> links = parseDocument(document);
            System.out.println(links);
            for (String l: links){
                if (checkLink(l)) {
                    ReadOnePage task = new ReadOnePage(this.indent
                            , this.link + makeSubLink(l));
                    //System.out.println(task);
                    task.fork();
                    taskList.add(task);
                    for (ReadOnePage t : taskList) {
                        result.addAll(t.join());
                        StringBuilder sb = new StringBuilder().append(t.getIndent()).append(t.getLink());
                        Files.writeString(Paths.get(pathFile1),sb.toString(), StandardOpenOption.APPEND);
                        //System.out.println(sb);
                    }
                }
            }
        } catch (InterruptedException | HttpStatusException exception){
            exception.printStackTrace();
        } catch (IOException ioException){
            ioException.getMessage();
        }
        return result;
    }


    public static ArrayList<String> parseDocument(Document document){
        ArrayList<String> links = new ArrayList<>();
        try {
            Elements elements1 = document.select(anchorTag);
            elements1.forEach(elem->links.add(elem.attr("href")));
        } catch (Exception exception){
            exception.printStackTrace();
        }
        return links ;
    }

    public static Document readHtml(String pathUrl) throws IOException, InterruptedException {
        Document document = Jsoup.connect(pathUrl).get();
        Thread.sleep(120);
        return document;
    }

    private static boolean checkLink(String link){

        String url = "https://www.larstornoe.com/";

        if ((link.equals("/"))||(link.contains("#"))||(link.contains("http"))||(link.equals(url))) {
            return false;
        }
        if (link.matches("/^(http|https|ftp)://([A-Z0-9][A-Z0-9_-]*(?:.[A-Z0-9][A-Z0-9_-]*)+):?(d+)?/?/i")) {
            return false;
        }
        return true;
    }

    private static String makeSubLink(String link){
        if (link.length()>1){
            if (link.charAt(0) == '/'){
                return link.substring(1);
            }
        }
        return link;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReadOnePage{");
        sb.append('\'').append(indent);
        sb.append(link).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
