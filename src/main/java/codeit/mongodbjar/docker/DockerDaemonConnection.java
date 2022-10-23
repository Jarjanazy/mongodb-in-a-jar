package codeit.mongodbjar.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.function.Consumer;

@Slf4j
public class DockerDaemonConnection {
    private DockerDaemonConnection() {}
    public static void runAgainstDaemon(Consumer<DockerClient> consumer) throws IOException {
        try (DockerClient dockerClient = DockerClientBuilder.getInstance().build()) {
            consumer.accept(dockerClient);
        } catch (IOException ioException) {
            log.error("Unable to connect to DockerClient", ioException);
            throw ioException;
        }
    }

}
