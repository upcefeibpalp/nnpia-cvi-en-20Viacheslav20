import { useForm } from 'react-hook-form';
import { TextField, Button, Box, Typography, FormControlLabel, Checkbox } from '@mui/material';
import axios from 'axios';
import { User } from '../types';

type FormData = Omit<User, 'id'>;

const UserForm = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FormData>();

  const onSubmit = async (data: FormData) => {
    console.log('Submitted data:', data);

    try {
      const response = await axios.post('http://localhost:9000/newUser', data);

      if (response.status === 201) {
        alert('User successfully added!');
      } else {
        console.error('Unexpected response:', response);
      }
    } catch (error) {
      console.error('Error adding user:', error);
    }
  };

  return (
    <Box component="form" onSubmit={handleSubmit(onSubmit)} sx={{ maxWidth: 400, mx: 'auto', mt: 4 }}>
      <Typography variant="h5" gutterBottom>Add New User</Typography>

      <TextField
        label="Name"
        fullWidth
        margin="normal"
        {...register('name', { required: 'Name is required' })}
        error={!!errors.name}
        helperText={errors.name?.message}
      />

      <TextField
        label="Email"
        fullWidth
        margin="normal"
        type="email"
        {...register('email', {
          required: 'Email is required',
          pattern: {
            value: /^\S+@\S+\.\S+$/,
            message: 'Invalid email format',
          },
        })}
        error={!!errors.email}
        helperText={errors.email?.message}
      />

      <FormControlLabel
        control={<Checkbox {...register('active')} />}
        label="Active user"
      />

      <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>
        Submit
      </Button>
    </Box>
  );
};

export default UserForm;
