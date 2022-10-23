package codeit.mongodbjar.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.PullImageResultCallback;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.function.Consumer;

import static java.lang.String.format;

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

    public static Consumer<DockerClient> pullImage(String image, String tag) {
        return (dockerClient) -> {
            try {
                log.info(format("Started pulling %s:%s", image, tag));

                dockerClient
                        .pullImageCmd(image)
                        .withTag(tag)
                        .exec(new PullImageResultCallback())
                        .awaitCompletion();

                log.info(format("Done pulling %s:%s", image, tag));
            } catch (InterruptedException e) {
                log.error("Exception while fetching the image: " + image);
                throw new RuntimeException(e);
            }
        };
    }


}
