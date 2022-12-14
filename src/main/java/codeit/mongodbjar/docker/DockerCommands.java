package codeit.mongodbjar.docker;

import codeit.mongodbjar.UserConfiguration;
import codeit.mongodbjar.docker.configuration.ContainerCreationConfiguration;
import codeit.mongodbjar.docker.creator.ContainerCreator;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.PullImageResultCallback;
import com.github.dockerjava.api.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;

import static codeit.mongodbjar.docker.creator.VolumeCreator.createVolume;
import static java.lang.String.format;

@Slf4j
public class DockerCommands {

    public static Consumer<DockerClient> listAllImages() {
        return dockerClient -> {
            var containers = dockerClient.listContainersCmd()
                    .withShowSize(true)
                    .withShowAll(true)
                    .withStatusFilter(Collections.singleton("exited"))
                    .exec();
            containers.forEach(container -> log.info(container.getImage()));
        };
    }

    public static Consumer<DockerClient> pullImage(String image, String tag) {
        return (dockerClient) -> {
            try {
                log.info(format("Started pulling %s:%s", image, tag));

                dockerClient
                        .pullImageCmd(image)
                        .withTag(tag)
                        .exec(new PullImageResultCallback())
                        .awaitCompletion();

                log.info(format("Done pulling %s:%s", image, tag));
            } catch (InterruptedException e) {
                log.error("Exception while fetching the image: " + image);
                throw new RuntimeException(e);
            }
        };
    }

    public static Consumer<DockerClient> removeContainerWithName(String containerName) {
        return dockerClient -> {
            try {
                dockerClient.inspectContainerCmd(containerName).exec();
                dockerClient.removeContainerCmd(containerName).exec();
            } catch (NotFoundException e) {
                log.info("Container doesn't exist: " + containerName);
            }
            log.info("Removed container: " + containerName);
        };
    }

    public static Function<DockerClient,CreateContainerResponse> startMongoDbContainerFromImage(UserConfiguration userConfiguration) {
        return dockerClient -> {
            log.info("Starting MongoDB container");

            var configuration = new ContainerCreationConfiguration(userConfiguration, "mongodb-in-a-jar", dockerClient);

            var container = ContainerCreator
                    .createMongoDbContainer(configuration)
                    .apply(dockerClient);

            dockerClient.startContainerCmd(container.getId()).exec();

            log.info("Done Starting MongoDB container");

            return container;
        };
    }

    public static Consumer<DockerClient> stopMongoDBContainer(CreateContainerResponse container) {
        return dockerClient -> {
            log.info("Stopping MongoDB container");

            dockerClient.stopContainerCmd(container.getId()).exec();

            log.info("Done stopping MongoDB container");
        };
    }


}
