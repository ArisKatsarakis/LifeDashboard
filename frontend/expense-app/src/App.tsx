import 'bootstrap/dist/css/bootstrap.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import { Dashboard } from './Components/Dashboard';
import { Header } from './Components/Header';
import { WalletDisplay } from './Components/Wallets';


function App() {
	return (
		<BrowserRouter>
			<Header loggedIn={false} />
			<Routes>
				<Route path='/' element={<WalletDisplay />} />
				<Route path='/wallet/:id' element={<Dashboard user={null} />} />
			</Routes>

		</BrowserRouter>
	);
}

export default App;
