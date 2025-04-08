import React, { useState } from 'react';
import User from './components/User';

const App: React.FC = () => {
  const [isActive, setIsActive] = useState<boolean>(true);

  const handleToggleActive = () => {
    setIsActive(prev => !prev);
  };

  return (
    <div>
      <h1>NNPIA - Single-page application</h1>
      <User
        id={1}
        name="Jan Novák"
        email="jan.novak@example.com"
        active={isActive}
        onToggleActive={handleToggleActive}
      />
    </div>
  );
};

export default App;