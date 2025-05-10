import { useState } from 'react';
import { useLoaderData } from 'react-router-dom';
import { Typography, Box, Button, Container } from '@mui/material';
import axios from 'axios';
import { User } from './types';
import UserTable from '../components/UserTable';


export async function loader() {
  try {
    const response = await axios.get(`${import.meta.env.VITE_API_URL}/users`);
    const rawUsers = response.data;
    if (!Array.isArray(rawUsers)) {
      return [];
    }

    const users: User[] = rawUsers.map((user: any) => ({
      id: user.id,
      name: user.name,
      email: user.email,
      active: user.active,
    }));

    return users;
  } catch (error) {
    return [];
  }
}

export default function Component() {
  const loaderData = useLoaderData() as User[];
  const [users, setUsers] = useState<User[]>(loaderData);

  const fetchUsers = async () => {
    try {
      const response = await axios.get(`${import.meta.env.VITE_API_URL}/users`);

      const data: User[] = response.data;
      setUsers(data);
    } catch (error) {
        alert("Error: ", error)
    }
  };

  const handleToggleActive = async (id: number) => {
    const user = users.find(u => u.id === id);
    if (!user) return;

    const endpoint = user.active
      ? `${import.meta.env.VITE_API_URL}/user/${id}/deactivate`
      : `${import.meta.env.VITE_API_URL}/user/${id}/activate`;

    try {
      const response = await axios.post(endpoint);
      const updatedUser = response.data;
      setUsers(users.map(u => (u.id === id ? updatedUser : u)));
    } catch (error) {
        alert("Error: ", error)
    }
  };

  return (
    <Container sx={{ py: 4 }}>
      <Box sx={{ mb: 2 }}>
        <Typography variant="h4">
          User Management
        </Typography>
      </Box>
      <Button variant="contained" color="primary" onClick={fetchUsers} sx={{ mb: 2 }}>
        Fetch Users
      </Button>
      <UserTable users={users} onToggleActive={handleToggleActive} />
    </Container>
  );
}
