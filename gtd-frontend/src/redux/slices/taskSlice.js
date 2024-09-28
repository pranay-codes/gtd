import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { addTask, getAllTasks, completeTask } from "../../api/taskApi";

export const fetchTasks = createAsyncThunk(
  "tasks/fetchTasks",
  async (userId) => {
    const tasks = await getAllTasks(userId);
    return tasks;
  }
);

export const createTask = createAsyncThunk("tasks/createTask", async (task) => {
  const response = await addTask(task);
  return response;
});

export const markTaskComplete = createAsyncThunk(
  "tasks/markTaskComplete",
  async (taskId) => {
    await completeTask(taskId);
    return taskId;
  }
);

const taskSlice = createSlice({
  name: "tasks",
  initialState: {
    tasks: [],
    status: "idle",
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchTasks.pending, (state) => {
        state.loading = true; // Set loading to true when fetching
      })
      .addCase(fetchTasks.fulfilled, (state, action) => {
        state.loading = false; // Set loading to false when done
        state.tasks = action.payload; // Update tasks with fetched data
      })
      .addCase(fetchTasks.rejected, (state, action) => {
        state.loading = false; // Set loading to false on error
        state.error = action.error.message; // Store the error message
      });
  },
});

// Selectors
export const selectAllTasks = (state) => state.tasks.tasks; // Selector for all tasks
export const selectTaskStatus = (state) => state.tasks.status; // Selector for task fetching status
export const selectTaskAddStatus = (state) => state.tasks.addStatus; // Selector for task adding status

export default taskSlice.reducer;
