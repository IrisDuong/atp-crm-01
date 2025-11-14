import React,{useEffect} from 'react';
import logo from './logo.svg';
import './App.css';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Main from './views/main/Main'
import Home from './views/main/Home';
import LoginPage from './views/module/authen/LoginPage';
import routes from './util/routes';
function App() {
  const router = createBrowserRouter([
    {
      path: "/",
      element: <Main/>,
      children: routes
    },
    {
      path: "/authen",
      element: <LoginPage/>
    },
    {
      path: "/home",
      element: <Home/>
    }
  ])
  useEffect(() => {document.title = "Hệ thống quản lý CRM ATP"},[]);
  return (
    <div className="App">
      <RouterProvider router={router}/>
    </div>
  );
}

export default App;
