import { Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { logout } from '../auth/authenticationSlice';
import { RootState } from '../store';

export default function LoginButton() {
  const token = useSelector((state: RootState) => state.auth.token);
  const dispatch = useDispatch();
  const navigate = useNavigate();

    const handleLogout = () => {
      dispatch(logout());
      navigate('/');
    };

    return token ? (
      <Button color="inherit" onClick={handleLogout}>
        Logout
      </Button>
    ) : (
      <Button color="inherit" onClick={() => navigate('/login')}>
        Login
      </Button>
    );

}