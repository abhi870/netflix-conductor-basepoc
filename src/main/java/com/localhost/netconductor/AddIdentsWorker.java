package com.localhost.netconductor;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

import java.util.HashMap;
import java.util.Map;

public class AddIdentsWorker implements Worker {
    private String taskRefName;
    public AddIdentsWorker(String dragonball2_add_idents) {
        this.taskRefName = dragonball2_add_idents;
    }

    @Override
    public String getTaskDefName() {
        return this.taskRefName;
    }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result= new TaskResult(task);
        System.out.println(task.getInputData());
        result.setStatus(TaskResult.Status.COMPLETED);
        result.getOutputData().put("add_idents_by_type", "done");
        return result;
    }
}
