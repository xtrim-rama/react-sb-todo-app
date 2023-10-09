import {
  AppBar,
  Box,
  IconButton,
  Toolbar,
  Tooltip,
  Typography,
} from '@mui/material';
import DashboardIcon from '@mui/icons-material/Dashboard';
import LogoutIcon from '@mui/icons-material/Logout';
import SettingsIcon from '@mui/icons-material/Settings';

export default function Header() {
  const switchToSettings = () => {
    return;
  };

  const switchToDashboard = () => {
    return;
  };

  const logout = () => {
    return;
  };

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="fixed">
        <Toolbar>
          <Typography variant="h5" component="div" sx={{ flexGrow: 1 }}>
            Todo Logo
          </Typography>
          <>
            <Tooltip title="Dashboard" arrow>
              <IconButton color="inherit" onClick={switchToDashboard}>
                <DashboardIcon />
              </IconButton>
            </Tooltip>
            <Tooltip title="Setting" arrow>
              <IconButton color="inherit" onClick={switchToSettings}>
                <SettingsIcon />
              </IconButton>
            </Tooltip>
            <Tooltip title="Sign out" arrow>
              <IconButton color="inherit" onClick={logout}>
                <LogoutIcon />
              </IconButton>
            </Tooltip>
          </>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
