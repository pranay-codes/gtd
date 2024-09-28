import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { createTask } from "../redux/slices/taskSlice";

const TaskForm = () => {
  const [title, setTitle] = useState("");
  const [details, setDetails] = useState("");
  const [context, setContext] = useState("");
  const dispatch = useDispatch();

  const handleSubmit = (e) => {
    e.preventDefault();
    const task = {
      title,
      details,
      context,
      status: "pending",
      userId: "userID-001",
    };
    dispatch(createTask(task));
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />
      <input
        type="text"
        placeholder="Details"
        value={details}
        onChange={(e) => setDetails(e.target.value)}
      />
      <input
        type="text"
        placeholder="Context"
        value={context}
        onChange={(e) => setContext(e.target.value)}
      />
      <button type="submit">Add Task</button>
    </form>
  );
};

export default TaskForm;
