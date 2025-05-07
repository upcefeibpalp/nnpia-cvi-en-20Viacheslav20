import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import User from './User';
import { User as UserType } from '../types';

interface UserTableProps {
  users: UserType[];
  onToggleActive: (id: number) => void;
}

const UserTable: React.FC<UserTableProps> = ({ users, onToggleActive }) => {
  return (
    <TableContainer>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>ID</TableCell>
            <TableCell>Email</TableCell>
            <TableCell>Active</TableCell>
            <TableCell>Action</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {users.map(user => (
            <TableRow key={user.id}>
              <User
                id={user.id}
                email={user.email}
                active={user.active}
                onToggleActive={() => onToggleActive(user.id)}
              />
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default UserTable;