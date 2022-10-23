package codeit.mongodbjar;

import codeit.mongodbjar.docker.DockerCommands;
import codeit.mongodbjar.docker.DockerDaemonConnection;
import java.io.IOException;

public class MongoDbInAJar {
    public static void main(String[] args) throws IOException {
        DockerDaemonConnection.runAgainstDaemon(DockerCommands.listAllImages());
    }

}
