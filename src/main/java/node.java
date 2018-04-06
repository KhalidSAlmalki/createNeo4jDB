import dataStructure.InterfaceSummary;
import dataStructure.classSummary;
import dataStructure.summary;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringEscapeUtils.escapeJava;

public class node {


//    createNode
//    deleteNode
//    updateNode

    public void createNode(String nodeName, summary att) {

        try (Session session = connection.getDriver().session()) {

            session.writeTransaction(new TransactionWork<StatementResult>() {

                @Override
                public StatementResult execute(Transaction tx) {
                    String cypher = String.format(" CREATE (:%s %s)", nodeName, att.toString());


                    return tx.run(cypher);
                }
            });


        }

    }

    private void executeCyper(String cypher){
        try (Session session = connection.getDriver().session()) {

            session.writeTransaction(new TransactionWork<StatementResult>() {

                @Override
                public StatementResult execute(Transaction tx) {

                    return tx.run(cypher);
                }
            });


        }
    }
    public void createListNodes(String nodeName, List<summary>  atts){


        for (summary s:atts) {

            createNode(nodeName,s);



        }

    }



    //    private StatementResult makeRelationshipBtwinterfcaceExtend(final Transaction tx, final NodeDetails extend, final String interfaceName  )
//    {
//         //return p.name
//        String cypher =   String.format("MATCH (a:Interface {name:\"%s\"}) "+
//                "match (p)-[:package]-(c:Interface{name:\"%s\"}) where p.name = \"%s\""+
//                "MERGE (a)-[:extend]->(c)",interfaceName.trim(),extend.getName(),extend.getDesc() );
//
//      //  System.out.println(cypher);
//        StatementResult txx = tx.run(cypher);
//
//        return txx ;
//
//    }
   public void makeRelationShipExtend(String nodeName,List<summary>  atts, String classInerfcaeName){

       for (summary s:atts) {

           makeRelationShipExtend(nodeName,classInerfcaeName,s.packageName,s.name);

       }
   }

    public void makeRelationShipExtend(String NodeName,String className,String PackageName,String extend){

        String cypher =   String.format("MATCH (a:%s {name:\"%s\"}) "+
                "match (p)-[:package]-(c:%s{name:\"%s\"}) where p.name = \"%s\""+
                "MERGE (a)-[:extend]->(c)",NodeName,className,NodeName,extend,PackageName );

       // System.out.println(cypher);
       executeCyper(cypher);

    }


    public  void makeRelationShipRetrunValue(List<summary>  atts,String classOrInterface,String className){

        for (summary s:atts) {


           makeRelationShipRetrunValue(classOrInterface,className,s.name);

        }

    }
    private void makeRelationShipRetrunValue(String nodeName,String className,String methodName){
        String cypher =   String.format("MATCH (a:%s {name:\"%s\"}) "+
                "MATCH (b:Method {name:\"%s\"})"+
                "MERGE (a)<-[:returnValue]-(b)",nodeName,className,methodName );

        System.out.println(cypher);
       // executeCyper(cypher);

    }



    public void makeRelationShipForClass(List<summary>  atts){

        for (summary s:atts) {


            makeRelationShipForClassInterterfcae("Class",s.name,s.packageName);

        }

    }

    public void makeRelationShipForMehtod(List<summary>  atts,String classOrInterface,String classOrInterfaceName){

        for (summary s:atts) {


           makeRelationShipForMethod(s.name,s.packageName,classOrInterfaceName,classOrInterface);

        }

    }

    private void makeRelationShipForClassInterterfcae(String NodeName,String className,String PackageName){
        String cypher =   String.format("MATCH (c:%s {name:\"%s\"}) "+
                                 "MATCH (p:Package {name:\"%s\"})"+
                              "MERGE (p)-[:%s]->(c)"+
                                 "MERGE (p)<-[:package]-(c)",NodeName,className,PackageName,NodeName );

      //  System.out.println(cypher);
         executeCyper(cypher);
    }

    private void makeRelationShipForMethod(String mehtodName,String PackageName,String classOrInterfaceName,String classOrInterface){
        String cypher =   String.format("MATCH (c:Method {name:\"%s\"})where c.package = \"%s\" "+
                                        "MATCH (p:%s {name:\"%s\"})"+
                                         "MERGE (p)-[:method]->(c)"+
                                         "MERGE (p)<-[:%s]-(c)",mehtodName,PackageName,classOrInterface ,classOrInterfaceName,classOrInterface);


        executeCyper(cypher);
    }



    public void makeRelationShipForInterFace(ArrayList<summary> atts) {


        for (summary s:atts) {


            makeRelationShipForClassInterterfcae("Interface",s.name,s.packageName);

        }
    }
}
