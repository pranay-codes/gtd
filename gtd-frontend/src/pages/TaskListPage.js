import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchTasks, markTaskComplete } from "../redux/slices/taskSlice";
import TaskItem from "../components/TaskItem";
import "./TaskListPage.css";

const TaskListPage = () => {
  const dispatch = useDispatch();
  const tasks = useSelector((state) => state.tasks.tasks);

  useEffect(() => {
    dispatch(fetchTasks("userID-001")); // Use dynamic userId here
  }, [dispatch]);

  const handleComplete = (taskId) => {
    dispatch(markTaskComplete(taskId));
  };

  return (
    <div>
      <h2>Task List</h2>
      <table className="task-table">
        <thead>
          <tr>
            <th>Action</th>
            <th>Title</th>
            <th>Details</th>
            <th>Context</th>
            <th>Priority</th>
            <th>Due Date</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {tasks.map((task) => (
            <TaskItem key={task.id} task={task} onComplete={handleComplete} />
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TaskListPage;
