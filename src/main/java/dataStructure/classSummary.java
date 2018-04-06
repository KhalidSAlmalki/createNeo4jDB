package dataStructure;

import org.apache.commons.lang.StringEscapeUtils;

import java.util.ArrayList;

public class classSummary extends summary{


    private ArrayList<summary> methods ;

    private ArrayList<implementSummary> Implements;
    private  extendSummary Extends;

    public classSummary(String name, String description, String href) {
        super(name, description, href);
        Implements = new ArrayList<>();

    }

    @Override
    public String toString() {

        return  String.format("{name:'%s', desc:\"%s\", href:'%s',package:'%s'}",this.name, StringEscapeUtils.escapeJava(descrption),href,packageName);
    }

    public ArrayList<summary>  getMethods() {
        return methods;
    }

    public void setMethods(ArrayList<summary> methods) {
        this.methods = methods;
    }

    public ArrayList<implementSummary> getImplements() {
        return Implements;
    }

    public void setImplements(ArrayList<implementSummary> anImplements) {
        Implements = anImplements;
    }

    public extendSummary getExtends() {
        return Extends;
    }

    public void setExtends(extendSummary anExtends) {
        Extends = anExtends;
    }
}
