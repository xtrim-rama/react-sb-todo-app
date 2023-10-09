import { BrowserRouter } from 'react-router-dom';

import Header from '../components/shared/Header';

function AppRouter() {
  return (
    <BrowserRouter>
      <Header />
    </BrowserRouter>
  );
}

export default AppRouter;
