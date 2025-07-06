import 'bootstrap/dist/css/bootstrap.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import { Dashboard } from './Components/Dashboard';
import { Header } from './Components/Header';


function App() {
	return (
		<BrowserRouter>
			<Header loggedIn={false} />
			<Routes>
				<Route path='/' element={<Dashboard user={null} />} >
				</Route>
			</Routes>

		</BrowserRouter>
	);
}

export default App;
