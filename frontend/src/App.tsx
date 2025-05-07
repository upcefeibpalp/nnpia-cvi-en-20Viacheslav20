import { useState } from 'react';
import UserTable from './components/UserTable';
import { Button, Container, Typography, IconButton, Box, createTheme, ThemeProvider, CssBaseline } from '@mui/material';
import { Brightness4, Brightness7 } from '@mui/icons-material';
import axios from 'axios';
import { User } from './types';

const App = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [themeMode, setThemeMode] = useState<'light' | 'dark'>('light');

  const theme = createTheme({
    palette: {
      mode: themeMode,
      background: {
        default: themeMode === 'light' ? '#f5f5f5' : '#121212',
        paper: themeMode === 'light' ? '#ffffff' : '#1e1e1e',
      },
      text: {
        primary: themeMode === 'light' ? '#000000' : '#ffffff',
        secondary: themeMode === 'light' ? '#555555' : '#bbbbbb',
      },
    },
  });

  const toggleTheme = () => {
    setThemeMode(themeMode === 'light' ? 'dark' : 'light');
  };

  const fetchUsers = async () => {
    try {
      const response = await axios.get('http://localhost:9000/users');
      const data: User[] = response.data;
      console.log('Fetched users:', data);
      setUsers(data);
    } catch (error) {
      console.error('Error fetching users:', error);
    }
  };

const handleToggleActive = async (id: number) => {
  const user = users.find(u => u.id === id);
  if (!user) return;

  const endpoint = user.active
    ? `http://localhost:9000/user/${id}/deactivate`
    : `http://localhost:9000/user/${id}/activate`;

  try {
    const response = await axios.post(endpoint);
    const updatedUser = response.data;

    setUsers(users.map(u => (u.id === id ? updatedUser : u)));
  } catch (error) {
    console.error('Error toggling user active status:', error);
  }
};
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Container sx={{ backgroundColor: 'background.default', minHeight: '100vh', py: 4 }}>
        <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 2 }}>
          <Typography variant="h4" color="text.primary">
            User Management
          </Typography>
          <IconButton onClick={toggleTheme} color="inherit">
            {themeMode === 'light' ? <Brightness4 /> : <Brightness7 />}
          </IconButton>
        </Box>
        <Button variant="contained" color="primary" onClick={fetchUsers}>
          Fetch Users
        </Button>
        <UserTable users={users} onToggleActive={handleToggleActive} />
      </Container>
    </ThemeProvider>
  );
};

export default App;
