import React from "react";
import { Routes, Route } from "react-router-dom";
import AddTaskPage from "./pages/AddTaskPage";
import TaskListPage from "./pages/TaskListPage";

const App = () => {
  return (
    <Routes>
      <Route path="/add-task" element={<AddTaskPage />} />
      <Route path="/tasks" element={<TaskListPage />} />
      {/* Add more routes as needed */}
    </Routes>
  );
};

export default App;
