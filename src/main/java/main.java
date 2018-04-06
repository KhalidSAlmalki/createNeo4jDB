import dataStructure.*;
import org.neo4j.driver.v1.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class main {


    public static connection con;
    public static void main(String arg[]){
        // establish the connection first
        con = new connection("bolt://localhost:7687", "testing1229", "123");
        System.out.println("staring...");

        parseJsonFile parse= new parseJsonFile("https://raw.githubusercontent.com/PalashSJain/JavaDocExtraction/master/all_documentation_3.json");

        // then parse the json file to create node upon the parsed data
        parse.parseJsonFile();

        // loop through the result

        List<Record> parsedData = parseJsonFile.getParsedJson();

        System.out.println("Json is parsed...");

        run(parsedData);

    }

    private static void run(List<Record> parsedData) {
        node node = new node();
        for (ListIterator<Record> iter = parsedData.listIterator(); iter.hasNext(); ) {


            extractData extractData = new extractData(iter.next().get(0));

            packageSummary packages = extractData.getPackageSummary();
            ArrayList<summary> classes = extractData.getClassesSummary();
            ArrayList<summary> interfaces = extractData.getInterfacesSummary();





//            for (summary c: interfaces) {
//                InterfaceSummary interfaceSummary =  (InterfaceSummary)c ;
//
//
//                node.makeRelationShipExtend("Interface",interfaceSummary.getAnExtends(),c.name);
//
//
//            }

            for (summary c: classes) {
                classSummary classSummary =  (classSummary)c ;

               extendSummary aExtends = classSummary.getExtends();

                node.makeRelationShipExtend("Class",classSummary.name,aExtends.packageName,aExtends.name);


            }

        // adding the return value



            // create all nodes
//            createAllNodes(node, packages, classes, interfaces);
//
//            node.makeRelationShipForClass(classes);
//
//            node.makeRelationShipForInterFace(interfaces);
//
//            createRelationClassMethod(node,classes);
//            createRelationInterfaceMethod(node,interfaces);
//




        }
    }

    private static void createAllNodes(node node, packageSummary packages, ArrayList<summary> classes, ArrayList<summary> interfaces) {
        // create all packages
        node.createNode("Package",packages);


        // create all classes
        node.createListNodes("Class",classes);

        // create all Interfaces

        node.createListNodes("Interface",interfaces);


        createClassMethod(node, classes);

        createInterfaceMethod(node, interfaces);
    }


    private static void createRelationInterfaceMethod(node node, ArrayList<summary> interfaces) {
        for (summary c: interfaces) {

            InterfaceSummary InterfaceSummary =  (InterfaceSummary)c ;


            node.makeRelationShipForMehtod(InterfaceSummary.getMethods(),"Interface",InterfaceSummary.name);



        }
    }

    private static void createRelationClassMethod(node node, ArrayList<summary> classes) {
        for (summary c: classes) {

            classSummary classSummary =  (classSummary)c ;


            node.makeRelationShipForMehtod(classSummary.getMethods(),"Class",classSummary.name);



        }
    }
    private static void createInterfaceMethod(node node, ArrayList<summary> interfaces) {
        for (summary c: interfaces) {

            InterfaceSummary InterfaceSummary =  (InterfaceSummary)c ;

              node.createListNodes("Method",InterfaceSummary.getMethods());

             // node.makeRelationShipForMehtod(InterfaceSummary.getMethods(),"interface");



        }
    }

    private static void createClassMethod(node node, ArrayList<summary> classes) {
        for (summary c: classes) {

            classSummary classSummary =  (classSummary)c ;

            node.createListNodes("Method",classSummary.getMethods());

            //node.makeRelationShipForMehtod(classSummary.getMethods(),"class");



        }
    }


}
