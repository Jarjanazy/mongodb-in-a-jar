package codeit.mongodbjar.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.PortBinding;
import java.util.function.Function;
import static java.lang.String.format;
public class ContainerCreator {

    public static Function<DockerClient,CreateContainerResponse> createMongoDbContainer(ContainerCreationConfiguration configuration) {

        return (dockerClient) -> dockerClient
                .createContainerCmd("mongo")
                .withName(configuration.getContainerName())

                .withPortBindings(PortBinding.parse(format("%s:27017", configuration.getHostPort())))
                .exec();
    }
}
