package xyz.grinner.happyproxy.tool;

public class Extractors {
    public static Extractor<String> tdText(int index){
        return element -> {
            return element.child(index).text();
        };
    }
    public static Extractor<Integer> tdInteger(int index){
        return element -> {
            return Integer.parseInt(element.child(index).text());
        };
    }
}
