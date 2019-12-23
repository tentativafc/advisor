package br.com.ortiz.advisor.advices;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.ortiz.security", "br.com.ortiz.advisor"})
//@EnableDiscoveryClient
//@RestController
public class Main {

//    @Autowired
//    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

//    @RequestMapping("/service-instances/{applicationName}")
//    public List<ServiceInstance> serviceInstancesByApplicationName(
//            @PathVariable String applicationName) {
//        return this.discoveryClient.getInstances(applicationName);
//    }
}
