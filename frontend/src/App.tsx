import { useState } from 'react';
import { Outlet, Link } from 'react-router-dom';
import { AppBar, Toolbar, Typography, IconButton, createTheme, ThemeProvider,
    CssBaseline, Box,} from '@mui/material';
import { Brightness4, Brightness7 } from '@mui/icons-material';
import LoginButton from './components/LoginButton';

function App() {
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

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            User Management
          </Typography>
          <Link to="/" style={{ color: 'white', marginRight: '1rem', textDecoration: 'none' }}>
            Home
          </Link>
          <Link to="/users" style={{ color: 'white', marginRight: '1rem', textDecoration: 'none' }}>
            Users
          </Link>
          <Link to="/register" style={{ color: 'white', marginRight: '1rem', textDecoration: 'none' }}>
            Register
          </Link>
          <LoginButton />
          <IconButton onClick={toggleTheme} color="inherit">
            {themeMode === 'light' ? <Brightness4 /> : <Brightness7 />}
          </IconButton>
        </Toolbar>
      </AppBar>
      <Box sx={{ padding: 2 }}>
        <Outlet />
      </Box>
    </ThemeProvider>
  );
}

export default App;