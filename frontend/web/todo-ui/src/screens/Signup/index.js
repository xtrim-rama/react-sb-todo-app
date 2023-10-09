import {
  Box,
  Button,
  IconButton,
  InputAdornment,
  TextField,
  Typography,
} from '@mui/material';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import PersonIcon from '@mui/icons-material/Person';

import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import CustomSnackbar from '../../components/ui/CustomSnackbar';
import CustomMessageModal from '../../components/ui/CustomMessageModal';

import {
  forgotDivStyle,
  forgotFontStyle,
  iconStyle,
  lockIconStyle,
  mainDiv,
  textInputStyle,
  wrapperDiv,
} from './styles';

function Signup() {
  const navigate = useNavigate();

  const [signupData, setSignupData] = useState({
    username: '',
    email: '',
    password: '',
  });
  const [showPassword, setShowPassword] = useState(false);

  const [toggle, setToggle] = useState({
    val: false,
    message: '',
    severity: '',
  });
  const [message, setMessage] = useState({
    toggle: false,
    title: '',
    description: '',
    value: '',
  });

  const onChangeInputHandler = (event, field) => {
    setSignupData({ ...signupData, [field]: event.target.value });
  };

  const toggleShowPassword = () => setShowPassword((show) => !show);

  const closeCustomMessageModal = () => {
    setMessage({
      toggle: false,
      title: '',
      description: '',
      value: '',
    });
  };

  const closeCustomSnackbar = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setToggle({ val: false, message: '', severity: '' });
  };

  const switchToLogin = () => {
    navigate('/');
  };

  const onOkHandler = () => {
    if (message['value'] === 'success') {
      navigate('/');
    } else {
      closeCustomMessageModal();
    }
  };

  const onSubmitHandler = async (e) => {
    e.preventDefault();
  };

  return (
    <Box component="span" display={'flex'} justifyContent={'center'}>
      <Box>
        <Box sx={wrapperDiv} component="form" onSubmit={onSubmitHandler}>
          <Box component={'span'} sx={mainDiv}>
            <Box component={'span'} sx={lockIconStyle}>
              <PersonIcon sx={iconStyle} />
            </Box>
            <Typography variant="h5">{'Sign up'}</Typography>
          </Box>
          <TextField
            id="username"
            label="User Name"
            variant="outlined"
            sx={textInputStyle}
            onChange={(e) => onChangeInputHandler(e, 'username')}
            required
          />
          <TextField
            id="email-id"
            label="Email Id"
            variant="outlined"
            sx={textInputStyle}
            onChange={(e) => onChangeInputHandler(e, 'email')}
            required
          />
          <TextField
            id="password"
            label="Password"
            variant="outlined"
            sx={textInputStyle}
            type={showPassword ? 'text' : 'password'}
            onChange={(e) => onChangeInputHandler(e, 'password')}
            required
            InputProps={{
              endAdornment: (
                <InputAdornment position="end">
                  <IconButton
                    aria-label="toggle password visibility"
                    onClick={toggleShowPassword}
                    edge="end"
                  >
                    {showPassword ? <VisibilityOff /> : <Visibility />}
                  </IconButton>
                </InputAdornment>
              ),
            }}
          />
          <Button variant="contained" onClick={onSubmitHandler} type="submit">
            Submit
          </Button>
        </Box>
        <Box
          component={'span'}
          display={'flex'}
          justifyContent={'space-between'}
          sx={forgotDivStyle}
        >
          <Typography
            component={'span'}
            paragraph
            sx={forgotFontStyle}
            onClick={() => {
              console.log('forgot password');
            }}
          >
            {''}
          </Typography>
          <Typography
            component={'span'}
            paragraph
            sx={forgotFontStyle}
            onClick={switchToLogin}
          >
            {'Already have an account? Log in'}
          </Typography>
        </Box>
      </Box>
      {toggle['val'] && (
        <CustomSnackbar
          open={toggle['val']}
          message={toggle['message']}
          severity={toggle['severity']}
          autoHideDuration={4000}
          onCloseHandler={closeCustomSnackbar}
        />
      )}
      {message['toggle'] && (
        <CustomMessageModal
          open={message['toggle']}
          title={message['title']}
          description={message['description']}
          isOk={true}
          onOkHandler={onOkHandler}
          onCloseHandler={closeCustomMessageModal}
        />
      )}
    </Box>
  );
}

export default Signup;
