package dataStructure;

import org.apache.commons.lang.StringEscapeUtils;

public class extendSummary extends summary{


    public void setClassName(String className) {
        this.className = className;
    }

    String className;
    public extendSummary(String name, String description, String href) {
        super(name, description, href);
    }

    @Override
    public String toString() {
        return  String.format("{name:'%s', package:\"%s\", href:'%s'}",name,StringEscapeUtils.escapeJava(packageName),href);

    }
}
