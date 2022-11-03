package codeit.mongodbjar;

import codeit.mongodbjar.docker.DockerCommands;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static codeit.mongodbjar.docker.DockerCommands.*;
import static codeit.mongodbjar.docker.DockerDaemonConnection.runAgainstDaemon;
import static codeit.mongodbjar.docker.DockerDaemonConnection.nonThrowingRunAgainstDaemon;

@Slf4j
public class MongoDbInAJar {
    public static void main(String[] args) throws IOException {
        runAgainstDaemon(DockerCommands.pullImage("mongo", "latest"));

        runAgainstDaemon(removeContainerWithName("mongodb-in-a-jar"));

        var container = runAgainstDaemon(startMongoDbContainerFromImage());

        Runtime
                .getRuntime()
                .addShutdownHook(new Thread(() -> nonThrowingRunAgainstDaemon(stopMongoDBContainer(container))));
    }

}
