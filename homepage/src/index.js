import React from 'react';
import ReactDOM from 'react-dom/client';
import {
    RouterProvider,
    createBrowserRouter,
    createRoutesFromElements,
    Route,
    Link
} from "react-router-dom"
import "./assets/dashboard-react.css"

import Layout from "./layouts/AuthLayouts"
import Login from "./views/Login"
import Register from "./views/Register"

const router = createBrowserRouter(createRoutesFromElements(
    <Route path="/auth" element={<Layout/>}>
        <Route path="login" element={<Login/>}/>
        <Route path="register" element={<Register/>}/>
    </Route>

))

function App() {
    return (
        <RouterProvider router={router} />
    )
}

ReactDOM
    .createRoot(document.getElementById('root'))
    .render(<App />);