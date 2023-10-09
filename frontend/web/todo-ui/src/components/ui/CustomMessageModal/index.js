import {
  Dialog,
  DialogActions,
  DialogContent,
  Button,
  DialogTitle,
  DialogContentText,
} from '@mui/material';

export default function CustomMessageModal({
  open = false,
  title,
  description,
  okText = 'Okay',
  cancelText = 'Cancel',
  isOk = false,
  isCancel = false,
  onOkHandler = () => {},
  onCancelHandler = () => {},
  onCloseHandler = () => {},
}) {
  return (
    <Dialog
      open={open}
      keepMounted
      onClose={onCloseHandler}
      aria-describedby="message-alert-dialog-slide-description"
      fullWidth
      maxWidth="sm"
    >
      <DialogTitle>{title}</DialogTitle>
      <DialogContent dividers>
        <DialogContentText id="message-alert-dialog-slide-description">
          {description}
        </DialogContentText>
      </DialogContent>
      <DialogActions>
        {isCancel && <Button onClick={onCancelHandler}>{cancelText}</Button>}
        {isOk && <Button onClick={onOkHandler}>{okText}</Button>}
      </DialogActions>
    </Dialog>
  );
}
