package codeit.mongodbjar.docker;

import codeit.mongodbjar.docker.configuration.VolumeConfiguration;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Volume;

public class VolumeCreator {

    public static VolumeConfiguration createVolume(String volumeName, DockerClient dockerClient) {
        // creates a volume under /var/lib/docker/volumes/volumeName/_data
        dockerClient.createVolumeCmd().withName(volumeName).exec();

        String pathWeWantToMountFromContainer = "/data/db";
        Volume volume = new Volume(pathWeWantToMountFromContainer);

        return new VolumeConfiguration(volume, volumeName);
    }
}
