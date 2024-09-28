import { configureStore } from "@reduxjs/toolkit";
import taskReducer from "./slices/taskSlice";

// Configure the Redux store
export const store = configureStore({
  reducer: {
    tasks: taskReducer,
  },
});

export default store;
