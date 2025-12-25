package com.myorg;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ecs.Cluster;
import software.amazon.awscdk.services.ecs.ContainerImage;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateService;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedTaskImageOptions;
import software.constructs.Construct;

public class FoodServiceStack extends Stack {

    public FoodServiceStack (final Construct scope, final String id, Cluster cluster) {
        this(scope,id,null,cluster);
    }

    public FoodServiceStack (final Construct scope, final String id,final StackProps props, Cluster cluster) {
        super(scope,id,props);
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
                                .build()
                )
                .memoryLimitMiB(1024)
                .publicLoadBalancer(true)
                .assignPublicIp(true)
                .build();
    }
}
