package dataStructure;

import org.apache.commons.lang.StringEscapeUtils;

import java.util.ArrayList;

public class InterfaceSummary extends summary{

    private ArrayList<summary> methods ;

    private ArrayList<summary> anExtends ;


    public InterfaceSummary(String name, String description, String href) {
        super(name, description, href);
    }

    @Override
    public String toString() {

        return  String.format("{name:'%s', desc:\"%s\", href:'%s',package:'%s'}",this.name, StringEscapeUtils.escapeJava(descrption),href,packageName);
    }


    public ArrayList<summary> getMethods() {
        return methods;
    }

    public void setMethods(ArrayList<summary> methods) {
        this.methods = methods;
    }


    public ArrayList<summary> getAnExtends() {
        return anExtends;
    }

    public void setAnExtends(ArrayList<summary> anExtends) {
        this.anExtends = anExtends;
    }
}
