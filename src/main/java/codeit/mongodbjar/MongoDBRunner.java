package codeit.mongodbjar;

import codeit.mongodbjar.docker.DockerCommands;

import java.io.IOException;

import static codeit.mongodbjar.docker.DockerCommands.*;
import static codeit.mongodbjar.docker.DockerDaemonConnection.nonThrowingRunAgainstDaemon;
import static codeit.mongodbjar.docker.DockerDaemonConnection.runAgainstDaemon;

public class MongoDBRunner {

    public void runMongoDB(UserConfiguration userConfiguration) throws IOException {
        runAgainstDaemon(DockerCommands.pullImage("mongo", "latest"));

        runAgainstDaemon(removeContainerWithName("mongodb-in-a-jar"));

        var container = runAgainstDaemon(startMongoDbContainerFromImage(userConfiguration));

        Runtime
                .getRuntime()
                .addShutdownHook(new Thread(() -> nonThrowingRunAgainstDaemon(stopMongoDBContainer(container))));
    }


}
