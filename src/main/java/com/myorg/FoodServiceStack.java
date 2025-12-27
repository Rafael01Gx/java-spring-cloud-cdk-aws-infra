package com.myorg;

import software.amazon.awscdk.Fn;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ecs.Cluster;
import software.amazon.awscdk.services.ecs.ContainerImage;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateService;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedTaskImageOptions;
import software.constructs.Construct;

import java.util.Map;

public class FoodServiceStack extends Stack {

    public FoodServiceStack (final Construct scope, final String id,final Cluster cluster) {
        this(scope,id,null,cluster);
    }

    public FoodServiceStack (final Construct scope, final String id,final StackProps props,final Cluster cluster) {
        super(scope,id,props);

        Map<String,String> env = Map.of("SPRING_DATASOURCE_URL",
                "jdbc:mysql://"+ Fn.importValue("pedidos-db-endpoint")+":3306/pedidos-ms?createDatabaseIfNotExist=true",
                "SPRING_DATASOURCE_USERNAME", "admin",
                "SPRING_DATASOURCE_PASSWORD", Fn.importValue("pedidos-db-senha"));

        ApplicationLoadBalancedFargateService.Builder.create(this,"FoodService")
                .serviceName("food-service")
                .cluster(cluster)
                .cpu(512)
                .desiredCount(1)
                .listenerPort(8080)
                .taskImageOptions(
                        ApplicationLoadBalancedTaskImageOptions.builder()
                                .image(ContainerImage.fromRegistry("amazon/amazon-ecs-sample"))
                                .containerPort(8080)
                                .containerName("food-app")
                                .environment(env)
                                .build()
                )
                .memoryLimitMiB(1024)
                .publicLoadBalancer(true)
                .assignPublicIp(true)
                .build();
    }
}
