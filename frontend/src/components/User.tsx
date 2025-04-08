
interface UserProps {
  id: number;
  name: string;
  email: string;
  active: boolean;
  onToggleActive: () => void;
}

const User: React.FC<UserProps> = ({ id, name, email, active, onToggleActive }) => {
  return (
    <div>
      <h2>User Information</h2>
      <p>ID: {id}</p>
      <p>Name: {name}</p>
      <p>Email: {email}</p>
      <p>Active: {active ? 'Yes' : 'No'}</p>
      <button onClick={onToggleActive}>
        {active ? 'Deactivate' : 'Activate'} User
      </button>
    </div>
  );
};

export default User;