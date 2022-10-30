package codeit.mongodbjar.docker;

import com.github.dockerjava.api.model.Volume;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ContainerCreationConfiguration {
    private final int HostPort;
    private final String containerName;
    private final Volume volume;
}
