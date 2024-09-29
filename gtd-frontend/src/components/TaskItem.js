import React from "react";

const TaskItem = ({ task, onComplete }) => {
  return (
    <tr
      className={`task-item ${task.status === "COMPLETE" ? "completed" : ""}`}
    >
      <td>
        <button
          onClick={() => onComplete(task.id)}
          disabled={task.status === "COMPLETE"}
          className={`complete-button ${
            task.status === "COMPLETE" ? "completed-btn" : ""
          }`}
        >
          {task.status === "COMPLETE" ? "Completed" : "Complete"}
        </button>
      </td>
      <td>{task.title}</td>

      <td>{task.details}</td>
      <td>{task.context}</td>
      <td>{task.priority}</td>
      <td>{task.dueDate}</td>
      <td>{task.status}</td>
    </tr>
  );
};

export default TaskItem;
