import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  fetchTasks,
  selectAllTasks,
  selectTaskStatus,
} from "../redux/slices/taskSlice";
import TaskItem from "./TaskItem";

const TaskList = () => {
  const dispatch = useDispatch();
  const tasks = useSelector(selectAllTasks); // Get tasks from the Redux store
  const taskStatus = useSelector(selectTaskStatus); // Get the task fetching status (idle, loading, failed)

  useEffect(() => {
    if (taskStatus === "idle") {
      dispatch(fetchTasks()); // Fetch tasks when component mounts and the status is 'idle'
    }
  }, [dispatch, taskStatus]);

  // Display loading message while tasks are being fetched
  if (taskStatus === "loading") {
    return <p>Loading tasks...</p>;
  }

  // Display error message if the task fetching fails
  if (taskStatus === "failed") {
    return <p>Error fetching tasks. Please try again later.</p>;
  }

  // Display message if there are no tasks
  if (tasks.length === 0) {
    return <p>No tasks available. Please add some tasks!</p>;
  }

  // Render the list of tasks
  return (
    <div className="task-list">
      <h2>Your Tasks</h2>
      <ul>
        {tasks.map((task) => (
          <TaskItem
            key={task.id}
            task={task} // Render each task using the TaskItem component
          />
        ))}
      </ul>
    </div>
  );
};

export default TaskList;
