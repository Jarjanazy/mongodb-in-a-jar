package codeit.mongodbjar.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.PortBinding;

import java.util.function.Function;

public class ContainerCreator {

    public static Function<DockerClient,CreateContainerResponse> createMongoDbContainer(String containerName) {
        return (dockerClient) -> dockerClient
                .createContainerCmd("mongo")
                .withName(containerName)
                .withPortBindings(PortBinding.parse("9999:27017"))
                .exec();
    }
}
