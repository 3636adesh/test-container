package com.example.demo.config;


import com.example.demo.domain.Bookmark;
import com.example.demo.domain.BookmarkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final BookmarkRepository repo;

    public DataInitializer(BookmarkRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        long count = repo.count();
        if (count > 0) {
            System.out.println("Data already exists. Skipping data initialization");
            return;
        }
        repo.saveAll(getBookmarks());
    }

    private List<Bookmark> getBookmarks() {
        return List.of(
                new Bookmark("How (not) to ask for Technical Help?",
                        "https://sivalabs.in/how-to-not-to-ask-for-technical-help",
                        Instant.now()),
                new Bookmark("Announcing My SpringBoot Tips Video Series on YouTube",
                        "https://sivalabs.in/announcing-my-springboot-tips-video-series",
                        Instant.now()),
                new Bookmark("Kubernetes - Exposing Services to outside of Cluster using Ingress",
                        "https://sivalabs.in/kubernetes-ingress",
                        Instant.now()),
                new Bookmark("Kubernetes - Blue/Green Deployments",
                        "https://sivalabs.in/kubernetes-blue-green-deployments",
                        Instant.now()),
                new Bookmark("Kubernetes - Releasing a new version of the application using Deployment Rolling Updates",
                        "https://sivalabs.in/kubernetes-deployment-rolling-updates",
                        Instant.now()),
                new Bookmark("Getting Started with Kubernetes",
                        "https://sivalabs.in/getting-started-with-kubernetes",
                        Instant.now()),
                new Bookmark("Get Super Productive with Intellij File Templates",
                        "https://sivalabs.in/get-super-productive-with-intellij-file-templates",
                        Instant.now()),
                new Bookmark("Few Things I learned in the HardWay in 15 years of my career",
                        "https://sivalabs.in/few-things-i-learned-the-hardway-in-15-years-of-my-career",
                        Instant.now()),
                new Bookmark("All the resources you ever need as a Java & Spring application developer",
                        "https://sivalabs.in/all-the-resources-you-ever-need-as-a-java-spring-application-developer",
                        Instant.now()),
                new Bookmark("GoLang from a Java developer perspective",
                        "https://sivalabs.in/golang-from-a-java-developer-perspective",
                        Instant.now()),
                new Bookmark("Imposing Code Structure Guidelines using ArchUnit",
                        "https://sivalabs.in/impose-architecture-guidelines-using-archunit",
                        Instant.now()),
                new Bookmark("SpringBoot Integration Testing using TestContainers Starter",
                        "https://sivalabs.in/spring-boot-integration-testing-using-testcontainers-starter",
                        Instant.now()),
                new Bookmark("Creating Yeoman based SpringBoot Generator",
                        "https://sivalabs.in/creating-yeoman-based-springboot-generator",
                        Instant.now()),
                new Bookmark("Testing REST APIs using Postman and Newman",
                        "https://sivalabs.in/testing-rest-apis-with-postman-newman",
                        Instant.now()),
                new Bookmark("Testing SpringBoot Applications",
                        "https://sivalabs.in/spring-boot-testing",
                        Instant.now())
        );
    }

}
