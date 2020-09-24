package com.localhost.netconductor;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

import java.util.HashMap;
import java.util.Map;

public class VerifyIdentWorker implements Worker {

    private String taskDefName;
    public VerifyIdentWorker(String taskDefName){
        this.taskDefName = taskDefName;
    }

    @Override
    public String getTaskDefName() {
        return this.taskDefName;
    }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result= new TaskResult(task);
        System.out.println(task.getInputData());
        result.setStatus(TaskResult.Status.COMPLETED);
        result.getOutputData().put("is_idents_added", false);
        Map<String, String> logMap = new HashMap<>();
        logMap.put("log", "there is success");
        logMap.put("createdTime", new Long(System.currentTimeMillis()).toString());
        result.getOutputData().put("logs", logMap);
        return result;
    }
}
