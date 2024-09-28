const API_URL = "http://localhost:8080/tasks/v1";

export const addTask = async (task) => {
  console.log(JSON.stringify(task));
  const response = await fetch(`${API_URL}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(task),
  });
  return await response.json();
};

export const getAllTasks = async (userId) => {
  const response = await fetch(`${API_URL}/${userId}`);
  return await response.json();
};

export const completeTask = async (taskId) => {
  const response = await fetch(`${API_URL}/complete/${taskId}`, {
    method: "PUT",
  });
  return await response.text();
};
