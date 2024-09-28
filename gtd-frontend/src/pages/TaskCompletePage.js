import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchTasks, completeTask } from "../redux/slices/taskSlice";
import TaskItem from "../components/TaskItem";

const TaskCompletePage = () => {
  const dispatch = useDispatch();
  const tasks = useSelector((state) => state.tasks.items); // Select tasks from the store
  const taskCompleteStatus = useSelector(
    (state) => state.tasks.completeTaskStatus
  ); // Task completion status
  const taskFetchStatus = useSelector((state) => state.tasks.fetchTasksStatus); // Task fetching status

  // Fetch all tasks when the component mounts
  useEffect(() => {
    dispatch(fetchTasks());
  }, [dispatch]);

  // Handler to complete a task
  const handleCompleteTask = (taskId) => {
    dispatch(completeTask(taskId));
  };

  if (taskFetchStatus === "loading") {
    return <p>Loading tasks...</p>;
  }

  if (taskFetchStatus === "failed") {
    return <p>Error fetching tasks. Please try again.</p>;
  }

  return (
    <div className="task-complete-page">
      <h2>Complete Tasks</h2>

      {tasks.length === 0 ? (
        <p>No tasks available to complete.</p>
      ) : (
        <ul className="task-list">
          {tasks.map((task) => (
            <li key={task.id}>
              <TaskItem task={task} />
              {task.status !== "completed" && (
                <button
                  onClick={() => handleCompleteTask(task.id)}
                  disabled={
                    taskCompleteStatus === "loading" &&
                    task.id === taskCompleteStatus.taskId
                  }
                >
                  {taskCompleteStatus === "loading" &&
                  task.id === taskCompleteStatus.taskId
                    ? "Completing..."
                    : "Mark as Complete"}
                </button>
              )}
              {taskCompleteStatus === "failed" &&
                task.id === taskCompleteStatus.taskId && (
                  <p className="error-message">
                    Failed to mark task as complete. Try again.
                  </p>
                )}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default TaskCompletePage;
