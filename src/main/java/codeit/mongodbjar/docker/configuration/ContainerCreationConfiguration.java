package codeit.mongodbjar.docker.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ContainerCreationConfiguration {
    private final int HostPort;
    private final String containerName;
    private final VolumeConfiguration volumeConfiguration;
}
