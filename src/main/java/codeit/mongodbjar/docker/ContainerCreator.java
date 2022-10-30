package codeit.mongodbjar.docker;

import codeit.mongodbjar.docker.configuration.ContainerCreationConfiguration;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.*;
import java.util.function.Function;
import static java.lang.String.format;
import static java.util.Collections.singletonList;

public class ContainerCreator {

    public static Function<DockerClient,CreateContainerResponse> createMongoDbContainer(ContainerCreationConfiguration configuration) {
        Volume volume = configuration.getVolumeConfiguration().getVolume();
        String volumeName = configuration.getVolumeConfiguration().getVolumeName();

        return (dockerClient) -> dockerClient
                .createContainerCmd("mongo")
                .withName(configuration.getContainerName())
                .withVolumes(singletonList(volume))
                .withBinds(new Bind(volumeName, volume))
                .withPortBindings(PortBinding.parse(format("%s:27017", configuration.getHostPort())))
                .exec();
    }
}
