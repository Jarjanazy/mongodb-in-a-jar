package codeit.mongodbjar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;


@Slf4j
public class MongoDbInAJar {
    public static void main(String[] args) throws IOException {

        String configurationFilePath = args[0];

        var userConfiguration = getUserConfiguration(configurationFilePath);

        new MongoDBRunner().runMongoDB(userConfiguration);
        keepThreadAlive();
    }

    private static UserConfiguration getUserConfiguration(String configurationFilePath) throws IOException {
        var mapper = new ObjectMapper(new YAMLFactory());

        return mapper.readValue(new File(configurationFilePath), UserConfiguration.class);
    }

    private static void keepThreadAlive() {
        Object forever = new Object();
        synchronized (forever) {
            try { forever.wait(); } catch (InterruptedException ignore) {}
        }
    }

}
