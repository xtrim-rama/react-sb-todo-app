import { Alert, Snackbar, IconButton } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';

export default function CustomSnackbar({
  open,
  message = 'Default Message',
  severity = 'success',
  autoHideDuration = 5000,
  anchorOrigin = { horizontal: 'center', vertical: 'top' },
  onCloseHandler = () => {},
}) {
  const action = (
    <IconButton
      size="small"
      aria-label="close"
      color="inherit"
      onClick={onCloseHandler}
    >
      <CloseIcon fontSize="small" />
    </IconButton>
  );

  return (
    <Snackbar
      anchorOrigin={anchorOrigin}
      open={open}
      autoHideDuration={autoHideDuration}
      onClose={onCloseHandler}
      message={message}
      action={action}
    >
      <Alert
        onClose={onCloseHandler}
        severity={severity}
        sx={{ width: '100%' }}
      >
        {message}
      </Alert>
    </Snackbar>
  );
}
