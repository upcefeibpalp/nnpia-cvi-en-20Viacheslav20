import { RouteObject } from 'react-router-dom';
import App from './App';
import RegisterPage from './pages/RegisterPage';
import UsersPage, { loader as usersLoader } from './pages/UsersPage';
import UserLoginForm from './pages/UserLoginForm';
import ProtectedRoute from './components/ProtectedRoute';

export const routes: RouteObject[] = [
  {
    path: '/',
    element: <App />,
    children: [
      {
        index: true,
        element: <UsersPage />,
        loader: usersLoader,
      },
      {
        path: 'users',
        element: <UsersPage />,
        loader: usersLoader,
      },
      {
        path: 'register',
        element: <RegisterPage />,
      },
      {
        path: 'login',
        element:
        (
           <ProtectedRoute>
             <UserLoginForm />
           </ProtectedRoute>
        ),
      },
    ],
  },
];
