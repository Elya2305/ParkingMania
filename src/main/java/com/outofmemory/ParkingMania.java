package com.outofmemory;

import com.outofmemory.utils.audit.SpringSecurityAuditorAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.Map;

// todo add default environment
@Slf4j
@EnableSwagger2
@EnableJpaAuditing(auditorAwareRef = SpringSecurityAuditorAware.NAME)
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
