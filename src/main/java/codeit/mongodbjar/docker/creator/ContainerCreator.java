package codeit.mongodbjar.docker.creator;

import codeit.mongodbjar.docker.configuration.ContainerCreationConfiguration;
import codeit.mongodbjar.docker.configuration.VolumeConfiguration;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.*;
import java.util.function.Function;
import static java.lang.String.format;
import static java.util.Collections.singletonList;

public class ContainerCreator {

    public static Function<DockerClient,CreateContainerResponse> createMongoDbContainer(ContainerCreationConfiguration configuration) {
        var hostConfig = createHostConfig(configuration);

        return (dockerClient) ->
                createContainerCmd(configuration, hostConfig, dockerClient)
                .exec();
    }

    private static CreateContainerCmd createContainerCmd(ContainerCreationConfiguration configuration, HostConfig hostConfig, DockerClient dockerClient) {
        var containerCmd = dockerClient
                .createContainerCmd("mongo")
                .withName(configuration.getContainerName())
                .withHostConfig(hostConfig);

        if (configuration.isPersistent()) {
            return containerCmd.withVolumes(singletonList(configuration.getVolumeConfiguration().getVolume()));
        }
        return containerCmd;
    }


    public static HostConfig createHostConfig(ContainerCreationConfiguration configuration) {
        var hostConfig = HostConfig.newHostConfig()
                .withPortBindings(PortBinding.parse(format("%s:27017", configuration.getHostPort())));

        if (configuration.isPersistent()) {
            var volumeConfiguration = configuration.getVolumeConfiguration();

            Volume volume = volumeConfiguration.getVolume();
            String volumeName = volumeConfiguration.getVolumeName();

            hostConfig.withBinds(new Bind(volumeName, volume));
        }

        return hostConfig;
    }
}
