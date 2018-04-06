import dataStructure.*;
import org.neo4j.driver.v1.Value;

import java.util.ArrayList;

public class extractData {

    private Value value;

    public extractData(Value value){

        this.value = value;
    }

    public packageSummary getPackageSummary() {


        return new packageSummary(value.get("name").asString(),value.get("description").asString(),value.get("href").asString());
    }

    public ArrayList<summary> getClassesSummary(){
        ArrayList<summary> list = new  ArrayList();


          Value classes = value.get("Class Summary");

        for (int i = 0; i <classes.size() ; i++) {

            Value Class = classes.get(i);

            classSummary  classSummary = new classSummary(Class.get("name").asString(),Class.get("description").asString(),Class.get("href").asString());

            classSummary.setMethods(getMethodsSummary(Class));

            classSummary.setImplements(getImplementClasses(Class.get("Implements")));

            classSummary.addPackageName(getPackageSummary().name);

            classSummary.setExtends((extendSummary) getExtendClasses(Class));
            list.add(classSummary);
        }


        return list;
    }

    public ArrayList<summary> getInterfacesSummary() {


        ArrayList<summary> list = new  ArrayList();


        Value interfaces = value.get("Interface Summary");

        for (int i = 0; i <interfaces.size() ; i++) {

            Value Interface = interfaces.get(i);

            InterfaceSummary  InterfaceSummary = new InterfaceSummary(Interface.get("name").asString(),Interface.get("description").asString(),Interface.get("href").asString());

            InterfaceSummary.setMethods(getMethodsSummary(Interface));

            InterfaceSummary.setAnExtends(getInterfaceExtends(Interface));

            InterfaceSummary.addPackageName(getPackageSummary().name);

            list.add(InterfaceSummary);
        }


        return list;
    }


    private ArrayList<summary> getMethodsSummary(Value aClass) {



        Value methodes = aClass.get("Method Summary");

        ArrayList<summary> list = new  ArrayList();

        for (int i = 0; i <methodes.size() ; i++) {

            Value method = methodes.get(i);

            Value returnValue = method.get("Modifier and Type").get("name");

            methodSummary methodSummary = new methodSummary(method.get("name").asString(),method.get("description").asString(),method.get("href").asString(),returnValue.asString());

            methodSummary.addPackageName(getPackageSummary().name);

            methodSummary.addParameters(method.get("parameters"));



            list.add(methodSummary);
        }


        return list;
    }

    public summary getExtendClasses(Value aclass) {


        Value extend = aclass.get("Extends");
        if (!extend.isNull()){
            extendSummary extendSummary = new extendSummary(extend.get("name").asString(),"",extend.get("href").asString());

            extendSummary.addPackageName(extend.get("package").asString());


            return extendSummary;
        }else {
            return null;
        }

    }

    public ArrayList<implementSummary> getImplementClasses(Value classes) {



        ArrayList<implementSummary> list = new  ArrayList();




            for (int i = 0; i <classes.size() ; i++) {

                Value Implement = classes.get(i);

                implementSummary  implementSummary = new implementSummary(Implement.asString(),"","");



                list.add(implementSummary);
            }




        return list;

    }

    public ArrayList<summary> getInterfaceExtends(Value interfaces) {

        ArrayList<summary> list = new  ArrayList();

        Value anExtends = interfaces.get("extends");

        if (!anExtends.isNull()){


            for (int i = 0; i <anExtends.size() ; i++) {

                Value anExtend = anExtends.get(i);

                extendSummary extendSummary = new extendSummary(anExtend.get("name").asString(),"",anExtend.get("href").asString());

                extendSummary.addPackageName(anExtend.get("package").asString());


                list.add(extendSummary);
            }

        }


        return list;
    }
}
