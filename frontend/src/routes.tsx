// src/routes.tsx
import { RouteObject } from 'react-router-dom';
import App from './App';
import RegisterPage from './pages/RegisterPage';
import UsersPage, { loader as usersLoader } from './pages/UsersPage';

export const routes: RouteObject[] = [
  {
    path: '/',
    element: <App />,
    children: [
      {
        index: true,
        element: <UsersPage />,
        loader: usersLoader, // ✅ <--- добавь это
      },
      {
        path: 'users',
        element: <UsersPage />,
        loader: usersLoader, // ✅ <--- и тут тоже
      },
      {
        path: 'register',
        element: <RegisterPage />,
      },
    ],
  },
];
