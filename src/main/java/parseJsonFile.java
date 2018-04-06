import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

import java.util.List;

public class parseJsonFile {

    private String jsonUrl;

    public static List<Record> parsedJson;

    public parseJsonFile(String jsonUrl) {
        this.jsonUrl = jsonUrl;
    }


    // return thr parsed file using apoc library , hint, database needs to install apoc dependency to run the parser.

    public  void parseJsonFile() {

        try (Session session = connection.getDriver().session() ) {

            parsedJson = session.readTransaction( tx -> getJson(tx,jsonUrl)).list();
        }

    }

    public static List<Record> getParsedJson() {
        return parsedJson;
    }

    // Create a package  node
    private StatementResult getJson(final Transaction tx, final String url )
    {
        String cypher = String.format("Call apoc.load.json(\"%s\") yield value \n",url);
        return tx.run( cypher);
    }
}
