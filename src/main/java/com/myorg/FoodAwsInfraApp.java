package com.myorg;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.Vpc;

import java.util.Arrays;

public class FoodAwsInfraApp {
    public static void main(final String[] args) {
        App app = new App();

        VpcStack vpcStack = new VpcStack(app, "Vpc");

        ClusterStack clusterStack = new ClusterStack(app,"Cluster",vpcStack.getVpc());

        clusterStack.addDependency(vpcStack);

        FoodServiceStack foodServiceStack = new FoodServiceStack(app,"FoodService",clusterStack.getCluster());

        foodServiceStack.addDependency(clusterStack);

        app.synth();
    }
}

