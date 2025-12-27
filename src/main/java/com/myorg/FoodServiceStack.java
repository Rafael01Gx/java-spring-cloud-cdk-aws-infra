package com.myorg;

import software.amazon.awscdk.*;
import software.amazon.awscdk.services.applicationautoscaling.EnableScalingProps;
import software.amazon.awscdk.services.ecr.IRepository;
import software.amazon.awscdk.services.ecs.*;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateService;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedTaskImageOptions;
import software.amazon.awscdk.services.logs.LogGroup;
import software.constructs.Construct;

import java.util.Map;

import static software.amazon.awscdk.services.ecr.Repository.fromRepositoryName;

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

        IRepository iRepositorio = fromRepositoryName(this,"repositorio","img-pedidos-ms");

          ApplicationLoadBalancedFargateService foodFargateService = ApplicationLoadBalancedFargateService.Builder.create(this,"FoodService")
                .serviceName("food-service")
                .cluster(cluster)
                .cpu(512)
                .desiredCount(1) //Number of instances of the task definition to place and keep running
                .listenerPort(8080)
                .taskImageOptions(
                        ApplicationLoadBalancedTaskImageOptions.builder()
                                .image(//ContainerImage.fromRegistry("amazon/amazon-ecs-sample")
                                        ContainerImage.fromEcrRepository(iRepositorio, "latest")

                                )
                                .containerPort(8080)
                                .containerName("food-app")
                                .environment(env)
                                .logDriver(LogDriver.awsLogs(
                                        AwsLogDriverProps.builder()
                                                .logGroup(LogGroup.Builder
                                                        .create(this,"PedidosMsLogGroup")
                                                        .logGroupName("PedidosMsLog")
                                                        .removalPolicy(RemovalPolicy.DESTROY)
                                                        .build())
                                                .streamPrefix("PedidosMs")
                                                .build()
                                ))
                                .build()
                )
                .memoryLimitMiB(1024)
                .publicLoadBalancer(true)
                .assignPublicIp(true)
                .build();


        ScalableTaskCount scalableTarget = foodFargateService.getService().autoScaleTaskCount(
                EnableScalingProps.builder()
                        .minCapacity(1)
                        .maxCapacity(3)
                        .build());
        scalableTarget.scaleOnCpuUtilization("CpuScaling",
                CpuUtilizationScalingProps.builder()
                        .targetUtilizationPercent(80)
                        .scaleInCooldown(Duration.minutes(3))
                        .scaleOutCooldown(Duration.minutes(2))
                        .build());
        scalableTarget.scaleOnMemoryUtilization("MemoryScaling",
                MemoryUtilizationScalingProps.builder()
                        .targetUtilizationPercent(85)
                        .scaleInCooldown(Duration.minutes(3))
                        .scaleOutCooldown(Duration.minutes(2))
                        .build());
    }
}
