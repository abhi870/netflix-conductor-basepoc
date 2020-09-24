package com.localhost.netconductor;

import com.netflix.conductor.client.http.TaskClient;
import com.netflix.conductor.client.task.WorkflowTaskCoordinator;
import com.netflix.conductor.client.worker.Worker;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class NetconductorApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetconductorApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				TaskClient taskClient = new TaskClient();
				taskClient.setRootURI("http://localhost:8080/api/");

				int threadCount = 1;

				Worker worker1 = new VerifyIdentWorker("dragonball1_verify_if_idents_are_added");
				Worker worker2 = new AddIdentsWorker("dragonball2_add_idents");
				WorkflowTaskCoordinator.Builder builder = new WorkflowTaskCoordinator.Builder();
				WorkflowTaskCoordinator coordinator = builder.withWorkers(Arrays.asList(worker1, worker2))
						.withThreadCount(threadCount)
						.withTaskClient(taskClient)
						.build();
				System.out.println("heremain...");
				coordinator.init();
			}
		};
	}
}
