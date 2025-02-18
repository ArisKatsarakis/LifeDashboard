
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';
import 'bootstrap/dist/css/bootstrap.css';
import { jwtDecode, JwtPayload } from 'jwt-decode';
import { useEffect, useState } from 'react';
import { Container } from 'react-bootstrap';
import { useCookies } from 'react-cookie';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import './App.css';
import { Dashboard } from './Components/Dashboard';
import { Login } from './Components/Login';
import { Register } from './Components/Register';



function App() {
  const [username, setUsername] = useState<string>('');
  const [authenticated, setAuthenticated] = useState<boolean>(false);
  const [cookies, setCookie] = useCookies(['jsonToken']);


  const getToken = () => {
    console.log(cookies);
    if (cookies.jsonToken != null) {
      setAuthenticated(true);
      console.log(jwtDecode(cookies.jsonToken));
      const decodedJwt = jwtDecode<JwtPayload>(cookies.jsonToken);
      setUsername(decodedJwt.sub == null ? '' : decodedJwt.sub);
    } else {
      setAuthenticated(false);
    }
  };

  useEffect(
    () => {
      getToken();
    }, []
  );
  return (
    <Container>
      <BrowserRouter>
        {
          (authenticated === true)
            ?
            < Routes >
              <Route path='/' element={<Dashboard username={username} />} />
            </Routes>
            :
            <Routes>
              <Route path='/' element={<Login />} />
              <Route path='/register' element={<Register />} />
            </Routes>
        }
      </BrowserRouter >
    </Container >

  );
}

export default App;
