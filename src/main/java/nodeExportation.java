//import org.apache.commons.lang.StringEscapeUtils;
//import org.neo4j.driver.v1.*;
//
//import java.util.ListIterator;
//
//import static org.neo4j.driver.v1.Values.parameters;
//
//public class nodeExportation
//{
//
//
//
//    // Create a package  node
//    private StatementResult addPackage(final Transaction tx, final NodeDetails nodeDetails)
//    {
//        return tx.run( "CREATE (p:Package"+ nodeDetails.toString()+")");
//    }
//
//    // Create a interface  node
//    private StatementResult addInterFace(final Transaction tx, final NodeDetails nodeDetails)
//    {
//        return tx.run( "CREATE (p:Interface"+ nodeDetails.toString()+")");
//    }
//
//    // Create a class  node
//    private StatementResult addClass(final Transaction tx, final NodeDetails nodeDetails)
//    {
//        return tx.run( "CREATE (p:Class"+ nodeDetails.toString()+")");
//    }
//
//    // Create a interface  node
//    private StatementResult addMethod(final Transaction tx, final NodeDetails nodeDetails)
//    {
//        return tx.run( "CREATE (p:Method"+ nodeDetails.getMethodDetails()+")");
//    }
//    // Create a relationship between two package and class.
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
//
//    // Create a relationship between two package and class.
//    private StatementResult makeExtendRelationshipBtwClassInterface(final Transaction tx, final NodeDetails extend, final String ClassName  )
//    {
//        //return p.name
//        String cypher =   String.format("MATCH (a:Class {name:\"%s\"}) "+
//                "match (p)-[:package]-(c:Class{name:\"%s\"}) where p.name = \"%s\""+
//                "MERGE (a)-[:extend]->(c)",ClassName.trim(),extend.getName(),extend.getDesc() );
//
//        System.out.println(cypher);
//        StatementResult txx = tx.run(cypher);
//
//        return txx ;
//
//    }
//
//    private StatementResult makeImplementingRelationshipClassInterface(final Transaction tx, final String className, final String interfaceName  )
//    {
//        //return p.name
//        String cypher =   String.format("MATCH (c:Interface {name:\"%s\"}) "+
//                "MATCH (a:Class {name:\"%s\"})"+
//                "MERGE (a)-[:implements]->(c)",interfaceName.trim(),className.trim() );
//
//        System.out.println(cypher);
//        StatementResult txx = tx.run(cypher);
//
//        return txx ;    }
//
//    // Create a relationship between two package and class.
//    private StatementResult makeRelationshipBtwPackageClass( final Transaction tx, final String packageName, final String className )
//    {
//        StatementResult txx = tx.run( "MATCH (a:Package {name: $packageName}) " +
//                        "MATCH (b:Class {name: $className}) " +
//                        "MERGE (a)-[:class]->(b)"+
//                        "MERGE (a)<-[:package]-(b)",
//                parameters( "packageName", packageName, "className", className ) );
//
//        return txx ;
//    }
//    // Create a relationship between two package and interface.
//    private StatementResult makeRelationshipBtwPackageInterface( final Transaction tx, final String packageName, final String interfaceName )
//    {
//        StatementResult txx = tx.run( "MATCH (a:Package {name: $packageName}) " +
//                        "MATCH (b:Interface {name: $interfaceName}) " +
//                        "MERGE (a)-[:Interface]->(b)"+
//                        "MERGE (a)<-[:package]-(b)",
//                parameters( "packageName", packageName, "interfaceName", interfaceName ) );
//
//        return txx ;
//    }
//    // Create a relationship between two package and class.
//    private StatementResult makeRelationshipBtwPClassMethod( final Transaction tx, final String className, final String methodName)
//    {
//        StatementResult txx = tx.run( "MATCH (a:Class {name: $className}) " +
//                        "MATCH (b:Method {name: $methodName}) " +
//                        "CREATE (a)-[:method]->(b)"+
//                        "MERGE (a)<-[:class]-(b)",
//                parameters( "methodName", methodName, "className", className ) );
//
//        return txx ;
//    }
//
//    private StatementResult makeRelationshipBtwPClassReturnMethod( final Transaction tx, final String className, final String methodName )
//    {
//        String cypher =   String.format("MATCH (a:Class {name:\"%s\"}) "+
//                "MATCH (b:Method {name:\"%s\"})"+
//                "MERGE (a)<-[:returnValue]-(b)",className.trim(),methodName );
//
//        StatementResult txx = tx.run(cypher);
//
//        return txx ;
//    }
//    private StatementResult makeRelationshipBtwPMethodWithpParameters( final Transaction tx, final String className, final String methodName )
//    {
//        StatementResult txx = tx.run( "MATCH (a:Class {name: $className}) " +
//                        "MATCH (b:Method {name: $methodName}) " +
//                        "CREATE (a)<-[:parameter]-(b)",
//                parameters( "methodName", methodName, "className", className ) );
//
//        return txx ;
//    }
//
//
//    // Create a relationship between two package and class.
//    private StatementResult makeRelationshipBtwInterfaceMethod( final Transaction tx, final String className, final String methodName)
//    {
//        StatementResult txx = tx.run( "MATCH (a:Interface {name: $className}) " +
//                        "MATCH (b:Method {name: $methodName}) " +
//                        "CREATE (a)-[:method]->(b)"+
//                        "MERGE (a)<-[:Interface]-(b)",
//                parameters( "methodName", methodName, "className", className ) );
//
//        return txx ;
//    }
//
//    private StatementResult makeRelationshipBtwInterfcaeReturnMethod( final Transaction tx, final String className, final String methodName )
//    {
//
//
//        String cypher =   String.format("MATCH (a:Interface {name:\"%s\"}) "+
//                "MATCH (b:Method {name:\"%s\"})"+
//                "MERGE (a)<-[:returnValue]-(b)",className.trim(),methodName );
//
//        StatementResult txx = tx.run(cypher);
//
//        return txx ;
//    }
//    private StatementResult makeRelationshipBtwPMethodWithpParametersInterfcae( final Transaction tx, final String className, final String methodName )
//    {
//        StatementResult txx = tx.run( "MATCH (a:Interface {name: $className}) " +
//                        "MATCH (b:Method {name: $methodName}) " +
//                        "CREATE (a)<-[:parameter]-(b)",
//                parameters( "methodName", methodName, "className", className ) );
//
//        return txx ;
//    }
//
////
//
//
//    public StatementResult test() {
//
//        Object node;
//        try (Session session = driver.session()) {
//
//
//            node = session.writeTransaction(new TransactionWork<Object>() {
//
//
//                @Override
//                public Object execute(Transaction tx) {
//
//
//                    return getJson(tx, "");
//                }
//            });
//
//
//        }
//
//        return (StatementResult) node;
//
//    }
//
//
//    public static void main( String... args ) throws Exception
//    {
//        try ( nodeExportation nodeexportation = new nodeExportation( "bolt://localhost:7687", "testing1229", "123" ) )
//        {
//          //  nodeexportation.createNodes( "hello, world" );
//
//            StatementResult re = nodeexportation.test();
//
//
//            for (ListIterator<Record> iter = re.list().listIterator(); iter.hasNext(); ) {
//                Record element = iter.next();
//
//                // package details
//
//                Value packageName = element.get(0).get("name");
//                Value packageDesc = element.get(0).get("description");
//                Value packageHref = element.get(0).get("href");
//
//
//                NodeDetails packageDetail = new NodeDetails(packageName.asString(),packageDesc.asString(),packageHref.asString());
//
//               // System.out.println("package "+packageDetail.toString());
//
//                // creating node package
//
////                try (Session session = driver.session() ) {
////
////                    session.writeTransaction( tx -> nodeexportation.addPackage(tx,packageDetail));
////
////                }
//
//                // getting class summary
//                Value classes = element.get(0).get("Class Summary");
//
//
//                for (int i = 0; i <classes.size() ; i++) {
//
//                    Value className = classes.get(i).get("name");
//                    Value classDesc = classes.get(i).get("description");
//                    Value classHref = classes.get(i).get("href");
//
//                    NodeDetails classDetail = new NodeDetails(className.asString(),classDesc.asString(),classHref.asString());
//
//                    // create class node
//
////                    try (Session session = driver.session() ) {
////
////                        session.writeTransaction( tx -> nodeexportation.addClass(tx,classDetail));
////
////                        session.writeTransaction( tx -> nodeexportation.makeRelationshipBtwPackageClass(tx,packageName.asString(),className.asString()));
////
////
////
////                    }
//
//               //     System.out.println("class -> "+classDetail.toString());
//
//
////                    // get extend
//                    Value extendss = classes.get(i).get("Extends");
//
//
//                    if (!extendss.isNull()){
//
//                        Value Name = extendss.get("name");
//                        Value href = extendss.get("href");
//                        Value Package = extendss.get("package");
//
//
//                        NodeDetails extendDetails = new NodeDetails(Name.asString(),Package.asString(),href.asString());
//
//
//
////                        try (Session session = driver.session() ) {
////
////
////                            session.writeTransaction( tx -> nodeexportation.makeExtendRelationshipBtwClassInterface(tx,extendDetails,className.asString()));
////
////
////
////                        }
//                    }
//
//                    // get implementation
//                    Value imp = classes.get(i).get("Implements");
//
//
//                    if (!imp.isNull()){
//
//                        for (int im = 0; im <imp.size() ; im++) {
//                            Value Name = imp.get(im);
//                            System.out.println(Name.asString().trim());
//
//
//                        try (Session session = driver.session() ) {
//
//
//                            session.writeTransaction( tx -> nodeexportation.makeImplementingRelationshipClassInterface(tx,className.asString(),Name.asString()));
//
//
//
//                        }
//
//                        }
//
//                    }
//
//                    Value methods = classes.get(i).get("Method Summary");
//
//                    // getting class summary methods
//
//                    for (int x = 0; x <methods.size() ; x++) {
//
//
//                        Value methodName = methods.get(x).get("name");
//                        Value returnValue = methods.get(x).get("Modifier and Type").get("name");
//                        Value desc = methods.get(x).get("description");
//
//
//                        NodeDetails methodDetail = new NodeDetails(methodName.asString(),returnValue.asString(),desc.asString(),classHref.asString());
//
//                        // creating method node
//
////                        try (Session session = driver.session() ) {
////
////                            session.writeTransaction( tx -> nodeexportation.addMethod(tx,methodDetail));
////
////                            session.writeTransaction( tx -> nodeexportation.makeRelationshipBtwPClassMethod(tx,className.asString(),methodName.asString()));
////
////
////
////                        }
//
//                      //  System.out.println("    - method -- > "+methodDetail.getMethodDetails());
//
//
//                         String returnvalue = cleanMethodReturnValue(returnValue.asString());
//
//                         if (returnvalue != null){
//
//                             // make the relationship with class which equal the name of return value  , if not having node
//
//
//                             // creating method node
//                             try (Session session = driver.session() ) {
//
//                           //      session.writeTransaction( tx -> nodeexportation.makeRelationshipBtwPClassReturnMethod(tx,returnvalue,methodName.asString()));
//
//
//                             }
//
//                         //    System.out.println(returnvalue);
//
//                         }
//
//                        // loop through parameters
//
//                        Value parameters = methods.get(x).get("parameters");
//
//                        for (int m = 0; m < parameters.size() ; m++) {
//
//                            Value classNameOfParameters = parameters.get(m).get("name");
//
////                            try (Session session = driver.session() ) {
////
////                                session.writeTransaction( tx -> nodeexportation.makeRelationshipBtwPMethodWithpParameters(tx,classNameOfParameters.asString(),methodName.asString()));
////
////
////                            }
//
//                            // make the relationship with class which equal the name of parameters  , if not having node
//
//                         //       System.out.println("                    - parameter -- > "+classNameOfParameters);
//
//
//
//
//                        }
//
//                    }
//                }
//
//
//
//
//                // getting interfaces summary
//
//                Value interfaces = element.get(0).get("Interface Summary");
//
//
//
//
//                for (int in = 0; in <interfaces.size() ; in++) {
//
//                    String interfaceName = interfaces.get(in).get("name").asString();
//                    String interfaceDesc = interfaces.get(in).get("description").asString();
//                    String interfaceHref = interfaces.get(in).get("href").asString();
//
//
//                    NodeDetails interfaceDetail = new NodeDetails(interfaceName,interfaceDesc,interfaceHref);
//
//                    // create class node
//
////                    try (Session session = driver.session() ) {
////
////                        session.writeTransaction( tx -> nodeexportation.addInterFace(tx,interfaceDetail));
////
////                        session.writeTransaction( tx -> nodeexportation.makeRelationshipBtwPackageInterface(tx,packageName.asString(),interfaceName));
////
////
////
////                    }
//
//                    //     System.out.println("class -> "+classDetail.toString());
//
//                    // get extend
//                    Value extendss = interfaces.get(in).get("extends");
//
//                    if (!extendss.isNull()){
//                        for (int e = 0; e <extendss.size() ; e++) {
//
//                            Value Name = extendss.get(e).get("name");
//                            Value href = extendss.get(e).get("href");
//                            Value Package = extendss.get(e).get("package");
//
//
//                            NodeDetails extendDetails = new NodeDetails(Name.asString(),Package.asString(),href.asString());
//
//
//
////                        try (Session session = driver.session() ) {
////
////
////                            session.writeTransaction( tx -> nodeexportation.makeRelationshipBtwinterfcaceExtend(tx,extendDetails,interfaceName));
////
////
////
////                        }
//
//
//                        }
//                    }
//
//
//                    Value methods = interfaces.get(in).get("Method Summary");
//
//                    // getting class summary methods
//
//                    for (int x = 0; x <methods.size() ; x++) {
//
//
//                        String methodName = methods.get(x).get("name").asString();
//                        String returnValue = methods.get(x).get("Modifier and Type").get("name").asString();
//                        String desc = methods.get(x).get("description").asString();
//
//
//                        NodeDetails methodDetail = new NodeDetails(methodName,returnValue,desc,interfaceHref);
//
//                        // creating method node
////                        try (Session session = driver.session() ) {
////
////                            session.writeTransaction( tx -> nodeexportation.addMethod(tx,methodDetail));
////
////                            session.writeTransaction( tx -> nodeexportation.makeRelationshipBtwInterfaceMethod(tx,interfaceName,methodName));
////
////
////
////                        }
//
//                        //  System.out.println("    - method -- > "+methodDetail.getMethodDetails());
//
//
//                        String returnvalue = cleanMethodReturnValue(returnValue);
//
//                        if (returnvalue != null){
//
//                            // make the relationship with class which equal the name of return value  , if not having node
//
//
//                            // creating method node
//                            try (Session session = driver.session() ) {
//
//                              //  session.writeTransaction( tx -> nodeexportation.makeRelationshipBtwInterfcaeReturnMethod(tx,returnvalue,methodName));
//
//
//                            }
//
//                            //    System.out.println(returnvalue);
//
//                        }
//
//                        // loop through parameters
//
//                        Value parameters = methods.get(x).get("parameters");
//
//                        for (int m = 0; m < parameters.size() ; m++) {
//
//                            Value classNameOfParameters = parameters.get(m).get("name");
//
////                            try (Session session = driver.session() ) {
////
////                                session.writeTransaction( tx -> nodeexportation.makeRelationshipBtwPMethodWithpParametersInterfcae(tx,classNameOfParameters.asString(),methodName));
////
////
////                            }
//
//                            // make the relationship with class which equal the name of parameters  , if not having node
//
//                            //       System.out.println("                    - parameter -- > "+classNameOfParameters);
//
//
//
//
//                        }
//
//                    }
//                }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//            }
//
//        }catch (Exception x){
//            x.printStackTrace();
//        }
//    }
//
//    private static String cleanMethodReturnValue(String returnValue) {
//
//        int index = returnValue.indexOf("static");
//        if (index >= 0){
//
//            if (index <= 10){
//
//                return returnValue.substring(index+6);
//
//            }else {
//                System.out.println("failed "+returnValue);
//                return null;
//
//                }
//
//        }
//        return null;
//    }
//
//
//}