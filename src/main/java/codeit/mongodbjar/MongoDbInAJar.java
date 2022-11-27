package codeit.mongodbjar;

import lombok.extern.slf4j.Slf4j;
import java.io.IOException;


@Slf4j
public class MongoDbInAJar {
    public static void main(String[] args) throws IOException {
        new MongoDBRunner().runMongoDB();
        keepThreadAlive();
    }

    private static void keepThreadAlive() {
        Object forever = new Object();
        synchronized (forever) {
            try { forever.wait(); } catch (InterruptedException ignore) {}
        }
    }

}
