package codeit.mongodbjar.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.*;
import java.util.function.Function;
import static java.lang.String.format;
import static java.util.Collections.singletonList;

public class ContainerCreator {

    public static Function<DockerClient,CreateContainerResponse> createMongoDbContainer(ContainerCreationConfiguration configuration) {
        return (dockerClient) -> dockerClient
                .createContainerCmd("mongo")
                .withName(configuration.getContainerName())
                .withVolumes(singletonList(configuration.getVolume()))
                .withBinds(new Bind("mongodb-in-jar", configuration.getVolume()))
                .withPortBindings(PortBinding.parse(format("%s:27017", configuration.getHostPort())))
                .exec();
    }
}
