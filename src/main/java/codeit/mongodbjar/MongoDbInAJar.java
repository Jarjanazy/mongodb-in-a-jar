package codeit.mongodbjar;

import codeit.mongodbjar.docker.DockerCommands;
import java.io.IOException;
import static codeit.mongodbjar.docker.DockerCommands.removeContainerWithName;
import static codeit.mongodbjar.docker.DockerCommands.startAndStopMongoDbContainerFromImage;
import static codeit.mongodbjar.docker.DockerDaemonConnection.runAgainstDaemon;

public class MongoDbInAJar {
    public static void main(String[] args) throws IOException {
        runAgainstDaemon(DockerCommands.pullImage("mongo", "latest"));

        runAgainstDaemon(removeContainerWithName("mongodb-in-a-jar"));

        var container = runAgainstDaemon(startAndStopMongoDbContainerFromImage("mongodb-in-a-jar"));

    }

}
