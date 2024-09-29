package io.gtd.be.service;

public sealed  interface TaskUpdateService permits TaskUpdateServiceImpl {

    String markTaskAsComplete(String taskId);
}
