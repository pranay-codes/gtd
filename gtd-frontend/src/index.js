import React from "react";
import ReactDOM from "react-dom/client";
import { Provider } from "react-redux";
import { BrowserRouter } from "react-router-dom";
import store from "./redux/store";
import App from "./App";
import "./index.css"; // Global CSS styles

// Creating the root for React 18+
const root = ReactDOM.createRoot(document.getElementById("root"));

// Render the App component wrapped with necessary providers
root.render(
  <React.StrictMode>
    <Provider store={store}>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </Provider>
  </React.StrictMode>
);
