package codeit.mongodbjar.docker;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ContainerCreationConfiguration {
    private final int HostPort;
    private final String containerName;
}
