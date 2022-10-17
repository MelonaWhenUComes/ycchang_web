//https://corini.tistory.com/entry/%EB%A6%AC%EC%95%A1%ED%8A%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-%EC%97%B0%EB%8F%99%ED%95%98%EC%97%AC-CRUD-%EA%B5%AC%ED%98%84-6-Axios%EB%A1%9C-Service-%EC%83%9D%EC%84%B1-6n?category=836393
import React from 'react';
import AppRouter from './component/route/RouterComponent';
import NavBar from './component/route/NavBar';

import Container from '@material-ui/core/Container'

function App() {
  return (
    <div>
      <NavBar />
      <Container>
        <AppRouter />
      </Container>
    </div>
  );
}

export default App;