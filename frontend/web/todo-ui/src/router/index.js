import { BrowserRouter, Routes, Route } from 'react-router-dom';

import Header from '../components/shared/Header';
import Login from '../screens/Login';
import Signup from '../screens/Signup';
import Dashboard from '../screens/Dashboard';
import Settings from '../screens/Settings';
import NotFound from '../screens/NotFound';

function AppRouter() {
  return (
    <BrowserRouter>
      <Header />
      <div
        style={{
          marginTop: `50px`,
        }}
      >
        <Routes>
          <Route>
            <Route path="/" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/settings" element={<Settings />} />
          </Route>
          <Route path="*" element={<NotFound />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default AppRouter;
