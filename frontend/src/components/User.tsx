import { TableCell, Button } from '@mui/material';
import { User as UserType } from '../types';

interface UserProps extends UserType {
  onToggleActive: () => void;
}

const User: React.FC<UserProps> = ({ id, email, name, active, onToggleActive }) => {
  return (
    <>
      <TableCell>{id}</TableCell>
      <TableCell>{name}</TableCell>
      <TableCell>{email}</TableCell>
      <TableCell>{active ? 'Yes' : 'No'}</TableCell>
      <TableCell>
        <Button
          variant="contained"
          color={active ? 'secondary' : 'primary'}
          onClick={onToggleActive}
        >
          {active ? 'Deactivate' : 'Activate'}
        </Button>
      </TableCell>
    </>
  );
};

export default User;