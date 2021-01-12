package com.outofmemory;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@SpringBootApplication
public class ParkingMania {
    private static EnvType env;

    public enum EnvType {
        LOCAL("local", "local"),
        SERVER("server", "server");

        private final String hostName;
        private final String profile;
        EnvType(String hostName, String profile) {
            this.hostName = hostName;
            this.profile = profile;
        }

        private static final Map<String, EnvType> hash = new HashMap<>();

        static {
            for (EnvType envType : values()) {
                hash.put(envType.hostName, envType);
            }
        }

        public static EnvType getEnvironment(String hostName) {
            return hash.getOrDefault(hostName, LOCAL);
        }

    }
    public static void main(String[] args) {
        String host = System.getenv("env");
        log.info("Host: " + host);
        env = EnvType.getEnvironment(host);
        String profile = env.profile;
        new SpringApplicationBuilder(ParkingMania.class).profiles(profile).build().run(args);
    }
}
