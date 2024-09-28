import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { createTask } from "../redux/slices/taskSlice";
import { selectTaskAddStatus } from "../redux/slices/taskSlice";
import "./AddTaskPage.css";

const AddTaskPage = () => {
  const dispatch = useDispatch();
  const taskAddStatus = useSelector(selectTaskAddStatus); // Get task adding status from the store

  // Local state for form inputs
  const [title, setTitle] = useState("");
  const [details, setDetails] = useState("");
  const [context, setContext] = useState("");
  const [priority, setPriority] = useState("");
  const [dueDate, setDueDate] = useState("");

  const [errorMessage, setErrorMessage] = useState("");

  // Handler for form submission
  const handleSubmit = (e) => {
    e.preventDefault();
    if (!title || !context || !priority) {
      setErrorMessage("Please fill in all required fields.");
      return;
    }

    const newTask = {
      userId: "userID-001",
      title,
      details,
      context,
      priority,
      dueDate,
      status: "pending", // Default status for a new task
    };

    // Dispatch the addTask action
    dispatch(createTask(newTask));

    // Reset form fields
    setTitle("");
    setDetails("");
    setContext("");
    setPriority("");
    setDueDate("");
    setErrorMessage("");
  };

  return (
    <div className="add-task-page">
      <h2>Add a New Task</h2>

      {taskAddStatus === "loading" && <p>Adding task...</p>}
      {taskAddStatus === "failed" && (
        <p>Error adding task. Please try again.</p>
      )}

      <form onSubmit={handleSubmit}>
        {errorMessage && <p className="error-message">{errorMessage}</p>}

        <div className="form-group">
          <label htmlFor="title">Title (Required)</label>
          <input
            id="title"
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="details">Details</label>
          <textarea
            id="details"
            value={details}
            onChange={(e) => setDetails(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label htmlFor="context">Context (Required)</label>
          <input
            id="context"
            type="text"
            value={context}
            onChange={(e) => setContext(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="priority">Priority (Required)</label>
          <select
            id="priority"
            value={priority}
            onChange={(e) => setPriority(e.target.value)}
            required
          >
            <option value="">Select Priority</option>
            <option value="low">Low</option>
            <option value="medium">Medium</option>
            <option value="high">High</option>
          </select>
        </div>

        <div className="form-group">
          <label htmlFor="dueDate">Due Date</label>
          <input
            id="dueDate"
            type="datetime-local"
            value={dueDate}
            onChange={(e) => setDueDate(e.target.value)}
          />
        </div>

        <button type="submit" className="btn-submit">
          Add Task
        </button>
      </form>
    </div>
  );
};

export default AddTaskPage;
