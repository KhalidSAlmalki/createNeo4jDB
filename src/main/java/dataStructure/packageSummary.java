package dataStructure;

import org.apache.commons.lang.StringEscapeUtils;

public class packageSummary extends summary {


    public packageSummary(String name, String description, String href) {
        super(name, description, href);
    }


    @Override
    public String toString() {

        return String.format("{name:'%s', desc:\"%s\", href:'%s'}", this.name, StringEscapeUtils.escapeJava(descrption), href);
    }
}