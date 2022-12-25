package codeit.mongodbjar;

import codeit.mongodbjar.docker.configuration.VolumeConfiguration;
import com.github.dockerjava.api.DockerClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static codeit.mongodbjar.docker.creator.VolumeCreator.createVolume;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserConfiguration {
    private int port;
    private boolean persistent;

    public VolumeConfiguration createVolumeConfiguration(DockerClient dockerClient) {
        return createVolume("mongodb-in-jar", dockerClient);
    }

}
