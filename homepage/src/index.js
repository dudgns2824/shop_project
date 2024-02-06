import React from 'react';
import ReactDOM from 'react-dom/client';
import {
    RouterProvider,
    createBrowserRouter,
    createRoutesFromElements,
    Route
} from "react-router-dom"
import "./assets/dashboard-react.css"

import Layout from "./layouts/AuthLayouts"
import Login from "./views/auth/Login"
import Register from "./views/auth/Register"
import Dashboard from "./views/home/dashboard/Dashboard";

const router = createBrowserRouter(createRoutesFromElements(
    <Route path="/">
        <Route path="/auth" element={<Layout/>}>
            <Route path="login" element={<Login/>}/>
            <Route path="register" element={<Register/>}/>
        </Route>
        <Route path="/home" element={<Layout/>}>
            <Route path="dashboard" element={<Dashboard/>}/>
        </Route>
    </Route>
))

function App() {
    return (
        <RouterProvider router={router}/>
    )
}

ReactDOM
    .createRoot(document.getElementById('root'))
    .render(<App/>);