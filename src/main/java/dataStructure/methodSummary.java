package dataStructure;

import org.apache.commons.lang.StringEscapeUtils;
import org.neo4j.driver.v1.Value;

import java.util.ArrayList;

public class methodSummary extends summary{

    private  String returnValue;
    private ArrayList<parameterSummary> parameters;

    public methodSummary(String name, String description, String href,String returnValue) {
        super(name, description, href);
        parameters = new ArrayList<>();

        this.returnValue = cleanMethodReturnValue(returnValue);
    }


    public String getReturnValue() {
        return returnValue;
    }

    public ArrayList<parameterSummary> getParameters() {
        return parameters;
    }

    public void addParameters(Value pars) {


       for (int m = 0; m < pars.size() ; m++) {

          Value par = pars.get(m);


           parameterSummary parameterSummary = new parameterSummary(par.get("name").asString(),"",par.get("href").asString());
           parameterSummary.addPackageName(par.get("package").asString());

           parameters.add(parameterSummary);


       }

    }

    // TODO:  improve the method to handle all return value thatnis more than one word
    private  String cleanMethodReturnValue(String returnValue) {

    int index = returnValue.indexOf("static");
    if (index >= 0){

        if (index <= 10){

            return returnValue.substring(index+6);

        }else {
            System.out.println("failed "+returnValue);
            return null;

            }

    }
    return returnValue;
}
    @Override
    public String toString() {

        return  String.format("{name:'%s', desc:\"%s\", href:'%s', returnValue:'%s',package:'%s'}",name,StringEscapeUtils.escapeJava(descrption),href,returnValue,packageName);
    }
}
