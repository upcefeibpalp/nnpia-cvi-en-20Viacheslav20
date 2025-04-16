import React, { useState } from 'react';
import UserTable from './components/UserTable';
import { Typography, Container, ThemeProvider, createTheme } from '@mui/material';
import { User } from './types';

const theme = createTheme({
  palette: {
    mode: 'light',
    background: {
      default: '#ffffff',
      paper: '#ffffff',
    },
  },
});

const App: React.FC = () => {
  const [users, setUsers] = useState<User[]>([
    { id: 1, name: 'Jan Novak', email: 'jan.novak@example.com', active: true },
    { id: 2, name: 'Petr Svoboda', email: 'petr.svoboda@example.com', active: false },
    { id: 3, name: 'Marie Kovarova', email: 'marie.kovarova@example.com', active: true },
  ]);

  const handleToggleActive = (id: number) => {
    setUsers(users.map(user =>
      user.id === id ? { ...user, active: !user.active } : user
    ));
  };

  return (
      <Container>
        <UserTable users={users} onToggleActive={handleToggleActive} />
      </Container>
  );
};

export default App;