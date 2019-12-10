package xyz.grinner.happyproxy.tool;

import org.jsoup.nodes.Element;

@FunctionalInterface
public interface Extractor<T> {
    T extract(Element element);
}
