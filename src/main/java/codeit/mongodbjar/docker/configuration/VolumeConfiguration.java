package codeit.mongodbjar.docker.configuration;

import com.github.dockerjava.api.model.Volume;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class VolumeConfiguration {
    private final Volume volume;
    private final String volumeName;
}
