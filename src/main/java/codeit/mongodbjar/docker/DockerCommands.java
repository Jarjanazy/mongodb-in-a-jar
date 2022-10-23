package codeit.mongodbjar.docker;

import com.github.dockerjava.api.DockerClient;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.function.Consumer;

@Slf4j
public class DockerCommands {

    public static Consumer<DockerClient> listAllImages() {
        return (dockerClient) -> {
            var containers = dockerClient.listContainersCmd()
                    .withShowSize(true)
                    .withShowAll(true)
                    .withStatusFilter(Collections.singleton("exited"))
                    .exec();

            log.info(containers.get(0).getImage());
        };
    }


}
