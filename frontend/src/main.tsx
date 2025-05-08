// src/main.tsx
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import { routes } from './routes';
import React from 'react';
import ReactDOM from 'react-dom/client';

const router = createBrowserRouter(routes);

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);