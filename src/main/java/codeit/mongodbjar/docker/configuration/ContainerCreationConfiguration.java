package codeit.mongodbjar.docker.configuration;

import codeit.mongodbjar.UserConfiguration;
import com.github.dockerjava.api.DockerClient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
public class ContainerCreationConfiguration {
    private final int hostPort;
    private final String containerName;
    private final Optional<VolumeConfiguration> volumeConfiguration;

    public ContainerCreationConfiguration(UserConfiguration userConfiguration, String containerName, DockerClient dockerClient) {
        this.hostPort = userConfiguration.getPort();
        this.containerName = containerName;
        volumeConfiguration = createVolumeConfiguration(userConfiguration, dockerClient);
    }

    public boolean isPersistent() {
        return volumeConfiguration.isPresent();
    }

    public VolumeConfiguration getVolumeConfiguration() {
        return volumeConfiguration.orElseThrow();
    }

    private Optional<VolumeConfiguration> createVolumeConfiguration(UserConfiguration userConfiguration, DockerClient dockerClient) {
        if (userConfiguration.isPersistent())
            return Optional.of(userConfiguration.createVolumeConfiguration(dockerClient));
        else
            return Optional.empty();
    }
}
